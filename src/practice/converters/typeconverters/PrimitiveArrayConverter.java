package practice.converters.typeconverters;

import java.lang.reflect.Array;


//包装类型和原始类型数组的转换
public class PrimitiveArrayConverter implements TypeConverter{

    @Override
    public boolean canConvert(Class type, Object obj) {

        if(!type.isArray()||!obj.getClass().isArray())return false;

        Class elementType1 = type.getComponentType();
        Class elementType2 = obj.getClass().getComponentType();

        return canConvertElement(elementType1,elementType2);
    }

    boolean canConvertElement(Class elementType1,Class elementType2){
        if(elementType1.isArray() && elementType2.isArray()){
            return canConvertElement(elementType1.getComponentType(),elementType2.getComponentType());
        }else if(elementType1.isArray()!=elementType2.isArray()){
            return false;
        }
        return elementType1.isPrimitive()!=elementType2.isPrimitive();
    }

    @Override
    public Object convert(Class type, Object obj) {

        System.out.println("convert array");

        Class elementType = type.getComponentType();

        int length = Array.getLength(obj);

        Object array = Array.newInstance(elementType,length);

        for (int i=0;i<length;i++){
            Object value = Array.get(obj,i);
            Array.set(array,i,value);
        }
        return array;
    }
}
