package com.wls.dmr.utils;


/**
 * @Author Sunne
 * @Date 2019/3/2715:03
 * @Param wx
 **/
public class TC {


    //gcc-add
    //字符串转成数组取出来
    public String test(String str, int k) {

        int m = str.length() / 2;
        if (m * 2 < str.length()) {
            m++;
        }
        String[] strs = new String[m];
//        System.out.println(strs);
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i % 2 == 0) {//每隔两个
                strs[j] = "" + str.charAt(i);
            } else {
                strs[j] = strs[j] + str.charAt(i);//将字符加上两个空格
                j++;
            }
        }


        return strs[k];
    }

    //2个字符串拼接
    public String testStringBuilder(String str1, String str2) {

        StringBuilder sb = new StringBuilder(str1);
//        for (int i=0; i<str1.length(); i++){
        sb.append(str2);
//        }

        return sb.toString();
    }

    //3个字符串拼接
    public String testStringBuilder0(String str1, String str2, String str3) {

        StringBuilder sb = new StringBuilder(str1);
//        for (int i=0; i<str1.length(); i++){
        sb.append(str2);
        sb.append(str3);
//        }

        return sb.toString();
    }

    //4个字符串拼接
    public String testStringBuilder1(String str1, String str2, String str3, String str4) {

        StringBuilder sb = new StringBuilder(str1);

        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb.toString();
    }

    //6个字符串拼接
    public String testStringBuilder2(String str1, String str2, String str3, String str4, String str5, String str6) {

        StringBuilder sb = new StringBuilder(str1);

        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        sb.append(str5);
        sb.append(str6);
        return sb.toString();
    }


    //16进制字符串转换为2进制字符串
    public static String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }

        return bString;
    }

    //gcc_end


    /*
     * 字符串转16进制字符串
     */
    public static String string2HexString(String s){
        String r = bytes2HexString(string2Bytes(s));
        return r;
    }

    /*
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        String r = "";

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }


    /*
     * 字符串转字节数组
     */
    public static byte[] string2Bytes(String s){
        byte[] r = s.getBytes();
        return r;
    }

}






