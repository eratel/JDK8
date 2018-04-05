package methodReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: jdk8
 * @GitHub: https://github.com/ABHSY
 * @author: ABHSY.Jason
 * @create: 2018-03-31 16:16
 * 接口调用
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int mathGrade;
    private int englishGrade;



    public int getGradeCount(){
        return mathGrade + englishGrade;
    }
}
