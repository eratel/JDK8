package Optional;

import lambda.User;
import org.junit.Test;

import java.util.Optional;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 * 接口调用
 **/
public class OptionalTest {

    public void test(User u){
        Optional optional = Optional.ofNullable(u);
        System.out.println(optional.orElse("nullPoint"));
    }
    @Test
    public void test2(){
        test(new User());
        test(null);
    }
}
