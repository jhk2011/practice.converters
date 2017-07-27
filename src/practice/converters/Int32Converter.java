package practice.converters;

class Int32Converter extends Converter<Integer>{

    @Override
    public boolean canConvert(Class c) {

        return c==Integer.class||c==int.class;
    }

    @Override
    protected byte[] getBytes(Integer obj, Class c, Convert convert) {

        int value = obj;

        byte[] bytes = new byte[4];


        bytes[0]=(byte)(value>>>0&0xFF);
        bytes[1]=(byte)(value>>>8&0xFF);
        bytes[2]=(byte)(value>>>16&0xFF);
        bytes[3]=(byte)(value>>>24&0xFF);


        return bytes;
    }

    @Override
    protected Integer getValue(byte[] bytes, Class c, Convert convert) {

        int value = ((bytes[0]&0xFF)<<0)+(bytes[1]<<8)+(bytes[2]<<16)+(bytes[3]<<24);

        return value;
    }
/*
    @Override
    public void write(DataOutputStream writer, Integer obj, Class cls, Convert convert) {
        try {
            writer.writeInt(obj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConverterException();
        }
    }

    @Override
    public Integer read(DataInputStream reader, Class cls, Convert convert) {
        try {
            return reader.readInt();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConverterException();
        }
    }
*/
}
