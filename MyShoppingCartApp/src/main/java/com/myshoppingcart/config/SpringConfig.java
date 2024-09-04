package com.myshoppingcart.config;

import com.myshoppingcart.persistence.ICompraRepository;
import com.myshoppingcart.service.IShoppingCart;
import com.myshoppingcart.service.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({DevConfig.class, ProdConfig.class})
@ComponentScan({"com.myshoppingcart.persistence"})
public class SpringConfig {

    @Autowired
    ICompraRepository icr;
    @Bean
    @Profile({"Dev","Prod"})
    public IShoppingCart getService() {
        ShoppingCart cart = new ShoppingCart();
        cart.setRepoCompras(icr);
        return cart;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}