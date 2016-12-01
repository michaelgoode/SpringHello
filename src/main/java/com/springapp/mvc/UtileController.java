package com.springapp.mvc;

import com.springapp.dao.UtileDAO;
import com.springapp.model.utile.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by michaelgoode on 08/11/2016.
 */

@Controller
@RequestMapping
public class UtileController {
    @Autowired
    //private UtileDAO utileDAO;

    @RequestMapping(value = "/utilehome")
    public ModelAndView utilehome() {
        String message = "Welcome...";
        return new ModelAndView("utilehome", "message", message);
    }

    @RequestMapping(value = "/searchmachines", method = RequestMethod.GET)
    public ModelAndView searchmachines() {
        return new ModelAndView("machinesearch", "command", new Machine());
    }
}
