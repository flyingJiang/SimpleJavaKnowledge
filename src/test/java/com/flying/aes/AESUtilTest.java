package com.flying.aes;

import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @program: SimpleJavaKnowledge
 * @description:
 * @author: jiangjianfei
 * @create: 2021-03-15 19:04
 **/
public class AESUtilTest {

    private static String mapJsonInput;

    @BeforeClass
    public static void init() {

        List<String> list = new LinkedList<>();
        list.add("P121782871728");
        list.add("P121782871728788");
        Map<String, Object> map = new HashMap<>(1);
        map.put("passId", list);
        map.put("code", "35020473786628X");

        Gson gson = new Gson();
        mapJsonInput = gson.toJson(map);
    }

    @Test
    public void testCase() {

        try {
            String ss = Base64.encodeBase64String(mapJsonInput.getBytes());

            System.out.println(ss);
            long l1 = System.currentTimeMillis();
            String sec = AESUtil.encrypt(ss, AESUtil.sKey, AESUtil.ivParameter);
            long l2 = System.currentTimeMillis();
            System.out.println("加密时间：" + (l2 - l1));
            System.out.println(sec);
            long l3 = System.currentTimeMillis();
            String decrypt = AESUtil.decrypt(sec, AESUtil.sKey, AESUtil.ivParameter);
            long l4 = System.currentTimeMillis();
            System.out.println("解密时间：" + (l4 - l3));
            System.out.println(decrypt);
            Map<String, Object> mapJsonOutput = getOutputByGson(decrypt);
            System.out.println("解密后：" + new String(Base64.decodeBase64(decrypt), "UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getOutputByGson(String decrypt) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>(2);
        map = gson.fromJson(new String(Base64.decodeBase64(decrypt)), map.getClass());
        List<String> passId = (List<String>) map.get("passId");
        System.out.println("map的值为:" + passId);
        return map;
    }
}
