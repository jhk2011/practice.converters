package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

public class DoubleConverter extends Converter<Double> {
    @Override
    public boolean canConvert(Class c) {

        return Double.class == c || double.class == c;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Double obj, Class c, Convert convert) {
        writer.writeDouble(obj);
    }

    @Override
    protected Double readObject(BinaryReader reader, Class c, Convert convert) {
        return reader.readDouble();
    }

}
