package date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;


public class DataExcample2 {
    private LocalDate today = LocalDate.now();

    @Test
    public void testData(){
        LocalDate of = LocalDate.of(2014,4,1);
//        System.out.print(of.equals(today));

        LocalDate localDate = today.plusDays(1);
//        boolean after = localDate.isAfter(of);
        System.out.print(localDate.isBefore(of));


        LocalDate datebirth = LocalDate.of(1994, 4, 1);
        MonthDay of1 = MonthDay.of(datebirth.getMonth(), datebirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);


    }

    @Test
    public void equalsData(){
        LocalDate of = LocalDate.of(2014, 4, 1);

        LocalDate localDate = today.plusDays(1);
        System.out.print(today.isAfter(of));

    }


}
