package com.kdb.scroogescaleweb.controller;

import com.kdb.scroogescaleweb.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    SurveyService surveyService;

    @RequestMapping(value = "/scrooge")
    public String main(){
        return "main";
    }

    @RequestMapping(value = "/test")
    public void test(){
        surveyService.test();
    }

    @RequestMapping(value = "/scrooge/result/1", method = RequestMethod.GET)
    public String resultView(){
        return "result";
    }
    @RequestMapping(value = "/scrooge/survey", method = RequestMethod.GET)
    public String survey(){
        return "survey";
    }

    @RequestMapping(value = "/scrooge/survey", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, HttpSession session){
        StringBuffer sb = new StringBuffer();
        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        sb.append(timeStampFormat.format(nowDate)).append(",");
        sb.append(session.getId()).append(",");
        sb.append(dateFormat.format(nowDate)).append(",");
        for(int i=1; i<12; i++){
            String param = "btnradio-"+i;
            sb.append(request.getParameter(param)).append(",");
        }

        String message = sb.deleteCharAt(sb.lastIndexOf(",")).toString();

        if(surveyService.send(message)>0)  return  "redirect:/scrooge/result";
        else    return "redirect:/error";
    }



}
