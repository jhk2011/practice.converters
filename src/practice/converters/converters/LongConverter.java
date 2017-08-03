package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

public class LongConverter extends Converter<Long> {
    @Override
    public boolean canConvert(Class c) {

        return Long.class == c || long.class == c;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Long obj, Class c, Convert convert) {
        writer.writeLong(obj);
    }

    @Override
    protected Long readObject(BinaryReader reader, Class c, Convert convert) {
        return reader.readLong();
    }
}
