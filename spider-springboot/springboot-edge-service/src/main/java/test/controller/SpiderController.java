package test.controller;

import test.thrift.ServiceProvider;
import com.test.thrift.spider.SpiderService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class SpiderController {
    @Resource
    private ServiceProvider serviceProvider;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/kind")
    public String spider() {
        /*SpiderService.Iface spiderService = serviceProvider.getSpiderService();
        try {
            spiderService.SpiderAllUrls();
        } catch (TException e) {
            e.printStackTrace();
            return null;
        }*/
        return "kind";
    }
}

