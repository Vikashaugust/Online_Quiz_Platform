package pd.example.demo.major.project.service;

import pd.example.demo.major.project.model.Proleap;

public interface ProleapService {
    void sendEmail(Proleap registration);

    void save(Proleap proleap);


    Proleap findByUidAndPassword(String uid, String password);
}
