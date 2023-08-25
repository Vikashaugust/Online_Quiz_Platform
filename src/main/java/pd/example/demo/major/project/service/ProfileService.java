package pd.example.demo.major.project.service;

import pd.example.demo.major.project.model.Profile;

public interface ProfileService {
    void register(Profile profile);

    void deleteCandidate( Long profileId);


    void updateCandidate(Long profileId, Profile profile);

    void save(Profile profile);

    Profile getData(String profileId);
}
