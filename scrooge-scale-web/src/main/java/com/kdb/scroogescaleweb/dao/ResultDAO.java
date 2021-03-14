package com.kdb.scroogescaleweb.dao;

import java.util.HashMap;

public interface ResultDAO {
    public HashMap<String, Double> selectStat();
    public int selectUserScore(String rowKey);
}
