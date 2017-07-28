package practice.converters;

import java.lang.reflect.Array;

public class ArrayConverter extends  Converter{
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
        int length = reader.readInt();
        Class cls =c.getComponentType();

        Object array = Array.newInstance(cls,length);

        for (int i=0;i<length;i++){
            Object value = convert.read(reader);
            Array.set(array,i,value);
        }
        return array;
    }
}
