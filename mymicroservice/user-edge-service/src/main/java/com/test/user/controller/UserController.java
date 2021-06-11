package com.test.user.controller;


import com.test.thrift.message.MessageService;
import com.test.thrift.user.UserInfo;
import com.test.thrift.user.UserService;
import com.test.thrift.user.dto.UserDTO;
import com.test.user.redis.RedisClient;
import com.test.user.thrift.ServiceProvider;
import com.test.user.util.conversion.ConvertDTO;
import com.test.user.util.messagedigest.MD;
import com.test.user.util.randoncode.RC;
import com.test.user.util.response.LoginResponse;
import com.test.user.util.response.Response;
import com.test.user.util.token.Token;
import org.apache.commons.lang.StringUtils;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private ServiceProvider serviceProvider;

    @Resource
    private RedisClient redisClient;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username") String username, @RequestParam("password")String password){
        UserService.Iface userService = serviceProvider.getUserService();
        UserInfo userInfo=null;
        try {
            userInfo = userService.getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if(userInfo == null){
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if(userInfo.getPassword().equalsIgnoreCase(MD.md5(password))){
            return Response.USERNAME_PASSWORD_INVALID;
        }

        String token = Token.genToken();
       UserDTO userDTO = ConvertDTO.toUserDTO(userInfo);
       redisClient.set(token,userDTO,3000);



        return new LoginResponse(token);
    }

    @RequestMapping(value = "/sendVerifyCode",method = RequestMethod.POST)
    @ResponseBody
    public Response sendVerifyCode(@RequestParam(value = "mobile",required = false)String mobile,
                                   @RequestParam(value = "email",required = false)String email){
        String message = "VerifyCode is : ";
        String code = RC.randomCode("0123456789",6);

        MessageService.Iface messageService = serviceProvider.getMessageService();
        try{
            boolean result = false;
            if(StringUtils.isNotBlank(mobile)){
                result = messageService.sendMobileMessage(mobile,message+code);
                redisClient.set(mobile,code);

            }
            else if(StringUtils.isNotBlank(email)){
                result = messageService.sendEmailMessage(email,message+code);
                redisClient.set(email,code);
            }else{
                return Response.MOBILE_OR_EMAIL_REQUIRED;
            }
            if(!result){
                return Response.SEND_VERIFYCODE_FAILED;
            }
        }catch (TException e){
            e.printStackTrace();
            return Response.exception(e);
        }
        return Response.SUCCESS;
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Response register(@RequestParam("username") String username,
                    @RequestParam("password") String password,
                    @RequestParam(value = "mobile",required = false)String mobile,
                    @RequestParam(value = "email",required = false)String email,
                    @RequestParam("verifyCode") String verifyCode){
        if(StringUtils.isBlank(mobile)&&StringUtils.isBlank(email)){
            return Response.MOBILE_OR_EMAIL_REQUIRED;
        }

        if(StringUtils.isNotBlank(mobile)){
            String redisCode = redisClient.get(mobile);
            if(!verifyCode.equals(redisCode)){
                return Response.VERIFY_CODE_INVALID;
            }
        }else{
            String redisCode = redisClient.get(email);
            if(!verifyCode.equals(redisCode)){
                return Response.VERIFY_CODE_INVALID;
            }
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(MD.md5(password));
        userInfo.setMobile(mobile);
        userInfo.setEmail(email);

        UserService.Iface userService = serviceProvider.getUserService();
        try{
            userService.registerUser(userInfo);
        } catch (TException e) {
            e.printStackTrace();
            return Response.exception(e);
        }
        return Response.SUCCESS;
    }

    @RequestMapping(value = "authentication",method = RequestMethod.POST)
    @ResponseBody
    public UserDTO authentication(String token){
        return redisClient.get(token);
    }



}
