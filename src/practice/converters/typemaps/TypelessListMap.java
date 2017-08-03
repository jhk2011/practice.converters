package practice.converters.typemaps;

import java.util.ArrayList;
import java.util.List;

public class TypelessListMap implements TypeNameMap {

    @Override
    public boolean canGetType(String name) {

        return name.equals("list");
    }

    @Override
    public boolean canGetName(Class type) {
        return type == List.class || type == ArrayList.class;
    }

    @Override
    public Class getType(String name) {

        return ArrayList.class;
    }

    @Override
    public String getName(Class type) {

        return "list";
    }
}
