package LamdbaTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-04-01 20:00
 **/
public class Test1 {

    //计算流中所有数之和
    @Test
    public void addUP(){
        List<Integer> wordList = Arrays.asList(1, 2, 3);
        Optional<Integer> reduce = wordList.stream().reduce((nu1, nu2) -> nu1 + nu2);
        System.out.print(reduce.get());
    }

}
