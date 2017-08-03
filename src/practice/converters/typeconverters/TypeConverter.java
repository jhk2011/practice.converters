package practice.converters.typeconverters;

public interface TypeConverter {
    boolean canConvert(Class type,Object obj);
    Object convert(Class type,Object obj);
}

