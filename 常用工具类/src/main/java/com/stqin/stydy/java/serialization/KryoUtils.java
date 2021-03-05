package com.stqin.stydy.java.serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import jcifs.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class KryoUtils {
    /**
     * 序列化对象为String
     *
     * @param obj
     * @return
     */
    public static <T extends Serializable> String serializationObject(T obj) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(obj.getClass(), new JavaSerializer());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeClassAndObject(output, obj);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encode(b));
    }

    /**
     * 从String反序列化对象
     *
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deserializationObject(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.register(clazz, new JavaSerializer());

        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(obj));
        Input input = new Input(bais);
        return (T) kryo.readClassAndObject(input);
    }

    /**
     * 序列化List对象为String
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T extends Serializable> String serializationList(List<T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(Base64.encode(b));
    }

    /**
     * 从Sting反序列化List对象
     *
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> List<T> deserializationList(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(ArrayList.class, serializer);

        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(obj));
        Input input = new Input(bais);
        return (List<T>) kryo.readObject(input, ArrayList.class, serializer);
    }

    /**
     * 序列化Map对象为String
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T extends Serializable> String serializationMap(Map<String, T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(Base64.encode(b));
    }

    /**
     * 从String反序列化对象为Map
     *
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Map<String, T> deserializationMap(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        MapSerializer serializer = new MapSerializer();
        serializer.setKeyClass(String.class, new JavaSerializer());
        serializer.setKeysCanBeNull(false);
        serializer.setValueClass(clazz, new JavaSerializer());
        serializer.setValuesCanBeNull(true);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashMap.class, serializer);

        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(obj));
        Input input = new Input(bais);
        return (Map<String, T>) kryo.readObject(input, HashMap.class, serializer);
    }

    /**
     * 序列化Set对象为String
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T extends Serializable> String serializationSet(Set<T> obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);
        kryo.writeObject(output, obj);
        output.flush();
        output.close();

        byte[] b = baos.toByteArray();
        try {
            baos.flush();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(Base64.encode(b));
    }

    /**
     * 从String反序列化Set对象
     *
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> Set<T> deserializationSet(String obj, Class<T> clazz) {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(true);

        CollectionSerializer serializer = new CollectionSerializer();
        serializer.setElementClass(clazz, new JavaSerializer());
        serializer.setElementsCanBeNull(false);

        kryo.register(clazz, new JavaSerializer());
        kryo.register(HashSet.class, serializer);

        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.decode(obj));
        Input input = new Input(bais);
        return (Set<T>) kryo.readObject(input, HashSet.class, serializer);
    }

}
