package info.jab.ms;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class MainApplication {

    public static void main(String... args) {
        System.out.println("Running main method");
        Quarkus.run(args);
    }
} 