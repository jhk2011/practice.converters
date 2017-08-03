package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

import java.util.UUID;

public class UUIDConverter extends Converter<UUID> {
    @Override
    public boolean canConvert(Class c) {
        return UUID.class == c ;
    }

    @Override
    protected void writeObject(BinaryWriter writer, UUID obj, Class c, Convert convert)
    {
       writer.writeUUID(obj);
    }

    @Override
    protected UUID readObject(BinaryReader reader, Class c, Convert convert) {

        return reader.readUUID();
    }

}
