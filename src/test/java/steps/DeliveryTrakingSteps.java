package steps;

import br.com.mslogistica.ms_logistica.MsLogisticaApplication;
import br.com.mslogistica.ms_logistica.presentation.dto.deliverytraking.DeliveryTrakingDto;
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
import templateDto.DeliveryTrakingTemplateDto;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = MsLogisticaApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeliveryTrakingSteps {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private DeliveryTrakingDto deliveryTrakingDto;
    private String responseContent;

    @Before
    public void setUp() throws Exception {
        deliveryTrakingDto = DeliveryTrakingTemplateDto.deliveryTrakingTemplate(1L);
    }

    @Given("a delivery traking details")
    public void aDeliveryTrakingDetails() {
        deliveryTrakingDto = DeliveryTrakingTemplateDto.deliveryTrakingTemplate(1L);
    }

    @Given("a delivery traking exists with ID {int}")
    public void aDeliveryTrakingExistsWithId(Integer id) throws Exception {
        deliveryTrakingDto = DeliveryTrakingTemplateDto.deliveryTrakingTemplate(id.longValue());
        String deliveryTrakingJson = objectMapper.writeValueAsString(deliveryTrakingDto);
        mockMvc.perform(post("/delivery-traking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deliveryTrakingJson))
                .andExpect(status().isCreated());
    }

   @When("I send a POST request to create new delivery traking {string}")
public void iSendAPostRequestToCreateNewDeliveryTraking(String url) throws Exception {
    String deliveryTrakingJson = objectMapper.writeValueAsString(deliveryTrakingDto);
    responseContent = mockMvc.perform(post(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(deliveryTrakingJson))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse()
            .getContentAsString();
}

  @When("I send a GET request to fetch delivery traking {string}")
public void iSendAGetRequestToFetchDeliveryTraking(String url) throws Exception {
    responseContent = mockMvc.perform(get(url))
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();
}

    @When("I send a DELETE request to {string}")
    public void iSendADeleteRequestTo(String url) throws Exception {
        mockMvc.perform(delete(url))
                .andExpect(status().isNoContent());
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int status) throws Exception {
        mockMvc.perform(post("/delivery-traking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(status().is(status));
    }

    @Then("the response should contain the delivery traking details")
    public void theResponseShouldContainTheDeliveryTrakingDetails() throws Exception {
        mockMvc.perform(post("/delivery-traking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deliveryTrakingDto)))
                .andExpect(content().string(containsString(deliveryTrakingDto.deliveryPersonCode().toString())));
    }
}