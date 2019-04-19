package com.jhtsoft.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Pattern;

/**
 * @ClassName: VaildUtil
 * @Describe: TODO
 * @Author: houyingwei
 * @Date: 2019/4/9
 **/
public class VaildUtil {
    public VaildUtil() {
    }

    public static boolean isEmpty(Object data) {
        boolean isEmpty = true;
        if (data != null && (!(data instanceof String) || !"".equals(((String)data).replaceAll("\\s", "")))) {
            isEmpty = false;
        } else {
            isEmpty = true;
        }

        return isEmpty;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    public static boolean checkStrLength(String str, int len) {
        if (str != null && !"".equals(str.trim())) {
            return str.length() <= len;
        } else {
            return false;
        }
    }

    public static boolean checkStrLen(String str, int len) {
        return str == null || str.length() <= len;
    }

    public static boolean isAccount(String username) {
        boolean b = false;
        if (username != null && !username.isEmpty() && username.matches("^[a-zA-Z0-9\\s]*$")) {
            b = true;
        }

        return b;
    }

    public static boolean isValMobile(String mob) {
        boolean b = false;
        if (mob != null && !mob.isEmpty() && mob.matches("^1\\d{10}$")) {
            b = true;
        }

        return b;
    }

    public static boolean isValQQ(String qq) {
        boolean b = false;
        if (qq != null && !qq.isEmpty() && qq.matches("[1-9][0-9]{4,14}")) {
            b = true;
        }

        return b;
    }

    public static boolean isValTel(String tel) {
        boolean b = false;
        if (tel != null && !tel.isEmpty() && tel.matches("^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$")) {
            b = true;
        }

        return b;
    }

    public static boolean isValEmail(String email) {
        boolean b = false;
        if (email != null && !email.isEmpty() && email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")) {
            b = true;
        }

        return b;
    }

    public static boolean isValAmount(String amount) {
        boolean b = false;
        if (amount != null && !amount.isEmpty() && amount.matches("^(([1-9]\\d*)|\\d)(\\.\\d{1,2})?$")) {
            b = true;
        }

        return b;
    }

    public static String formatNumber(double num) {
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setGroupingUsed(false);
        return format.format(num);
    }

    public static double setPrecision(double value, String precision) {
        return Double.parseDouble((new DecimalFormat(precision)).format(value));
    }

    public static double setPrecisionTwo(double value) {
        return Double.parseDouble((new DecimalFormat("#.00")).format(value));
    }

    /**
     * 验证字符串是否为纯数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}