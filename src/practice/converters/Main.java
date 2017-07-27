package practice.converters;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	   Class cls= String.class;


	   int value = 1234;



	   Convert convert = new Convert(null,null);


	  byte[] bytes= convert.getBytes(Integer.class,value);

	  Integer value2 = (Integer) convert.getValue(Integer.class,bytes);

	  Person p = new Person();

	  p.setGrade(200.25);

	  p.setAge(45);

	  bytes = convert.getBytes(Person.class,p);

	  Person p2 = (Person)convert.getValue(Person.class,bytes);

	  System.out.println(p2.getAge());
	  System.out.println(p2.getGrade());
    }
}


