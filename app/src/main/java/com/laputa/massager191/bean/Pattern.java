package com.laputa.massager191.bean;

import com.laputa.massager191.R;

/**
 * Created by Administrator on 2016/9/22.
 */
public enum Pattern {
    Pattern_01(0x11, R.string.str_pattern_01)
    ,Pattern_02(0x12,R.string.str_pattern_02)
    ,Pattern_03(0x13,R.string.str_pattern_03)
    ,Pattern_04(0x14,R.string.str_pattern_04)
    ,Pattern_05(0x15,R.string.str_pattern_05)
    ,Pattern_06(0x17,R.string.str_pattern_06)
    ,Pattern_07(0x23,R.string.str_pattern_07)
    ,Pattern_08(0x49,R.string.str_pattern_08)
    ,Pattern_09(0x31,R.string.str_pattern_09)
    ,Pattern_10(0x34,R.string.str_pattern_10)
    ,Pattern_11(0x36,R.string.str_pattern_11)
    ,Pattern_12(0x42,R.string.str_pattern_12)
    ;

     public int  name;
     public int code;
    Pattern(int code,int name){
        this.name = name;
        this.code = code;
    }



}
