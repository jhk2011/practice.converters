package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

public class ByteConverter extends Converter<Byte> {

    @Override
    public boolean canConvert(Class c) {

        return c==Byte.class || c==byte.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Byte obj, Class c, Convert convert) {
        writer.writeByte(obj);
    }

    @Override
    protected Byte readObject(BinaryReader reader, Class c, Convert convert)
    {
        return reader.readByte();
    }
}
