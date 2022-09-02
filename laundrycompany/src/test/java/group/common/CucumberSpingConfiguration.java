package group.common;


import group.LaundrycompanyApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { LaundrycompanyApplication.class })
public class CucumberSpingConfiguration {
    
}
