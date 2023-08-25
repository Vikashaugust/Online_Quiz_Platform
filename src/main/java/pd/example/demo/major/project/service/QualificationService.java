package pd.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd.example.demo.major.project.repository.QualificationRepository;
import pd.example.demo.major.project.utility.Qualification;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualificationService {
    @Autowired
    private QualificationRepository qualificationRepository;
    /**
     * It will save the details of qualification.
     * @param qualification
     */
    public void save(Qualification qualification) {
        qualificationRepository.save(qualification);

    }
    /**
     * It will find all the data from database.
     * @return
     */
    public Object findAll() {
        return qualificationRepository.findAll();
    }
    /**
     * It will get the data into webpage.
     * @return
     */
    public List<Qualification> getDropDown() {
//        List<Qualification> emp=new ArrayList<>();
//        qualificationRepository.save(new Qualification("BSC"));
//        qualificationRepository.save(new Qualification("BCOM"));
//        qualificationRepository.save(new Qualification("BA"));
//        qualificationRepository.save(new Qualification("B.TECH."));
//        qualificationRepository.save(new Qualification("B.E."));
//        qualificationRepository.save(new Qualification("BBA"));
//        qualificationRepository.save(new Qualification("B.PHARMA."));
//        qualificationRepository.save(new Qualification("BCA"));
//        qualificationRepository.save(new Qualification("M.TECH."));
//        qualificationRepository.save(new Qualification("MCA"));
//        qualificationRepository.save(new Qualification("MBA"));
//        qualificationRepository.save(new Qualification("M.PHARM."));
        List<Qualification> qualificationList = qualificationRepository.findAll();
        //System.out.printf(qualificationList.get(1).getHighestQualification());
        return qualificationList;
    }
}
