package practice.converters;

import java.io.*;

abstract class Converter<T> {

    public abstract boolean canConvert(Class c);

    public void write(BinaryWriter writer, T obj, Class c, Convert convert) {
        if (obj == null) {
            writer.writeInt(-1);
        } else {
            byte[] bytes = getBytes(obj, c, convert);
            if (bytes == null) {
                writer.writeInt(0);
            } else {
                writer.writeInt(bytes.length);
                writer.writeBytes(bytes);
            }
        }
    }

    protected byte[] getBytes(T obj, Class c, Convert convert) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BinaryWriter writer = new BinaryWriter(out);
        writeObject(writer, obj, c, convert);
        writer.flush();
        return out.toByteArray();
    }

    protected void writeObject(BinaryWriter writer, T obj, Class c, Convert convert) {
        throw new ConverterException("not implement");
    }

    public T read(BinaryReader reader, Class c, Convert convert) {

        int size = reader.readInt();

        if (size == -1) {
            return null;
        } else {
            byte[] bytes = reader.readBytes(size);
            return getValue(bytes, c, convert);
        }
    }

    protected T getValue(byte[] bytes, Class c, Convert convert) {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        BinaryReader reader = new BinaryReader(in);
        return readObject(reader, c, convert);
    }

    protected T readObject(BinaryReader reader, Class c, Convert convert) {
        throw  new ConverterException("not implement");
    }
}
