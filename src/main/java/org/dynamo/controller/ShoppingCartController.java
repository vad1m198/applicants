package org.dynamo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.dynamo.dao.ProductDao;
import org.dynamo.dao.ShoppingCartAnswerDao;
import org.dynamo.entity.Product;
import org.dynamo.entity.ShoppingCartAnswer;
import org.dynamo.model.AuthenticationUser;
import org.dynamo.model.CartInfo;
import org.dynamo.model.CustomerInfo;
import org.dynamo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
	
	@Autowired
	private ProductDao productdao;
	
	@Autowired
	private ShoppingCartAnswerDao answersdao;
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
	public String getProductList(Model model) {
		
		model.addAttribute("products", productdao.getAllProducts());
		return "shopping-cart/list";
	}
	
    @RequestMapping(value = "buy", method = RequestMethod.GET)
    public String buyProduct(@RequestParam(value = "code", defaultValue = "") String code,
    		Model model, HttpServletRequest request) {
    	
    	Product product = productdao.getProductByCode(code);
    	
    	if(product != null) {
    		CartInfo info = Utils.getCartInSession(request);
    		info.addProduct(product, 1);    		
    	}
    	
    	return "redirect:/shopping-cart/cart";
    }
    
    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String getCartInfo(Model model, HttpServletRequest request) {
    	CartInfo info = Utils.getCartInSession(request);
    	model.addAttribute("cartForm", info);
    	return "/shopping-cart/cart";
    }
    
    @RequestMapping(value = "cart", method = RequestMethod.POST)
    public String postCartInfo(Model model, HttpServletRequest request, 
    		@ModelAttribute("cart") CartInfo cartForm) {    	
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.updateQuantity(cartForm);
        return "redirect:/shopping-cart/cart";
    }
    
    @RequestMapping(value = { "/customer" }, method = RequestMethod.GET)
    public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {

        CartInfo cartInfo = Utils.getCartInSession(request);
        if (cartInfo.isEmpty()) {
            return "redirect:/shopping-cart/cart";
        }

        CustomerInfo customerInfo = cartInfo.getCustomerInfo();
        if (customerInfo == null) {
            customerInfo = new CustomerInfo();
        }
        model.addAttribute("customerForm", customerInfo);
        return "shopping-cart/customer";
    }
    
    @RequestMapping(value = { "/customer" }, method = RequestMethod.POST)
    public String shoppingCartCustomerSave(HttpServletRequest request,
            Model model, 
            @Valid @ModelAttribute("customerForm") CustomerInfo customerForm,
            BindingResult result) {
    	
        if(result.hasErrors()) {
        	return "shopping-cart/customer";
        }
        
        CartInfo cartInfo = Utils.getCartInSession(request);
        cartInfo.setCustomerInfo(customerForm);
        return "redirect:/shopping-cart/confirmation";
    }
    

    @RequestMapping(value = { "/confirmation" }, method = RequestMethod.GET)
    public String shoppingCartConfirmationReview(HttpServletRequest request, Model model) {
        CartInfo cartInfo = Utils.getCartInSession(request);

        if (cartInfo.isEmpty()) {
            return "redirect:/shopping-cart/cart";
        } else if (!cartInfo.isValidCustomer()) {
            return "redirect:/shopping-cart/customer";
        }
        
        return "shopping-cart/confirmation";
    }
    
    @RequestMapping(value = { "/answers" }, method = RequestMethod.GET)
    public String getAnswersView(Model model,Authentication authentication) {
    	
		AuthenticationUser auth = (AuthenticationUser) authentication.getPrincipal();
		ShoppingCartAnswer answer = answersdao.getAnswerByUserId(auth.getUserId());
		if (answer == null) {
			answer = new ShoppingCartAnswer();
			answer.setUserId(auth.getUserId());
		}		
		if(answer.getId() > 0 && answer.isSubmitted()) {
			model.addAttribute("answersForm", answer);
			return "shopping-cart/show-answers";
		}
        model.addAttribute("answersForm", answer);
        return "shopping-cart/answers";
    }
    
    @RequestMapping(value = { "/answers" }, method = RequestMethod.POST)
    public String postShoppingCartAnswer(Model model, Authentication authentication,
    		@ModelAttribute ("answersForm") ShoppingCartAnswer answer) {
    	
    	long id = answersdao.saveAnswer(answer);
    	answer.setId(id);    	
    	if(answer.isSubmitted()) {
    		return "redirect:/shopping-cart/answers";
    	}
    	
        model.addAttribute("answersForm", answer);
        model.addAttribute("answersFormSaved", true);
        return "shopping-cart/answers";
    }
    
     

}
