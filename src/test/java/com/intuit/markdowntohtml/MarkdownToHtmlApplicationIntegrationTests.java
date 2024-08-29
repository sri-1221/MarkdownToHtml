package com.intuit.markdowntohtml;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
public class MarkdownToHtmlApplicationIntegrationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testConvertMarkdownToHtml() throws Exception {
        String markdownInput = "# Heading";
        String expectedHtml = "<h1>Heading</h1>";

        // Perform the request and get the response
        MvcResult result =mockMvc.perform(post("/api/markdowntohtml/convert")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(markdownInput))
                        .andExpect(status().isOk())
                        .andReturn();

        // Get the actual HTML response
        String actualHtml = result.getResponse().getContentAsString();

        // Normalize content if needed
        String normalizedExpected = expectedHtml.replaceAll("\\s+", " ").trim();
        String normalizedActual = actualHtml.replaceAll("\\s+", " ").trim();

        // Assert normalized content
        assertEquals(normalizedExpected, normalizedActual);
    }

    @Test
    public void testConvertMarkdownToHtmlWithEmptyInput() throws Exception {
        mockMvc.perform(post("/api/markdowntohtml/convert")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(""))
                .andExpect(status().isOk())
                .andExpect(content().string(" "));
    }

}
