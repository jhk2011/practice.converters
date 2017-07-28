package practice.converters;

public class DoubleConverter extends Converter<Double> {
    @Override
    public boolean canConvert(Class c) {
        return Double.class == c || double.class == c;
    }

    @Override
    protected byte[] getBytes(Double obj, Class c, Convert convert) {

        long value = Double.doubleToRawLongBits(obj);

        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((value >> 8 * i) & 0xff);
        }

        return bytes;
    }

    @Override
    protected Double getValue(byte[] bytes, Class c, Convert convert) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (bytes[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }
}
