package com.intuit.markdowntohtml;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
        String expectedHtml = "<h1>Heading</h1>\n";

        mockMvc.perform(post("/api/markdowntohtml/convert")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(markdownInput))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedHtml));
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
