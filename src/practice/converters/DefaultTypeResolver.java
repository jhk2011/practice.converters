package practice.converters;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class DefaultTypeResolver implements TypeResolver {

    @Override
    public Class read(DataInputStream reader) {

        try {
            int size = reader.readInt();

            byte[] bytes = new byte[size];

            reader.read(bytes);

            String name = new String(bytes,"utf8");

            try {
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw  new ConverterException("read type error");
    }

    @Override
    public void write(DataOutputStream writer, Class cls) {
        String name = cls.getName();
        try {
            byte[] bytes=name.getBytes("utf8");

            writer.writeInt(bytes.length);
            writer.write(bytes);

            return;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        throw new ConverterException("write type error");
    }
}
