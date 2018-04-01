package lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 **/
public class LamdbaLonna {

    public static void main(String arg[]) {
        List<String> strings = Arrays.asList("name", "name1", "name2");
        long l = fromLondonPrinted(strings);
        System.out.print(l);
    }

    /**
     * filter 过滤数据
     */
    public static long fromLondonPrinted(List<String> allArtists) {
        // BEGIN internal_count_londoners_printed
        long count = allArtists.stream()
                .filter(artist -> {
                    System.out.println(artist);
                    return artist.equals("name");
                })
                .count();
        // END internal_count_londoners_printed
        return count;
    }

    @Test
    /**
     * map 操作符
     */
    public void allToUpperCase() {
//        Stream<String> a = Stream.of("a", "b", "c");
//        List<String> collect = a.map(string -> string.toUpperCase())
//                .collect(Collectors.<String>toList());
//        System.out.print(collect.toString());

        List<String> strings = Arrays.asList("a", "b", "c");
        Stream<String> stringStream = strings.stream().map(a -> {
            return a.toUpperCase();
        });
        //将stream转换为List<String> 的对象
        List<String> collect = stringStream.collect(Collectors.<String>toList());

    }

    /**
     * 获取最小 最大
     */
    @Test
    public void streamsMaxLength() {
        List<User> uu = asList(new User("Bakai", 524),
                new User("Violets for Your Furs", 378),
                new User("Time Was", 451));

        User user = uu.stream()
//                .min(Comparator.comparing(u -> u.getAge()))
                .max(Comparator.comparing(u -> u.getAge()))
                .get();
        System.out.print(user);
    }

    /**
     * reduce  返回值为BinaryOperator
     */
    @Test
    public void sumUsingReduce() {
        int count = Stream.of(1, 2, 3)
                .reduce(0, (acc, element) -> acc + element);
        System.out.print(count);
        assertEquals(6, count);//断言
    }
}
