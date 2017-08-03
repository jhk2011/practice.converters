package practice.converters.typeconverters;

import practice.converters.ConverterException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PrimitiveConverter implements TypeConverter{

    @Override
    public boolean canConvert(Class type, Object obj) {
        return !type.isPrimitive() && obj.getClass().isPrimitive();
    }

    @Override
    public Object convert(Class type, Object obj) {

       Constructor[] constructors = type.getConstructors();

       for(Constructor constructor:constructors){
           if(constructor.getParameterCount()==1&&constructor.getParameterTypes()[0]==obj){
               try {
                   return constructor.newInstance(obj);
               } catch (InstantiationException e) {
                   e.printStackTrace();
                   throw new ConverterException("can not convert obj");
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
                   throw new ConverterException("can not convert obj");
               } catch (InvocationTargetException e) {
                   e.printStackTrace();
                   throw new ConverterException("can not convert obj");
               }
           }
       }

        throw new ConverterException("can not create obj");
    }
}
