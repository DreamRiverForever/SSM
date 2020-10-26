package com.itheima.utils;

import org.springframework.beans.propertyeditors.PropertiesEditor;

import java.beans.PropertyEditor;
import java.util.Date;

public class DateStringEditor extends PropertiesEditor {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            Date date = DateUtils.string2Date(text, "yyyy-MM-dd HH:mm");
            super.setValue(date);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
