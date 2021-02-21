package com.oguzhan.petclinic.controller;

import com.oguzhan.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PetClinicController {

    private PetClinicService petClinicService;

    @Autowired
    public PetClinicController(PetClinicService petClinicService) {
        this.petClinicService = petClinicService;
    }

    @RequestMapping("/owners")
    public ModelAndView getOwners(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("owners",petClinicService.findOwners());
        mav.setViewName("owners");
        return mav;
    }

    @RequestMapping("/pcs")
    @ResponseBody
    public String welcome() {
        return "Welcome to Petclinic world";
    }
}
