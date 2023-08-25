package pd.example.demo.major.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pd.example.demo.major.project.model.Result;
@Repository
public interface ResultRepository extends MongoRepository<Result,String> {
}
