package pd.example.demo.major.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class Question {

    @Id
    private String quesId;
    private String title;
    private String optionA;
    private String optionB;
    private String optionC;
    private int ans;
    private int chose;


}