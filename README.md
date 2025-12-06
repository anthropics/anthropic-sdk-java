# Anthropic Java API Library

<!-- x-release-please-start-version -->

[![Maven Central](https://img.shields.io/maven-central/v/com.anthropic/anthropic-java)](https://central.sonatype.com/artifact/com.anthropic/anthropic-java/2.12.0)
[![javadoc](https://javadoc.io/badge2/com.anthropic/anthropic-java/2.12.0/javadoc.svg)](https://javadoc.io/doc/com.anthropic/anthropic-java/2.12.0)

<!-- x-release-please-end -->

The Anthropic Java SDK provides convenient access to the [Anthropic REST API](https://docs.anthropic.com/claude/reference/) from applications written in Java.

<!-- x-release-please-start-version -->

The REST API documentation can be found on [docs.anthropic.com](https://docs.anthropic.com/claude/reference/). Javadocs are available on [javadoc.io](https://javadoc.io/doc/com.anthropic/anthropic-java/2.12.0).

<!-- x-release-please-end -->

## Installation

<!-- x-release-please-start-version -->

### Gradle

```kotlin
implementation("com.anthropic:anthropic-java:2.12.0")
```

### Maven

```xml
<dependency>
  <groupId>com.anthropic</groupId>
  <artifactId>anthropic-java</artifactId>
  <version>2.12.0</version>
</dependency>
```

<!-- x-release-please-end -->

## Requirements

This library requires Java 8 or later.

## Usage

See the [`anthropic-java-example`](anthropic-java-example/src/main/java/com/anthropic/example) directory for complete and runnable examples.

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

// Configures using the `anthropic.apiKey`, `anthropic.authToken` and `anthropic.baseUrl` system properties
// Or configures using the `ANTHROPIC_API_KEY`, `ANTHROPIC_AUTH_TOKEN` and `ANTHROPIC_BASE_URL` environment variables
AnthropicClient client = AnthropicOkHttpClient.fromEnv();

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_OPUS_4_5_20251101)
    .build();
Message message = client.messages().create(params);
```

## Client configuration

Configure the client using system properties or environment variables:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

// Configures using the `anthropic.apiKey`, `anthropic.authToken` and `anthropic.baseUrl` system properties
// Or configures using the `ANTHROPIC_API_KEY`, `ANTHROPIC_AUTH_TOKEN` and `ANTHROPIC_BASE_URL` environment variables
AnthropicClient client = AnthropicOkHttpClient.fromEnv();
```

Or manually:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .apiKey("my-anthropic-api-key")
    .build();
```

Or using a combination of the two approaches:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.builder()
    // Configures using the `anthropic.apiKey`, `anthropic.authToken` and `anthropic.baseUrl` system properties
    // Or configures using the `ANTHROPIC_API_KEY`, `ANTHROPIC_AUTH_TOKEN` and `ANTHROPIC_BASE_URL` environment variables
    .fromEnv()
    .apiKey("my-anthropic-api-key")
    .build();
```

See this table for the available options:

| Setter      | System property       | Environment variable   | Required | Default value                 |
| ----------- | --------------------- | ---------------------- | -------- | ----------------------------- |
| `apiKey`    | `anthropic.apiKey`    | `ANTHROPIC_API_KEY`    | false    | -                             |
| `authToken` | `anthropic.authToken` | `ANTHROPIC_AUTH_TOKEN` | false    | -                             |
| `baseUrl`   | `anthropic.baseUrl`   | `ANTHROPIC_BASE_URL`   | true     | `"https://api.anthropic.com"` |

System properties take precedence over environment variables.

> [!TIP]
> Don't create more than one client in the same application. Each client has a connection pool and
> thread pools, which are more efficient to share between requests.

### Modifying configuration

To temporarily use a modified client configuration, while reusing the same connection and thread pools, call `withOptions()` on any client or service:

```java
import com.anthropic.client.AnthropicClient;

AnthropicClient clientWithOptions = client.withOptions(optionsBuilder -> {
    optionsBuilder.baseUrl("https://example.com");
    optionsBuilder.maxRetries(42);
});
```

The `withOptions()` method does not affect the original client or service.

## Requests and responses

To send a request to the Anthropic API, build an instance of some `Params` class and pass it to the corresponding client method. When the response is received, it will be deserialized into an instance of a Java class.

For example, `client.messages().create(...)` should be called with an instance of `MessageCreateParams`, and it will return an instance of `Message`.

### Long requests

> [!IMPORTANT]
> We highly encourage you to use [streaming](#streaming) for longer running requests.

We do not recommend setting a large `maxTokens` value without using streaming. Some networks may drop idle connections after a certain period of time, which can cause the request to fail or [timeout](#timeouts) without receiving a response from Anthropic. We periodically ping the API to keep the connection alive and reduce the impact of these networks.

The SDK throws an error if a non-streaming request is expected to take longer than 10 minutes. Using a [streaming method](#streaming) or [overriding the timeout](#timeouts) at the client or request level disables the error.

## Immutability

Each class in the SDK has an associated [builder](https://blogs.oracle.com/javamagazine/post/exploring-joshua-blochs-builder-design-pattern-in-java) or factory method for constructing it.

Each class is [immutable](https://docs.oracle.com/javase/tutorial/essential/concurrency/immutable.html) once constructed. If the class has an associated builder, then it has a `toBuilder()` method, which can be used to convert it back to a builder for making a modified copy.

Because each class is immutable, builder modification will _never_ affect already built class instances.

## Asynchronous execution

The default client is synchronous. To switch to asynchronous execution, call the `async()` method:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import java.util.concurrent.CompletableFuture;

// Configures using the `anthropic.apiKey`, `anthropic.authToken` and `anthropic.baseUrl` system properties
// Or configures using the `ANTHROPIC_API_KEY`, `ANTHROPIC_AUTH_TOKEN` and `ANTHROPIC_BASE_URL` environment variables
AnthropicClient client = AnthropicOkHttpClient.fromEnv();

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_OPUS_4_5_20251101)
    .build();
CompletableFuture<Message> message = client.async().messages().create(params);
```

Or create an asynchronous client from the beginning:

```java
import com.anthropic.client.AnthropicClientAsync;
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import java.util.concurrent.CompletableFuture;

// Configures using the `anthropic.apiKey`, `anthropic.authToken` and `anthropic.baseUrl` system properties
// Or configures using the `ANTHROPIC_API_KEY`, `ANTHROPIC_AUTH_TOKEN` and `ANTHROPIC_BASE_URL` environment variables
AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_OPUS_4_5_20251101)
    .build();
CompletableFuture<Message> message = client.messages().create(params);
```

The asynchronous client supports the same options as the synchronous one, except most methods return `CompletableFuture`s.

## Streaming

The SDK defines methods that return response "chunk" streams, where each chunk can be individually processed as soon as it arrives instead of waiting on the full response. Streaming methods generally correspond to [SSE](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events) or [JSONL](https://jsonlines.org) responses.

Some of these methods may have streaming and non-streaming variants, but a streaming method will always have a `Streaming` suffix in its name, even if it doesn't have a non-streaming variant.

These streaming methods return [`StreamResponse`](anthropic-java-core/src/main/kotlin/com/anthropic/core/http/StreamResponse.kt) for synchronous clients:

```java
import com.anthropic.core.http.StreamResponse;
import com.anthropic.models.messages.RawMessageStreamEvent;

try (StreamResponse<RawMessageStreamEvent> streamResponse = client.messages().createStreaming(params)) {
    streamResponse.stream().forEach(chunk -> {
        System.out.println(chunk);
    });
    System.out.println("No more chunks!");
}
```

Or [`AsyncStreamResponse`](anthropic-java-core/src/main/kotlin/com/anthropic/core/http/AsyncStreamResponse.kt) for asynchronous clients:

```java
import com.anthropic.core.http.AsyncStreamResponse;
import com.anthropic.models.messages.RawMessageStreamEvent;
import java.util.Optional;

client.async().messages().createStreaming(params).subscribe(chunk -> {
    System.out.println(chunk);
});

// If you need to handle errors or completion of the stream
client.async().messages().createStreaming(params).subscribe(new AsyncStreamResponse.Handler<>() {
    @Override
    public void onNext(RawMessageStreamEvent chunk) {
        System.out.println(chunk);
    }

    @Override
    public void onComplete(Optional<Throwable> error) {
        if (error.isPresent()) {
            System.out.println("Something went wrong!");
            throw new RuntimeException(error.get());
        } else {
            System.out.println("No more chunks!");
        }
    }
});

// Or use futures
client.async().messages().createStreaming(params)
    .subscribe(chunk -> {
        System.out.println(chunk);
    })
    .onCompleteFuture();
    .whenComplete((unused, error) -> {
        if (error != null) {
            System.out.println("Something went wrong!");
            throw new RuntimeException(error);
        } else {
            System.out.println("No more chunks!");
        }
    });
```

Async streaming uses a dedicated per-client cached thread pool [`Executor`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html) to stream without blocking the current thread. This default is suitable for most purposes.

To use a different `Executor`, configure the subscription using the `executor` parameter:

```java
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

Executor executor = Executors.newFixedThreadPool(4);
client.async().messages().createStreaming(params).subscribe(
    chunk -> System.out.println(chunk), executor
);
```

Or configure the client globally using the `streamHandlerExecutor` method:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import java.util.concurrent.Executors;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    .streamHandlerExecutor(Executors.newFixedThreadPool(4))
    .build();
```

### Streaming helpers

The SDK provides conveniences for streamed messages. A
[`MessageAccumulator`](anthropic-java-core/src/main/kotlin/com/anthropic/helpers/MessageAccumulator.kt)
can record the stream of events in the response as they are processed and accumulate a
[`Message`](anthropic-java-core/src/main/kotlin/com/anthropic/models/messages/Message.kt) object
similar to that which would have been returned by the non-streaming API.

A [`BetaMessageAccumulator`](anthropic-java-core/src/main/kotlin/com/anthropic/helpers/BetaMessageAccumulator.kt)
is also available for the accumulation of a
[`BetaMessage`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/BetaMessage.kt)
object. It is used in the same manner as the `MessageAccumulator`.

For a synchronous response add a
[`Stream.peek()`](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#peek-java.util.function.Consumer-)
call to the stream pipeline to accumulate each event:

```java
import com.anthropic.core.http.StreamResponse;
import com.anthropic.helpers.MessageAccumulator;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.RawMessageStreamEvent;

MessageAccumulator messageAccumulator = MessageAccumulator.create();

try (StreamResponse<RawMessageStreamEvent> streamResponse =
         client.messages().createStreaming(createParams)) {
    streamResponse.stream()
            .peek(messageAccumulator::accumulate)
            .flatMap(event -> event.contentBlockDelta().stream())
            .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
            .forEach(textDelta -> System.out.print(textDelta.text()));
}

Message message = messageAccumulator.message();
```

For an asynchronous response, add the `MessageAccumulator` to the `subscribe()` call:

```java
import com.anthropic.helpers.MessageAccumulator;
import com.anthropic.models.messages.Message;

MessageAccumulator messageAccumulator = MessageAccumulator.create();

client.messages()
        .createStreaming(createParams)
        .subscribe(event -> messageAccumulator.accumulate(event).contentBlockDelta().stream()
                .flatMap(deltaEvent -> deltaEvent.delta().text().stream())
                .forEach(textDelta -> System.out.print(textDelta.text())))
        .onCompleteFuture()
        .join();

Message message = messageAccumulator.message();
```

## Structured outputs with JSON schemas

Anthropic [Structured Outputs](https://docs.claude.com/docs/en/build-with-claude/structured-outputs)
(beta) is a feature that ensures that the model will always generate responses that adhere to a
supplied [JSON schema](https://json-schema.org/overview/what-is-jsonschema).

A JSON schema can be defined by creating a
[`BetaJsonOutputFormat`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/BetaJsonOutputFormat.kt)
and setting it on the input parameters. However, for greater convenience, a JSON schema can instead
be derived automatically from the structure of an arbitrary Java class. The JSON content from the
response will then be converted automatically to an instance of that Java class. A full, working
example of the use of Structured Outputs with arbitrary Java classes can be seen in
[`BetaStructuredOutputsExample`](anthropic-java-example/src/main/java/com/anthropic/example/BetaStructuredOutputsExample.java).

Java classes can contain fields declared to be instances of other classes and can use collections
(see [Defining JSON schema properties](#defining-json-schema-properties) for more details):

```java
class Person {
    public String name;
    public int birthYear;
}

class Book {
    public String title;
    public Person author;
    public int publicationYear;
}

class BookList {
    public List<Book> books;
}
```

Pass the top-level class—`BookList` in this example—to `outputFormat(Class<T>)` when building the
parameters and then access an instance of `BookList` from the generated message content in the
response:

```java
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.models.beta.messages.StructuredMessageCreateParams;
import com.anthropic.models.messages.Model;

StructuredMessageCreateParams<BookList> createParams = MessageCreateParams.builder()
        .model(Model.CLAUDE_SONNET_4_20250514)
        .maxTokens(2048)
        .outputFormat(BookList.class)
        .addUserMessage("List some famous late twentieth century novels.")
        .build();

client.beta().messages().create(createParams).content().stream()
        .flatMap(contentBlock -> contentBlock.text().stream())
        .flatMap(textBlock -> textBlock.text().books.stream())
        .forEach(book -> System.out.println(book.title + " by " + book.author.name));
```

You can start building the parameters with an instance of
[`MessageCreateParams.Builder`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/MessageCreateParams.kt)
or
[`StructuredMessageCreateParams.Builder`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/StructuredMessageCreateParams.kt).
If you start with the former (which allows for more compact code) the builder type will change to
the latter when `MessageCreateParams.Builder.outputFormat(Class<T>)` is called.

If a field in a class is optional and does not require a defined value, you can represent this using
the [`java.util.Optional`](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html) class.
It is up to the AI model to decide whether to provide a value for that field or leave it empty.

```java
import java.util.Optional;

class Book {
    public String title;
    public Person author;
    public int publicationYear;
    public Optional<String> isbn;
}
```

Generic type information for fields is retained in the class's metadata, but _generic type erasure_
applies in other scopes. While, for example, a JSON schema defining an array of books can be derived
from the `BookList.books` field with type `List<Book>`, a valid JSON schema cannot be derived from a
local variable of that same type, so the following will _not_ work:

```java
List<Book> books = new ArrayList<>();

StructuredMessageCreateParams<List<Book>> params = MessageCreateParams.builder()
        .outputFormat(books.getClass())
        // ...
        .build();
```

If an error occurs while converting a JSON response to an instance of a Java class, the error
message will include the JSON response to assist in diagnosis. For instance, if the response is
truncated, the JSON data will be incomplete and cannot be converted to a class instance. If your
JSON response may contain sensitive information, avoid logging it directly, or ensure that you
redact any sensitive details from the error message.

### Local JSON schema validation

_Structured Outputs_ supports a
[subset](https://docs.claude.com/docs/en/build-with-claude/structured-outputs#json-schema-limitations)
of the JSON Schema language. Schemas are generated automatically from classes to align with this
subset. However, due to the inherent structure of the classes, the generated schema may still
violate certain Anthropic schema restrictions, such as utilizing unsupported data types.

To facilitate compliance, the method `outputFormat(Class<T>)` performs a validation check on the
schema derived from the specified class. This validation ensures that all restrictions are adhered
to. If any issues are detected, an exception will be thrown, providing a detailed message outlining
the reasons for the validation failure.

- **Local Validation**: The validation process occurs locally, meaning no requests are sent to the
  remote AI model. If the schema passes local validation, it is likely to pass remote validation as
  well.
- **Remote Validation**: The remote AI model will conduct its own validation upon receiving the JSON
  schema in the request.
- **Version Compatibility**: There may be instances where local validation fails while remote
  validation succeeds. This can occur if the SDK version is outdated compared to the restrictions
  enforced by the remote AI model.
- **Disabling Local Validation**: If you encounter compatibility issues and wish to bypass local
  validation, you can disable it by passing
  [`JsonSchemaLocalValidation.NO`](anthropic-java-core/src/main/kotlin/com/anthropic/core/JsonSchemaLocalValidation.kt)
  to the `outputFormat(Class<T>, JsonSchemaLocalValidation)` method when building the parameters.
  (The default value for this parameter is `JsonSchemaLocalValidation.YES`.)

```java
import com.anthropic.core.JsonSchemaLocalValidation;
import com.anthropic.models.beta.messages.MessageCreateParams;
import com.anthropic.models.beta.messages.StructuredMessageCreateParams;
import com.anthropic.models.messages.Model;

StructuredMessageCreateParams<BookList> createParams = MessageCreateParams.builder()
        .model(Model.CLAUDE_SONNET_4_20250514)
        .maxTokens(2048)
        .outputFormat(BookList.class, JsonSchemaLocalValidation.NO)
        .addUserMessage("List some famous late twentieth century novels.")
        .build();

```

By following these guidelines, you can ensure that your structured outputs conform to the necessary
schema requirements and minimize the risk of remote validation errors.

### Usage with streaming

_Structured Outputs_ can also be used with [Streaming](#streaming) and the Messages API. As
responses are returned in "stream events", the full response must first be accumulated to
concatenate the JSON strings that can then be converted into instances of the arbitrary Java class.
Normal streaming operations can be performed while accumulating the JSON strings.

Use the [`BetaMessageAccumulator`](anthropic-java-core/src/main/kotlin/com/anthropic/helpers/BetaMessageAccumulator.kt)
as described in the section on [Streaming helpers](#streaming-helpers) to accumulate the JSON
strings. Once accumulated, use `BetaMessageAccumulator.message(Class<T>)` to convert the
accumulated `BetaMessage` into a
[`StructuredMessage`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/StructuredMessage.kt).
The `StructuredMessage` can then automatically deserialize the JSON strings into instances of your
Java class.

For a full example of the usage of _Structured Outputs_ with Streaming and the Messages API, see
[`BetaStructuredOutputsStreamingExample`](anthropic-java-example/src/main/java/com/anthropic/example/BetaStructuredOutputsStreamingExample.java).

### Defining JSON schema properties

When a JSON schema is derived from your Java classes, all properties represented by `public` fields
or `public` getter methods are included in the schema by default. Non-`public` fields and getter
methods are _not_ included by default. You can exclude `public`, or include non-`public` fields or
getter methods, by using the `@JsonIgnore` or `@JsonProperty` annotations respectively (see
[Annotating classes and JSON schemas](#annotating-classes-and-json-schemas) for details).

If you do not want to define `public` fields, you can define `private` fields and corresponding
`public` getter methods. For example, a `private` field `myValue` with a `public` getter method
`getMyValue()` will result in a `"myValue"` property being included in the JSON schema. If you
prefer not to use the conventional Java "get" prefix for the name of the getter method, then you
_must_ annotate the getter method with the `@JsonProperty` annotation and the full method name will
be used as the property name. You do not have to define any corresponding setter methods if you do
not need them.in

Each of your classes _must_ define at least one property to be included in the JSON schema. A
validation error will occur if any class contains no fields or getter methods from which schema
properties can be derived. This may occur if, for example:

- There are no fields or getter methods in the class.
- All fields and getter methods are `public`, but all are annotated with `@JsonIgnore`.
- All fields and getter methods are non-`public`, but none are annotated with `@JsonProperty`.
- A field or getter method is declared with a `Map` type. A `Map` is treated like a separate class
  with no named properties, so it will result in an empty `"properties"` field in the JSON schema.

### Annotating classes and JSON schemas

You can use annotations to add further information to the JSON schema derived from your Java
classes, or to control which fields or getter methods will be included in the schema. Details from
annotations captured in the JSON schema may be used by the AI model to improve its response. The SDK
supports the use of [Jackson Databind](https://github.com/FasterXML/jackson-databind) annotations.

```java
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

class Person {
    @JsonPropertyDescription("The first name and surname of the person")
    public String name;
    public int birthYear;
    @JsonPropertyDescription("The year the person died, or 'present' if the person is living.")
    public String deathYear;
}

@JsonClassDescription("The details of one published book")
class Book {
    public String title;
    public Person author;
    @JsonPropertyDescription("The year in which the book was first published.")
    public int publicationYear;
    @JsonIgnore public String genre;
}

class BookList {
    public List<Book> books;
}
```

- Use `@JsonClassDescription` to add a detailed description to a class.
- Use `@JsonPropertyDescription` to add a detailed description to a field or getter method of a
  class.
- Use `@JsonIgnore` to exclude a `public` field or getter method of a class from the generated JSON
  schema.
- Use `@JsonProperty` to include a non-`public` field or getter method of a class in the generated
  JSON schema.

If you use `@JsonProperty(required = false)`, the `false` value will be ignored. Anthropic JSON
schemas must mark all properties as _required_, so the schema generated from your Java classes will
respect that restriction and ignore any annotation that would violate it.

You can also use [OpenAPI Swagger 2](https://swagger.io/specification/v2/)
[`@Schema`](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#schema) and
[`@ArraySchema`](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#arrayschema)
annotations. These allow type-specific constraints to be added to your schema properties. You can
learn more about the supported constraints in the Anthropic documentation on
[Supported properties](https://docs.claude.com/docs/en/build-with-claude/structured-outputs).

```java
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

class Article {
    @ArraySchema(minItems = 1)
    public List<String> authors;

    public String title;

    @Schema(format = "date")
    public String publicationDate;

    @Schema(minimum = "1")
    public int pageCount;
}
```

Local validation will check that you have not used any unsupported constraint keywords. However, the
values of the constraints are _not_ validated locally. For example, if you use a value for the
`"format"` constraint of a string property that is not in the list of
[supported format names](https://docs.claude.com/docs/en/build-with-claude/structured-outputs),
then local validation will pass, but the AI model may report an error.

If you use both Jackson and Swagger annotations to set the same schema field, the Jackson annotation
will take precedence. In the following example, the description of `myProperty` will be set to
"Jackson description"; "Swagger description" will be ignored:

```java
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.Schema;

class MyObject {
    @Schema(description = "Swagger description")
    @JsonPropertyDescription("Jackson description")
    public String myProperty;
}
```

## Tool use with JSON schemas

Anthropic [Tool Use](https://docs.anthropic.com/en/docs/agents-and-tools/tool-use/overview)
lets you integrate external tools and functions directly into the AI model's responses. Instead of
producing plain text, the model can output instructions (with parameters) for invoking a tool or
calling a function when appropriate. You define
[JSON schemas](https://json-schema.org/overview/what-is-jsonschema) for tools, and the model uses
the schemas to decide when and how to use these tools, enabling more interactive, data-driven
applications.

Now in beta, the tool use feature supports a "strict" mode that guarantees that the JSON output from
the AI model will conform to a JSON schema that you provide in the input parameters.

Use the API to define a JSON schema describing a tool's parameters with a
[`BetaTool.InputSchema`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/BetaTool.kt),
then build a [`BetaTool`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/BetaTool.kt)
that uses that `InputSchema`, then add that `BetaTool` to the
[`MessageCreateParams`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/MessageCreateParams.kt)
using `addTool`. The response from the AI model may then contain `"tool_use"` requests to invoke
your tools (or call your functions), detailing the tools' names and their parameter values as JSON
data that conforms to the JSON schema from the `InputSchema` definition. You can then parse the
parameter values from this JSON, invoke your tools, and pass your tools' results back to the AI
model. A full, working example of _Tool Use_ using the low-level API can be seen in
[`BetaMessagesToolsRawExample`](anthropic-java-example/src/main/java/com/anthropic/example/BetaMessagesToolsRawExample.java).

However, for greater convenience, the SDK can derive a tool and its parameters automatically from
the structure of an arbitrary Java class: the class's name (converted to snake case) provides the
tool name, and the class's fields define the tool's parameters. When the AI model responds with the
parameter values in JSON form, you can then easily convert that JSON to an instance of your Java
class and use the parameter values to invoke your custom tool. A full, working example of the use of
_Tool Use_ with Java classes to define a tool and its parameters can be seen in
[`BetaMessagesToolsExample`](anthropic-java-example/src/main/java/com/anthropic/example/BetaMessagesToolsExample.java).

Like for [Structured Outputs](#structured-outputs-with-json-schemas), Java classes can contain
fields declared to be instances of other classes and can use collections (see
[Defining JSON schema properties](#defining-json-schema-properties) for more details). Optionally,
annotations can be used to set the descriptions of the tool (class) and its parameters (fields) to
assist the AI model in understanding the purpose of the tool and the possible values of its
parameters.

```java
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

enum Unit {
  CELSIUS, FAHRENHEIT;

  public String toString() {
    switch (this) {
      case CELSIUS: return "°C";
      case FAHRENHEIT: default: return "°F";
    }
  }

  public double fromKelvin(double temperatureK) {
    switch (this) {
      case CELSIUS: return temperatureK - 273.15;
      case FAHRENHEIT: default: return (temperatureK - 273.15) * 1.8 + 32.0;
    }
  }
}

@JsonClassDescription("Get the weather in a given location")
static class GetWeather {
  @JsonPropertyDescription("The city and state, e.g. San Francisco, CA")
  public String location;

  @JsonPropertyDescription("The unit of temperature")
  public Unit unit;

  public Weather execute() {
    double temperatureK;
    switch (location) {
      case "San Francisco, CA": temperatureK = 300.0; break;
      case "New York, NY": temperatureK = 310.0; break;
      case "Dallas, TX": temperatureK = 305.0; break;
      default: temperatureK = 295; break;
    }
    return new Weather(String.format("%.0f%s", unit.fromKelvin(temperatureK), unit));
  }
}

static class Weather {
  public String temperature;

  public Weather(String temperature) {
    this.temperature = temperature;
  }
}
```

When your tool classes are defined, add them to the message parameters using
`MessageCreateParams.addTool(Class<T>)` and then call them if requested to do so in the AI model's
response.
[`BetaToolUseBlock.input(Class<T>)`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/BetaToolUseBlock.kt)
can be used to parse a tool's parameters in JSON form to an instance of your tool-defining class.
The fields of that instance will be set to the values of the parameters to the tool use.

After invoking the tool, use
[`BetaToolResultBlockParam.Builder.contentAsJson(Object)`](anthropic-java-core/src/main/kotlin/com/anthropic/models/beta/messages/BetaToolResultBlockParam.kt)
to pass the tool's result back to the AI model. The method will convert the result to JSON form for
consumption by the model. The `Object` can be any object, including simple `String` instances and
boxed primitive types.

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.beta.messages.*;
import com.anthropic.models.messages.Model;
import java.util.List;

AnthropicClient client = AnthropicOkHttpClient.fromEnv();

MessageCreateParams.Builder createParamsBuilder = MessageCreateParams.builder()
        .model(Model.CLAUDE_SONNET_4_20250514)
        .maxTokens(2048)
        .addTool(GetWeather.class)
        .addUserMessage("What's the temperature in New York?");

client.beta().messages().create(createParamsBuilder.build()).content().stream()
        .flatMap(contentBlock -> contentBlock.toolUse().stream())
        .forEach(toolUseBlock -> createParamsBuilder
              // Add a message indicating that the tool use was requested.
              .addAssistantMessageOfBetaContentBlockParams(
                      List.of(BetaContentBlockParam.ofToolUse(BetaToolUseBlockParam.builder()
                              .name(toolUseBlock.name())
                              .id(toolUseBlock.id())
                              .input(toolUseBlock._input())
                              .build())))
              // Add a message with the result of the requested tool use.
              .addUserMessageOfBetaContentBlockParams(
                      List.of(BetaContentBlockParam.ofToolResult(BetaToolResultBlockParam.builder()
                              .toolUseId(toolUseBlock.id())
                              .contentAsJson(callTool(toolUseBlock))
                              .build()))));

client.beta().messages().create(createParamsBuilder.build()).content().stream()
        .flatMap(contentBlock -> contentBlock.text().stream())
        .forEach(textBlock -> System.out.println(textBlock.text()));

private static Object callTool(BetaToolUseBlock toolUseBlock) {
  if (!"get_weather".equals(toolUseBlock.name())) {
    throw new IllegalArgumentException("Unknown tool: " + toolUseBlock.name());
  }

  GetWeather tool = toolUseBlock.input(GetWeather.class);
  return tool != null ? tool.execute() : new Weather("unknown");
}
```

In the code above, an `execute()` method encapsulates each tool's logic. However, there is no
requirement to follow that pattern. You are free to implement your tool's logic in any way that
best suits your use case. The pattern above is only intended to _suggest_ that a suitable pattern
may make the process of tool use simpler to understand and implement.

The tool names are derived from the camel case tool class names (e.g., `GetWeather`) and converted
to snake case (e.g., `get_weather`). All characters are converted to lower-case and underscores are
inserted on word boundaries. Word boundaries begin where the current character is not the first
character, is upper-case, and either the preceding character is lower-case, or the following
character is lower-case. For example, `MyJSONParser` becomes `my_json_parser` and `ParseJSON`
becomes `parse_json`. This conversion can be overridden using the `@JsonTypeName` annotation (see
[Annotating tool classes](#annotating-tool-classes))

### Local tool JSON schema validation

Like for _Structured Outputs_, you can perform local validation to check that the JSON schema
derived from your tool class respects the restrictions imposed by Anthropic on such schemas. Local
validation is enabled by default, but it can be disabled by adding `JsonSchemaLocalValidation.NO` to
the call to `addTool`.

```java
MessageCreateParams.Builder createParamsBuilder = MessageCreateParams.builder()
        .model(Model.CLAUDE_SONNET_4_20250514)
        .maxTokens(2048)
        .addTool(GetWeather.class, JsonSchemaLocalValidation.NO)
        .addUserMessage("What's the temperature in New York?");
```

See [Local JSON schema validation](#local-json-schema-validation) for more details on local schema
validation and under what circumstances you might want to disable it.

### Annotating tool classes

You can use annotations to add further information about tools to the JSON schemas that are derived
from your tool classes, or to control which fields or getter methods will be used as parameters to
the tool. Details from annotations captured in the JSON schema may be used by the AI model to
improve its response. The SDK supports the use of
[Jackson Databind](https://github.com/FasterXML/jackson-databind) annotations.

- Use `@JsonClassDescription` to add a description to a tool class detailing when and how to use
  that tool.
- Use `@JsonTypeName` to set the tool name to something other than the simple name of the class
  converted to snake case, which is used by default.
- Use `@JsonPropertyDescription` to add a detailed description to a tool parameter (a field or
  getter method of a tool class). Where JSON schema constraints are not supported, these might be
  added as textual descriptions using this annotation.
- Use `@JsonIgnore` to exclude a `public` field or getter method of a class from the generated JSON
  schema for a tool's parameters.
- Use `@JsonProperty` to include a non-`public` field or getter method of a class in the generated
  JSON schema for a tool's parameters.

Anthropic provides some
[Best practices for defining tools](https://docs.claude.com/docs/en/build-with-claude/structured-outputs)
that may help you to understand how to use the above annotations effectively for your tools.

See also [Defining JSON schema properties](#defining-json-schema-properties) for more details on how
to use fields and getter methods and combine access modifiers and annotations to define the
parameters of your tools. The same rules apply to tool classes and to the structured output classes
described in that section.

## File uploads

The SDK defines methods that accept files, the main interface for which is exposed through [`MultipartField`](anthropic-java-core/src/main/kotlin/com/anthropic/core/Values.kt):

```java
import com.anthropic.core.MultipartField;
import com.anthropic.models.beta.files.FileMetadata;
import com.anthropic.models.beta.files.FileUploadParams;
import com.anthropic.models.beta.AnthropicBeta;
import java.io.InputStream;
import java.nio.file.Paths;

FileUploadParams params = FileUploadParams.builder()
    .file(MultipartField.<InputStream>builder()
        .value(Files.newInputStream(Paths.get("/path/to/file.pdf")))
        .contentType("application/pdf") // content type must be manually specified
        .build())
    .addBeta(AnthropicBeta.FILES_API_2025_04_14)
    .build();
FileMetadata fileMetadata = client.beta().files().upload(params);
```

Or an arbitrary [`InputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html):

```java
import com.anthropic.core.MultipartField;
import com.anthropic.models.beta.files.FileMetadata;
import com.anthropic.models.beta.files.FileUploadParams;
import com.anthropic.models.beta.AnthropicBeta;
import java.io.InputStream;
import java.net.URL;

FileUploadParams params = FileUploadParams.builder()
    .file(MultipartField.<InputStream>builder()
        .value(new URL("https://example.com/path/to/file").openStream())
        .filename("document.pdf")
        .contentType("application/pdf")
        .build())
    .addBeta(AnthropicBeta.FILES_API_2025_04_14)
    .build();
FileMetadata fileMetadata = client.beta().files().upload(params);
```

Or a `byte[]` array:

```java
import com.anthropic.core.MultipartField;
import com.anthropic.models.beta.files.FileMetadata;
import com.anthropic.models.beta.files.FileUploadParams;
import com.anthropic.models.beta.AnthropicBeta;

FileUploadParams params = FileUploadParams.builder()
    .file(MultipartField.<byte[]>builder()
        .value("content".getBytes())
        .filename("document.txt")
        .contentType("text/plain")
        .build())
    .addBeta(AnthropicBeta.FILES_API_2025_04_14)
    .build();
FileMetadata fileMetadata = client.beta().files().upload(params);
```

Note that you can also pass certain values directly, however this is not recommended as the
files API will not infer the correct content-type for you.

```java
FileUploadParams params = FileUploadParams.builder()
    .file(Paths.get("/path/to/file"))
    .addBeta(AnthropicBeta.FILES_API_2025_04_14)
    .build();
```

## Binary responses

The SDK defines methods that return binary responses, which are used for API responses that shouldn't necessarily be parsed, like non-JSON data.

These methods return [`HttpResponse`](anthropic-java-core/src/main/kotlin/com/anthropic/core/http/HttpResponse.kt):

```java
import com.anthropic.core.http.HttpResponse;
import com.anthropic.models.beta.files.FileDownloadParams;

HttpResponse response = client.beta().files().download("file_id");
```

To save the response content to a file, use the [`Files.copy(...)`](https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html#copy-java.io.InputStream-java.nio.file.Path-java.nio.file.CopyOption...-) method:

```java
import com.anthropic.core.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

try (HttpResponse response = client.beta().files().download(params)) {
    Files.copy(
        response.body(),
        Paths.get(path),
        StandardCopyOption.REPLACE_EXISTING
    );
} catch (Exception e) {
    System.out.println("Something went wrong!");
    throw new RuntimeException(e);
}
```

Or transfer the response content to any [`OutputStream`](https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html):

```java
import com.anthropic.core.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

try (HttpResponse response = client.beta().files().download(params)) {
    response.body().transferTo(Files.newOutputStream(Paths.get(path)));
} catch (Exception e) {
    System.out.println("Something went wrong!");
    throw new RuntimeException(e);
}
```

## Raw responses

The SDK defines methods that deserialize responses into instances of Java classes. However, these methods don't provide access to the response headers, status code, or the raw response body.

To access this data, prefix any HTTP method call on a client or service with `withRawResponse()`:

```java
import com.anthropic.core.http.Headers;
import com.anthropic.core.http.HttpResponseFor;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_OPUS_4_5_20251101)
    .build();
HttpResponseFor<Message> message = client.messages().withRawResponse().create(params);

int statusCode = message.statusCode();
Headers headers = message.headers();
```

You can still deserialize the response into an instance of a Java class if needed:

```java
import com.anthropic.models.messages.Message;

Message parsedMessage = message.parse();
```

### Request IDs

> For more information on debugging requests, see [the API docs](https://docs.anthropic.com/en/api/errors#request-id).

When using raw responses, you can access the `request-id` response header using the `requestId()` method:

```java
import com.anthropic.core.http.HttpResponseFor;
import com.anthropic.models.messages.Message;
import java.util.Optional;

HttpResponseFor<Message> message = client.messages().withRawResponse().create(params);
Optional<String> requestId = message.requestId();
```

This can be used to quickly log failing requests and report them back to Anthropic.

## Error handling

The SDK throws custom unchecked exception types:

- [`AnthropicServiceException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicServiceException.kt): Base class for HTTP errors. See this table for which exception subclass is thrown for each HTTP status code:

  | Status | Exception                                                                                                                    |
  | ------ | ---------------------------------------------------------------------------------------------------------------------------- |
  | 400    | [`BadRequestException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/BadRequestException.kt)                     |
  | 401    | [`UnauthorizedException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/UnauthorizedException.kt)                 |
  | 403    | [`PermissionDeniedException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/PermissionDeniedException.kt)         |
  | 404    | [`NotFoundException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/NotFoundException.kt)                         |
  | 422    | [`UnprocessableEntityException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/UnprocessableEntityException.kt)   |
  | 429    | [`RateLimitException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/RateLimitException.kt)                       |
  | 5xx    | [`InternalServerException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/InternalServerException.kt)             |
  | others | [`UnexpectedStatusCodeException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/UnexpectedStatusCodeException.kt) |

  [`SseException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/SseException.kt) is thrown for errors encountered during [SSE streaming](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events) after a successful initial HTTP response.

- [`AnthropicIoException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicIoException.kt): I/O networking errors.

- [`AnthropicRetryableException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicRetryableException.kt): Generic error indicating a failure that could be retried by the client.

- [`AnthropicInvalidDataException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicInvalidDataException.kt): Failure to interpret successfully parsed data. For example, when accessing a property that's supposed to be required, but the API unexpectedly omitted it from the response.

- [`AnthropicException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicException.kt): Base class for all exceptions. Most errors will result in one of the previously mentioned ones, but completely generic errors may be thrown using the base class.

## Pagination

The SDK defines methods that return a paginated lists of results. It provides convenient ways to access the results either one page at a time or item-by-item across all pages.

### Auto-pagination

To iterate through all results across all pages, use the `autoPager()` method, which automatically fetches more pages as needed.

When using the synchronous client, the method returns an [`Iterable`](https://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)

```java
import com.anthropic.models.messages.batches.BatchListPage;
import com.anthropic.models.messages.batches.MessageBatch;

BatchListPage page = client.messages().batches().list();

// Process as an Iterable
for (MessageBatch batch : page.autoPager()) {
    System.out.println(batch);
}

// Process as a Stream
page.autoPager()
    .stream()
    .limit(50)
    .forEach(batch -> System.out.println(batch));
```

When using the asynchronous client, the method returns an [`AsyncStreamResponse`](anthropic-java-core/src/main/kotlin/com/anthropic/core/http/AsyncStreamResponse.kt):

```java
import com.anthropic.core.http.AsyncStreamResponse;
import com.anthropic.models.messages.batches.BatchListPageAsync;
import com.anthropic.models.messages.batches.MessageBatch;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

CompletableFuture<BatchListPageAsync> pageFuture = client.async().messages().batches().list();

pageFuture.thenRun(page -> page.autoPager().subscribe(batch -> {
    System.out.println(batch);
}));

// If you need to handle errors or completion of the stream
pageFuture.thenRun(page -> page.autoPager().subscribe(new AsyncStreamResponse.Handler<>() {
    @Override
    public void onNext(MessageBatch batch) {
        System.out.println(batch);
    }

    @Override
    public void onComplete(Optional<Throwable> error) {
        if (error.isPresent()) {
            System.out.println("Something went wrong!");
            throw new RuntimeException(error.get());
        } else {
            System.out.println("No more!");
        }
    }
}));

// Or use futures
pageFuture.thenRun(page -> page.autoPager()
    .subscribe(batch -> {
        System.out.println(batch);
    })
    .onCompleteFuture()
    .whenComplete((unused, error) -> {
        if (error != null) {
            System.out.println("Something went wrong!");
            throw new RuntimeException(error);
        } else {
            System.out.println("No more!");
        }
    }));
```

### Manual pagination

To access individual page items and manually request the next page, use the `items()`,
`hasNextPage()`, and `nextPage()` methods:

```java
import com.anthropic.models.messages.batches.BatchListPage;
import com.anthropic.models.messages.batches.MessageBatch;

BatchListPage page = client.messages().batches().list();
while (true) {
    for (MessageBatch batch : page.items()) {
        System.out.println(batch);
    }

    if (!page.hasNextPage()) {
        break;
    }

    page = page.nextPage();
}
```

## Amazon Bedrock

This SDK also provides support for the
[Anthropic Bedrock API](https://aws.amazon.com/bedrock/claude/). This support
requires the `anthropic-java-bedrock` library dependency.

<!-- x-release-please-start-version -->

### Gradle

```kotlin
implementation("com.anthropic:anthropic-java-bedrock:2.12.0")
```

### Maven

```xml
<dependency>
    <groupId>com.anthropic</groupId>
    <artifactId>anthropic-java-bedrock</artifactId>
    <version>2.12.0</version>
</dependency>
```

<!-- x-release-please-end -->

### Usage

To use Anthropic on Bedrock, create the Anthropic client with the
[`BedrockBackend`](anthropic-java-bedrock/src/main/kotlin/com/anthropic/bedrock/backends/BedrockBackend.kt).
Usage of the API is otherwise the same.

```java
import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.builder()
        .backend(BedrockBackend.fromEnv())
        .build();
```

`BedrockBackend.fromEnv()` automatically resolves the AWS credentials using the
[AWS default credentials provider chain](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-chain.html)
and resolves the AWS region using the
[AWS default region provider chain](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/region-selection.html).
See those AWS documents for details on how to configure the AWS credentials and
AWS region for resolution by those provider chains.

Instead of resolving the AWS credentials and AWS region using the default AWS
provider chains, you can resolve them independently using any provider, or any
scheme of your choice, and pass them directly to the `BedrockBackend` during
building. For example, you can resolve the AWS credentials directly from
environment variables and hard-code the AWS region:

```java
import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.regions.Region;

AwsCredentials awsCredentials = AwsBasicCredentials.create(
        System.getenv("AWS_ACCESS_KEY_ID"),
        System.getenv("AWS_SECRET_ACCESS_KEY"));

AnthropicClient client = AnthropicOkHttpClient.builder()
        .backend(BedrockBackend.builder()
                .awsCredentials(awsCredentials)
                .region(Region.US_EAST_1)
                .build())
        .build();
```

You can also create and configure your own AWS credentials provider and set it when building a
`BedrockBackend`. For example, you can use the AWS `DefaultCredentialsProvider`, but enable
automatic asynchronous refreshing of credentials:

```java
import com.anthropic.bedrock.backends.BedrockBackend;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

AwsCredentialsProvider awsCredentialsProvider =
        DefaultCredentialsProvider.builder()
                .asyncCredentialUpdateEnabled(true)
                .build();

AnthropicClient client = AnthropicOkHttpClient.builder()
        .backend(BedrockBackend.builder()
                .fromEnv(awsCredentialsProvider)
                .build())
        .build();
```

The AWS classes used above are included automatically as transitive dependencies
of the `anthropic-java-bedrock` library dependency. For other resolution
schemes, you may need additional AWS dependencies.

Currently, the Bedrock backend does _not_ support the following:

- Anthropic Batch API
- Anthropic Token Counting API

#### Usage with an API key

The `BedrockBackend` can also use an API key instead of AWS credentials for request authorization.
See the [AWS documentation](https://docs.aws.amazon.com/bedrock/latest/userguide/api-keys-how.html)
for details on API keys and how to generate them.

You can set the `AWS_BEARER_TOKEN_BEDROCK` environment variable to the value of your API key and
call `BedrockBackend.fromEnv()` to authorize requests using that API key. An API key will be used in
preference to AWS credentials if both are set in the environment. If calling
`BedrockBackend.Builder.fromEnv(AwsCredentialsProvider)` with a non-`null` provider instance, that
provider's credentials will take precedence over any API key set in the environment.

The API key can also be passed directly to the backend, so you can resolve it from a source other
than an environment variable, if preferred:

```java
AnthropicClient client = AnthropicOkHttpClient.builder()
        .backend(BedrockBackend.builder()
                .apiKey(myApiKey)
                .region(Region.US_EAST_1)
                .build())
        .build();
```

An error will occur if you set _both_ an API key _and_ an AWS credentials provider.

## Google Cloud Vertex AI

This SDK also provides support for Anthropic models on the
[Google Cloud Vertex AI](https://cloud.google.com/vertex-ai?hl=en) platform.
This support requires the `anthropic-java-vertex` library dependency.

<!-- x-release-please-start-version -->

### Gradle

```kotlin
implementation("com.anthropic:anthropic-java-vertex:2.12.0")
```

### Maven

```xml
<dependency>
    <groupId>com.anthropic</groupId>
    <artifactId>anthropic-java-vertex</artifactId>
    <version>2.12.0</version>
</dependency>
```

<!-- x-release-please-end -->

### Usage

To use Anthropic on Vertex AI, create the Anthropic client with the
`VertexBackend`. Usage of the API is otherwise the same.

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.vertex.backends.VertexBackend;

AnthropicClient client = AnthropicOkHttpClient.builder()
        .backend(VertexBackend.fromEnv())
        .build();
```

`VertexBackend.fromEnv()` automatically resolves the Google OAuth2 credentials
from your configured Google Cloud
[Application Default Credentials](https://cloud.google.com/docs/authentication/provide-credentials-adc)
(ADC), the Google Cloud region from the `CLOUD_ML_REGION` environment variable,
and the Google Cloud project ID from `ANTHROPIC_VERTEX_PROJECT_ID` environment
variable. See the Google documentation for details on how to configure your ADC.

Instead of resolving the Google ADC, region and project ID automatically using
`fromEnv()`, you can resolve them independently using an alternative Google
Cloud facility, or any scheme of your choice, and pass them directly to the
`VertexBackend` during building. For example, you could resolve the Google
credentials and project ID directly from environment variables and hard-code the
Google Cloud region:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.vertex.backends.VertexBackend;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;

String accessToken = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
String project = System.getenv("ANTHROPIC_VERTEX_PROJECT_ID");

GoogleCredentials googleCredentials = GoogleCredentials.create(
        AccessToken.newBuilder().setTokenValue(accessToken).build());

AnthropicClient client = AnthropicOkHttpClient.builder()
        .backend(VertexBackend.builder()
                .googleCredentials(googleCredentials)
                .region("us-central1")
                .project(project)
                .build())
        .build();
```

The Google Cloud classes used above are included automatically as transitive
dependencies of the `anthropic-java-vertex` library dependency. For other
resolution schemes, you may need additional Google Cloud dependencies.

Currently, the Vertex backend does _not_ support the following:

- Anthropic Batch API

## Logging

The SDK uses the standard [OkHttp logging interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor).

Enable logging by setting the `ANTHROPIC_LOG` environment variable to `info`:

```sh
export ANTHROPIC_LOG=info
```

Or to `debug` for more verbose logging:

```sh
export ANTHROPIC_LOG=debug
```

## ProGuard and R8

Although the SDK uses reflection, it is still usable with [ProGuard](https://github.com/Guardsquare/proguard) and [R8](https://developer.android.com/topic/performance/app-optimization/enable-app-optimization) because `anthropic-java-core` is published with a [configuration file](anthropic-java-core/src/main/resources/META-INF/proguard/anthropic-java-core.pro) containing [keep rules](https://www.guardsquare.com/manual/configuration/usage).

ProGuard and R8 should automatically detect and use the published rules, but you can also manually copy the keep rules if necessary.

## Jackson

The SDK depends on [Jackson](https://github.com/FasterXML/jackson) for JSON serialization/deserialization. It is compatible with version 2.13.4 or higher, but depends on version 2.18.2 by default.

The SDK throws an exception if it detects an incompatible Jackson version at runtime (e.g. if the default version was overridden in your Maven or Gradle config).

If the SDK threw an exception, but you're _certain_ the version is compatible, then disable the version check using the `checkJacksonVersionCompatibility` on [`AnthropicOkHttpClient`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClient.kt) or [`AnthropicOkHttpClientAsync`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClientAsync.kt).

> [!CAUTION]
> We make no guarantee that the SDK works correctly when the Jackson version check is disabled.

## Network options

### Retries

The SDK automatically retries 2 times by default, with a short exponential backoff between requests.

Only the following error types are retried:

- Connection errors (for example, due to a network connectivity problem)
- 408 Request Timeout
- 409 Conflict
- 429 Rate Limit
- 5xx Internal

The API may also explicitly instruct the SDK to retry or not retry a request.

To set a custom number of retries, configure the client using the `maxRetries` method:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    .maxRetries(4)
    .build();
```

### Timeouts

Requests time out after 10 minutes by default.

However, for methods that accept `maxTokens`, if you specify a large `maxTokens` value and are _not_ streaming, then the default timeout will be calculated dynamically using this formula:

```java
Duration.ofSeconds(
    Math.min(
        60 * 60, // 1 hour max
        Math.max(
            10 * 60, // 10 minute minimum
            60 * 60 * maxTokens / 128_000
        )
    )
)
```

Which results in a timeout of up to 60 minutes, scaled by the `maxTokens` parameter, unless overridden.

To set a custom timeout, configure the method call using the `timeout` method:

```java
import com.anthropic.models.messages.Message;

Message message = client.messages().create(
  params, RequestOptions.builder().timeout(Duration.ofSeconds(30)).build()
);
```

Or configure the default for all method calls at the client level:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import java.time.Duration;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    .timeout(Duration.ofSeconds(30))
    .build();
```

### Proxies

To route requests through a proxy, configure the client using the `proxy` method:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import java.net.InetSocketAddress;
import java.net.Proxy;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    .proxy(new Proxy(
      Proxy.Type.HTTP, new InetSocketAddress(
        "https://example.com", 8080
      )
    ))
    .build();
```

### HTTPS

> [!NOTE]
> Most applications should not call these methods, and instead use the system defaults. The defaults include
> special optimizations that can be lost if the implementations are modified.

To configure how HTTPS connections are secured, configure the client using the `sslSocketFactory`, `trustManager`, and `hostnameVerifier` methods:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    // If `sslSocketFactory` is set, then `trustManager` must be set, and vice versa.
    .sslSocketFactory(yourSSLSocketFactory)
    .trustManager(yourTrustManager)
    .hostnameVerifier(yourHostnameVerifier)
    .build();
```

### Custom HTTP client

The SDK consists of three artifacts:

- `anthropic-java-core`
  - Contains core SDK logic
  - Does not depend on [OkHttp](https://square.github.io/okhttp)
  - Exposes [`AnthropicClient`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClient.kt), [`AnthropicClientAsync`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientAsync.kt), [`AnthropicClientImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientImpl.kt), and [`AnthropicClientAsyncImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientAsyncImpl.kt), all of which can work with any HTTP client
- `anthropic-java-client-okhttp`
  - Depends on [OkHttp](https://square.github.io/okhttp)
  - Exposes [`AnthropicOkHttpClient`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClient.kt) and [`AnthropicOkHttpClientAsync`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClientAsync.kt), which provide a way to construct [`AnthropicClientImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientImpl.kt) and [`AnthropicClientAsyncImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientAsyncImpl.kt), respectively, using OkHttp
- `anthropic-java`
  - Depends on and exposes the APIs of both `anthropic-java-core` and `anthropic-java-client-okhttp`
  - Does not have its own logic

This structure allows replacing the SDK's default HTTP client without pulling in unnecessary dependencies.

#### Customized [`OkHttpClient`](https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html)

> [!TIP]
> Try the available [network options](#network-options) before replacing the default client.

To use a customized `OkHttpClient`:

1. Replace your [`anthropic-java` dependency](#installation) with `anthropic-java-core`
2. Copy `anthropic-java-client-okhttp`'s [`OkHttpClient`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/OkHttpClient.kt) class into your code and customize it
3. Construct [`AnthropicClientImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientImpl.kt) or [`AnthropicClientAsyncImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientAsyncImpl.kt), similarly to [`AnthropicOkHttpClient`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClient.kt) or [`AnthropicOkHttpClientAsync`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClientAsync.kt), using your customized client

### Completely custom HTTP client

To use a completely custom HTTP client:

1. Replace your [`anthropic-java` dependency](#installation) with `anthropic-java-core`
2. Write a class that implements the [`HttpClient`](anthropic-java-core/src/main/kotlin/com/anthropic/core/http/HttpClient.kt) interface
3. Construct [`AnthropicClientImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientImpl.kt) or [`AnthropicClientAsyncImpl`](anthropic-java-core/src/main/kotlin/com/anthropic/client/AnthropicClientAsyncImpl.kt), similarly to [`AnthropicOkHttpClient`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClient.kt) or [`AnthropicOkHttpClientAsync`](anthropic-java-client-okhttp/src/main/kotlin/com/anthropic/client/okhttp/AnthropicOkHttpClientAsync.kt), using your new client class

## Undocumented API functionality

The SDK is typed for convenient usage of the documented API. However, it also supports working with undocumented or not yet supported parts of the API.

### Parameters

To set undocumented parameters, call the `putAdditionalHeader`, `putAdditionalQueryParam`, or `putAdditionalBodyProperty` methods on any `Params` class:

```java
import com.anthropic.core.JsonValue;
import com.anthropic.models.messages.MessageCreateParams;

MessageCreateParams params = MessageCreateParams.builder()
    .putAdditionalHeader("Secret-Header", "42")
    .putAdditionalQueryParam("secret_query_param", "42")
    .putAdditionalBodyProperty("secretProperty", JsonValue.from("42"))
    .build();
```

These can be accessed on the built object later using the `_additionalHeaders()`, `_additionalQueryParams()`, and `_additionalBodyProperties()` methods.

> [!WARNING]
> The values passed to these methods overwrite values passed to earlier methods.
>
> For security reasons, ensure these methods are only used with trusted input data.

To set undocumented parameters on _nested_ headers, query params, or body classes, call the `putAdditionalProperty` method on the nested class:

```java
import com.anthropic.core.JsonValue;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Metadata;

MessageCreateParams params = MessageCreateParams.builder()
    .metadata(Metadata.builder()
        .putAdditionalProperty("secretProperty", JsonValue.from("42"))
        .build())
    .build();
```

These properties can be accessed on the nested built object later using the `_additionalProperties()` method.

To set a documented parameter or property to an undocumented or not yet supported _value_, pass a [`JsonValue`](anthropic-java-core/src/main/kotlin/com/anthropic/core/Values.kt) object to its setter:

```java
import com.anthropic.core.JsonValue;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(JsonValue.from(3.14))
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_OPUS_4_5_20251101)
    .build();
```

The most straightforward way to create a [`JsonValue`](anthropic-java-core/src/main/kotlin/com/anthropic/core/Values.kt) is using its `from(...)` method:

```java
import com.anthropic.core.JsonValue;
import java.util.List;
import java.util.Map;

// Create primitive JSON values
JsonValue nullValue = JsonValue.from(null);
JsonValue booleanValue = JsonValue.from(true);
JsonValue numberValue = JsonValue.from(42);
JsonValue stringValue = JsonValue.from("Hello World!");

// Create a JSON array value equivalent to `["Hello", "World"]`
JsonValue arrayValue = JsonValue.from(List.of(
  "Hello", "World"
));

// Create a JSON object value equivalent to `{ "a": 1, "b": 2 }`
JsonValue objectValue = JsonValue.from(Map.of(
  "a", 1,
  "b", 2
));

// Create an arbitrarily nested JSON equivalent to:
// {
//   "a": [1, 2],
//   "b": [3, 4]
// }
JsonValue complexValue = JsonValue.from(Map.of(
  "a", List.of(
    1, 2
  ),
  "b", List.of(
    3, 4
  )
));
```

Normally a `Builder` class's `build` method will throw [`IllegalStateException`](https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalStateException.html) if any required parameter or property is unset.

To forcibly omit a required parameter or property, pass [`JsonMissing`](anthropic-java-core/src/main/kotlin/com/anthropic/core/Values.kt):

```java
import com.anthropic.core.JsonMissing;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

MessageCreateParams params = MessageCreateParams.builder()
    .addUserMessage("Hello, world")
    .model(Model.CLAUDE_OPUS_4_5_20251101)
    .maxTokens(JsonMissing.of())
    .build();
```

### Response properties

To access undocumented response properties, call the `_additionalProperties()` method:

```java
import com.anthropic.core.JsonValue;
import java.util.Map;

Map<String, JsonValue> additionalProperties = client.messages().create(params)._additionalProperties();
JsonValue secretPropertyValue = additionalProperties.get("secretProperty");

String result = secretPropertyValue.accept(new JsonValue.Visitor<>() {
    @Override
    public String visitNull() {
        return "It's null!";
    }

    @Override
    public String visitBoolean(boolean value) {
        return "It's a boolean!";
    }

    @Override
    public String visitNumber(Number value) {
        return "It's a number!";
    }

    // Other methods include `visitMissing`, `visitString`, `visitArray`, and `visitObject`
    // The default implementation of each unimplemented method delegates to `visitDefault`, which throws by default, but can also be overridden
});
```

To access a property's raw JSON value, which may be undocumented, call its `_` prefixed method:

```java
import com.anthropic.core.JsonField;
import java.util.Optional;

JsonField<Long> maxTokens = client.messages().create(params)._maxTokens();

if (maxTokens.isMissing()) {
  // The property is absent from the JSON response
} else if (maxTokens.isNull()) {
  // The property was set to literal null
} else {
  // Check if value was provided as a string
  // Other methods include `asNumber()`, `asBoolean()`, etc.
  Optional<String> jsonString = maxTokens.asString();

  // Try to deserialize into a custom type
  MyClass myObject = maxTokens.asUnknown().orElseThrow().convert(MyClass.class);
}
```

### Response validation

In rare cases, the API may return a response that doesn't match the expected type. For example, the SDK may expect a property to contain a `String`, but the API could return something else.

By default, the SDK will not throw an exception in this case. It will throw [`AnthropicInvalidDataException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicInvalidDataException.kt) only if you directly access the property.

If you would prefer to check that the response is completely well-typed upfront, then either call `validate()`:

```java
import com.anthropic.models.messages.Message;

Message message = client.messages().create(params).validate();
```

Or configure the method call to validate the response using the `responseValidation` method:

```java
import com.anthropic.models.messages.Message;

Message message = client.messages().create(
  params, RequestOptions.builder().responseValidation(true).build()
);
```

Or configure the default for all method calls at the client level:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    .responseValidation(true)
    .build();
```

## FAQ

### Why don't you use plain `enum` classes?

Java `enum` classes are not trivially [forwards compatible](https://www.stainless.com/blog/making-java-enums-forwards-compatible). Using them in the SDK could cause runtime exceptions if the API is updated to respond with a new enum value.

### Why do you represent fields using `JsonField<T>` instead of just plain `T`?

Using `JsonField<T>` enables a few features:

- Allowing usage of [undocumented API functionality](#undocumented-api-functionality)
- Lazily [validating the API response against the expected shape](#response-validation)
- Representing absent vs explicitly null values

### Why don't you use [`data` classes](https://kotlinlang.org/docs/data-classes.html)?

It is not [backwards compatible to add new fields to a data class](https://kotlinlang.org/docs/api-guidelines-backward-compatibility.html#avoid-using-data-classes-in-your-api) and we don't want to introduce a breaking change every time we add a field to a class.

### Why don't you use checked exceptions?

Checked exceptions are widely considered a mistake in the Java programming language. In fact, they were omitted from Kotlin for this reason.

Checked exceptions:

- Are verbose to handle
- Encourage error handling at the wrong level of abstraction, where nothing can be done about the error
- Are tedious to propagate due to the [function coloring problem](https://journal.stuffwithstuff.com/2015/02/01/what-color-is-your-function)
- Don't play well with lambdas (also due to the function coloring problem)

## Semantic versioning

This package generally follows [SemVer](https://semver.org/spec/v2.0.0.html) conventions, though certain backwards-incompatible changes may be released as minor versions:

1. Changes to library internals which are technically public but not intended or documented for external use. _(Please open a GitHub issue to let us know if you are relying on such internals.)_
2. Changes that we do not expect to impact the vast majority of users in practice.

We take backwards-compatibility seriously and work hard to ensure you can rely on a smooth upgrade experience.

We are keen for your feedback; please open an [issue](https://www.github.com/anthropics/anthropic-sdk-java/issues) with questions, bugs, or suggestions.
