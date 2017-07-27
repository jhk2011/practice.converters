package practice.converters;

import java.io.DataInputStream;
import java.io.DataOutputStream;

interface TypeResolver{
     Class read(DataInputStream reader);
     void write(DataOutputStream writer, Class cls);
}
