package com.liufeismart.weibo.utils;

import android.content.Context;

import com.liufeismart.weibo.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    public static String getCreateTime(Context context, String createTime) {
        //Tue May 31 17:46:55 +0800 2011
        DateFormat df1 = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
        DateFormat df2 = new SimpleDateFormat("mm月ss日 HH:mm");
        try {
//            createTime = createTime.replace("+0800", "");
            Date date = df1.parse(createTime);
            long time = date.getTime();
            long nowTime = System.currentTimeMillis();
            long intervalTime = (nowTime - time)/1000;
            if(intervalTime< 60) {
                String result = context.getResources().getString(R.string.seconds_before);
                result = String.format(result, intervalTime);
                return result;
            }
            else if(intervalTime< 3600) {
                String result = context.getResources().getString(R.string.minutes_before);
                result = String.format(result, (intervalTime/60));
                return result;
            }
            else if(intervalTime < 86400) {
                String result = context.getResources().getString(R.string.hours_before);
                result = String.format(result, (intervalTime/3600));
                return result;
            }
            else {
                return df2.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return createTime;
    }

    public static String getSource(Context context, String source) {
        String regex = "[>].+[<]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);
        if(m.find()) {
            int start = m.start()+1;
            int end = m.end()-1;
            return source.substring(start, end);
        }
        return source;
    }
}
