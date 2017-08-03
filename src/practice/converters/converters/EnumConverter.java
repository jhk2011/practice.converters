package practice.converters.converters;

import practice.converters.*;

public class  EnumConverter extends Converter<Enum> {

    @Override
    public boolean canConvert(Class c) {
        return c.getSuperclass()==Enum.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Enum obj, Class c, Convert convert) {
       writer.writeInt(obj.ordinal());
    }

    @Override
    protected Enum readObject(BinaryReader reader, Class c, Convert convert) {

        int value = reader.readInt();

       for (Object e:c.getEnumConstants()){
           Enum ee = (Enum)e;
           if(ee.ordinal()==value){
               return ee;
           }
       }
       throw new ConverterException("enum value does not exist");
    }
}
