package com.kdb.scroogescaleweb.mapper;


import com.kdb.scroogescaleweb.model.Type;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeMapper {
    public Type selectOne(int id);
}
