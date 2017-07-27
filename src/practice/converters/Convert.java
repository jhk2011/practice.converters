package practice.converters;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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
            converters.add(new ObjectConverter());
        }

        this.typeResolver=typeResolver;
        this.converters = converters;

    }


    public void write(DataOutputStream writer, Class cls, Object obj){
        Converter converter = getConverter(cls);
        if(converter==null){
            throw new ConverterException("不支持的类型");
        }
        converter.write(writer,obj,cls,this);
    }

    public Object read(DataInputStream reader, Class cls){
        Converter converter = getConverter(cls);
        if(converter==null){
            throw new ConverterException();
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

    public byte[] getBytes(Class cls,Object obj){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream writer = new DataOutputStream(out);
        write(writer,cls,obj);
        return out.toByteArray();
    }

    public Object getValue(Class cls,byte[] bytes){
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        DataInputStream reader = new DataInputStream(in);
        return read(reader,cls);
    }

}
