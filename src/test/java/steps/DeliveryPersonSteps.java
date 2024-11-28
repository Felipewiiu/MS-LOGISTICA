package steps;

import br.com.mslogistica.ms_logistica.MsLogisticaApplication;
import br.com.mslogistica.ms_logistica.presentation.dto.deliveryperson.DeliveryPersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import templateDto.DeliveryTemplateDto;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MsLogisticaApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeliveryPersonSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DeliveryPersonDto deliveryPersonDto;
    private String responseContent;

    @Before
    public void setUp() throws Exception {
        deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(1L);
    }

    @Given("the delivery person details")
    public void theDeliveryPersonDetails() {
        deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(1L);
    }

    @Given("a delivery person exists with ID {int}")
    public void a_delivery_person_exists_with_id(Integer id) throws Exception {
        deliveryPersonDto = DeliveryTemplateDto.deliveryPersonTemplate(id.longValue());
        String deliveryPersonJson = objectMapper.writeValueAsString(deliveryPersonDto);
        mockMvc.perform(post("/delivery-person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryPersonJson))
                .andExpect(status().isCreated());
    }

    @When("I send a POST request to create delivery person {string}")
    public void iSendAPostRequestToCreateDeliveryPerson(String url) throws Exception {
        String deliveryPersonJson = objectMapper.writeValueAsString(deliveryPersonDto);
        responseContent = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryPersonJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String url) throws Exception {
        responseContent = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Then("the response should contain the delivery person details")
    public void the_response_should_contain_the_delivery_person_details() throws Exception {
        mockMvc.perform(post("/delivery-person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryPersonDto)))
                .andExpect(content().string(containsString(deliveryPersonDto.name())));
    }

}