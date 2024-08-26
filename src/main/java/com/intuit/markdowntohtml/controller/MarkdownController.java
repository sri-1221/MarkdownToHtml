package com.intuit.markdowntohtml.controller;

import com.intuit.markdowntohtml.service.MarkdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/markdowntohtml")
public class MarkdownController {
    private final MarkdownService markdownService;

    @Autowired
    public MarkdownController(MarkdownService markdownService) {
        this.markdownService = markdownService;
    }

    @PostMapping("/convert")
    public String convertMarkdownToHtml(@RequestBody(required = false) String markdown) {
        if (markdown==null || markdown.isEmpty() || markdown.isBlank()){
            return " ";
        }
        return markdownService.convertToHtml(markdown);
    }
}
