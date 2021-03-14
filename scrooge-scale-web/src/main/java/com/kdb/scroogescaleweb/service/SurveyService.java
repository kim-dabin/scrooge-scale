package com.kdb.scroogescaleweb.service;

import com.kdb.scroogescaleweb.model.Question;

public interface SurveyService {
    public Question getQuestion(int id);
    public int send(String message);
    public void test();

}
