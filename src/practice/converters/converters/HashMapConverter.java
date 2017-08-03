package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

import java.util.*;

public class HashMapConverter extends Converter<HashMap> {

    @Override
    public boolean canConvert(Class c) {
        return c == Map.class || c == HashMap.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, HashMap obj, Class c, Convert convert) {

        int size = obj.size();

        writer.writeInt(size);

        for (Object key:obj.keySet()){
            Object value = obj.get(key);

            convert.write(writer, key == null ? Object.class : key.getClass(), key);
            convert.write(writer, value == null ? Object.class : value.getClass(), value);
        }
    }

    @Override
    protected HashMap readObject(BinaryReader reader, Class c, Convert convert) {

        int size = reader.readInt();

        HashMap map = new HashMap(size);

        for (int i = 0; i < size; i++) {
            Object key = convert.read(reader);
            Object value = convert.read(reader);
            map.put(key,value);
        }
        return map;
    }
}


