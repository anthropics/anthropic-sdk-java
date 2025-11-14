package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.models.beta.messages.StructuredMessageCreateParams;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import java.util.List;

public final class BetaStructuredOutputsExample {

    public static class Person {
        @JsonPropertyDescription("The first name and surname of the person.")
        public String name;

        public int birthYear;

        @JsonPropertyDescription("The year the person died, or 'present' if the person is living.")
        public String deathYear;

        @Override
        public String toString() {
            return name + " (" + birthYear + '-' + deathYear + ')';
        }
    }

    public static class Book {
        public String title;

        public Person author;

        @JsonPropertyDescription("The year in which the book was first published. No earlier than 1500.")
        public int publicationYear;

        public String genre;

        @JsonIgnore
        public String isbn;

        @Override
        public String toString() {
            return '"' + title + "\" (" + publicationYear + ") [" + genre + "] by " + author;
        }
    }

    public static class BookList {
        @ArraySchema(minItems = 1)
        public List<Book> books;
    }

    private BetaStructuredOutputsExample() {}

    public static void main(String[] args) {
        // Configure by setting the `ANTHROPIC_API_KEY` environment variable.
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        StructuredMessageCreateParams<BookList> createParams = MessageCreateParams.builder()
                .model("claude-sonnet-4-5-20250929-structured-outputs")
                .putAdditionalHeader("anthropic-beta", "structured-outputs-2025-09-17")
                .maxTokens(2048)
                .outputFormat(BookList.class)
                .addUserMessage("List some famous late twentieth century novels.")
                .build();

        client.beta().messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .flatMap(textBlock -> textBlock.text().books.stream())
                .forEach(book -> System.out.println(" - " + book));
    }
}
