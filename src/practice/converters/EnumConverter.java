package practice.converters;

class  EnumConverter extends Converter<Enum>{

    @Override
    public boolean canConvert(Class c) {
        return Enum.class==c;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Enum obj, Class c, Convert convert) {
       writer.writeInt(obj.ordinal());
    }

    @Override
    protected Enum readObject(BinaryReader reader, Class c, Convert convert) {
        int value = reader.readInt();

        return super.readObject(reader, c, convert);
    }
}
