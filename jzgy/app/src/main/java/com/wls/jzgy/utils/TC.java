package com.wls.jzgy.utils;


/**
 * @Author Sunne
 * @Date 2019/3/2715:03
 * @Param wx
 **/
public class TC {


    //gcc-add
    //字符串转成数组取出来
    public String test(String str, int k){

        int m=str.length()/2;
        if(m*2<str.length()){
            m++;
        }
        String[] strs=new String[m];
//        System.out.println(strs);
        int j=0;
        for(int i=0;i<str.length();i++){
            if(i%2==0){//每隔两个
                strs[j]=""+str.charAt(i);
            }else{
                strs[j]=strs[j]+str.charAt(i);//将字符加上两个空格
                j++;
            }
        }


        return strs[k];
    }

    //2个字符串拼接
    public String testStringBuilder(String str1, String str2){

        StringBuilder sb = new StringBuilder(str1);
//        for (int i=0; i<str1.length(); i++){
            sb.append(str2);
//        }

        return sb.toString();
    }

    //3个字符串拼接
    public String testStringBuilder0(String str1, String str2, String str3){

        StringBuilder sb = new StringBuilder(str1);
//        for (int i=0; i<str1.length(); i++){
        sb.append(str2);
        sb.append(str3);
//        }

        return sb.toString();
    }

    //4个字符串拼接
    public String testStringBuilder1(String str1, String str2, String str3, String str4){

        StringBuilder sb = new StringBuilder(str1);

        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        return sb.toString();
    }

    //6个字符串拼接
    public String testStringBuilder2(String str1, String str2, String str3, String str4, String str5, String str6){

        StringBuilder sb = new StringBuilder(str1);

        sb.append(str2);
        sb.append(str3);
        sb.append(str4);
        sb.append(str5);
        sb.append(str6);
        return sb.toString();
    }


    public static byte[] toByteArray(String hexString) {
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() / 2];
        int k = 0;
        for (int i = 0; i < byteArray.length; i++) {// 因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
            byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
            byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
            byteArray[i] = (byte) (high << 4 | low);
            k += 2;
        }
        return byteArray;
    }

    public static String bytes2Hex(byte[] src) {
        if (src == null || src.length <= 0) {
            return null;
        }

        char[] res = new char[src.length * 2]; // 每个byte对应两个字符
        final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        for (int i = 0, j = 0; i < src.length; i++) {
            res[j++] = hexDigits[src[i] >> 4 & 0x0f]; // 先存byte的高4位
            res[j++] = hexDigits[src[i] & 0x0f]; // 再存byte的低4位
        }

        return new String(res);
    }

    public static String bin2hex(String bin) {
        char[] digital = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0x0f;
            sb.append(digital[bit]);
        }
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

    //将16进制串转成二进制数组，用于解密(方式1)
    public static byte[] parseHexStr2bytes(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < result.length; i++) {
            int high = Integer.parseInt(hexStr.substring(2 * i, 2 * i + 1), 16);
            int low = Integer.parseInt(hexStr.substring(2 * i + 1, 2 * i + 2), 16);
            result[i] = (byte)(high * 16 + low);
        }

        return result;
    }

    //gcc_end

}

//    public byte[] HexStr2Byte(String hexStr) {
//        if (hexStr.length() < 1)
//            return null;
//        byte[] result = new byte[hexStr.length() / 2];
//        for (int i = 0; i < hexStr.length() / 2; i++) {
//            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
//            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
//            result[i] = (byte) ((high * 16 + low) & 0xFF);
//        }
//        return result;
//    }
//
//
//    public void main(String[] args) {
//
//        String str = "0001";
//        HexStr2Byte(str);
//        System.out.println("66666666");
//
//    }
//}





