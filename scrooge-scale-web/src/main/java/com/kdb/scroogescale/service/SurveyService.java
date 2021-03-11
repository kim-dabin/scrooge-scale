package com.kdb.scroogescale.service;

import com.kdb.scroogescale.vo.Question;

public interface SurveyService {
    public Question getQuestion(int id);
    public int send(String message);
}
