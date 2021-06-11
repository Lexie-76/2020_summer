package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.domain.Data1;
import test.domain.Data2;
import test.domain.MongoEntity;
import test.service.MongoService;

import javax.annotation.Resource;
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



    @GetMapping("/countrynames")
    @ResponseBody
    public Data1 getCountryData(){
        Data1 md = this.getAllCountry();
        return md;
    }

    @GetMapping("/countrydata2")
    @ResponseBody
    public List<Data2> getCountryData2()
    {
        return this.getAllCountry2();
    }

    @RequestMapping("/languagedata")
    @ResponseBody
    public Data1 getLanguageData(){
        Data1 md = this.getAllLanguage();
        return md;
    }

    @RequestMapping("/languagedata2")
    @ResponseBody
    public List<Data2> getLanguageData2(){
        return this.getAllLanguage2();
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
        List<String> names = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
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
            names.add(country.get(i));
            value.add(countryNum.get(i));
        }
        Data1 md = new Data1(names,value);
        return md;
    }

    public List<Data2> getAllCountry2(){
        getMongoData();
        List<String> names = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
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
        List<Data2> md2s = new ArrayList<>();
        for(int i=0;i<country.size();i++){
            md2s.add(new Data2(country.get(i),countryNum.get(i)));
        }
        return md2s;
    }

    public Data1 getAllLanguage(){
        getMongoData();
        List<String> names = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
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
            names.add(language.get(i));
            value.add(languageNum.get(i));
        }
        Data1 md = new Data1(names,value);
        return md;
    }

    public List<Data2> getAllLanguage2(){
        getMongoData();
        List<String> names = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
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
        List<Data2> md2s = new ArrayList<>();
        for(int i=0;i<language.size();i++){
            md2s.add(new Data2(language.get(i),languageNum.get(i)));
        }
        return md2s;
    }


    public Data1 getAllType(){
        getMongoData();
        List<String> names = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
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
            names.add(type.get(i));
            value.add(typeNum.get(i));
        }
        Data1 md = new Data1(names,value);
        return md;
    }

    public Data1 getAllScore(){
        getMongoData();
        List<String> names = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
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
            names.add(score.get(i));
            value.add(scoreNum.get(i));
        }
        Data1 md = new Data1(names,value);
        return md;
    }

}

