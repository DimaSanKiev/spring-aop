import org.aspectj.lang.annotation.Pointcut;

public class SystemArchitecture {

    @Pointcut("execution(* (@org.springframework.stereotype.Repository *).*(..))") // any class annotated with @Repository
    public void repository() {

    }

    @Pointcut("execution(* (@org.springframework.stereotype.Service *).*(..))") // any class annotated with @Service
    public void service() {

    }
}
