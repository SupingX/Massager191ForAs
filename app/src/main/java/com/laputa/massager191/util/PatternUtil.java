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

}
