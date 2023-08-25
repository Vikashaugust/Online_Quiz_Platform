package pd.example.demo.major.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "profile")
public class Profile {
    @MongoId
    private String id;

    private Long profileId;
    private String name;
    private String fatherName;
    private String motherName;
    private String dob;
    private String gender;
    private String phoneNumber;
    private String address;
    private String emailId;
    private String highestQualification;
    private String collegeName;
    private String degree;
    private String yop;
    private String field;


    public Profile(Long profileId, String name, String fatherName, String motherName, String dob, String gender, String phoneNumber, String address, String emailId, String highestQualification, String collegeName, String degree, String yop, String field) {
        this.profileId = profileId;
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.emailId = emailId;
        this.highestQualification = highestQualification;
        this.collegeName = collegeName;
        this.degree = degree;
        this.yop = yop;
        this.field = field;
    }
}
