package com.shangwf.app.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * Created by menmen on 16/5/12.
 */
public class EditViewUtils {

    /**
     * 设置小数位数控制 和第一个输入不能为点
     */
    /** 输入框小数的位数 */
    public static InputFilter setEditViewPointNum(EditText editText,int num) {
        InputFilter lengthfilter = new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {

                    String dValue = dest.toString();
                    if (".".equals(source.toString()) && dValue.length() == 0) {
                        return "";
                    }
                    String[] splitArray = dValue.split("\\.");
                    if (splitArray.length > 1) {
                        String dotValue = splitArray[1];
                        int diff = dotValue.length() + 1 - num;
                        if (diff > 0 && dstart > dest.length() - num - 1) {
                            return source.subSequence(start, end - diff);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
        return lengthfilter;
    }
}
