package com.kdb.scroogescaleweb.service;

import com.kdb.scroogescaleweb.dao.KafkaProducerDAO;
import com.kdb.scroogescaleweb.dao.ResultDAO;
import com.kdb.scroogescaleweb.dao.SurveyDAO;
import com.kdb.scroogescaleweb.mapper.SurveyMapper;
import com.kdb.scroogescaleweb.util.CalculTendencyUtil;
import com.kdb.scroogescaleweb.util.MD5Util;
import com.kdb.scroogescaleweb.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyDAO surveyDAO;
    @Autowired
    private SurveyMapper surveyMapper;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private CalculTendencyUtil calculTendencyUtil;

    @Autowired
    private KafkaProducerDAO kafkaProducerDAO;
    @Autowired
    private ResultDAO resultDAO;

    @Override
    public Question getQuestion(int id) {
        return surveyMapper.selectOne(id);
    }

    @Override
    public void test() {
        resultDAO.selectStat();
    }

    @Override
    @Transient
    public int send(String message) {
        String[] values = message.split(",");
        String keyByMD5 = md5Util.getMD5(values[0]+values[1]);
        int step = -1;
        if(kafkaProducerDAO.sendMessage(message)){
            HashMap<String, Double> map = resultDAO.selectStat();
            int userScore = resultDAO.selectUserScore(keyByMD5);
            if(map!=null&&userScore>0){
                double avg = map.get("avg");
                double std = map.get("std");
                step = calculTendencyUtil.getTendency(avg, std, userScore);
            }

            return step;
        }

        return step;
    }
}
