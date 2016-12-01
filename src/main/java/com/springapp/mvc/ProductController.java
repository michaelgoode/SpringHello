package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by michaelgoode on 23/09/2015.
 */
@Controller
@RequestMapping("/addproduct")
public class ProductController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Add a product!");
        return "hello";
    }

    @RequestMapping(value = "/users", method=RequestMethod.POST)
    public String saveProduct() {
        return "";
    }




}
