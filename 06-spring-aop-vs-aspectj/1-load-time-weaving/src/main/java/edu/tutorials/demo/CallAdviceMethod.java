package edu.tutorials.demo;

public class CallAdviceMethod {

    /*
     To make the aspect executed load time weaving needs to be configured by adding as VM argument:
      -javaagent:"C:\Users\Dima\.m2\repository\org\aspectj\aspectjweaver\1.8.10\aspectjweaver-1.8.10.jar"
     */
    public static void main(String[] args) {
        DemoClass demoClass = new DemoClass();
        demoClass.adviceMethod();
    }
}
