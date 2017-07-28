package practice.converters;

import java.util.ArrayList;
import java.util.List;

public class ListConverter extends  Converter<List> {
    @Override
    public boolean canConvert(Class c) {
        return c==List.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, List obj, Class c, Convert convert) {
        int size = obj.size();
        writer.writeInt(size);
        for (int i = 0; i < size; i++) {
            Object item = obj.get(i);
            convert.write(writer,item==null?Object.class :item.getClass(),item);
        }
    }

    @Override
    protected List readObject(BinaryReader reader, Class c, Convert convert) {
        int size = reader.readInt();
        List obj = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            Object item = convert.read(reader);
            obj.set(i,item);
        }
        return obj;
    }
}


