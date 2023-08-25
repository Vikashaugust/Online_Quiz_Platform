package pd.example.demo.major.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pd.example.demo.major.project.utility.Timeslot;
@Repository
public interface TimeslotRepository extends MongoRepository<Timeslot,String> {
}
