package com.kdb.scroogescaleweb.service;

import com.kdb.scroogescaleweb.dao.KafkaProducerDAO;
import com.kdb.scroogescaleweb.dao.ResultDAO;
import com.kdb.scroogescaleweb.dao.SurveyDAO;
import com.kdb.scroogescaleweb.mapper.SurveyMapper;
import com.kdb.scroogescaleweb.util.MD5Util;
import com.kdb.scroogescaleweb.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyDAO surveyDAO;
    @Autowired
    private SurveyMapper surveyMapper;
    @Autowired
    private MD5Util md5Util;
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
        if(kafkaProducerDAO.sendMessage(message)){
            //Spark로 보내기
            return 1;
        }

        return -1;
    }
}
