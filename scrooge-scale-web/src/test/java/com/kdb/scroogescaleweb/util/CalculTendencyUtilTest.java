package com.kdb.scroogescaleweb.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculTendencyUtilTest {

    @Test
    void getTendency() {
        CalculTendencyUtil calculTendencyUtil = new CalculTendencyUtil();
        assertEquals(calculTendencyUtil.getTendency(38.22,8.78, 44),3);
    }
}