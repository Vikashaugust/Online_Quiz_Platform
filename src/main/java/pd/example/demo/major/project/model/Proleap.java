package pd.example.demo.major.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "proleap")
public class Proleap {
    private Long proleapId;
    private String name;
    private String courseRegistered;
    private String doj;
    private String uid;
    private String password;
    private String email; // add by vikash


}
