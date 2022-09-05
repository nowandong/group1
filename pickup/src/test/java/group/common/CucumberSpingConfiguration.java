package group.common;


import group.PickupApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { PickupApplication.class })
public class CucumberSpingConfiguration {
    
}
