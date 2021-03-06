package practice.converters.typemaps;

import java.util.ArrayList;
import java.util.List;

public class TypelessArrayMap implements TypeNameMap {

    @Override
    public boolean canGetType(String name) {

        return name.equals("array");
    }

    @Override
    public boolean canGetName(Class type) {

        return type.isArray();
    }

    @Override
    public Class getType(String name) {

        return Object[].class;
    }

    @Override
    public String getName(Class type) {
        return "array";
    }
}
