package pd.example.demo.major.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pd.example.demo.major.project.utility.Qualification;

public interface QualificationRepository extends MongoRepository<Qualification,String> {
}
