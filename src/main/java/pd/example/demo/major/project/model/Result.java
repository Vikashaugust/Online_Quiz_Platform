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
public class Result {
    @Id
    private String id;
    private String username;
    private int totalCorrect=0;
    private int totalSkipped;

}
