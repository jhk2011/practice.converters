package practice.converters.converters;

import practice.converters.Convert;
import practice.converters.Converter;
import practice.converters.ConverterException;

import java.io.UnsupportedEncodingException;

public class StringConverter extends Converter<String> {
    @Override
    public boolean canConvert(Class c) {

        return c == String.class;
    }

    @Override
    protected byte[] getBytes(String obj, Class c, Convert convert) {
        try {
            return obj.getBytes("utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ConverterException("encoding dost not support", e);
        }

    }

    @Override
    protected String getValue(byte[] bytes, Class c, Convert convert) {
        try {
            return new String(bytes, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new ConverterException("encoding dost not support", e);
        }
    }
}
