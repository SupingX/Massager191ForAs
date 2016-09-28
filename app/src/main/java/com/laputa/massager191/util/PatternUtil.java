package com.laputa.massager191.util;

import com.laputa.massager191.R;
import com.laputa.massager191.bean.Pattern;

/**
 * Created by Administrator on 2016/9/22.
 */
public class PatternUtil {
    public static int getPatternName(int code) {
        int name = R.string.str_pattern_00;
        if (code == Pattern.Pattern_01.code) {
            name = Pattern.Pattern_01.name;
        } else if(code == Pattern.Pattern_02.code){
            name = Pattern.Pattern_02.name;
        }else if(code == Pattern.Pattern_03.code){
            name = Pattern.Pattern_03.name;
        }else if(code == Pattern.Pattern_04.code){
            name = Pattern.Pattern_04.name;
        }else if(code == Pattern.Pattern_05.code){
            name = Pattern.Pattern_05.name;
        }else if(code == Pattern.Pattern_06.code){
            name = Pattern.Pattern_06.name;
        }else if(code == Pattern.Pattern_07.code){
            name = Pattern.Pattern_07.name;
        }else if(code == Pattern.Pattern_08.code){
            name = Pattern.Pattern_08.name;
        }else if(code == Pattern.Pattern_09.code){
            name = Pattern.Pattern_09.name;
        }else if(code == Pattern.Pattern_10.code){
            name = Pattern.Pattern_10.name;
        }else if(code == Pattern.Pattern_11.code){
            name = Pattern.Pattern_11.name;
        }else if(code == Pattern.Pattern_12.code){
            name = Pattern.Pattern_12.name;
        }
        return name;
    }

    public static int getPatternImg(int code) {
        int img =R.mipmap.ic_pattern_01;
        if (code == Pattern.Pattern_01.code) {
            img =R.mipmap.ic_pattern_01;
        } else if(code == Pattern.Pattern_02.code){
            img =R.mipmap.ic_pattern_02;
        }else if(code == Pattern.Pattern_03.code){
            img =R.mipmap.ic_pattern_03;
        }else if(code == Pattern.Pattern_04.code){
            img =R.mipmap.ic_pattern_04;
        }else if(code == Pattern.Pattern_05.code){
            img =R.mipmap.ic_pattern_05;
        }else if(code == Pattern.Pattern_06.code){
            img =R.mipmap.ic_pattern_06;
        }else if(code == Pattern.Pattern_07.code){
            img =R.mipmap.ic_pattern_07;
        }else if(code == Pattern.Pattern_08.code){
            img =R.mipmap.ic_pattern_08;
        }else if(code == Pattern.Pattern_09.code){
            img =R.mipmap.ic_pattern_09;
        }else if(code == Pattern.Pattern_10.code){
            img =R.mipmap.ic_pattern_10;
        }else if(code == Pattern.Pattern_11.code){
            img =R.mipmap.ic_pattern_11;
        }else if(code == Pattern.Pattern_12.code){
            img =R.mipmap.ic_pattern_12;
        }
        return img;
    }

}
