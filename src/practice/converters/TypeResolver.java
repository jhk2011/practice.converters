package practice.converters;


public interface TypeResolver {
    Class read(BinaryReader reader);

    void write(BinaryWriter writer, Class cls);
}


