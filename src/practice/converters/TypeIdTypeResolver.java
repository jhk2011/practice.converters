package practice.converters;

import java.awt.geom.Arc2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class TypeIdTypeResolver implements TypeResolver {


    HashMap<Integer, Class> idToClass = new HashMap<Integer, Class>();
    HashMap<Class, Integer> classToId = new HashMap<Class, Integer>();
    HashMap<Class,Class> privitives = new HashMap<>();

    public TypeIdTypeResolver(){
        privitives.put(byte.class,Byte.class);
        privitives.put(short.class,Short.class);
        privitives.put(int.class,Integer.class);
        privitives.put(long.class,Long.class);
        privitives.put(float.class,Float.class);
        privitives.put(double.class,Double.class);
        privitives.put(char.class,Character.class);
        privitives.put(boolean.class,Boolean.class);
    }

    public void addPrimitive() {
        add(Byte.class, 1);
        add(Short.class, 2);
        add(Integer.class, 3);
        add(Long.class, 4);
        add(Double.class, 5);
        add(Float.class, 6);
        add(String.class, 7);
        add(UUID.class, 8);
        add(String[].class,200);
    }

    public void add(Class type, int id) {

        if (classToId.containsKey(type)) {
            throw new ConverterException("此类型已有对应id");
        }

        if (idToClass.containsKey(id)) {
            throw new ConverterException("此id已有对应类型");
        }
        classToId.put(type, id);
        idToClass.put(id, type);
    }

    public Class getClass(int id) {
        Class type;
        if (idToClass.containsKey(id)) {
            return idToClass.get(id);
        }
        return null;
    }

    public Integer getId(Class type) {

        if(type.isPrimitive()){
            type= privitives.get(type);
        }

        if (classToId.containsKey(type)) {
            return classToId.get(type);
        }
        return null;
    }

    public Class read(BinaryReader reader) {
        int id = reader.readInt();
        return getClass(id);
    }

    public void write(BinaryWriter writer, Class type) {


        Integer typeid = getId(type);

        if (typeid == null) throw new ConverterException("can not find id of class");
        writer.writeInt(typeid);
    }
}
