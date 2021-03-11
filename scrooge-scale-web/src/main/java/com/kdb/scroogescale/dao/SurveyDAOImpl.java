package com.kdb.scroogescale.dao;

import com.kdb.scroogescale.vo.Question;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyDAOImpl implements SurveyDAO{
    @Autowired
    private SqlSession session;

//    @Override
//    public Question selectOne(int id) {
//        return session.selectOne("question.selectOne", id);
//    }
}
