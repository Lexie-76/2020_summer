package com.test.controller;

import com.test.domain.Response;
import com.test.thrift.ServiceProvider;
import com.test.thrift.spider.SpiderService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpiderController {
    @Resource
    private ServiceProvider serviceProvider;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/spider",method = RequestMethod.POST)
    public String spider(){
        SpiderService.Iface spiderService = serviceProvider.getSpiderService();
        try {
            spiderService.SpiderAllUrls();
        } catch (TException e) {
            e.printStackTrace();
            return null;
        }
        return "redirect:/kind";
    }
}
