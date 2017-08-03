package practice.converters.converters;

import practice.converters.Convert;
import practice.converters.Converter;

public class BooleanConverter extends Converter<Boolean> {
    @Override
    public boolean canConvert(Class c) {
        return false;
    }

    @Override
    protected byte[] getBytes(Boolean obj, Class c, Convert convert) {
        return new byte[]{(byte)(obj==true?1:0)};
    }

    @Override
    protected Boolean getValue(byte[] bytes, Class c, Convert convert) {
        return bytes[0]==1;
    }
}

