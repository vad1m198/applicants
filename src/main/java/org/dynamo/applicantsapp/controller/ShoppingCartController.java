package org.dynamo.applicantsapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.authentication.CustomUser;
import org.dynamo.applicantsapp.entity.Product;
import org.dynamo.applicantsapp.entity.ShoppingCartAnswer;
import org.dynamo.applicantsapp.model.CartInfo;
import org.dynamo.applicantsapp.model.CustomerInfo;
import org.dynamo.applicantsapp.service.MailServiceImpl;
import org.dynamo.applicantsapp.service.ProductService;
import org.dynamo.applicantsapp.service.ShoppingCartAnswersService;
import org.dynamo.applicantsapp.util.Utils;
import org.dynamo.applicantsapp.validator.CustomerInfoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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
public class ShoppingCartController {
    
    @Autowired
    private CustomerInfoValidator customerInfoValidator;
        
    @Autowired
    private MailServiceImpl mailService;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartAnswersService shoppingCartAnswersService;
    
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
   
   @RequestMapping(value = "/shopping-cart/productList", method = RequestMethod.GET)
   public String productList(Model model) {
	   List<Product> products = productService.getAllProducts();
	   model.addAttribute("products", products);
	   return "productListPage";	    
   }
   
   @RequestMapping({ "/shopping-cart/buyProduct" })
   public String listProductHandler(HttpServletRequest request, Model model, //
           @RequestParam(value = "code", defaultValue = "") String code) {

	   Product product = null;
       if (code != null && code.length() > 0) {
           product = productService.getByCode(code);
       }

       if (product != null) {
           // Cart info stored in Session.
           CartInfo cartInfo = Utils.getCartInSession(request);
           cartInfo.addProduct(product, 1);
       }
       // Redirect to shoppingCart page.
       return "redirect:/shopping-cart/shoppingCart";
   }
   
   // GET: Show Cart
   @RequestMapping(value = { "/shopping-cart/shoppingCart" }, method = RequestMethod.GET)
   public String shoppingCartHandler(HttpServletRequest request, Model model) {
       CartInfo myCart = Utils.getCartInSession(request);

       model.addAttribute("cartForm", myCart);
       return "shoppingCartPage";
   }
   
   @RequestMapping(value = { "/shopping-cart/shoppingCart" }, method = RequestMethod.POST)
   public String shoppingCartUpdateQty(HttpServletRequest request, //
           Model model, //
           @ModelAttribute("cart") CartInfo cartForm) {
       CartInfo cartInfo = Utils.getCartInSession(request);       
       cartInfo.updateQuantity(cartForm);
       // Redirect to shoppingCart page.
       return "redirect:/shopping-cart/shoppingCart";
   }
   
   // GET: Enter customer information.
   @RequestMapping(value = { "/shopping-cart/shoppingCartCustomer" }, method = RequestMethod.GET)
   public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

       CartInfo cartInfo = Utils.getCartInSession(request);
     
       // Cart is empty.
       if (cartInfo.isEmpty()) {
            
           // Redirect to shoppingCart page.
           return "redirect:/shopping-cart/shoppingCart";
       }

       CustomerInfo customerInfo = cartInfo.getCustomerInfo();
       if (customerInfo == null) {
           customerInfo = new CustomerInfo();
       }

       model.addAttribute("customerForm", customerInfo);

       return "shoppingCartCustomer";
   }

   // POST: Save customer information.
   @RequestMapping(value = { "/shopping-cart/shoppingCartCustomer" }, method = RequestMethod.POST)
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
       return "redirect:/shopping-cart/shoppingCartConfirmation";
   }
   
   // GET: Review Cart to confirm.
   @RequestMapping(value = { "/shopping-cart/shoppingCartConfirmation" }, method = RequestMethod.GET)
   public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
       CartInfo cartInfo = Utils.getCartInSession(request);

       // Cart have no products.
       if (cartInfo.isEmpty()) {
           // Redirect to shoppingCart page.
           return "redirect:/shoppingCartPage";
       } else if (!cartInfo.isValidCustomer()) {
           // Enter customer info.
           return "redirect:/shopping-cart/shoppingCartCustomer";
       }       
       return "shoppingCartConfirmation";
   } 
   
   @RequestMapping(value = {"/shopping-cart/shoppingCartAnswers"}, method = RequestMethod.GET)
   public String shoppingCartAnswersGet(HttpServletRequest request, Model model, 
		   	Authentication authentication) {
	   CustomUser cu = (CustomUser) authentication.getPrincipal();   		
	   ShoppingCartAnswer answerInfo = Utils.getAnswersInSession(request);
	   answerInfo.setApplicantId(cu.getUserId());
	   model.addAttribute("answersForm", answerInfo);
       return "shoppingCartAnswersPage";
   }
   
   @RequestMapping(value = {"/shopping-cart/shoppingCartAnswers"}, method = RequestMethod.POST)
   public String shoppingCartAnswersPost(HttpServletRequest request, Model model,
		   @ModelAttribute("answersForm") ShoppingCartAnswer answersForm, Authentication authentication) {
   		ShoppingCartAnswer answerInfo = Utils.getAnswersInSession(request);
   		answerInfo.setBugs(answersForm.getBugs());
	   	answerInfo.setImprovements(answersForm.getImprovements());
	   	answerInfo.setTestCases(answersForm.getTestCases());
	   		   	
	   	if("true".equals(request.getParameter("submit"))) {
            shoppingCartAnswersService.saveAndFlush(answerInfo);
	   		authentication.setAuthenticated(false);
	        mailService.sendEmail(answerInfo, authentication.getName());	   		
	   		return "shoppingCartAnswersSubmitted";
	   	}
       return "shoppingCartAnswersPage";
   }   
}