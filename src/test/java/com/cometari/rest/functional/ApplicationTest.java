package com.cometari.rest.functional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cometari.rest.Application;
import com.cometari.rest.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ApplicationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
 
    private MockMvc mockMvc;

    private final ObjectWriter writer = new ObjectMapper()
        .writer()
        .withDefaultPrettyPrinter();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build();
    }

    @Test
    public void checkGetWorks() throws Exception {
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaTypes.HAL_JSON));
    }

    @Test
    public void checkCreatingResource() throws Exception {
        assertNoUsers();

        postExampleUser();

        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaTypes.HAL_JSON))
            .andExpect(jsonPath("$.page.totalElements").value(1));

        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaTypes.HAL_JSON))
            .andExpect(jsonPath("$.emailAddress").value("wp@wp.pl"))
            .andExpect(jsonPath("$.lastName").value("Kovolsky"))
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.passwordHash").value("testtesthash"))
            .andExpect(jsonPath("$.isActivated").value(true));
    }

    private void assertNoUsers() throws Exception {
        mockMvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaTypes.HAL_JSON))
            .andExpect(jsonPath("$.page.totalElements").value(0));
    }

    private void postExampleUser() throws Exception {
        User exampleUser = new User()
            .setEmailAddress("wp@wp.pl")
            .setLastName("Kovolsky")
            .setFirstName("John")
            .setPasswordHash("testtesthash")
            .setActivated(true);

        String json = this.writer.writeValueAsString(exampleUser);

        mockMvc.perform(
                post("/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(json)
            )
            .andExpect(status().isCreated());

    }

    @Test
    public void checkResourceUpdate() throws Exception {
        postExampleUser();

        mockMvc.perform(
                patch("/users/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{\"firstName\": \"Bilbo\"}")
            )
            .andExpect(status().isNoContent());

        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaTypes.HAL_JSON))
            .andExpect(jsonPath("$.emailAddress").value("wp@wp.pl"))
            .andExpect(jsonPath("$.lastName").value("Kovolsky"))
            .andExpect(jsonPath("$.firstName").value("Bilbo"))
            .andExpect(jsonPath("$.passwordHash").value("testtesthash"))
            .andExpect(jsonPath("$.isActivated").value(true));
    }

    @Test
    public void checkResourceDelete() throws Exception {
        postExampleUser();

        mockMvc.perform(
                delete("/users/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
            )
            .andExpect(status().isNoContent());

        assertNoUsers();
    }

    @Test
    public void checkEmailValidation() throws Exception {
        mockMvc.perform(
                post("/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{\"emailAddress\": \"Bilbo\", \"passwordHash\": null}")
            )
            .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void checkBadRequest() throws Exception {
        postExampleUser();

        mockMvc.perform(
                post("/users")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{\"emailAddress\"")
            )
            .andExpect(status().isBadRequest());
    }
}
