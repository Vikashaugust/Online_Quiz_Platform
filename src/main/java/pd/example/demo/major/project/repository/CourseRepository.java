package pd.example.demo.major.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pd.example.demo.major.project.utility.Course;

public interface CourseRepository extends MongoRepository<Course,String> {
}
