package com.kdb.scroogescale.service;

import com.kdb.scroogescale.dao.KafkaProducerDAO;
import com.kdb.scroogescale.dao.SurveyDAO;
import com.kdb.scroogescale.mapper.SurveyMapper;
import com.kdb.scroogescale.util.MD5Util;
import com.kdb.scroogescale.vo.Question;
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

    @Override
    public Question getQuestion(int id) {
        return surveyMapper.selectOne(id);
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
