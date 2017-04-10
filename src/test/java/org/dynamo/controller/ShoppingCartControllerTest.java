package org.dynamo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.dynamo.entity.Product;
import org.dynamo.model.CartInfo;
import org.dynamo.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.View;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {
	
    @InjectMocks
    private ShoppingCartController controller;
    
    @Mock
	private ProductService productService;
        
    @Mock
    private View mockView;

    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
    }
    
    @Test
    public void whenGetProductListThenGetProductsFromService() throws Exception {
    	List<Product> products = Arrays.asList(new Product(), new Product());
    	
    	when(productService.getAllProducts()).thenReturn(products);
    	
        mockMvc.perform(get("/shopping-cart/list"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("products", products))
                .andExpect(view().name("shopping-cart/list"));
    }
    
    @Test
    public void whenBuyProductWithValidCodeThenRedirectToCartView() throws Exception {
    	List<Product> products = Arrays.asList(new Product(), new Product());
    	Product product = new Product();
    	product.setCode("P001");
    	product.setName("name");
    	product.setPrice(new BigDecimal(100));
    	
    	when(productService.getAllProducts()).thenReturn(products);
    	when(productService.getProductByCode("P001")).thenReturn(product);
    	
    	MvcResult result = mockMvc.perform(get("/shopping-cart/buy?code=P001"))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/shopping-cart/cart"))
                .andReturn();
    	    	
    	CartInfo info = (CartInfo) result.getRequest().getSession().getAttribute("cartInfo");
    	assertNotNull(info);
        assertEquals(1,info.getCartLines().size());
        assertEquals(1,info.getCartLines().get(0).getQuantity());
        assertEquals(new BigDecimal(100),info.getCartLines().get(0).getAmount());
        assertEquals(product,info.getCartLines().get(0).getProduct());
    }
}

