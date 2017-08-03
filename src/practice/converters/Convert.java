package practice.converters;

import practice.converters.converters.*;
import practice.converters.typeconverters.TypeConvert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Convert {

    List<Converter> converters = new ArrayList<Converter>();

    public TypeResolver getTypeResolver() {
        return typeResolver;
    }

    TypeResolver typeResolver;

    public TypeConvert getTypeConvert() {
        return typeConvert;
    }

    TypeConvert typeConvert;

    public Convert(TypeResolver typeResolver, List<Converter> converters) {

        if (typeResolver == null) {
            typeResolver = new DefaultTypeResolver();
        }

        typeConvert = new TypeConvert();

        if (converters == null) {

            converters = new ArrayList<Converter>();

            converters.add(new BooleanConverter());
            converters.add(new IntegerConverter());
            converters.add(new ShortConverter());
            converters.add(new LongConverter());

            converters.add(new StringConverter());

            converters.add(new DoubleConverter());
            converters.add(new FloatConverter());

            //practice.converters.add(new EnumConverter());

            converters.add(new UUIDConverter());
            converters.add(new DateConverter());

            converters.add(new ArrayConverter());
            converters.add(new ListConverter());
            converters.add(new HashMapConverter());

            converters.add(new ObjectConverter());
        }

        this.typeResolver = typeResolver;
        this.converters = converters;

    }


    public void write(BinaryWriter writer, Class cls, Object obj) {
        Converter converter = getConverter(cls);
        if (converter == null) {
            throw new ConverterException("不支持的类型:" + cls.getTypeName());
        }
        typeResolver.write(writer, cls);

        System.out.println("write object:" + converter.getClass().getName());

        converter.write(writer, obj, cls, this);

    }


    public Object read(BinaryReader reader) {

        Class cls = typeResolver.read(reader);

        if (cls == null) {
            throw new ConverterException("can not read class");
        }

        Converter converter = getConverter(cls);
        if (converter == null) {
            throw new ConverterException("no converter");
        }

        System.out.println("read object:" + converter.getClass().getName());

        Object obj = converter.read(reader, cls, this);

        return obj;
    }

    private Converter getConverter(Class cls) {
        for (Converter converter : converters) {
            if (converter.canConvert(cls)) {
                return converter;
            }
        }
        return null;
    }

    public void write(OutputStream out, Class cls, Object obj) {
        BinaryWriter writer = new BinaryWriter(out, true);
        write(writer, cls, obj);
        writer.flush();
    }

    public void write(OutputStream out, Object obj) {
        if (obj == null) throw new IllegalArgumentException("obj");
        write(out, obj.getClass(), obj);
    }

    public byte[] getBytes(Class cls, Object obj) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        write(out, cls, obj);
        return out.toByteArray();
    }


    public Object read(InputStream in) {
        BinaryReader reader = new BinaryReader(in, true);
        return read(reader);
    }

    public Object getValue(byte[] bytes) {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return read(in);
    }

}
