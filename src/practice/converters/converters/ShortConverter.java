package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

public class ShortConverter extends Converter<Short> {

    @Override
    public boolean canConvert(Class c) {

        return c == Short.class || c == short.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Short obj, Class c, Convert convert) {

        writer.writeShort(obj);
    }

    @Override
    protected Short readObject(BinaryReader reader, Class c, Convert convert) {
        return reader.readShort();
    }
}
