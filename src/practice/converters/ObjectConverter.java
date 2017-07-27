package practice.converters;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

class  ObjectConverter extends  Converter<Object>{

    @Override
    public boolean canConvert(Class c) {

        System.out.println(c.getName());

        if(c.isPrimitive())return false;
        if(c.isAnnotation())return false;
        if(c.isInterface())return false;
        if(Modifier.isAbstract(c.getModifiers()))return false;

        Constructor[] constructors = c.getConstructors();

        if(constructors.length==0)return true;

        for (Constructor constructor:
                constructors) {
            if(constructor.getParameterCount()==0){
                return true;
            }
        }
        return false;
    }

    @Override
    public void write(DataOutputStream writer, Object obj, Class c, Convert convert)  {

        ArrayList<Method> getters = new ArrayList<Method>();

        for(Method method:c.getMethods()){
            String name = method.getName();
            if(method.getDeclaringClass()==c){
            if(name.startsWith("get")&& method.getParameterCount()==0){
                getters.add(method);
            }}
        }

        try {
            writer.writeInt(getters.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Method method:getters) {

            String name = method.getName();

            Class cls = method.getReturnType();

            System.out.println(cls.getName());

            name = name.substring(3, name.length());

            convert.write(writer, String.class, name);

            try {

                Object value = method.invoke(obj);


                convert.write(writer, cls, value);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new ConverterException();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new ConverterException();
            }
        }
    }

    @Override
    public Object read(DataInputStream reader, Class c, Convert convert) {

        try {
            Object obj = c.newInstance();

            ArrayList<Method> setters = new ArrayList<Method>();

            for(Method method:c.getMethods()){

                String name =method.getName();

                if(name.startsWith("set")&& method.getParameterCount()==1){
                    setters.add(method);
                }
            }

            try {
                int count = reader.readInt();


                for (int i=0;i<count;i++) {

                    String name = (String) convert.read(reader, String.class);

                    for (Method method:setters){

                        if(method.getName().equals("set"+name)){
                            Class cls = method.getParameterTypes()[0];
                            Object value = convert.read(reader,cls);

                            try {
                                method.invoke(obj,value);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

                return obj;

            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
