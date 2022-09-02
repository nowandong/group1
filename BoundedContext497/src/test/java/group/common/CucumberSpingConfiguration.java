package group.common;


import group.BoundedContext497Application;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { BoundedContext497Application.class })
public class CucumberSpingConfiguration {
    
}
