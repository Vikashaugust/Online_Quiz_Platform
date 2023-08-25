package pd.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd.example.demo.major.project.repository.CourseRepository;
import pd.example.demo.major.project.utility.Course;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    /**
     * here all the data entered in frontend it will save in repository(database)
     * @param course
     */

    public void save(Course course) {
        courseRepository.save(course);

    }
    /**
     * It will find all the details from database.
     * @return
     */
    public Object findAll() {
        return courseRepository.findAll();
    }
    /**
     * List of data will be save in database for the dropdown and fetch from the database into frontend(webpage).
     * @return
     */
    public List<Course> getDropdown() {
//        List<Course> courses=new ArrayList<>();
//        courseRepository.save(new Course("Java"));
//        courseRepository.save(new Course("Python"));
//        courseRepository.save(new Course("HTML/CSS"));
//       courseRepository.save(new Course("J2EE"));
//        courseRepository.save(new Course("SQL"));
        List<Course> courseList = courseRepository.findAll();

        return courseList;
    }
}
