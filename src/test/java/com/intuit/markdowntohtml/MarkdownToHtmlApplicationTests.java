package com.intuit.markdowntohtml;

import com.intuit.markdowntohtml.controller.MarkdownController;
import com.intuit.markdowntohtml.service.MarkdownService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MarkdownToHtmlApplicationTests {

    @InjectMocks
    private MarkdownController markdownController;

    @Mock
    private MarkdownService markdownService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertMarkdownToHtml_EmptyMarkdown() {
        // Arrange
        String markdown = "";
        String expectedHtml = " ";
        when(markdownService.convertToHtml(markdown)).thenReturn(expectedHtml);

        // Act
        String actualHtml = markdownController.convertMarkdownToHtml(markdown);

        // Assert
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    void testConvertMarkdownToHtml_NullMarkdown() {
        // Arrange
        String markdown = null;
        String expectedHtml = " ";
        when(markdownService.convertToHtml(markdown)).thenReturn(expectedHtml);

        // Act
        String actualHtml = markdownController.convertMarkdownToHtml(markdown);

        // Assert
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    void testConvertMarkdownToHtml_ValidMarkdown() {
        // Arrange
        String markdown = "# Hello World\nThis is Markdown";
        String expectedHtml = "<h1>Hello World</h1>\n<p>This is Markdown</p>";
        when(markdownService.convertToHtml(markdown)).thenReturn(expectedHtml);

        // Act
        String actualHtml = markdownController.convertMarkdownToHtml(markdown);

        // Assert
        assertEquals(expectedHtml, actualHtml);
    }
    @Test
    void testConvertMarkdownToHtml_BlankMarkdown() {
        // Arrange
        String markdown = "   "; // Blank but with spaces
        String expectedHtml = " ";
        when(markdownService.convertToHtml(markdown)).thenReturn(expectedHtml);

        // Act
        String actualHtml = markdownController.convertMarkdownToHtml(markdown);

        // Assert
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    void testConvertMarkdownToHtml_WithMultipleHeadings() {
        // Arrange
        String markdown = "# Heading 1\n## Heading 2\n### Heading 3\nSome text.";
        String expectedHtml = "<h1>Heading 1</h1>\n<h2>Heading 2</h2>\n<h3>Heading 3</h3>\n<p>Some text.</p>";
        when(markdownService.convertToHtml(markdown)).thenReturn(expectedHtml);

        // Act
        String actualHtml = markdownController.convertMarkdownToHtml(markdown);

        // Assert
        assertEquals(expectedHtml, actualHtml);
    }

    @Test
    void testConvertMarkdownToHtml_WithLink() {
        // Arrange
        String markdown = "This is a link to [Wikipedia](https://en.wikipedia.org/).";
        String expectedHtml = "<p>This is a link to <a href=\"https://en.wikipedia.org/\">Wikipedia</a>.</p>" ;
        when(markdownService.convertToHtml(markdown)).thenReturn(expectedHtml);
        // Act
        String actualHtml = markdownController.convertMarkdownToHtml(markdown);

        // Assert
        assertEquals(expectedHtml, actualHtml);
    }
    }

