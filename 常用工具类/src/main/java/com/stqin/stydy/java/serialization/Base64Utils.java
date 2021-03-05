package com.stqin.stydy.java.serialization;

import org.apache.commons.codec.binary.Base64;

import java.io.*;

public class Base64Utils {
    /**
     * str 编码为base64
     *
     * @param s s
     * @return String base64
     */
    public static String getBase64(String s) {
        if (s == null) {
            return null;
        }
        return Base64.encodeBase64String(s.getBytes());
    }

    /**
     * byte[] 编码为base64
     *
     * @param ba ba
     * @return String base64
     */
    public static String getBase64(byte[] ba) {
        if (ba == null) {
            return null;
        }
        return Base64.encodeBase64String(ba);
    }

    /**
     * base64 解码为 byte[]
     *
     * @param base64 base64
     * @return byte[]
     * @throws IOException
     */
    public static byte[] base64ToByteArray(String base64) {
        if (base64 == null) {
            return new byte[0];
        }

        return Base64.decodeBase64(base64);
    }

    /**
     * 将base64 字符串反序列化为指定的类
     *
     * @param base64 序列化后用Base64编码的字符串
     * @return 反序列化对象，若输入为null，输出null
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deserialize(String base64) {
        if (base64 == null) {
            return null;
        }
        Object o = null;
        byte[] ba = Base64Utils.base64ToByteArray(base64);
        try (ByteArrayInputStream bai = new ByteArrayInputStream(ba);
             ObjectInputStream oi = new ObjectInputStream(bai)) {
            o = oi.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return o == null ? null : (T) o;
    }

    /**
     * 将对象序列化为Base64 字符串
     *
     * @param obj 实现了可序列化接口的对象
     * @return 对象序列化后的Base64，若输入为null，输出null
     */
    public static String serialize(Serializable obj) {
        if (obj == null) {
            return null;
        }
        byte[] ba = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            ba = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ba == null ? null : Base64Utils.getBase64(ba);
    }
}
