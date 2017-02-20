import org.aspectj.lang.annotation.Pointcut;

public class SystemArchitecture {

//    @Pointcut("execution(* (@org.springframework.stereotype.Repository *).*(..))") // any class annotated with @Repository
    @Pointcut("execution(* edu.tutorials..repository.*.*(..))") // any class in a subpackage of edu.tutorials
    public void repository() {
    }

//    @Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))") // any class annotated with @Service
    @Pointcut("execution(* edu.tutorials..service.*.*(..))") // any class in a subpackage of edu.tutorials
    public void service() {
    }
}
