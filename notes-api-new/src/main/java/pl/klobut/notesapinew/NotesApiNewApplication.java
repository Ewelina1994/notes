package pl.klobut.notesapinew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class NotesApiNewApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NotesApiNewApplication.class, args);
    }

}
