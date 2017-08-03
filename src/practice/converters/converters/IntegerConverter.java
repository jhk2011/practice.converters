package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

public class IntegerConverter extends Converter<Integer> {

    @Override
    public boolean canConvert(Class c) {

        return c==Integer.class || c==int.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Integer obj, Class c, Convert convert) {
        writer.writeInt(obj);
    }

    @Override
    protected Integer readObject(BinaryReader reader, Class c, Convert convert) {
        return reader.readInt();
    }
}
