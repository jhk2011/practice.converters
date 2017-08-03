package practice.converters.converters;

import practice.converters.BinaryReader;
import practice.converters.BinaryWriter;
import practice.converters.Convert;
import practice.converters.Converter;

import java.util.Calendar;
import java.util.Date;

public class DateConverter extends Converter<Date> {
    @Override
    public boolean canConvert(Class c) {

        return c==Date.class;
    }

    @Override
    protected void writeObject(BinaryWriter writer, Date obj, Class c, Convert convert) {
        long millseconds = obj.getTime();
        writer.writeLong(millseconds);
    }

    @Override
    protected Date readObject(BinaryReader reader, Class c, Convert convert) {
        long millseconds = reader.readLong();
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(millseconds);
        return calendar.getTime();
    }
}

