package practice.converters;

import java.io.DataInputStream;
import java.io.DataOutputStream;

interface TypeResolver{
     Class read(BinaryReader reader);
     void write(BinaryWriter writer, Class cls);
}
