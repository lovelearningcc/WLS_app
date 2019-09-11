/*
 *  Copyright ® 2019 Shanghai TNSOFT Co. Ltd.
 *  All right reserved.
 *  Author: Kris.wu
 */
package com.wls.jzgy.utils;

public final class Hex {

    private static final char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F'};

    public Hex() {
    }

    public static String toHexString(byte[] block) {
        return toHexString(block, 0, block.length);
    }

    public static String toHexString(byte[] block, int offset, int len) {
        if (block == null) {
            return null;
        }
        if (offset < 0 || offset + len > block.length) {
            throw new IllegalArgumentException("Invalid offset or length.");
        }

        StringBuilder buf = new StringBuilder();
        for (int i = offset, size = offset + len; i < size; i++) {
            int high = ((block[i] & 0xf0) >> 4);
            int low = (block[i] & 0x0f);

            buf.append(HEX_CHARS[high]);
            buf.append(HEX_CHARS[low]);
        }
        return buf.toString();
    }

    public static byte[] toByteArray(String s) {
        int len = s.length();

        if ((len % 2) != 0) {
            throw new NumberFormatException("Invalid Hex String");
        }

        byte[] ret = new byte[len / 2];
        for (int i = 0; i < len / 2; i++) {
            ret[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
        }

        return ret;
    }


    public static String codeAddOne(String code, int len){
        while (code.length() < len) {
            code = "0" + code;
        }
        return code;
    }

//    public static String addZeroForNum(String str, int strLength) {
//        int strLen = str.length();
//        if (strLen < strLength) {
//            while (strLen < strLength) {
//                StringBuffer sb = new StringBuffer();
//                sb.append("0").append(str);//左补0
//// sb.append(str).append("0");//右补0
////                str = sb.toString();
////                strLen = str.length();
//            }
//        }
//
////　       return str;
//    }
}
