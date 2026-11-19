package com.laioffer.onlineorder;


import com.laioffer.onlineorder.entity.*;
import com.laioffer.onlineorder.model.RestaurantDto;
import com.laioffer.onlineorder.repository.*;
import com.laioffer.onlineorder.service.CartService;
import com.laioffer.onlineorder.service.MenuItemService;
import com.laioffer.onlineorder.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.List;


@Component
@Profile("dev")
public class DevRunner implements ApplicationRunner {


    // A logger for print
    private static final Logger logger = LoggerFactory.getLogger(DevRunner.class);


    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final CartService cartService;
    private final MenuItemService menuItemService;
    private final RestaurantService restaurantService;


    public DevRunner(
            CartRepository cartRepository,
            CustomerRepository customerRepository,
            MenuItemRepository menuItemRepository,
            OrderItemRepository orderItemRepository,
            RestaurantRepository restaurantRepository,
            CartService cartService,
            MenuItemService menuItemService,
            RestaurantService restaurantService) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.restaurantRepository = restaurantRepository;
        this.cartService = cartService;
        this.menuItemService = menuItemService;
        this.restaurantService = restaurantService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
