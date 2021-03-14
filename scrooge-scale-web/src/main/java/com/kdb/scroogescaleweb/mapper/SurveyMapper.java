package com.kdb.scroogescaleweb.mapper;

import com.kdb.scroogescaleweb.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyMapper {
    public Question selectOne(int id);
}
