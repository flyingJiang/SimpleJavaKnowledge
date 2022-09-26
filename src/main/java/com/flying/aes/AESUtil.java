package com.flying.aes;

import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AESUtil {
    /**
     * 加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    public static String sKey = "ABCDABCDABCDABCD";
    public static String ivParameter = "123456";


    // 加密
    public static String encrypt(String sSrc, String sKey, String ivParameter) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        // 此处使用BASE64做转码。
        return new BASE64Encoder().encode(encrypted);
    }

    // 解密
    public static String decrypt(String sSrc, String sKey, String ivParameter) {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            // 先用base64解密
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        try {

            List<String> list = new LinkedList<>();
            list.add("P121782871728");
            list.add("P121782871728788");
            Map<String, Object> map = new HashMap<>(2);
            map.put("passId", list);
            map.put("code", "35020473786628X");

            Gson gson = new Gson();
            String mapJson =  gson.toJson(map);



            String ss = Base64.encodeBase64String(mapJson.getBytes());


            System.out.println(ss);
            long l1 = System.currentTimeMillis();
            String sec = encrypt(ss, sKey, ivParameter);
            long l2 = System.currentTimeMillis();
            System.out.println("加密时间：" + (l2 - l1));
            System.out.println(sec);
            long l3 = System.currentTimeMillis();
            String decrypt = decrypt(sec, sKey, ivParameter);
            long l4 = System.currentTimeMillis();
            System.out.println("解密时间：" + (l4 - l3));
            System.out.println(decrypt);
            System.out.println("解密后：" + new String(Base64.decodeBase64(decrypt), "UTF-8"));

            gson = new Gson();
            Map<String, Object> map2 = new HashMap<>(2);
            map2 = gson.fromJson(new String(Base64.decodeBase64(decrypt)), map2.getClass());
            List<String> passId = (List<String>) map2.get("passId");
            System.out.println("map2的值为:" + passId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提供的接口输入参数为：统一社会信用代码/注册证号/身份证号
     * 35020473786628X
     */
}
