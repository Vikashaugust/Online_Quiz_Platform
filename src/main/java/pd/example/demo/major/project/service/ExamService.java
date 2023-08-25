package pd.example.demo.major.project.service;

import pd.example.demo.major.project.model.Exam;

public interface ExamService {
    public String register(Exam exam);

    void sendEmail(Exam registration1);
}
