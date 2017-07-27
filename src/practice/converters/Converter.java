package practice.converters;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

abstract class Converter<T>{

    public abstract boolean canConvert(Class c);

    public void write(DataOutputStream writer, T obj, Class c, Convert convert){
        try {
            if(obj==null){
                writer.writeInt(-1);
            }else{
                byte[] bytes = getBytes(obj,c,convert);
                if(bytes==null){
                    writer.writeInt(0);
                }else{
                    writer.writeInt(bytes.length);
                    writer.write(bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected byte[] getBytes(T obj,Class c,Convert convert){
        return null;
    }

    public  T read(DataInputStream reader, Class c, Convert convert){
        try {
            int size = reader.readInt();

            if(size==-1){
                return null;
            }else{
                byte[] bytes = new byte[size];
                reader.read(bytes,0,size);
                return getValue(bytes,c,convert);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConverterException();
        }
    }

    protected T getValue(byte[] bytes,Class c,Convert convert){
        return null;
    }
}
