package com.kdb.scroogescaleweb.controller;

import com.kdb.scroogescaleweb.service.SurveyService;
import com.kdb.scroogescaleweb.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
    @Autowired
    private SurveyService surveyService;

    @RequestMapping(value = "/survey/question/num/{id}", method = RequestMethod.POST)
    public Question getQuestion(@PathVariable int id){
        return surveyService.getQuestion(id);
    }


}
