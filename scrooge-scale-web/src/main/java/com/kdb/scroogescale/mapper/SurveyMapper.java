package com.kdb.scroogescale.mapper;

import com.kdb.scroogescale.vo.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SurveyMapper {
    public Question selectOne(int id);
}
