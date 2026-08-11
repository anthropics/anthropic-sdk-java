package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.anthropic.models.messages.OutputConfig;
import com.anthropic.models.messages.StructuredMessageCreateParams;
import com.anthropic.models.messages.StructuredOutputConfig;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import java.util.List;

/**
 * <p>
 * Example demonstrating GA (non-beta) structured outputs where the output format is derived from the
 * structure of Java classes <i>and</i> other output configuration options (here, the effort level)
 * are set at the same time.
 * </p>
 * <p>
 * Compare to {@link StructuredOutputsExample}, which passes only the Java class to
 * {@code outputConfig(Class)} and so cannot set the other output configuration options.
 * </p>
 */
public final class StructuredOutputsOutputConfigExample {

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

        @Override
        public String toString() {
            return '"' + title + "\" (" + publicationYear + ") [" + genre + "] by " + author;
        }
    }

    public static class BookList {
        @ArraySchema(minItems = 1)
        public List<Book> books;
    }

    private StructuredOutputsOutputConfigExample() {}

    public static void main(String[] args) {
        // Configure by setting the `ANTHROPIC_API_KEY` environment variable.
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        // `format(Class)` derives the JSON schema for the output format from the class; the other
        // output configuration options (here, the effort level) are set alongside it.
        StructuredOutputConfig<BookList> outputConfig = StructuredOutputConfig.<BookList>builder()
                .effort(OutputConfig.Effort.HIGH)
                .format(BookList.class)
                .build();

        StructuredMessageCreateParams<BookList> createParams = MessageCreateParams.builder()
                .model(Model.CLAUDE_SONNET_5)
                .maxTokens(2048)
                .outputConfig(outputConfig)
                .addUserMessage("List some famous late twentieth century novels.")
                .build();

        client.messages().create(createParams).content().stream()
                .flatMap(contentBlock -> contentBlock.text().stream())
                .flatMap(textBlock -> textBlock.text().books.stream())
                .forEach(book -> System.out.println(" - " + book));
    }
}
