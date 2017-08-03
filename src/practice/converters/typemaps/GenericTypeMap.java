package practice.converters.typemaps;

import practice.converters.ConverterException;

public class GenericTypeMap implements TypeNameMap {

    TypeMap typeMap;

    Class genericType;
    String genericName;

    public GenericTypeMap(TypeMap typeMap, Class genericType, String genericName) {
        this.typeMap = typeMap;
        this.genericType = genericType;
        this.genericName = genericName;
    }


    @Override
    public boolean canGetType(String name) {

        if (!name.endsWith(">")) return false;

        int index = name.indexOf("<");

        if (index < 0) throw new ConverterException("name is not generic type");

        String genericName = name.substring(0, index);

        String[] genericArgumentNames = name.substring(index + 1, name.length() - 1).split(",");

        return canGetGenericType(genericName, genericArgumentNames);
    }

    @Override
    public boolean canGetName(Class type) {

        Class genericType = getGenericType(type);
        Class[] genericArgumentsTypes = getGenericArgumentTypes(type);
        return canGetGenericName(type, genericArgumentsTypes);
    }

    @Override
    public Class getType(String name) {

        int index = name.indexOf("<");
        String genericName = name.substring(0, index);
        String[] genericArgumentNames = name.substring(index + 1, name.length() - 1).split(",");

        return getGenericType(genericName, genericArgumentNames);
    }

    @Override
    public String getName(Class type) {
        Class genericType = getGenericType(type);
        Class[] genericArgumentsTypes = getGenericArgumentTypes(type);
        return getGenericName(type, genericArgumentsTypes);
    }

    protected Class getGenericType(Class type) {
        return genericType;
    }

    protected Class[] getGenericArgumentTypes(Class type) {
        return null;
    }

    protected boolean canGetGenericType(String genericName, String[] genericArgumentNames) {
        return this.genericName.equals(genericName);
    }

    protected Class getGenericType(String genericName, String[] genericArgumentNames) {
        return genericType;
    }

    protected boolean canGetGenericName(Class genericType, Class[] genericArgumentsTypes) {
        return this.genericType == genericType;
    }

    protected String getGenericName(Class type, Class[] genericArgumentsTypes) {

        if (genericArgumentsTypes == null || genericArgumentsTypes.length == 0) {
            return genericName;
        }

        String[] genericArgumentNames = new String[genericArgumentsTypes.length];

        for (int i = 0; i < genericArgumentsTypes.length; i++) {
            String genericArgumentName = typeMap.getName(genericArgumentsTypes[i]);
            if (genericArgumentName == null) {
                throw new ConverterException("can not get name of type:" +
                        genericArgumentsTypes[i].getName());
            }
            genericArgumentNames[i] = genericArgumentName;
        }

        return String.format("%s<%s>", genericName, String.join(",", genericArgumentNames));
    }

}
