package com.leo.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static final String RINDEX="html/index";
    private static final String LEARN="html/learn";
    private static final String BOOK="html/gbook";
    private static final String ABOUT="html/about";
    private static final String MANSHENGHUO="html/manshenghuo";
    @RequestMapping("/index")
    public String index(){

        return  RINDEX;
    }
    @RequestMapping("/learn")
    public String learn(){

        return  LEARN;
    }
    @RequestMapping("/gbook")
    public String gbook(){

        return  BOOK;
    }
    @RequestMapping("/about")
    public String about(){

        return  ABOUT;
    }
    @RequestMapping("/manshenghuo")
    public String manshenghuo(){

        return  MANSHENGHUO;
    }

}
