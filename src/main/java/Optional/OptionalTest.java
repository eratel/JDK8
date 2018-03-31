package Optional;

import lambda.User;
import org.junit.Test;

import java.util.Optional;

/**
 * Author:JettZhou
 * Date: 2017/5/11
 * Tine:17:22
 */
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
