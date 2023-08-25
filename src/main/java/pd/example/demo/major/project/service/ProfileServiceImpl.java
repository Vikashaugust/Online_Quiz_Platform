package pd.example.demo.major.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pd.example.demo.major.project.model.Profile;
import pd.example.demo.major.project.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    /**
     * It will save the all the details of candidate which will entered in profile registration page into profile repository.
     * @param profile
     */
    @Override
    public void register( Profile profile) {
        profileRepository.save(profile);

    }
    /**
     * The admin can delete of the details of the profile candidate from the list.
     * @param profileId
     */
    @Override
    public void deleteCandidate(Long profileId) {
        profileRepository.deleteByprofileId(profileId);
    }


    /**
     * The admin can update the details of the profile candidate.
     * @param profileId
     * @param profile
     */
    @Override
    public void updateCandidate(Long profileId, Profile profile) {

        profileRepository.findByProfileId(profileId);
        profile.setName(profile.getName());

        profileRepository.save(profile);
    }
    /**
     * It can get the data by the id from the database.
     * @param id
     * @return
     */
    public Profile getData(String id){
        return profileRepository.findAll().stream().filter(profile->profile.getId().equals(id)).findFirst().get();
    }


    /**
     * after crud operation it will save in the same id.
     * @param profile
     */
    public void save(Profile profile) {
        profileRepository.save(profile);
    }
}
