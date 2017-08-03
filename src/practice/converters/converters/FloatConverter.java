package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

public class FloatConverter extends Converter<Float> {

    @Override
    public boolean canConvert(Class c) {

        return c==Float.class || c==float.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Float obj, Class c, Convert convert) {

        writer.writeFloat(obj);
    }

    @Override
    protected Float readObject(BinaryReader reader, Class c, Convert convert) {
        return reader.readFloat();
    }
}
