package methodReference;

import org.junit.Test;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Author:JettZhou
 * Date: 2017/5/11
 * Tine:14:18
 */
public class MethodReference {

    @Test
    public void test() {
        print2();
        System.out.println();
        print3();
    }

    public static void print2() {
        List<String> wordList = Arrays.asList("spring", "summer", "autumn", "winter");
        wordList.forEach(word -> System.out.print(word));
    }

    public static void print3() {
        List<String> wordList = Arrays.asList("spring", "summer", "autumn", "winter");
        wordList.forEach(System.out::print);
    }

    /**
     * 静态方法引用
     */
    public List<Integer> sortList1() {
        List<Integer> wordList = Arrays.asList(21, 53, 22);
        wordList.sort((w1, w2) -> Integer.compare(w1, w2));
        return wordList;
    }

    public List<Integer> sortList2() {
        List<Integer> wordList = Arrays.asList(21, 53, 22);
        wordList.sort(Integer::compare);
        return wordList;
    }

    public List<Integer> sortList3() {
        List<Integer> wordList = Arrays.asList(21, 53, 22);
        wordList.sort(Comparator.comparingInt(a -> a));
        return wordList;
    }

    @Test
    public void test2() {
        System.out.println(sortList1().toString());
        System.out.println(sortList2().toString());
        System.out.println(sortList3().toString());
        BinaryOperator<Integer> tComparator = (x, y) -> x + y;
        Runnable runnable = () -> System.out.print(1);
        ActionListener tConsumer = event -> System.out.print(1);
        Comparator<Integer> tComparator1 = (x, y) -> x + y;
        String  name = getName();
        Runnable runnable1 = () -> System.out.print(name);
        runnable1.run();
    }




    public void test3(){
        String  name = getName();
        Runnable runnable = () -> System.out.print(name);
        ActionListener actionListener = event -> System.out.print(1);
        Runnable runnable1 = () -> {
            System.out.print(1);
        };
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        BinaryOperator<Long> binaryOperator1 = (Long x,Long y) -> x + y;



    }










    public String getName(){
        return "111";
    }

    @Test
    public void test77(){
        ActionListener actionListener = e -> {System.out.print(1);};
    }


    /**
     * 引用对象方法
     */
    public String[] sort1() {
        String[] words = new String[]{"spring", "summer", "autumn", "winter"};
        Arrays.sort(words, (x, y) -> x.compareToIgnoreCase(y));
        return words;
    }

    public String[] sort2() {
        String[] words = new String[]{"spring", "summer", "autumn", "winter"};
        Arrays.sort(words, String::compareToIgnoreCase);
        return words;
    }

    @Test
    public void test1() {
        System.out.println(Arrays.toString(sort1()));
        System.out.println(Arrays.toString(sort2()));
    }

    /**
     * 构造器引用
     */
    @Test
    public void  construct(){
        StudentFactory studentFactory = new StudentFactory(Student::new);
    }

    static class StudentFactory{
        private Supplier<Student> supplier;

        StudentFactory(Supplier<Student> supplier) {
            this.supplier = supplier;
        }
    }


}
