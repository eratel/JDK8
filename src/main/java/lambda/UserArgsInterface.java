package lambda;

//@FunctionalInterface标记在接口上，“函数式接口”是指仅仅只包含一个抽象方法的接口
@FunctionalInterface
public interface UserArgsInterface {
    void study(User user);

    public default void welcome(){
        System.out.print("welcome+++++++++++++++++++");
    }
}
