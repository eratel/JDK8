package date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * jdk8 时间处理
 * 用到的方法都是线程安全的
 */
public class DateExample {
    private LocalDate today = LocalDate.now();

    /**
     * 时间相关的获取
     */
    @Test
    public void test() {
        //获取当前日期
        System.out.println("今天的日期 : " + today);


        //获取单独的年月日
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year : %d Month : %d day : %d \t %n", year, month, day);


        //输出当前时间 不包含日期
        LocalDateTime now = LocalDateTime.now();
        LocalTime time = LocalTime.now();
        System.out.println("LocalDateTime : " + now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/hh/ss")));

        //输出当前的日期和时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("今天的时间和日期：" + localDateTime);
    }

    /**
     * 时间相关的比较、检查和验证
     */
    @Test
    public void test2() {


        //LocalDate重写了equals方法，可以直接比较两个LocalDate
        LocalDate date1 = LocalDate.of(2014, 1, 14);
        if (!date1.equals(today)) {
            System.out.printf("Today %s and date1 %s are same date %n", today, date1);
        }


        //日期大小比较
        LocalDate tomorrow = today.plusDays(1);
        if (tomorrow.isAfter(today)) {
            System.out.println("Tomorrow comes after today");
        }
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("Yesterday is day before today");
        }


        //日期检查
        LocalDate dateOfBirth = LocalDate.of(2017, 5, 16);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }


        YearMonth yearMonth = YearMonth.now();
        DayOfWeek dayOfWeek = DayOfWeek.of(2);
        System.out.println("年月：" + yearMonth);
        System.out.println("几号：" + MonthDay.now());
        System.out.println("星期几：" + dayOfWeek);

        //检查是否是闰年
        System.out.println(today.isLeapYear());
        System.out.println(today.minus(1, ChronoUnit.YEARS).isLeapYear());


        LocalDate addMonth = today.plusMonths(-2).plusWeeks(2).plusDays(2);


        //Period是一个时间容器,做比较比较好用
        Period period = Period.between(addMonth, today);
        //两个日期相差的天数 月份并不会转换成天数。相应的也有getMonths() getYear()方法
        System.out.println(period.getDays());
        //这个方法为判断传入的年月日是否是负数，不知道是啥用
        period.isNegative();
        //在当前年月日的基础之上加上指定的天数，月份或者年份
        System.out.println(period.withDays(2).getDays());
        //该方法判断Period容器中的年月日是否都是0
        period.isZero();
        //将Period中的年月日进行翻倍
        period = period.multipliedBy(3);
        System.out.println(period.getDays());
        //将时间差进行反转，比如year:2 month:-1 day:15  转换成 year:-2 month: 1 day: -15
        period.negated();
        //将年转换成月之后，统计总月数
        period.toTotalMonths();
        //将总月份或者年加上超过12个月的月份，根据一年12个月进行格式化。
        period.normalized();

    }

    /**
     * 时间格式转换
     */
    @Test
    public void test3() {


        //时间转换
        String dayAfterTommorrow = "20170516";
        //2014-01-16
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow,
                DateTimeFormatter.BASIC_ISO_DATE);
        //DateTimeFormatter 内置了很多时间格式化器 同上
        System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);

        //这种格式的可以直接parse
        LocalDate endOfFeb = LocalDate.parse("2017-05-16");
        System.out.println(endOfFeb);

        //自定义格式
        String goodFriday = "05 18 2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate holiday = LocalDate.parse(goodFriday, formatter);
        System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday);
    }

    /**
     * 时间相关的操作 和一些常用的时间提取
     */
    @Test
    public void test1() {


        //自定义日期
        LocalDate dateOfBirths = LocalDate.of(2010, 1, 14);
        System.out.println("Your Date of birth is : " + dateOfBirths);


        //时间变化
        LocalDate addToday = today.plusDays(2);
        LocalDate addMonth = today.minusDays(1);
        //Duration这个类没用懂  类似的方法在165行
       // System.out.println("测试minus ：" + today.minus(Duration.ofDays(1589713165)));


        //增加两个世纪
        LocalDate newEras = today.plus(2, ChronoUnit.CENTURIES);
        System.out.println(addToday);
        System.out.println(newEras);


        //minus和plus是相反的   也是修改日期相关的
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year : " + nextYear);


        //时间戳
        Instant timestamp = Instant.now();
        System.out.println("time:" + timestamp.getEpochSecond());

        Instant instant = Instant.ofEpochSecond(timestamp.getEpochSecond());
        System.out.println(LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai")));


        //TemporalAdjusters封装很多提取时间的方法
        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2014-12-02
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
        // 取下一天：
        LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05

        //以下方法基本上都是LocalDate LocalDateTime LocalTime三个类通用的，小有区别
        //今年的第355天的日期
        System.out.println("测试withDayOfYear ：" + today.withDayOfYear(355));
        //当前日期的在一月的日期，如果当前日期的在响应的月份没有则取当月的最后一天。比如取3月31,如果月份换成2月，2月没有31天它会直接判断今年是平年还是闰年然后取最后一天。
        System.out.println("测试withDayOfMonth ：" + today.withMonth(1));
        System.out.println("测试：" + LocalDate.now().plusDays(14).withMonth(2));
        //本月第二天的日期
        System.out.println("测试withDayOfWeek：" + today.withDayOfMonth(2));
        //显示哪一年的今天的日期
        System.out.println("测试withYear" + today.withYear(1995));


        //TemporalAdjusters有封装的选取指定日期的方法。如今年的第一天，明年的第一天，下个月的最后一天，本月的最后一天等等方法都有封装
        //返回今天零点的LocalDateTime
        System.out.println("返回今天零点的LocalDateTime：" + today.atStartOfDay());
        //返回一个今天的指定时区的ZoneDateTime
        System.out.println("返回一个今天的达尔文的ZoneDateTime : " + today.atStartOfDay(ZoneId.of("Australia/Darwin")));
        //返回今天两点二十的时间
        System.out.println(today.atTime(2, 20));
        System.out.println(today.atTime(LocalTime.of(2, 20, 20, 321)));
    }

    @Test
    public void test5() {
        //时钟
        /*
        map.put("ACT", "Australia/Darwin");
        map.put("AET", "Australia/Sydney");
        map.put("AGT", "America/Argentina/Buenos_Aires");
        map.put("ART", "Africa/Cairo");
        map.put("AST", "America/Anchorage");
        map.put("BET", "America/Sao_Paulo");
        map.put("BST", "Asia/Dhaka");
        map.put("CAT", "Africa/Harare");
        map.put("CNT", "America/St_Johns");
        map.put("CST", "America/Chicago");
        map.put("CTT", "Asia/Shanghai");
        map.put("EAT", "Africa/Addis_Ababa");
        map.put("ECT", "Europe/Paris");
        map.put("IET", "America/Indiana/Indianapolis");
        map.put("IST", "Asia/Kolkata");
        map.put("JST", "Asia/Tokyo");
        map.put("MIT", "Pacific/Apia");
        map.put("NET", "Asia/Yerevan");
        map.put("NST", "Pacific/Auckland");
        map.put("PLT", "Asia/Karachi");
        map.put("PNT", "America/Phoenix");
        map.put("PRT", "America/Puerto_Rico");
        map.put("PST", "America/Los_Angeles");
        map.put("SST", "Pacific/Guadalcanal");
        map.put("VST", "Asia/Ho_Chi_Minh");
        map.put("EST", "-05:00");
        map.put("MST", "-07:00");
        map.put("HST", "-10:00");
        */
        LocalDateTime datetime = LocalDateTime.of(2017, Month.JANUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);


        //拿取偏移量
        ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/New_York"));
        ZonedDateTime china = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Shanghai"));
        ZonedDateTime Darwin = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Australia/Darwin"));
        System.out.println("America : " + zonedDateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println("China : " + china);


        //拿取具体时区的时间
        System.out.println("偏移时间" + LocalDateTime.now(Clock.fixed(Instant.now(), ZoneId.of("Australia/Darwin"))));
        System.out.println("Darwin : " + Darwin.getOffset());
        System.out.println("Now : " + LocalDateTime.now());
    }
    /*
    总结：
        LocalDate、LocalDate、LocalDateTime、YearMonth、MonthDay、DayOfWeek都支持  ：
            plus加时间，
            minus减时间，
   */
}
