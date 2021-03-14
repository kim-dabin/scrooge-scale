package com.kdb.scroogescaleweb.service;

import com.kdb.scroogescaleweb.model.Type;

public interface ResultService {
    public Type getUserType(int id);
    public Type getUserType(String rowkey);
}
