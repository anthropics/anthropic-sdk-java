package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.StructuredMessageCreateParams;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import java.util.List;

/**
 * Example demonstrating GA (non-beta) structured outputs.
 *
 * <p>This example uses the GA Messages API with structured outputs, which does NOT require any beta
 * headers. Compare with {@link BetaStructuredOutputsExample} which uses the beta API.
 */
public final class StructuredOutputsExample {

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

    private StructuredOutputsExample() {}

    public static void main(String[] args) {
        // Configure by setting the `ANTHROPIC_API_KEY` environment variable.
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // GA structured outputs - no beta header required!
        // Use client.messages() instead of client.beta().messages()
        StructuredMessageCreateParams<BookList> createParams = MessageCreateParams.builder()
                .model("claude-sonnet-4-5")
                .maxTokens(2048)
                .outputConfig(BookList.class)
                .addUserMessage("List some famous late twentieth century novels.")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .flatMap(textBlock -> textBlock.text().books.stream())
                .forEach(book -> System.out.println(" - " + book));
    }
}
