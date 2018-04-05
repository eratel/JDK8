package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 * 接口调用
 **/
public class InterfaceInner {
////////////////////////////////////////////////////////////////
// 接口调用
    /** lambda第一种格式，无参 */
    /**
     * 无非就是将实现 作为参数 使用lamdba表达式的形式实现
     * 然后在类中被调用
     */
    @Test
    public void test(){
        eat(() -> System.out.println("-----------"));
    }

    /**
     * 当你在实现底层框架 实现lamdba的形式时。 这块写入base 业务逻辑
     * 传输 关键业务逻辑
     */
    private void eat(UserInterface c){
        c.eat();
    }


    /**
     * lambda第二种格式 ，有参
     */
    @Test
    public void test1(){
        study( user -> System.out.println(user.getName()));
    }

    private void study(UserArgsInterface c){
       c.study(new User("name",10)); //传入直接代码片段 对接口实现可以这么理解
       c.welcome(); //直接执行接口中的方法
    }





    ////////////////////////////////////////////////////////////////////////////////////////

    /**
     * for 循环
     */
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
