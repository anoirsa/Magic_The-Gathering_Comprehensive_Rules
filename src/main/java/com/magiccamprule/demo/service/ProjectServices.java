package com.magiccamprule.demo.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectServices {
    public static boolean isContain(String source, String subItem) {
        String pattern ="\\b"+subItem+"\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return  m.find();
    }
}
