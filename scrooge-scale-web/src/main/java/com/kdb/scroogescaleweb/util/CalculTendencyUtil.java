package com.kdb.scroogescaleweb.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class CalculTendencyUtil {
    private Map<Integer, Integer> map;
    private final Logger LOGGER = LoggerFactory.getLogger(CalculTendencyUtil.class.getName());
    public CalculTendencyUtil(){
        map = new HashMap<>();
        map.put(3, 1);
        map.put(2,2);
        map.put(1,3);
        map.put(0,3);
        map.put(-1,3);
        map.put(-2,4);
        map.put(-3,5);
    }

    public int getTendency(double avg, double std, int score){
        double diff = avg-score;
        int rest = (int)(diff%std);
        int step = (int) (diff/std);
        int result = -1;

        if(diff>0&& rest!=0)    step+=1;
        else if(diff<0&&rest!=0)    step-=1;

        if(step>3)  step = 3;
        else if(step<-3) step = -3;

        LOGGER.info("STEP >>>> "+step);

        result = map.get(step);

        LOGGER.info("RESULT >>>> "+result);

        return result;
    }
}
