package org.dynamo.applicantsapp.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dynamo.applicantsapp.dao.ProductInfoDAO;
import org.dynamo.applicantsapp.entity.Product;
import org.dynamo.applicantsapp.model.CartInfo;
import org.dynamo.applicantsapp.model.CartLineInfo;
import org.dynamo.applicantsapp.model.ProductInfo;
import org.dynamo.applicantsapp.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
@Controller
@Transactional
public class MainController {
	
    @Autowired
    private ProductInfoDAO productDAO;
  
   @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
   public String loginPage(Model model, Principal principal) {	  
       return "loginPage";
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
}