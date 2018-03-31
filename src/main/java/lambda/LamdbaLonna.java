package lambda;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 **/
public class LamdbaLonna {

    public static void main(String arg[]){
        List<String> strings = Arrays.asList("name", "name1", "name2");
        long l = fromLondonPrinted(strings);
        System.out.print(l);
    }

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
    public void allToUpperCase(){
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
}
