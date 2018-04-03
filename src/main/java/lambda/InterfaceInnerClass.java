package lambda;

import org.junit.Test;

import java.util.*;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 **/
public class InterfaceInnerClass {
    @Test
    public void test() {
        List<String> wordList = Arrays.asList("123456", "1234", "123", "12", "11111");


        System.out.println(compare(wordList));
        System.out.println(compareLambda(wordList));
        System.out.println(compareLambdaFunction(wordList));


    }

    //匿名内部类
    public List<String> compare(List<String> wordList) {

        wordList.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
        return wordList;
    }

    //lambda
    //Integer.compare 输出结果  大于=1；等于=0；小于=-1
    public List<String> compareLambda(List<String> wordList) {
        wordList.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        return wordList;
    }

    //函数(非空验证)
    public List<String> compareLambdaFunction(List<String> wordList) {
        wordList.sort(Comparator.comparingLong(String::length));
       // wordList.sort(Comparator.comparingLong((a) -> a.length()));
        return wordList;
    }


    public void treeSetCompare() {

        Set<User> set = new TreeSet<>(Comparator.comparing(User::getAge));
        //原来的写法
        Set<User> oldSet = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
    }


}

