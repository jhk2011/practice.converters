package practice.converters.typemaps;

public class SimpleTypeMap implements TypeNameMap{

    Class type;
    String name;

     public SimpleTypeMap(Class type, String name){
        this.type=type;
        this.name=name;
     }

    @Override
    public boolean canGetType(String name) {
         return this.name.equals(name);
    }

    @Override
    public boolean canGetName(Class type)
    {
        return this.type==type;
    }

    @Override
    public Class getType(String name) {
         return type;
    }

    @Override
    public String getName(Class type) {
         return name;
    }
}

