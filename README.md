# Title: Markdown to HTML Converter

## Description:

This repository provides a robust and efficient Java program that allows you to effortlessly convert Markdown text files to HTML format. It handles various Markdown elements ensuring reliable and accurate conversions.

## Features:
    * Supports common Markdown elements:

        * Headings (H1-H6)
        * Links (link text)
        * Paragraphs

    * Flexibility: 

        You can easily extend the code to support additional Markdown elements by adding more conditional checks.

## Installation:

    * Clone the Repository
        
            git clone https://github.com/sri-1221/MarkdownToHtml.git

    * Compile the code


## Usage:

    * Bash

       $ curl -X POST http://localhost:8080/api/markdowntohtml/convert \
            -H "Content-Type: text/plain" \
            -d "##### Sample Document"
        
       Output:

       $ <h5> Sample Document </h5>


    * Using Postman/Browser:

        Method: POST

        URL: http://localhost:8080/api/markdowntohtml/convert

        Headers:

            Content-Type: text/plain

            Body:

                Raw
                Text
                Input your Markdown content in the POST request body (e.g., # Sample Document)


## Contributing:

        Feel free to add contributions to this project and open pull requests with bug fixes, improvements, or support for additional Markdown elements.
