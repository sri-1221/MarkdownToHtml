package com.intuit.markdowntohtml.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MarkdownService {

    // Converts Markdown to HTML
    public String convertToHtml(String markdown) {
        if (markdown == null || markdown.trim().isEmpty()) {
            return "";
        }

        // Convert Markdown to HTML
        String html = escapeHtml(markdown);
        html = convertHeadingsToHtml(html);
        html = convertLinksToHtml(html);
        html = wrapUnformattedTextInParagraphs(html);

        return html;
    }

    // Converts Markdown headings to HTML
    private String convertHeadingsToHtml(String markdown) {
        StringBuilder result = new StringBuilder();
        String[] lines = markdown.split("\n");

        for (String line : lines) {
            int headingLevel = getHeadingLevel(line);
            if (headingLevel > 0) {
                String content = line.substring(headingLevel).trim();
                result.append(String.format("<h%d>%s</h%d>\n", headingLevel, content, headingLevel));
            } else {
                result.append(line).append("\n");
            }
        }
        return result.toString();
    }

    private int getHeadingLevel(String line) {
        int level = 0;
        while (level < line.length() && line.charAt(level) == '#') {
            level++;
        }
        return (level > 0 && level <= 6 && line.charAt(level) == ' ') ? level : 0;
    }

    // Converts Markdown links to HTML links
    private String convertLinksToHtml(String markdown) {
        StringBuilder result = new StringBuilder();
        int pos = 0;
        String url=null;
        while (pos < markdown.length()) {
            int startLink = markdown.indexOf("[", pos);
            if (startLink == -1) {
                result.append(markdown.substring(pos));
                break;
            }
            result.append(markdown.substring(pos, startLink));
            int endLinkText = markdown.indexOf("]", startLink);
            if (endLinkText == -1) break;
            int startUrl = markdown.indexOf("(", endLinkText);
            if (startUrl == -1) break;
            int endUrl = markdown.indexOf(")", startUrl);
            if (endUrl == -1) break;

            String linkText = markdown.substring(startLink + 1, endLinkText);
            url = markdown.substring(startUrl + 1, endUrl);
            result.append(String.format("<a href=\"%s\">%s</a>", url, linkText));
            pos = endUrl + 1;
        }
        return result.toString();
    }

    // Wraps unformatted text in paragraph tags
    private String wrapUnformattedTextInParagraphs(String html) {
        StringBuilder result = new StringBuilder();
        String[] blocks = html.split("\n\n");

        for (String block : blocks) {
            String trimmedBlock = block.trim();
            if (trimmedBlock.isEmpty() ) {
                result.append("\n");
            } else {
                if (!isHtmlHeading(trimmedBlock)) {
                    result.append("<p>")
                            .append(trimmedBlock.replaceAll("\\s+", " "))
                            .append("</p>\n\n");
                } else {
                    result.append(trimmedBlock).append("\n\n");
                }
            }
        }
        return result.toString();
    }

    private boolean isHtmlHeading(String line) {
        return line.matches("<h\\d+>.*</h\\d+>");
    }
    private String escapeHtml(String text) {
        if (text == null) return null;
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

}
