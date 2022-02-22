package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.MenuItem;
import com.laioffer.onlineorder.entity.Restaurant;
import com.laioffer.onlineorder.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.util.List;

@Controller //表示这class 用来定义rest API
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;


    // {restaurant} is from frontEnd when user selected a restaurant
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants() {
        return menuInfoService.getRestaurants();
    }

    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable(value = "restaurantId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }
}


