package com.cometari.rest.functional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cometari.rest.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
 
	private MockMvc mockMvc;

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
            .andExpect(
                content().contentType(MediaTypes.HAL_JSON)
            );
    }
    
}
