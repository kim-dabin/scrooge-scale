package com.kdb.scroogescaleweb.controller;

<<<<<<< HEAD
import com.kdb.scroogescaleweb.service.ResultService;
import com.kdb.scroogescaleweb.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
=======
import com.kdb.scroogescaleweb.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
>>>>>>> cbdaeb7f97b44d65fc101ce6b27681c8fa40e782

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    SurveyService surveyService;
<<<<<<< HEAD
    @Autowired
    ResultService resultService;
=======
>>>>>>> cbdaeb7f97b44d65fc101ce6b27681c8fa40e782

    @RequestMapping(value = "/scrooge")
    public String main(){
        return "main";
    }

<<<<<<< HEAD
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

=======
    @RequestMapping(value = "/test")
    public void test(){
        surveyService.test();
    }

    @RequestMapping(value = "/scrooge/result/1", method = RequestMethod.GET)
    public String resultView(){
        return "result";
    }
>>>>>>> cbdaeb7f97b44d65fc101ce6b27681c8fa40e782
    @RequestMapping(value = "/scrooge/survey", method = RequestMethod.GET)
    public String survey(){
        return "survey";
    }

    @RequestMapping(value = "/scrooge/survey", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, HttpSession session){
<<<<<<< HEAD
        int step = -1;
=======
>>>>>>> cbdaeb7f97b44d65fc101ce6b27681c8fa40e782
        StringBuffer sb = new StringBuffer();
        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
<<<<<<< HEAD

        sb.append(timeStampFormat.format(nowDate)).append(",");
        sb.append(session.getId()).append(",");
        sb.append(dateFormat.format(nowDate)).append(",");

=======
        sb.append(timeStampFormat.format(nowDate)).append(",");
        sb.append(session.getId()).append(",");
        sb.append(dateFormat.format(nowDate)).append(",");
>>>>>>> cbdaeb7f97b44d65fc101ce6b27681c8fa40e782
        for(int i=1; i<12; i++){
            String param = "btnradio-"+i;
            sb.append(request.getParameter(param)).append(",");
        }

        String message = sb.deleteCharAt(sb.lastIndexOf(",")).toString();
<<<<<<< HEAD
        step = surveyService.send(message);
        if(step>0)  return  "redirect:/scrooge/result/"+step;
=======

        if(surveyService.send(message)>0)  return  "redirect:/scrooge/result";
>>>>>>> cbdaeb7f97b44d65fc101ce6b27681c8fa40e782
        else    return "redirect:/error";
    }



}
