package practice.converters;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Convert{

    List<Converter> converters = new ArrayList<Converter>();

    TypeResolver typeResolver;

    public  Convert(TypeResolver typeResolver,List<Converter> converters){

        if(typeResolver==null){
            typeResolver = new DefaultTypeResolver();
        }
        if(converters==null){
            converters = new ArrayList<Converter>();
            converters.add(new Int32Converter());
            converters.add(new StringConverter());
            converters.add(new DoubleConverter());
            converters.add(new ArrayConverter());
            converters.add(new ObjectConverter());
        }

        this.typeResolver=typeResolver;
        this.converters = converters;

    }


    public void write(BinaryWriter writer, Class cls, Object obj){
        Converter converter = getConverter(cls);
        if(converter==null){
            throw new ConverterException("不支持的类型");
        }
        typeResolver.write(writer,cls);
        converter.write(writer,obj,cls,this);
    }


    public Object read(BinaryReader reader){

        Class cls = typeResolver.read(reader);
        if(cls==null){
            throw new ConverterException("can not read class");
        }
        Converter converter = getConverter(cls);
        if(converter==null){
            throw new ConverterException("no converter");
        }
        return converter.read(reader,cls,this);
    }

    private Converter getConverter(Class cls){
        for (Converter converter: converters) {
            if(converter.canConvert(cls)){
                return converter;
            }
        }
        return null;
    }

    public void write(OutputStream out,Class cls,Object obj){
        BinaryWriter writer = new BinaryWriter(out,true);
        write(writer,cls,obj);
        writer.flush();
    }

    public byte[] getBytes(Class cls,Object obj){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        write(out,cls,obj);
        return out.toByteArray();
    }


    public Object read(InputStream in){
        BinaryReader reader = new BinaryReader(in,true);
        return read(reader);
    }

    public Object getValue(byte[] bytes){
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        return read(in);
    }

}
