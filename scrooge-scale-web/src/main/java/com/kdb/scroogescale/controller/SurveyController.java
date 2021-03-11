package com.kdb.scroogescale.controller;

import com.kdb.scroogescale.service.SurveyService;
import com.kdb.scroogescale.vo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ajax")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/survey/question/num/{id}", method = RequestMethod.POST)
    public Question getQuestion(@PathVariable int id){
        return surveyService.getQuestion(id);
    }


}
