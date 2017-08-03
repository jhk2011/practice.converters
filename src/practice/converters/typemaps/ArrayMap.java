package practice.converters.typemaps;

import practice.converters.ConverterException;

import java.lang.reflect.Array;

class ArrayMap implements TypeNameMap {

    TypeMap typeMap;

    public ArrayMap(TypeMap typeMap) {
        this.typeMap = typeMap;
    }

    @Override
    public boolean canGetType(String name) {
        return name.endsWith("[]");
    }

    @Override
    public boolean canGetName(Class type) {
        return type.isArray();
    }

    @Override
    public Class getType(String name) {
        String elementName = name.substring(0, name.length() - 2);
        Class elementType = typeMap.getType(elementName);
        if (elementType == null) {
            throw new ConverterException("can not get type of:" + elementName);
        }
        return Array.newInstance(elementType, 0).getClass();
    }

    @Override
    public String getName(Class type) {
        Class elementType = type.getComponentType();
        String elementName = typeMap.getName(elementType);
        if (elementName == null) {
            throw new ConverterException("can not get name of type:" + elementType.getName());
        }
        return elementName + "[]";
    }
}
