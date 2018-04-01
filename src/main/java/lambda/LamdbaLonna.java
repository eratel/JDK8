package lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
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
     * reduce  求和
     */
    @Test
    public void sumUsingReduce() {
        Optional<Integer> reduce = Stream.of(1, 2, 3)
                .reduce((acc, element) -> acc + element);
        System.out.print(reduce.get());
    }

    /**
     * 找到对象中 name 开头ba 的年龄是多少
     */
    @Test
    public void originsOfBands() {
        List<User> userList = asList(new User("Bakai", 524),
                new User("Violets for Your Furs", 378),
                new User("Time Was", 451));
        // BEGIN origins_of_bands
        List<Integer> origins = userList.stream()
                //先过滤
                .filter(artist -> artist.getName().startsWith("Ba"))
                //对年龄进行重新赋值
                .map(artist -> artist.getAge())
                //将 值装换
                .collect(toList());
        // END origins_of_bands
        System.out.print(origins.toString());
    }
}
