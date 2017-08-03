package practice.converters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DefaultTypeResolver implements TypeResolver {

    @Override
    public Class read(BinaryReader reader) {

        try {
            int size = reader.readInt();

            byte[] bytes = reader.readBytes(size);

            String name = new String(bytes, "utf8");

            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new ConverterException("read type error");
    }

    @Override
    public void write(BinaryWriter writer, Class cls) {
        String name = cls.getName();
        try {
            byte[] bytes = name.getBytes("utf8");

            writer.writeInt(bytes.length);
            writer.writeBytes(bytes);

            return;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new ConverterException("write type error");
    }
}
