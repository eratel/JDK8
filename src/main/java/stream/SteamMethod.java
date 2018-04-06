package stream;

import methodReference.Student;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 * 接口调用
 **/
public class SteamMethod {
    private List<String> list = Arrays.asList("111", "112", "131", "4", "5", "6");
    private List<Student> data = new ArrayList<>();

    {
        data.add(new Student("xiaoming", 85, 95));
        data.add(new Student("xiaohong", 86, 99));
        data.add(new Student("xiaogang", 100, 100));
    }

    @Test
    public void getDistinct() {

        //去重 distinct(非懒加载)
        System.out.println(list.stream().distinct().count());


        /** 流转换
         *  流转换是懒操作的，返回值大多都是Stream，所以才支持链式编程。要想获取转换的数据需要就行聚合操作。通常遇到聚合函数才会开始执行。
         **/

        // map           对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。

        //flatmap        对流进行合并
        Stream.of(list, list).flatMap(Collection::stream).forEach(System.out::println);


        System.out.println("----------------------map           对于Stream中包含的元素使用给定的转换函数进行转换操作");
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.toList()).toString());

        // collect      收集转换后的元素将其转换成map list,同时可以进行分组，排序等一系列操作
        System.out.println("----------------------collect      收集转换后的元素将其转换成map list,同时可以进行分组，排序等一系列操作");
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.toList()).toString());
        //set 的唯一特性  会让重复元素去掉
        System.out.println(list.stream().map(String::toUpperCase).collect(Collectors.toSet()));
        // equipmentSetEffectMetaMap = equipmentSetMetaList.stream().collect(Collectors.toMap(EquipmentSetMeta::getId, EquipmentSetEffectMeta -> EquipmentSetEffectMeta));



        //peek 对每一个元素附加处理
        System.out.println("---------------------peek 对每一个元素附加处理");
        System.out.println(Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList()));
    }


    @Test
    //compareToIgnoreCase  字典排序方式
    public  void sortedTest(){

        List<String> collect = list.stream().sorted((x, y) -> x.compareToIgnoreCase(y)).collect(Collectors.toList());
        System.out.print(collect);
        System.out.print(list.stream().sorted((x,y)-> new Integer(x).compareTo(new Integer(y))).collect(Collectors.toList()));

    }

    /**
     * 聚合操作（非懒加载）
     **/
    @Test
    public void test3() {

        //max  min   count   在已有的Stream中获取最大值（需要传入比较器），最小值（需要传入比较器）或者统计总数。
        System.out.println("----------------------max  min   count   在已有的Stream中获取最大值（需要传入比较器），最小值（需要传入比较器）或者统计总数。");
        System.out.println(list.stream().filter(w -> w.length() < 4).count());
        System.out.println(list.stream().max(Comparator.comparingInt(String::length)));
        System.out.println(data.stream().min(Comparator.comparingInt(Student::getGradeCount)));


        //reduce    在一组数据中生成一个值   max min和count因为常用所以单独提取出来了，原本也是属于reduce操作
        System.out.println("----------------------reduce    在一组数据中生成一个值   max min和count因为常用所以单独提取出来了，原本也是属于reduce操作");
        //下面是一个累加
        int accResult = Stream.of(1, 2, 3, 4)
                .reduce(0, (acc, item) -> {
                    System.out.println("acc : " + acc);
                    acc += item;
                    System.out.println("item: " + item);
                    System.out.println("acc+ : " + acc);
                    System.out.println("--------");
                    return acc;
                });
        System.out.println("accResult: " + accResult);
        System.out.println("--------");
        //http://blog.csdn.net/io_field/article/details/54971679

        //collect  把Stream中的元素收集到一个容器中去。
        System.out.println("----------------------collect  把Stream中的元素收集到一个容器中去。");
        List<Integer> numList = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 11, 8, 9, 10);
        System.out.println(numList.stream().filter(Objects::nonNull).distinct().sorted().collect(Collectors.toList()));

        //pipeline  对集合的并行操作. fork/join 框架，fork 递归式地分解问题，然后每段并行执行，最终由 join 合并结果，返回最后的值。
        list.parallelStream();
        /*
        1. 是否需要并行？
        2. 任务之间是否是独立的？是否会引起任何竞态条件？
        3. 结果是否取决于任务的调用顺序？
         */
        /*性能好
            ArrayList 、数组或 IntStream.range ，这些数据结构支持随机读取，也就是说它们能轻
            而易举地被任意分解。
          性能一般
            HashSet 、 TreeSet ，这些数据结构不易公平地被分解，但是大多数时候分解是可能的。
          性能差 ?
            有些数据结构难于分解，比如，可能要花 O(N) 的时间复杂度来分解问题。其中包括
            LinkedList ，对半分解太难了。还有 Streams.iterate 和 BufferedReader.lines ，它们
            长度未知，因此很难预测该在哪里分解。
        */

        //forEach 和 forEachOrdered 的区别：Stream中并行处理集合forEach可能会将原本有序的数据进行打乱，forEachOrdered不会出现，仍然保持有序；
        System.out.println("----------------------forEach 和 forEachOrdered 的区别：Stream中并行处理集合forEach可能会将原本有序的数据进行打乱，forEachOrdered不会出现，仍然保持有序；");
        List<Integer> aa = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        aa.stream().parallel().forEach(System.out::print);
        System.out.println();
        aa.stream().parallel().forEachOrdered(System.out::print);
    }
    /** 短路操作*/
    @Test
    public void test2(){
        //allMatch  判断Stream中的元素是否全部符合要求
        boolean allMatch = Stream.of(1, 2, 3, 4)
                .allMatch(integer -> integer > 0);
        System.out.println("allMatch: " + allMatch); // 打印结果：allMatch: true

        //anyMatch   判断流中是否有一个符合要求的，有一个则返回true
        boolean anyMatch = Stream.of(1, 2, 3, 4)
                .anyMatch(integer -> integer > 3);
        System.out.println("anyMatch: " + anyMatch); // 打印结果：anyMatch: true

        //noMatch   判断流中是否所有的元素都不符合要求，是的话返回true。
        boolean noMatch = Stream.of(1, 2, 3, 4)
                .noneMatch(integer -> integer < 0);
        System.out.println("noMatch: " + noMatch); // 打印结果：noMatch: true

        //获取第一个元素，如果Stream中的元素没有排序，并行操作中调用可能会随机返回一个。
        Optional<Integer> first = Stream.of(1, 2, 3, 4).findFirst();
        System.out.println(first.get());

        //获取Stream中的元素，串行操作中默认是选取第一个元素，并行操作中取到的结果不同。
        Optional<Integer> any = Stream.of(1, 2, 3, 4).findAny();
        System.out.println(any.get());

        //limit 和 sql中的limit类似  截断数据
        System.out.println("----------------------limit 和sql中的limit类似  截断数据");
        System.out.println(list.stream().limit(2).collect(Collectors.toList()).toString());

        //skip          返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream。   这个是一个非短路操作，因为和limit相关所以放到一起。
        System.out.println("----------------------skip  返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream");
        System.out.println(list.stream().skip(2).collect(Collectors.toList()).toString());


    }

}
