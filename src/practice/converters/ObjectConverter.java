package practice.converters;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

class ObjectConverter extends Converter<Object> {

    @Override
    public boolean canConvert(Class c) {

        System.out.println(c.getName());

        if (c.isPrimitive()) return false;
        if (c.isAnnotation()) return false;
        if (c.isInterface()) return false;
        if (Modifier.isAbstract(c.getModifiers())) return false;

        Constructor[] constructors = c.getConstructors();

        if (constructors.length == 0) return true;

        for (Constructor constructor :
                constructors) {
            if (constructor.getParameterCount() == 0) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void writeObject(BinaryWriter writer, Object obj, Class c, Convert convert) {

        ArrayList<Method> getters = new ArrayList<Method>();

        for (Method method : c.getMethods()) {
            String name = method.getName();
            if (method.getDeclaringClass() == c) {
                if (name.startsWith("get") && method.getParameterCount() == 0) {
                    getters.add(method);
                }
            }
        }

        writer.writeInt(getters.size());

        for (Method method : getters) {

            String name = method.getName();

            Class cls = method.getReturnType();

            System.out.println(cls.getName());

            name = name.substring(3, name.length());

            // convert.write(writer, String.class, name);

            writer.writeStringSection(name);

            Object value = getValue(obj, method);


            convert.write(writer, cls, value);
        }
    }


    private Object getValue(Object obj, Method method) {
        try {

            Object value = method.invoke(obj);

            return value;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ConverterException("can not get property", e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new ConverterException("can not get property", e);
        }
    }

    @Override
    public Object readObject(BinaryReader reader, Class c, Convert convert) {

        Object obj = createObject(c);

        ArrayList<Method> setters = new ArrayList<Method>();

        for (Method method : c.getMethods()) {

            String name = method.getName();

            if (name.startsWith("set") && method.getParameterCount() == 1) {
                setters.add(method);
            }
        }

        int count = reader.readInt();


        for (int i = 0; i < count; i++) {

            String name = reader.readStringSection();
            Object value = convert.read(reader);

            for (Method method : setters) {

                if (method.getName().equals("set" + name)) {
                    setValue(method, obj, value);
                }
            }

        }

        return obj;

    }

    private void setValue(Method method, Object obj, Object value) {
        try {
            method.invoke(obj, value);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new ConverterException("can not set property", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ConverterException("can not set property", e);
        }
    }

    private Object createObject(Class cls) {
        try {
            Object obj = cls.newInstance();
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new ConverterException("can not set property", e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new ConverterException("can not set property", e);
        }
    }
}
