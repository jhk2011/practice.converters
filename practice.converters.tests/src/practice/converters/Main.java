package practice.converters;

import practice.converters.typemaps.*;

import java.io.*;
import java.util.*;

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


        Date date =new Date(System.currentTimeMillis());

        Calendar.getInstance().getTimeInMillis();

        System.out.println(date);

        Calendar calendar = Calendar.getInstance();

        date= calendar.getTime();


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

        p.setDate(new Date());
        p.setGuid(UUID.randomUUID());

        p.setIntegers(new Integer[]{1,2,3});

        Object obj = null;

        obj = p;

        //obj = list;

        FileOutputStream out = new FileOutputStream("f:/personj.dat");

        convert.write(out,obj);

        out.close();

        System.out.println("---");

        FileInputStream in = new FileInputStream("f:/personj.dat");

        Object obj2 = convert.read(in);

        System.out.println(obj2.getClass().getName());

        Person person2 = (Person)obj2;

        System.out.println(person2.getGuid());
        System.out.println(person2.getDate());

        in.close();

        Integer i = null;

        int ii=i;

        System.out.println(ii);
    }
}


