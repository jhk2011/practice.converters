package practice.converters;

import practice.converters.typemaps.TypeMap;
import practice.converters.typemaps.TypeNameMap;

public class TypeNameTypeResolver implements TypeResolver {


    TypeMap typeMap = new TypeMap();

    public void add(TypeNameMap map) {
        typeMap.add(map);
    }

    @Override
    public Class read(BinaryReader reader) {

        String name = reader.readStringSection();

        Class type = typeMap.getType(name);

        if (type == null) throw new ConverterException("can not get type from name:" + name);

        return type;
    }

    @Override
    public void write(BinaryWriter writer, Class cls) {

        String name = typeMap.getName(cls);

        if (name == null) throw new ConverterException("can not get name from type:" + cls.getName());

        writer.writeStringSection(name);
    }
}
