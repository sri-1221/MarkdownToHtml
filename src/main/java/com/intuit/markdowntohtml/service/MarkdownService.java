package com.intuit.markdowntohtml.service;

import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MarkdownService {

    public String convertToHtml(String markdown) {

        String html = markdown.trim();

        // Convert headings
        for (int i = 6; i >= 1; i--) {
            String headingPattern = String.format("(?m)^#{%d} (.+)$", i);
            String headingReplacement = String.format("<h%d>$1</h%d>", i, i);
            html = html.replaceAll(headingPattern, headingReplacement);
        }
        // Convert links
        html = html.replaceAll("\\[([^]]+)]\\(([^)]+)\\)", "<a href=\"$2\">$1</a>");

        html = wrapUnformattedTextInParagraphs(html);

        return html;
    }
    private String wrapUnformattedTextInParagraphs(String html) {
        // Pattern to match lines that are headings
        Pattern headingPattern = Pattern.compile("(?m)^<h\\d+>.*?</h\\d+>$");

        // Split the content by new lines to process each line individually
        String[] lines = html.split("(?<=\\n)\\n(?=\\S)");

        StringBuilder result = new StringBuilder();

        for (String line : lines) {
            String trimmedLine = line.trim();
            // Check if the line is a heading
            Matcher headingMatcher = headingPattern.matcher(trimmedLine);

            if (headingMatcher.find() || trimmedLine.equals("...") || trimmedLine.isEmpty()) {
                result.append(line).append("\n");
            } else {
                result.append("<p>")
                      .append(trimmedLine).append(" ")
                      .append("</p>\n\n");
            }
        }
        return result.toString();
    }
}
