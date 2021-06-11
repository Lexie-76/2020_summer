package com.test.controller;

import com.test.domain.Data1;
import com.test.domain.MongoEntity;
import com.test.service.MongoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MongoController {

    @Resource
    private MongoService mongoService;

    List<MongoEntity> result;


    public void getMongoData(){
        result = new ArrayList<>();
        result = mongoService.findAll();
    }

    @RequestMapping("/kind")
    public String kind(){
        return "kind";
    }

    @GetMapping("/countrydata")
    @ResponseBody
    public Data1 getCountryData(){
        Data1 md = this.getAllCountry();
        return md;
    }

    @RequestMapping("/languagedata")
    @ResponseBody
    public Data1 getLanguageData(){
        Data1 md = this.getAllLanguage();
        return md;
    }

    @RequestMapping("/typedata")
    @ResponseBody
    public Data1 getTypeData(){
        Data1 md = this.getAllType();
        return md;
    }

    @RequestMapping("/scoredata")
    @ResponseBody
    public Data1 getScoreData(){
        Data1 md = this.getAllScore();
        return md;
    }

    public Data1 getAllCountry(){
        getMongoData();
        List<String> categories = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        List<String> country = new ArrayList<>();
        List<Integer> countryNum = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            if(!country.contains(result.get(i).getCountry())){
                country.add(result.get(i).getCountry());
            }
        }
        for(int i=0;i<country.size();i++){
            countryNum.add(0);
        }
        for(int i=0;i<result.size();i++){
            for(int j=0;j<country.size();j++){
                if(result.get(i).getCountry().equals(country.get(j))){
                    int curNum = countryNum.get(j);
                    countryNum.set(j, curNum+1);
                }
            }
        }
        for(int i=0;i<country.size();i++){
            categories.add(country.get(i));
            data.add(countryNum.get(i));
        }
        Data1 md = new Data1(categories,data);
        return md;
    }

    public Data1 getAllLanguage(){
        getMongoData();
        List<String> categories = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        List<String> language = new ArrayList<>();
        List<Integer> languageNum = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            if(!language.contains(result.get(i).getLanguage())){
                language.add(result.get(i).getLanguage());
            }
        }
        for(int i=0;i<language.size();i++){
            languageNum.add(0);
        }
        for(int i=0;i<result.size();i++){
            for(int j=0;j<language.size();j++){
                if(result.get(i).getLanguage().equals(language.get(j))){
                    int curNum = languageNum.get(j);
                    languageNum.set(j, curNum+1);
                }
            }
        }
        for(int i=0;i<language.size();i++){
            categories.add(language.get(i));
            data.add(languageNum.get(i));
        }
        Data1 md = new Data1(categories,data);
        return md;
    }

    public Data1 getAllType(){
        getMongoData();
        List<String> categories = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        List<String> type = new ArrayList<>();
        List<Integer> typeNum = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            if(!type.contains(result.get(i).getType())){
                type.add(result.get(i).getType());
            }
        }
        for(int i=0;i<type.size();i++){
            typeNum.add(0);
        }
        for(int i=0;i<result.size();i++){
            for(int j=0;j<type.size();j++){
                if(result.get(i).getType().equals(type.get(j))){
                    int curNum = typeNum.get(j);
                    typeNum.set(j, curNum+1);
                }
            }
        }
        for(int i=0;i<type.size();i++){
            categories.add(type.get(i));
            data.add(typeNum.get(i));
        }
        Data1 md = new Data1(categories,data);
        return md;
    }

    public Data1 getAllScore(){
        getMongoData();
        List<String> categories = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        List<String> score = new ArrayList<>();
        List<Integer> scoreNum = new ArrayList<>();
        for(int i=0;i<result.size();i++){
            if(!score.contains(result.get(i).getScore())){
                score.add(result.get(i).getScore());
            }
        }
        for(int i=0;i<score.size();i++){
            scoreNum.add(0);
        }
        for(int i=0;i<result.size();i++){
            for(int j=0;j<score.size();j++){
                if(result.get(i).getScore().equals(score.get(j))){
                    int curNum = scoreNum.get(j);
                    scoreNum.set(j, curNum+1);
                }
            }
        }
        for(int i=0;i<score.size();i++){
            categories.add(score.get(i));
            data.add(scoreNum.get(i));
        }
        Data1 md = new Data1(categories,data);
        return md;
    }

}

