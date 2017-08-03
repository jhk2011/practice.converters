package practice.converters.converters;

import practice.converters.BinaryWriter;
import practice.converters.Converter;
import practice.converters.BinaryReader;
import practice.converters.Convert;

import java.lang.reflect.Array;

public class ArrayConverter extends Converter {

    @Override
    public boolean canConvert(Class c) {
        return c.isArray();
    }

    @Override
    protected void writeObject(BinaryWriter writer, Object obj, Class c, Convert convert) {

       int length= Array.getLength(obj);

       Class cls = c.getComponentType();

       writer.writeInt(length);

       for (int i=0;i<length;i++){
           Object value = Array.get(obj,i);
           convert.write(writer,value == null? cls:value.getClass(),value);
       }

    }

    @Override
    protected Object readObject(BinaryReader reader, Class c, Convert convert) {

        Class cls = c.getComponentType();

        int length = reader.readInt();

        Object array = Array.newInstance(cls,length);

        for (int i=0;i<length;i++){
            Object value = convert.read(reader);
            Array.set(array,i,value);
        }

        return array;
    }
}
