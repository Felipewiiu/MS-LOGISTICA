package configCucumber;

import br.com.mslogistica.ms_logistica.MsLogisticaApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = MsLogisticaApplication.class)
@AutoConfigureMockMvc
public class CucumberSpringConfiguration {
}