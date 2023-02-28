package io.example.app.backend.rest.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BaseUnitTest {
    @Autowired
    protected MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    public <T> T jsonToObject(String response, Class<T> classObject) throws JsonProcessingException {
        return mapper.readValue(response, classObject);
    }

    public <T> List<T> jsonToList(String response, Class<T> classElement) throws JsonProcessingException {
        return mapper.readValue(response, mapper.getTypeFactory().constructCollectionType(List.class, classElement));
    }

    public <T> String jsonToString(T classObject) throws JsonProcessingException {
        return mapper.writeValueAsString(classObject);
    }

    public String callApi(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, HttpStatus expectedStatus) throws Exception {

        return this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(status().is(expectedStatus.value()))
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
