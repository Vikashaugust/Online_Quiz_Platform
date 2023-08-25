package pd.example.demo.major.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class QuestionForm {
    private List<Question> question;
}
