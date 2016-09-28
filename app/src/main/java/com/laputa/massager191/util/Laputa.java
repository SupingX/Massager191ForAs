package com.laputa.massager191.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 工具类集合
 */
public class Laputa {
    // TODO 屏幕处理
    //****************************************************************************************************************************************************
    //*******************************************************                 ****************************************************************************
    //*******************************************************     屏幕处理    ****************************************************************************
    //*******************************************************                 ****************************************************************************
    //****************************************************************************************************************************************************

    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue px值
     * @param scale   （DisplayMetrics类中属性density）
     * @return result
     */
    public static int px2dip(float pxValue, float scale) {
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue dip值
     * @param scale    （DisplayMetrics类中属性density）
     * @return result
     */
    public static int dip2px(float dipValue, float scale) {
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue   px值
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return result
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue   sp值
     * @param fontScale （DisplayMetrics类中属性scaledDensity）
     * @return result
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * dip转px
     *
     * @param context  上下文
     * @param dipValue dip值
     * @return result
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    //f0e9e9

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        //Log.i(TAG, "Screen---Width = " + w_screen + " Height = " + h_screen + " densityDpi = " + dm.densityDpi);
        return new Point(w_screen, h_screen);

    }

    /**
     * 获取屏幕长宽比
     *
     * @param context
     * @return
     */
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }

    // TODO 时间处理
    //****************************************************************************************************************************************************
    //*******************************************************                 ****************************************************************************
    //*******************************************************     时间处理    ****************************************************************************
    //*******************************************************                 ****************************************************************************
    //****************************************************************************************************************************************************
    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

    public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
        final long interval = ms1 - ms2;
        return interval < MILLIS_IN_DAY && interval > -1L * MILLIS_IN_DAY && toDay(ms1) == toDay(ms2);
    }

    /**
     * 获取当前手机的时间格式
     *
     * @param context
     * @return 24小时制/12小时制
     */
    public static int getTimeFormat(Context context) {
//		String timeFormat = android.provider.Settings.System.getString(context.getContentResolver(), android.provider.Settings.System.TIME_12_24);
//		//Log.e("DateUtil", "timeFormat :" + timeFormat);
//		if (timeFormat.equals("24")) {
//			return 0;
//		} else if (timeFormat.equals("12")) {
//			return 1;
//		}
//		return -1;
        boolean is24HourFormat = android.text.format.DateFormat.is24HourFormat(context);
        if (is24HourFormat) {
            return 0;
        } else {
            return 1;
        }
    }

    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }

    private static SimpleDateFormat DEFAULT_SDF = new SimpleDateFormat("yyyyMMdd");

    public static boolean isSameDay(String date1, Date date2) {
        String dateStr2 = DEFAULT_SDF.format(date2);
        return date1.equals(dateStr2);
    }

    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) && c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);

    }

    public static String dateToString(Date date) {
        return DEFAULT_SDF.format(date);
    }

    public static String dateToHourStr(long date) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date);
        SimpleDateFormat s = new SimpleDateFormat("mm:ss");
        return s.format(c.getTime());
    }

    public static Date stringToDate(String date) {
        try {
            return DEFAULT_SDF.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDayOfMonth(Date date) {
        // 获取一个月的天
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        ;
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfMonth(String date) {
        // 获取一个月的天
        return getDayOfMonth(stringToDate(date));
    }

    // 当前的前DIFF天
    public static Date getDateOfDiffDay(Date date, int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        ;
        c.add(Calendar.DATE, diff);
        return c.getTime();
    }

    // 当前的前DIFF月
    public static Date getDateOfDiffMonth(Date date, int diff) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        ;
        c.add(Calendar.MONTH, diff);
        //Log.v("", "偏移后的月份 ：" + c.get(Calendar.MONTH));
        return c.getTime();
    }

    // 当前月的所有天数
    public static int getMonthMaxDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getMaximum(Calendar.MONTH);

    }

    public static String dateToString(Date date, String sdf) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(sdf);
        return sdf1.format(date);
    }

    public static Date stringToDate(String date, String sdf) {
        SimpleDateFormat sdf1 = new SimpleDateFormat(sdf);
        try {
            return sdf1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据传入的毫秒数，转换成MM:SS时间串形式返回
     *
     * @param timeMillis
     * @return
     */
    public static String getMMSS(long timeMillis) {
        StringBuffer sb = new StringBuffer();
        long mm = (timeMillis % (1000 * 60 * 60)) / 1000 / 60;
        long ss = ((timeMillis % (1000 * 60 * 60)) % (1000 * 60)) / 1000;

        if (mm < 0) {
            sb.append("00:");
        } else if (mm < 10) {
            sb.append("0" + mm + ":");
        } else {
            sb.append(mm + ":");
        }
        if (ss < 0) {
            sb.append("00");
        } else if (ss < 10) {
            sb.append("0" + ss);
        } else {
            sb.append(ss);
        }
        return sb.toString();
    }

    /**
     * 根据传入的耗秒数, 转换成为HH:MM:SS的字符串返回
     */
    public static String getHHMMSS(long time) {
        String hhmmss = "00:00:00";
        StringBuffer bf = new StringBuffer();
        if (time == 0) {
            return hhmmss;
        }
        long hh = time / 1000 / 60 / 60;
        long mm = (time % (1000 * 60 * 60)) / 1000 / 60;
        long ss = ((time % (1000 * 60 * 60)) % (1000 * 60)) / 1000;

        if (hh < 0) {
            bf.append("00:");
        } else if (hh < 10) {
            bf.append("0" + hh + ":");
        } else {
            bf.append(hh + ":");
        }
        if (mm < 0) {
            bf.append("00:");
        } else if (mm < 10) {
            bf.append("0" + mm + ":");
        } else {
            bf.append(mm + ":");
        }
        if (ss < 0) {
            bf.append("00");
        } else if (ss < 10) {
            bf.append("0" + ss);
        } else {
            bf.append(ss);
        }
        hhmmss = bf.toString();
        System.out.println(hhmmss);
        return hhmmss;
    }

    /**
     * 比较2个时间的大小
     */
    public static boolean compareTime(Date time1, Date time2) {
        return time1.before(time2);
    }

    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH);
    }

    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String parseDate(String createTime) {
        try {
            String ret = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long create = sdf.parse(createTime).getTime();
            Calendar now = Calendar.getInstance();
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));//毫秒数
            long ms_now = now.getTimeInMillis();
            if (ms_now - create < ms) {
                ret = "今天 " + createTime.substring(createTime.indexOf(" ") + 1);
            } else if (ms_now - create < (ms + 24 * 3600 * 1000)) {
                ret = "昨天 " + createTime;
            } else if (ms_now - create < (ms + 24 * 3600 * 1000 * 2)) {
                ret = "前天 " + createTime;
            } else {
                ret = "更早 " + createTime;
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO 持久化处理
    //****************************************************************************************************************************************************
    //*******************************************************                 ****************************************************************************
    //*******************************************************     持久化处理  *****************************************************************************
    //*******************************************************                 ****************************************************************************
    //****************************************************************************************************************************************************
    public static SharedPreferences sharedPreferences;
    public final static String SHARED_NAME = "Laputa";

    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            // sharedPreferences = PreferenceManager
            // .getDefaultSharedPreferences(context);
            sharedPreferences = context.getSharedPreferences(
                    SHARED_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    /**
     * 存入数据
     *
     * @param context
     * @param key     键
     * @param value   值
     */
    public static void put(Context context, String key, Object value) {
        SharedPreferences.Editor editor = getEditor(context);
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, (String) value);
        }
        commit(editor);
    }

    /**
     * 读取数据
     *
     * @param context
     * @param key          键
     * @param defaultValue 默认值
     */
    public static Object get(Context context, String key, Object defaultValue) {
        SharedPreferences sp = getInstance(context);
        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        }
        return null;
    }

    /**
     * 可编辑
     *
     * @param context
     * @return
     */
    public static SharedPreferences.Editor getEditor(Context context) {
        return getInstance(context).edit();
    }

    /**
     * 提交
     *
     * @param editor
     */
    public static void commit(SharedPreferences.Editor editor) {
        editor.commit();
    }

    /**
     * 是否包含某键值
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        return getInstance(context).contains(key);
    }

    /**
     * 清除某个key所对应的value值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        getEditor(context).remove(key);
    }

    /**
     * 清除所有存储的值
     *
     * @param context
     */
    public static void removeAll(Context context) {
        getEditor(context).clear();
    }

    // TODO 数据处理
    //****************************************************************************************************************************************************
    //*******************************************************                 ****************************************************************************
    //*******************************************************     数据  处理  *****************************************************************************
    //*******************************************************                 ****************************************************************************
    //****************************************************************************************************************************************************

    public static DecimalFormat df = new DecimalFormat("0.00");
    public static DecimalFormat df2 = new DecimalFormat("0.0");

    /**
     * byte[]转变为16进制String字符, 每个字节2位, 不足补0
     */
    public static String byteToHexString(byte[] bytes) {
        String result = null;
        String hex = null;
        if (bytes != null && bytes.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(bytes.length);
            for (byte byteChar : bytes) {
                hex = Integer.toHexString(byteChar & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                stringBuilder.append(hex.toUpperCase());
            }
            result = stringBuilder.toString();
        }
        return result;
    }

    /**
     * 十进制String--->十六进制String, 每个字节2位, 不足补0
     */
    public static String toHexStringByString(String value) {
        String hex = Integer.toHexString(Integer.valueOf(value));
        StringBuilder stringBuilder = new StringBuilder();
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        stringBuilder.append(hex);
        return stringBuilder.toString();

    }

    /**
     * 把16进制String字符转变为byte[]
     */
    public static byte[] hexStringToByte(String data) {
        byte[] bytes = null;
        if (data != null) {
            data = data.toUpperCase();
            int length = data.length() / 2;
            char[] dataChars = data.toCharArray();
            bytes = new byte[length];
            for (int i = 0; i < length; i++) {
                int pos = i * 2;
                bytes[i] = (byte) (charToByte(dataChars[pos]) << 4 | charToByte(dataChars[pos + 1]));
            }
        }
        return bytes;
    }


    /**
     * 取得在16进制字符串中各char所代表的16进制数
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 十进制int ---->十六进制String 不足补0
     *
     * @param i
     * @return
     */
    public static String toHexString(int i) {
        String value = Integer.toHexString(i);
        if (value.length() == 1) {
            value = "0" + value;
        }
        return value;
    }


    /**
     * 根据传入的2个字节2位16进制字符比如FFFF, 计算返回int类型的绝对值
     */
    public static int hexStringToInt(String hexString) {
        return binaryString2int(hexString2binaryString(hexString));
    }

    /**
     * 16进制转换为2进制
     */
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0) {
            return null;
        }
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }

    /**
     * 二进制转为10进制 返回int
     */
    public static int binaryString2int(String binarysString) {
        if (binarysString == null || binarysString.length() % 8 != 0) {
            return 0;
        }
        int result = Integer.valueOf(binarysString, 2);
        if ("1".equals(binarysString.substring(0, 1))) {
            System.out.println("这是个负数");
            char[] values = binarysString.toCharArray();
            for (int i = 0; i < values.length; i++) {
                if (values[i] == '1') {
                    values[i] = '0';
                } else {
                    values[i] = '1';
                }
            }
            binarysString = String.valueOf(values);
            result = Integer.valueOf(binarysString, 2) + 1;
        }

        return result;
    }

    // 二进制转十六进制
    public static String binaryToHex(String s) {
        return Long.toHexString(Long.parseLong(s, 2));
    }

    // 十六进制String --> 转int
    public static int hexToInt(String hex) {
        return Integer.parseInt(hex, 16);
    }

    // 十六进制转二进制
    public static String HexToBinary(String s) {
        return Long.toBinaryString(Long.parseLong(s, 16));
    }

    /**
     * 根据步数得到卡洛里 并且格式化
     *
     * @param step
     * @return
     */
    public static String getKal(int step) {
        DecimalFormat df = new DecimalFormat("0.00");
        String kael = df.format(((step / 1000) * 18.842));
        return kael;

    }

    /**
     * 根据步数得到距离 并且格式化
     *
     * @param step
     * @return
     */
    public static String getDistance(int step) {
        DecimalFormat df = new DecimalFormat("0.00");
        String km = df.format((step / 1000) * 0.416);
        return km;
    }

    /**
     * 根据完成步数/目标步数 得到百分比
     *
     * @param x
     * @return
     */
    public static String getPercent(int x, int total) {
        // NumberFormat num = NumberFormat.getPercentInstance();
        // NumberFormat nf = NumberFormat.getInstance(Locale.FRENCH);
        // nf .setMaximumIntegerDigits(2);
        // nf .setMaximumFractionDigits(2);
        // return nf.format(b);

        String result = "";// 接受百分比的值
        double x_double = x * 1.0;
        double tempresult = x_double / total;
        // NumberFormat nf = NumberFormat.getPercentInstance(); 注释掉的也是一种方法
        // nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
        DecimalFormat df1 = new DecimalFormat("0.00%"); // ##.00%
        // 百分比格式，后面不足2位的用0补齐
        // result=nf.format(tempresult);
        result = df1.format(tempresult);
        return result;
    }

    /**
     * 十进制的字符串 --> 十六进制的字符串
     *
     * @param value
     * @return
     */
    public static String getHexFormOctString(String value) {
        return Integer.toHexString(Integer.valueOf(value));
    }

    /**
     * int n1 = 14; //十进制转成十六进制： Integer.toHexString(n1); //十进制转成八进制
     * Integer.toOctalString(n1); //十进制转成二进制 Integer.toBinaryString(12);
     * <p/>
     * //十六进制转成十进制 Integer.valueOf("FFFF",16).toString(); //十六进制转成二进制
     * Integer.toBinaryString(Integer.valueOf("FFFF",16)); //十六进制转成八进制
     * Integer.toOctalString(Integer.valueOf("FFFF",16));
     * <p/>
     * //八进制转成十进制 Integer.valueOf("576",8).toString(); //八进制转成二进制
     * Integer.toBinaryString(Integer.valueOf("23",8)); //八进制转成十六进制
     * Integer.toHexString(Integer.valueOf("23",8));
     * <p/>
     * <p/>
     * //二进制转十进制 Integer.valueOf("0101",2).toString(); //二进制转八进制
     * Integer.toOctalString(Integer.parseInt("0101", 2)); //二进制转十六进制
     * Integer.toHexString(Integer.parseInt("0101", 2));
     */

    public static String format(float value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);

    }

    public static String format1(float value) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(value);

    }

    // TODO 日志
    //****************************************************************************************************************************************************
    //*******************************************************                 ****************************************************************************
    //*******************************************************     日志  处理  *****************************************************************************
    //*******************************************************                 ****************************************************************************
    //****************************************************************************************************************************************************
    public static boolean isDebug_all = true;
    public static boolean isDebug_e = true;
    public static boolean isDebug_i = true;
    public static final String TAG = "laputa";
    public static String HEAD_END = "(*▔＾▔*)  ";
    public static String HEAD_START = "  (*▔＾▔*) ";

    public static void e(String msg) {
        if (isDebug_all && isDebug_e) {
            Log.e(TAG, HEAD_START + msg + HEAD_END);
        }
    }

    public static void i(String msg) {
        if (isDebug_all && isDebug_i) {
            Log.i(TAG, HEAD_START + msg + HEAD_END);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug_all && isDebug_e) {
            Log.e(tag, HEAD_START + msg + HEAD_END);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug_all && isDebug_i) {
            Log.i(tag, HEAD_START + msg + HEAD_END);
        }
    }

    public static void e(Class clz, String msg) {
        if (isDebug_all && isDebug_e) {
            Log.e(clz.getSimpleName(), HEAD_START + msg + HEAD_END);
        }
    }

    public static void i(Class clz, String msg) {
        if (isDebug_all && isDebug_i) {
            Log.i(clz.getSimpleName(), HEAD_START + msg + HEAD_END);
        }
    }
}
