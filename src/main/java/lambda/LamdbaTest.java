package lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 **/
public class LamdbaTest {

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
        List<String> list = strings.stream().map(a -> {
            return a.toUpperCase();
        }).collect(toList());
        //将stream转换为List<String> 的对象
//        List<String> collect = stringStream.collect(toList());

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

    /////////////////////////////////////////////////////////////////////////////////////////////
    //以下是练习

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

    @Test
    public void findName() {
        List<User> users = asList(new User("Bakai", 524),
                new User("Violets for Your Furs", 378),
                new User("Time Was", 451));
        Set<String> tn = new HashSet<>();
        users.stream()
                .filter(user -> user.getName().startsWith("B"))
                .map(user -> user.getName())
                .forEachOrdered(name ->tn.add(name));
//                .forEach(name ->tn.add(name));
        System.out.print(tn);
    }

    //计算流中所有数之和
    @Test
    public void addUP(){
        List<Integer> wordList = Arrays.asList(1, 2, 3);
        Optional<Integer> reduce = wordList.stream().reduce((nu1, nu2) -> nu1 + nu2);
        System.out.print(reduce.get());
    }


    /**
     * IntSummaryStatistics  int
     * LongSummaryStatistics  long
     * 等等 都包含  man min average sum 这些方法
     */
    @Test
    public void printTrackLengthStatistics() {
        List<User> users = asList(new User("Bakai", 524),
                new User("Violets for Your Furs", 378),
                new User("Time Was", 451));

        IntSummaryStatistics trackLengthStats
                = users.stream()
                //转换为int类型 自动解包
                .mapToInt(user -> user.getAge() )
                .summaryStatistics();

        System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
                trackLengthStats.getMax(),
                trackLengthStats.getMin(),
                trackLengthStats.getAverage(),
                trackLengthStats.getSum());
    }


    /**
     * map 循环
     */
    @Test
    public void countAlbums() {
        HashMap map = new HashMap<String,Integer>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        Map<String, Integer> countOfAlbums = new HashMap<>();
        map.forEach((k, v) -> {
            countOfAlbums.put(k.toString().toUpperCase(), Integer.parseInt(v.toString()));
        });
        System.out.print(countOfAlbums.toString());
    }



}
