package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class LambdaExample {

    /** lambda第一种格式，无参 */
    @Test
    public void test(){
        eat(() -> System.out.println("-----------"));
    }

    private void eat(){

    }
    private void eat(UserInterface c){

    }


    /** lambda第二种格式 ，有参 */

    @Test
    public void test1(){
        study( user -> System.out.println("----------------"));
//        study((user) -> System.out.println("---------------"));
    }

    private void study(UserArgsInterface c){
    }

    @Test
    public void getLoopList() {
        List<User> all = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            all.add(new User("111"+i, 11));
        }
        all.forEach(user -> System.out.print(user));

    }

    public void lambdaLoopList(List<User> list) {
        //三行循环效果相同
        //list.forEach(user -> System.out.println(user.toString()));
        //list.forEach(user -> System.out.println(user));//下面一行代码就是简写形式
        list.forEach(System.out::println);
    }
}
