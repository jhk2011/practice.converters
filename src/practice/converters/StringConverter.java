package practice.converters;

import java.io.UnsupportedEncodingException;

class StringConverter extends  Converter<String>{
    @Override
    public boolean canConvert(Class c) {
        return c==String.class;
    }

    @Override
    protected byte[] getBytes(String obj, Class c, Convert convert) {
        try {
            return obj.getBytes("utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new ConverterException();
    }

    @Override
    protected String getValue(byte[] bytes, Class c, Convert convert) {
        try {
            return new String(bytes,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        throw new ConverterException();
    }
}
