package pd.example.demo.major.project.exception;

public class InvalidNameException extends RuntimeException{
    public InvalidNameException(String message){
        super(message);
    }
}
