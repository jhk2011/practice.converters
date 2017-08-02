package practice.converters;

import com.sun.corba.se.spi.activation.LocatorPackage.ServerLocationPerORB;
import practice.converters.typemaps.SimpleTypeMap;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    static void test(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        BinaryWriter writer = new BinaryWriter(out);

        writer.writeInt(-1234);

        writer.flush();

        byte[] bytes = out.toByteArray();

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        BinaryReader reader = new BinaryReader(in);

        int value = reader.readInt();

    }

    public static void testgereric(){
/*
            Student st=new Student();
            Class clazz=st.getClass();
            //getSuperclass()获得该类的父类
            System.out.println(clazz.getSuperclass());
            //getGenericSuperclass()获得带有泛型的父类
            //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
            Type type=clazz.getGenericSuperclass();
            System.out.println(type);
            //ParameterizedType参数化类型，即泛型
            ParameterizedType p=(ParameterizedType)type;
            //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
            Class c=(Class) p.getActualTypeArguments()[0];

            System.out.println(c);*/
    }

    public static void main(String[] args) throws IOException {

        //test();
        //testgereric();

        TypeIdTypeResolver resover = new TypeIdTypeResolver();

        resover.addPrimitive();
        resover.add(Object.class,88);
        resover.add(Person.class,100);
        resover.add(List.class,500);
        resover.add(int[].class,600);
        resover.add(Gender.class,700);
        //resover.add(int[][].class,601);
        //resover.add(Object.class,101);

        TypeNameTypeResolver typeNameResolver = new TypeNameTypeResolver();

        typeNameResolver.add(new SimpleTypeMap(Person.class,"person"));

        typeNameResolver.add(new SimpleTypeMap(Gender.class,"gender"));


        Convert convert = new Convert(typeNameResolver, null);

        int value = 1234;

        //byte[] bytes1 = convert.getBytes(Integer.class, value);

        //Integer value2 = (Integer) convert.getValue(bytes1);

        //if(true)return;

        Person p = new Person();


        p.setGrade(3.14);
        p.setName("test");
        p.setId(1);
        p.setCourses(new Object[]{"语文","英语",123});
        p.setMatrix(new int[][]{
                {1,2,3},
                {4,5,6}
        });

        List<String> list = new ArrayList<>();

        list.add("list-item1");

        p.setList(list);
        p.setGuid(UUID.randomUUID());

        Object obj = null;

        obj = p;

        //obj = list;

        FileOutputStream out = new FileOutputStream("f:/personj.dat");

        convert.write(out,obj);

        out.close();

        System.out.println("---");

        FileInputStream in = new FileInputStream("f:/person.dat");

        Object obj2 = convert.read(in);

        System.out.println(obj2.getClass().getName());

       System.out.println(((Person)obj2).getGuid());

        in.close();
    }
}


