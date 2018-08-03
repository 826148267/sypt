package com.gzf.sypt.util;

import java.io.*;

public class JavaSerializeUtil {
    //序列化
    public static byte[] serialize(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException("序列化失败："+e.getMessage());
        }
    }

    //反序列化
    public static Object unSerialize(byte[] bytes){
        System.out.print(bytes);
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("反序列化失败:"+e.getMessage());
        }
    }
}
