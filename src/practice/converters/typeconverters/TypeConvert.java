package practice.converters.typeconverters;

import java.util.ArrayList;
import java.util.List;

public class TypeConvert {
    List<TypeConverter> converters = new ArrayList<>();

    public TypeConvert() {
        //practice.converters.add(new PrimitiveConverter());
        converters.add(new PrimitiveArrayConverter());
    }

    public Object convert(Class type, Object obj) {
        for (TypeConverter converter : converters) {
            if (converter.canConvert(type, obj)) return converter.convert(type, obj);
        }
        return obj;
    }
}
