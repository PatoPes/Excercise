package utilities;
import com.arakelian.faker.model.Address;
import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public final class Util{

    private static final String DATE_FORMAT = "MM-dd-yyyy";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);


    /*Generates a random person*/
    public static Person getARandonCustomer(){
        Person person = RandomPerson.get().next();
        return person;
    }

    /*Generates a random address*/
    public static Address getARandomAddress(){
        Address address = RandomAddress.get().next();
        return address;
    }

    /*Generates a random Int between start and end*/
    public static int generatesRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    /*Generates a random date*/
    public static String generatesRandomDate(int startYear, int endYear) {
        int day = generatesRandomIntBetween(1, 28);
        int month = generatesRandomIntBetween(1, 12);
        int year = generatesRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day).format(formatter);
    }

}
