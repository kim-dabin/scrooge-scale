package com.kdb.scroogescaleweb.service;

import com.kdb.scroogescaleweb.dao.ResultDAO;
import com.kdb.scroogescaleweb.mapper.TypeMapper;
import com.kdb.scroogescaleweb.model.Type;
import com.kdb.scroogescaleweb.util.CalculTendencyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ResultServiceImpl implements ResultService{
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    ResultDAO resultDAO;
    @Autowired
    private CalculTendencyUtil calculTendencyUtil;
    private final Logger LOGGER = LoggerFactory.getLogger(ResultServiceImpl.class.getName());
    @Override
    @Transient
    public Type getUserType(String rowkey) {
        HashMap<String, Double> map = resultDAO.selectStat();
        if(map!=null){
            int userScore = resultDAO.selectUserScore(rowkey);
            LOGGER.info("User Score >>>> "+userScore);
            int step = -1;
            if(userScore>0){
                double avg = map.get("avg");
                double std = map.get("std");
                step = calculTendencyUtil.getTendency(avg, std, userScore);
            }
            if(step>0)  return typeMapper.selectOne(step);
        }

        return null;
    }

    @Override
    public Type getUserType(int id) {
        return typeMapper.selectOne(id);
    }
}
