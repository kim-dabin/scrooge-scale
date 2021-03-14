package com.kdb.scroogescaleweb.controller;

import com.kdb.scroogescaleweb.service.ResultService;
import com.kdb.scroogescaleweb.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    SurveyService surveyService;
    @Autowired
    ResultService resultService;


    @RequestMapping(value = "/scrooge")
    public String main(){
        return "main";
    }


    @RequestMapping(value = "/scrooge/result/find/{row}")
    public ModelAndView resultViewFind(@PathVariable String row){
        ModelAndView mv = new ModelAndView(); ///
        mv.setViewName("result");
        mv.addObject("type",resultService.getUserType(row));
        return mv;
    }

    @RequestMapping(value = "/scrooge/result/{id}", method = RequestMethod.GET)
    public ModelAndView resultView(@PathVariable int id){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("result");
        mv.addObject("type",resultService.getUserType(id));
        return mv;
    }

    @RequestMapping(value = "/test")
    public void test(){
        surveyService.test();
    }

    @RequestMapping(value = "/scrooge/survey", method = RequestMethod.GET)
    public String survey(){
        return "survey";
    }

    @RequestMapping(value = "/scrooge/survey", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, HttpSession session){
        int step = -1;
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

        step = surveyService.send(message);
        if(step>0)  return  "redirect:/scrooge/result/"+step;
        else    return "redirect:/error";
    }



}
