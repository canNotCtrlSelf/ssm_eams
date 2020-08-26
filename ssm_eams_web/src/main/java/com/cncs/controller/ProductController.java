package com.cncs.controller;

import com.cncs.domain.Product;
import com.cncs.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;


    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "forward:/product/findAll.do";
    }

    /**
     * 查询所有
     * @return
     */
    //@RolesAllowed({"USER","ADMIN"})
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Product> list = productService.findAll();
        //System.out.println(list);
        mv.addObject("productList",list);
        mv.setViewName("product-list");
        return mv;
    }

}
