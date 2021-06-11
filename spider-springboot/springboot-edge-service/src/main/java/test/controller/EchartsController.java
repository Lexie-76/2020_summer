package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @RequestMapping("/country")
    public String countryPic(){
        return "country";
    }

    @RequestMapping(value = "/language", method = RequestMethod.GET)
    public String languagePic(){
        return "language";
    }

    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public String typePic(){
        return "type";
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public String scorePic(){
        return "score";
    }
}
