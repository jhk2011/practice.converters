package practice.converters;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {


        Class<int[]> cls=int[].class;

        //cls.getComponentType()

        System.out.println(cls);

        int value = 1234;

        TypeIdTypeResolver resover = new TypeIdTypeResolver();

        resover.addPrimitive();
        resover.add(Person.class,100);

        Convert convert = new Convert(resover, null);


        byte[] bytes1 = convert.getBytes(Integer.class, value);

        Integer value2 = (Integer) convert.getValue(bytes1);


        Person p = new Person();

        p.setGrade(3.14);
        p.setName("test");
        p.setId(1);
        p.setCourses(new String[]{"语文","英语"});


        FileOutputStream out = new FileOutputStream("f:/personj.dat");

        convert.write(out,Person.class,p);

        out.close();

        FileInputStream in = new FileInputStream("f:/person.dat");


        Person p2 = (Person) convert.read(in);

        in.close();

        System.out.println(p2.getId());
        System.out.println(p2.getGrade());
        System.out.println(p2.getName());
    }
}


