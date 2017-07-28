package practice.converters;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class BinaryReader implements  AutoCloseable {

    InputStream in;
    boolean leaveOpen;

    public BinaryReader(InputStream in,boolean leaveOpen) {
        this.in = in;
        this.leaveOpen=leaveOpen;
    }

    public BinaryReader(InputStream in) {
        this(in,false);
    }

    public int readShort() {
        byte[] bytes = readBytes(2);

        int value = 0;

        for (int i = 0; i < 2; i++) {
            bytes[i] = (byte) (value << 8 * i & 0xFF);
        }

        return value;
    }

    public int readInt() {
        byte[] bytes = readBytes(4);

        int value = 0;

        for (int i = 0; i < 4; i++) {
            value |= bytes[i] << 8 * i & 0xFF;
        }

        return value;
    }

    public int readLong() {
        byte[] bytes = readBytes(8);

        int value = 0;

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (value << 8 * i & 0xFF);
        }

        return value;
    }

    public byte readByte() {
        byte[] bytes = readBytes(1);
        return bytes[0];
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public byte[] readBytes(int n) {
        byte[] bytes = new byte[n];
        try {
            in.read(bytes, 0, n);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConverterException("read error", e);
        }
        return bytes;
    }

    public UUID readUUID() {
        byte[] bytes = readBytes(16);
        return new UUID(readLong(), readLong());
    }

    public byte[] readBytesSection(){
        int n = readInt();
        if(n==-1){
            return null;
        }else{
            return readBytes(n);
        }
    }


    public String readStringSection(){
        byte[] bytes = readBytesSection();
        if(bytes==null)return null;

        try {
            return new String(bytes,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw  new ConverterException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if(!leaveOpen){
            in.close();
        }
    }
}
