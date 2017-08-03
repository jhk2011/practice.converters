package practice.converters.typemaps;

public interface TypeNameMap {
    boolean canGetType(String name);
    boolean canGetName(Class type);
    Class getType(String name);
    String getName(Class type);
}


