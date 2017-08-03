package practice.converters.typemaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypelessDictionary  implements TypeNameMap {

    @Override
    public boolean canGetType(String name) {

        return name.equals("dictionary");
    }

    @Override
    public boolean canGetName(Class type) {
        return type == Map.class || type == HashMap.class;
    }

    @Override
    public Class getType(String name) {
        return HashMap.class;
    }

    @Override
    public String getName(Class type) {
        return "dictionary";
    }
}
