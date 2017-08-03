package practice.converters.typemaps;

import java.util.*;

public class TypeMap {

    List<TypeNameMap> maps = new ArrayList();

    public TypeMap() {
        maps.add(new SimpleTypeMap(int.class, "int"));
        maps.add(new SimpleTypeMap(Integer.class, "int"));
        maps.add(new SimpleTypeMap(double.class, "double"));
        maps.add(new SimpleTypeMap(Double.class, "double"));
        maps.add(new SimpleTypeMap(String.class, "string"));
        maps.add(new SimpleTypeMap(Object.class, "object"));
        maps.add(new SimpleTypeMap(UUID.class, "guid"));
        maps.add(new SimpleTypeMap(Date.class, "datetime"));

        maps.add(new SimpleTypeMap(List.class,"list"));

        maps.add(new SimpleTypeMap(HashMap.class,"dictionary"));

        maps.add(new ArrayMap(this));
        maps.add(new TypelessArrayMap());

        maps.add(new GenericTypeMap(this, ArrayList.class, "list"));
        maps.add(new GenericTypeMap(this, List.class, "list"));
        maps.add(new TypelessListMap());

        maps.add(new GenericTypeMap(this, HashMap.class,"dictionary"));
        maps.add(new GenericTypeMap(this, Map.class,"dictionary"));
        maps.add(new TypelessDictionary());

        maps.add(new TypelessArrayMap());
    }

    public void add(TypeNameMap map) {
        maps.add(map);
    }

    public Class getType(String name) {
        for (TypeNameMap map : maps) {
            if (map.canGetType((name))) {
                Class type = map.getType(name);
                System.out.println("read type:" + type.getName() + "(" + name + ")");
                return type;
            }
        }
        return null;
    }

    public String getName(Class type) {
        for (TypeNameMap map : maps) {
            if (map.canGetName((type))) {
                String name = map.getName(type);
                System.out.println("write type:" + type.getName() + "(" + name + ")");
                return name;
            }
        }
        return null;
    }
}
