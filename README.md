# Anthropic Java API Library

> [!NOTE]  
> The Anthropic Java API Library is currently in _beta_.
>
> There may be minor breaking changes.
>
> Have thoughts or feedback? [File an issue](https://github.com/anthropics/anthropic-sdk-java/issues/new) or comment on [this discussion](https://github.com/anthropics/anthropic-sdk-java/discussions/95). 

<!-- x-release-please-start-version -->

[![Maven Central](https://img.shields.io/maven-central/v/com.anthropic/anthropic-java)](https://central.sonatype.com/artifact/com.anthropic/anthropic-java/0.8.0)
[![javadoc](https://javadoc.io/badge2/com.anthropic/anthropic-java/0.8.0/javadoc.svg)](https://javadoc.io/doc/com.anthropic/anthropic-java/0.8.0)

<!-- x-release-please-end -->

The Anthropic Java SDK provides convenient access to the Anthropic REST API from applications written in Java.

The REST API documentation can be found on [docs.anthropic.com](https://docs.anthropic.com/claude/reference/). Javadocs are also available on [javadoc.io](https://javadoc.io/doc/com.anthropic/anthropic-java/0.0.1-alpha.0).

## Installation

<!-- x-release-please-start-version -->

### Gradle

```kotlin
implementation("com.anthropic:anthropic-java:0.8.0")
```

### Maven

```xml
<dependency>
    <groupId>com.anthropic</groupId>
    <artifactId>anthropic-java</artifactId>
    <version>0.8.0</version>
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

// Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables
AnthropicClient client = AnthropicOkHttpClient.fromEnv();

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_3_7_SONNET_LATEST)
    .build();
Message message = client.messages().create(params);
```

## Client configuration

Configure the client using environment variables:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

// Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables
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
    // Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables
    .fromEnv()
    .apiKey("my-anthropic-api-key")
    .build();
```

See this table for the available options:

| Setter      | Environment variable   | Required | Default value |
| ----------- | ---------------------- | -------- | ------------- |
| `apiKey`    | `ANTHROPIC_API_KEY`    | false    | -             |
| `authToken` | `ANTHROPIC_AUTH_TOKEN` | false    | -             |

> [!TIP]
> Don't create more than one client in the same application. Each client has a connection pool and
> thread pools, which are more efficient to share between requests.

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

// Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables
AnthropicClient client = AnthropicOkHttpClient.fromEnv();

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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

// Configures using the `ANTHROPIC_API_KEY` and `ANTHROPIC_AUTH_TOKEN` environment variables
AnthropicClientAsync client = AnthropicOkHttpClientAsync.fromEnv();

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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

## Error handling

The SDK throws custom unchecked exception types:

- [`AnthropicServiceException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicServiceException.kt): Base class for HTTP errors. See this table for which exception subclass is thrown for each HTTP status code:

  | Status | Exception                       |
  | ------ | ------------------------------- |
  | 400    | `BadRequestException`           |
  | 401    | `AuthenticationException`       |
  | 403    | `PermissionDeniedException`     |
  | 404    | `NotFoundException`             |
  | 422    | `UnprocessableEntityException`  |
  | 429    | `RateLimitException`            |
  | 5xx    | `InternalServerException`       |
  | others | `UnexpectedStatusCodeException` |

- [`AnthropicIoException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicIoException.kt): I/O networking errors.

- [`AnthropicInvalidDataException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicInvalidDataException.kt): Failure to interpret successfully parsed data. For example, when accessing a property that's supposed to be required, but the API unexpectedly omitted it from the response.

- [`AnthropicException`](anthropic-java-core/src/main/kotlin/com/anthropic/errors/AnthropicException.kt): Base class for all exceptions. Most errors will result in one of the previously mentioned ones, but completely generic errors may be thrown using the base class.

## Pagination

For methods that return a paginated list of results, this library provides convenient ways access the results either one page at a time, or item-by-item across all pages.

### Auto-pagination

To iterate through all results across all pages, you can use `autoPager`, which automatically handles fetching more pages for you:

### Synchronous

```java
import com.anthropic.models.beta.messages.batches.BatchListPage;
import com.anthropic.models.beta.messages.batches.BetaMessageBatch;

// As an Iterable:
BatchListPage page = client.beta().messages().batches().list(params);
for (BetaMessageBatch batch : page.autoPager()) {
    System.out.println(batch);
};

// As a Stream:
client.beta().messages().batches().list(params).autoPager().stream()
    .limit(50)
    .forEach(batch -> System.out.println(batch));
```

### Asynchronous

```java
// Using forEach, which returns CompletableFuture<Void>:
asyncClient.beta().messages().batches().list(params).autoPager()
    .forEach(batch -> System.out.println(batch), executor);
```

### Manual pagination

If none of the above helpers meet your needs, you can also manually request pages one-by-one. A page of results has a `data()` method to fetch the list of objects, as well as top-level `response` and other methods to fetch top-level data about the page. It also has methods `hasNextPage`, `getNextPage`, and `getNextPageParams` methods to help with pagination.

```java
import com.anthropic.models.beta.messages.batches.BatchListPage;
import com.anthropic.models.beta.messages.batches.BetaMessageBatch;

BatchListPage page = client.beta().messages().batches().list(params);
while (page != null) {
    for (BetaMessageBatch batch : page.data()) {
        System.out.println(batch);
    }

    page = page.getNextPage().orElse(null);
}
```

## Amazon Bedrock

This SDK also provides support for the
[Anthropic Bedrock API](https://aws.amazon.com/bedrock/claude/). This support
requires the `anthropic-java-bedrock` library dependency.

<!-- x-release-please-start-version -->

### Gradle

```kotlin
implementation("com.anthropic:anthropic-java-bedrock:0.8.0")
```

### Maven

```xml
<dependency>
    <groupId>com.anthropic</groupId>
    <artifactId>anthropic-java-bedrock</artifactId>
    <version>0.8.0</version>
</dependency>
```

<!-- x-release-please-end -->

### Usage

To use Anthropic on Bedrock, create the Anthropic client with the 
`BedrockBackend`. Usage of the API is otherwise the same.

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

The AWS classes used above are included automatically as transitive dependencies
of the `anthropic-java-bedrock` library dependency. For other resolution 
schemes, you may need additional AWS dependencies. 

Currently, the Bedrock backend does _not_ support the following:

* Anthropic Batch API
* Anthropic Token Counting API

## Google Cloud Vertex AI

This SDK also provides support for Anthropic models on the 
[Google Cloud Vertex AI](https://cloud.google.com/vertex-ai?hl=en) platform.
This support requires the `anthropic-java-vertex` library dependency.

<!-- x-release-please-start-version -->

### Gradle

```kotlin
implementation("com.anthropic:anthropic-java-vertex:0.8.0")
```

### Maven

```xml
<dependency>
    <groupId>com.anthropic</groupId>
    <artifactId>anthropic-java-vertex</artifactId>
    <version>0.8.0</version>
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

* Anthropic Batch API

## Logging

The SDK uses the standard [OkHttp logging interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor).

Enable logging by setting the `ANTHROPIC_LOG` environment variable to `info`:

```sh
$ export ANTHROPIC_LOG=info
```

Or to `debug` for more verbose logging:

```sh
$ export ANTHROPIC_LOG=debug
```

## Network options

### Retries

The SDK automatically retries 2 times by default, with a short exponential backoff.

Only the following error types are retried:

- Connection errors (for example, due to a network connectivity problem)
- 408 Request Timeout
- 409 Conflict
- 429 Rate Limit
- 5xx Internal

The API may also explicitly instruct the SDK to retry or not retry a response.

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

However, for methods that accept `maxTokens`, if you specify a large `maxTokens` value and are *not* streaming, then the default timeout will be calculated dynamically using this formula:

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
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

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

To set undocumented parameters on _nested_ headers, query params, or body classes, call the `putAdditionalProperty` method on the nested class:

```java
import com.anthropic.core.JsonValue;
import com.anthropic.models.completions.CompletionCreateParams;
import com.anthropic.models.messages.Metadata;

CompletionCreateParams params = CompletionCreateParams.builder()
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
    .model(Model.CLAUDE_3_7_SONNET_LATEST)
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
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

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
