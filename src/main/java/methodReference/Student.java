package methodReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author:JettZhou
 * Date: 2017/5/11
 * Tine:14:40
 */
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
