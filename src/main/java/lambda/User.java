package lambda;

import lombok.Data;

/**
 * Author:JettZhou
 * Date: 2017/5/11
 * Tine:10:47
 */
@Data
public class User {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public User() {

    }
}
