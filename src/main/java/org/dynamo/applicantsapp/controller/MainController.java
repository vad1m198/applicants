package org.dynamo.applicantsapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.authentication.CustomUser;
import org.dynamo.applicantsapp.dao.ProductInfoDAO;
import org.dynamo.applicantsapp.dao.ShoppingCartAnswerDAO;
import org.dynamo.applicantsapp.model.CartInfo;
import org.dynamo.applicantsapp.model.CustomerInfo;
import org.dynamo.applicantsapp.model.ProductInfo;
import org.dynamo.applicantsapp.model.ShoppingCartAnswerInfo;
import org.dynamo.applicantsapp.service.MailServiceImpl;
import org.dynamo.applicantsapp.util.Utils;
import org.dynamo.applicantsapp.validator.CustomerInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
@Controller
@Transactional
public class MainController {
	
    @Autowired
    private ProductInfoDAO productDAO;
    
    @Autowired
    private ShoppingCartAnswerDAO shoppingCartAnswerDAO;
    
    @Autowired
    private CustomerInfoValidator customerInfoValidator;
        
    @Autowired
    MailServiceImpl mailService;
    
    @InitBinder
    public void myInitBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);
 
        // For Cart Form.
        // (@ModelAttribute("cartForm") @Validated CartInfo cartForm)
        if (target.getClass() == CartInfo.class) {
 
        }
        // For Customer Form.
        // (@ModelAttribute("customerForm") @Validated CustomerInfo
        // customerForm)
        else if (target.getClass() == CustomerInfo.class) {
            dataBinder.setValidator(customerInfoValidator);
        }
 
    }
  
   @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
   public String loginPage(Model model, Principal principal) {	  
       return "loginPage";
   }
   
   @RequestMapping("/403")
   public String accessDenied() {
       return "/403";
   }
 
   @RequestMapping(value = "/welcome", method = RequestMethod.GET)
   public String userInfo(HttpServletRequest request, Model model, Principal principal) {	   
       // After user login successfully.
       String userName = principal.getName();
       System.out.println("User Name: "+ userName);
       return "welcomePage";
   }
   
   @RequestMapping(value = "/productList", method = RequestMethod.GET)
   public String productList(Model model) {
	   	   
	   List<ProductInfo> products = productDAO.getAllProducts();	   
	   model.addAttribute("products", products);
	   return "productListPage";	    
   }
   
   @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
   public String logoutSuccessfulPage(Model model) {
       return "logoutSuccessfulPage";
   }
   
   @RequestMapping({ "/buyProduct" })
   public String listProductHandler(HttpServletRequest request, Model model, //
           @RequestParam(value = "code", defaultValue = "") String code) {

	   ProductInfo productInfo = null;
       if (code != null && code.length() > 0) {
    	   productInfo = productDAO.findProductInfo(code);
       }
       if (productInfo != null) {
           // Cart info stored in Session.
           CartInfo cartInfo = Utils.getCartInSession(request);
           cartInfo.addProduct(productInfo, 1);
       }
       // Redirect to shoppingCart page.
       return "redirect:/shoppingCart";
   }
   
   // GET: Show Cart
   @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
   public String shoppingCartHandler(HttpServletRequest request, Model model) {
       CartInfo myCart = Utils.getCartInSession(request);

       model.addAttribute("cartForm", myCart);
       return "shoppingCartPage";
   }
   
   @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
   public String shoppingCartUpdateQty(HttpServletRequest request, //
           Model model, //
           @ModelAttribute("cart") CartInfo cartForm) {
       CartInfo cartInfo = Utils.getCartInSession(request);       
       cartInfo.updateQuantity(cartForm);
       // Redirect to shoppingCart page.
       return "redirect:/shoppingCart";
   }
   
   // GET: Enter customer information.
   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
   public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

       CartInfo cartInfo = Utils.getCartInSession(request);
     
       // Cart is empty.
       if (cartInfo.isEmpty()) {
            
           // Redirect to shoppingCart page.
           return "redirect:/shoppingCart";
       }

       CustomerInfo customerInfo = cartInfo.getCustomerInfo();
       if (customerInfo == null) {
           customerInfo = new CustomerInfo();
       }

       model.addAttribute("customerForm", customerInfo);

       return "shoppingCartCustomer";
   }

   // POST: Save customer information.
   @RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
   public String shoppingCartCustomerSave(HttpServletRequest request, //
           Model model, //
           @ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
           BindingResult result, //
           final RedirectAttributes redirectAttributes) {
 
       // If has Errors.
       if (result.hasErrors()) {
           customerForm.setValid(false);
           // Forward to reenter customer info.
           return "shoppingCartCustomer";
       }

       customerForm.setValid(true);
       CartInfo cartInfo = Utils.getCartInSession(request);

       cartInfo.setCustomerInfo(customerForm);

       // Redirect to Confirmation page.
       return "redirect:/shoppingCartConfirmation";
   }
   
   // GET: Review Cart to confirm.
   @RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
   public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
       CartInfo cartInfo = Utils.getCartInSession(request);

       // Cart have no products.
       if (cartInfo.isEmpty()) {
           // Redirect to shoppingCart page.
           return "redirect:/shoppingCartPage";
       } else if (!cartInfo.isValidCustomer()) {
           // Enter customer info.
           return "redirect:/shoppingCartCustomer";
       }       
       return "shoppingCartConfirmation";
   } 
   
   @RequestMapping(value = {"/shoppingCartAnswers"}, method = RequestMethod.GET)
   public String shoppingCartAnswersGet(HttpServletRequest request, Model model, 
		   	Authentication authentication) {
	   CustomUser cu = (CustomUser) authentication.getPrincipal();   		
	   ShoppingCartAnswerInfo answerInfo = Utils.getAnswersInSession(request);
	   answerInfo.setApplicant_id(Integer.parseInt(cu.getUserId()));
	   model.addAttribute("answersForm", answerInfo);
       return "shoppingCartAnswersPage";
   }
   
   @RequestMapping(value = {"/shoppingCartAnswers"}, method = RequestMethod.POST)
   public String shoppingCartAnswersPost(HttpServletRequest request, Model model,
		   @ModelAttribute("answersForm") ShoppingCartAnswerInfo answersForm, Authentication authentication) {
   		ShoppingCartAnswerInfo answerInfo = Utils.getAnswersInSession(request);
   		answerInfo.setBugs(answersForm.getBugs());
	   	answerInfo.setImprovements(answersForm.getImprovements());
	   	answerInfo.setTest_cases(answersForm.getTest_cases());
	   		   	
	   	if("true".equals(request.getParameter("submit"))) {
	   		shoppingCartAnswerDAO.saveShoppingCartAnswers(answerInfo);	   		
	   		authentication.setAuthenticated(false);
	        mailService.sendEmail(answerInfo, authentication.getName());
	   		
	   		return "shoppingCartAnswersSubmitted";
	   	}
       return "shoppingCartAnswersPage";
   }   
}