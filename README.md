# Anthropic Java API Library

<!-- x-release-please-start-version -->

[![Maven Central](https://img.shields.io/maven-central/v/com.anthropic/anthropic-java)](https://central.sonatype.com/artifact/com.anthropic/anthropic-java/0.0.1-alpha.0)

<!-- x-release-please-end -->

The Anthropic Java SDK provides convenient access to the Anthropic REST API from applications written in Java. It includes helper classes with helpful types and documentation for every request and response property.

The Anthropic Java SDK is similar to the Anthropic Kotlin SDK but with minor differences that make it more ergonomic for use in Java, such as `Optional` instead of nullable values, `Stream` instead of `Sequence`, and `CompletableFuture` instead of suspend functions.

## Documentation

The REST API documentation can be found on [docs.anthropic.com](https://docs.anthropic.com/claude/reference/).

---

## Getting started

### Install dependencies

#### Gradle

<!-- x-release-please-start-version -->

```kotlin
implementation("com.anthropic:anthropic-java:0.0.1-alpha.0")
```

#### Maven

```xml
<dependency>
    <groupId>com.anthropic</groupId>
    <artifactId>anthropic-java</artifactId>
    <version>0.0.1-alpha.0</version>
</dependency>
```

<!-- x-release-please-end -->

### Configure the client

Use `AnthropicOkHttpClient.builder()` to configure the client.

Alternately, set the environment with `ANTHROPIC_API_KEY` or `ANTHROPIC_AUTH_TOKEN`, and use `AnthropicOkHttpClient.fromEnv()` to read from the environment.

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.fromEnv();

// Note: you can also call fromEnv() from the client builder, for example if you need to set additional properties
AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    // ... set properties on the builder
    .build();
```

| Property  | Environment variable   | Required | Default value |
| --------- | ---------------------- | -------- | ------------- |
| apiKey    | `ANTHROPIC_API_KEY`    | false    | —             |
| authToken | `ANTHROPIC_AUTH_TOKEN` | false    | —             |

Read the documentation for more configuration options.

---

### Example: creating a resource

To create a new message, first use the `MessageCreateParams` builder to specify attributes, then pass that to the `create` method of the `messages` service.

```java
import com.anthropic.models.Message;
import com.anthropic.models.MessageCreateParams;
import com.anthropic.models.MessageParam;
import com.anthropic.models.Model;

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addMessage(MessageParam.builder()
        .role(MessageParam.Role.USER)
        .content(MessageParam.Content.ofString("Hello, Claude"))
        .build())
    .model(Model.CLAUDE_3_5_HAIKU_LATEST)
    .build();
Message message = client.messages().create(params);
```

### Example: listing resources

The Anthropic API provides a `list` method to get a paginated list of batches. You can retrieve the first page by:

```java
import com.anthropic.models.BetaMessageBatch;
import com.anthropic.models.BetaMessageBatchListPage;

BetaMessageBatchListPage page = client.beta().messages().batches().list();
for (BetaMessageBatch batch : page.data()) {
    System.out.println(batch);
}
```

Use the `BetaMessageBatchListParams` builder to set parameters:

```java
import com.anthropic.models.AnthropicBeta;
import com.anthropic.models.BetaMessageBatchListPage;
import com.anthropic.models.BetaMessageBatchListParams;

BetaMessageBatchListParams params = BetaMessageBatchListParams.builder()
    .afterId("after_id")
    .beforeId("before_id")
    .limit(20L)
    .addBeta(AnthropicBeta.MESSAGE_BATCHES_2024_09_24)
    .build();
BetaMessageBatchListPage page1 = client.beta().messages().batches().list(params);

// Using the `from` method of the builder you can reuse previous params values:
BetaMessageBatchListPage page2 = client.beta().messages().batches().list(BetaMessageBatchListParams.builder()
    .from(params)
    .lastId("abc123...")
    .build());

// Or easily get params for the next page by using the helper `getNextPageParams`:
BetaMessageBatchListPage page3 = client.beta().messages().batches().list(params.getNextPageParams(page2));
```

See [Pagination](#pagination) below for more information on transparently working with lists of objects without worrying about fetching each page.

---

## Requests

### Parameters and bodies

To make a request to the Anthropic API, you generally build an instance of the appropriate `Params` class.

In [Example: creating a resource](#example-creating-a-resource) above, we used the `MessageCreateParams.builder()` to pass to the `create` method of the `messages` service.

Sometimes, the API may support other properties that are not yet supported in the Java SDK types. In that case, you can attach them using the `putAdditionalProperty` method.

```java
import com.anthropic.core.JsonValue;
import com.anthropic.models.MessageCreateParams;

MessageCreateParams params = MessageCreateParams.builder()
    // ... normal properties
    .putAdditionalProperty("secret_param", JsonValue.from("4242"))
    .build();
```

## Responses

### Response validation

When receiving a response, the Anthropic Java SDK will deserialize it into instances of the typed model classes. In rare cases, the API may return a response property that doesn't match the expected Java type. If you directly access the mistaken property, the SDK will throw an unchecked `AnthropicInvalidDataException` at runtime. If you would prefer to check in advance that that response is completely well-typed, call `.validate()` on the returned model.

```java
import com.anthropic.models.Message;

Message message = client.messages().create().validate();
```

### Response properties as JSON

In rare cases, you may want to access the underlying JSON value for a response property rather than using the typed version provided by this SDK. Each model property has a corresponding JSON version, with an underscore before the method name, which returns a `JsonField` value.

```java
import com.anthropic.core.JsonField;
import java.util.Optional;

JsonField field = responseObj._field();

if (field.isMissing()) {
  // Value was not specified in the JSON response
} else if (field.isNull()) {
  // Value was provided as a literal null
} else {
  // See if value was provided as a string
  Optional<String> jsonString = field.asString();

  // If the value given by the API did not match the shape that the SDK expects
  // you can deserialise into a custom type
  MyClass myObj = responseObj._field().asUnknown().orElseThrow().convert(MyClass.class);
}
```

### Additional model properties

Sometimes, the server response may include additional properties that are not yet available in this library's types. You can access them using the model's `_additionalProperties` method:

```java
import com.anthropic.core.JsonValue;

JsonValue secret = apiErrorObject._additionalProperties().get("secret_field");
```

---

## Pagination

For methods that return a paginated list of results, this library provides convenient ways access the results either one page at a time, or item-by-item across all pages.

### Auto-pagination

To iterate through all results across all pages, you can use `autoPager`, which automatically handles fetching more pages for you:

### Synchronous

```java
import com.anthropic.models.BetaMessageBatch;
import com.anthropic.models.BetaMessageBatchListPage;

// As an Iterable:
BetaMessageBatchListPage page = client.beta().messages().batches().list(params);
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
import com.anthropic.models.BetaMessageBatch;
import com.anthropic.models.BetaMessageBatchListPage;

BetaMessageBatchListPage page = client.beta().messages().batches().list(params);
while (page != null) {
    for (BetaMessageBatch batch : page.data()) {
        System.out.println(batch);
    }

    page = page.getNextPage().orElse(null);
}
```

---

## Error handling

This library throws exceptions in a single hierarchy for easy handling:

- **`AnthropicException`** - Base exception for all exceptions

- **`AnthropicServiceException`** - HTTP errors with a well-formed response body we were able to parse. The exception message and the `.debuggingRequestId()` will be set by the server.

  | 400    | BadRequestException           |
  | ------ | ----------------------------- |
  | 401    | AuthenticationException       |
  | 403    | PermissionDeniedException     |
  | 404    | NotFoundException             |
  | 422    | UnprocessableEntityException  |
  | 429    | RateLimitException            |
  | 5xx    | InternalServerException       |
  | others | UnexpectedStatusCodeException |

- **`AnthropicIoException`** - I/O networking errors
- **`AnthropicInvalidDataException`** - any other exceptions on the client side, e.g.:
  - We failed to serialize the request body
  - We failed to parse the response body (has access to response code and body)

## Network options

### Retries

Requests that experience certain errors are automatically retried 2 times by default, with a short exponential backoff. Connection errors (for example, due to a network connectivity problem), 408 Request Timeout, 409 Conflict, 429 Rate Limit, and >=500 Internal errors will all be retried by default. You can provide a `maxRetries` on the client builder to configure this:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    .maxRetries(4)
    .build();
```

### Timeouts

Requests time out after 10 minutes by default. You can configure this on the client builder:

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

Requests can be routed through a proxy. You can configure this on the client builder:

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import java.net.InetSocketAddress;
import java.net.Proxy;

AnthropicClient client = AnthropicOkHttpClient.builder()
    .fromEnv()
    .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("example.com", 8080)))
    .build();
```

## Making custom/undocumented requests

This library is typed for convenient access to the documented API. If you need to access undocumented params or response properties, the library can still be used.

### Undocumented request params

To make requests using undocumented parameters, you can provide or override parameters on the params object while building it.

```java
FooCreateParams address = FooCreateParams.builder()
    .id("my_id")
    .putAdditionalProperty("secret_prop", JsonValue.from("hello"))
    .build();
```

### Undocumented response properties

To access undocumented response properties, you can use `res._additionalProperties()` on a response object to get a map of untyped fields of type `Map<String, JsonValue>`. You can then access fields like `._additionalProperties().get("secret_prop").asString()` or use other helpers defined on the `JsonValue` class to extract it to a desired type.

## Logging

We use the standard [OkHttp logging interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor).

You can enable logging by setting the environment variable `ANTHROPIC_LOG` to `info`.

```sh
$ export ANTHROPIC_LOG=info
```

Or to `debug` for more verbose logging.

```sh
$ export ANTHROPIC_LOG=debug
```

## Semantic versioning

This package generally follows [SemVer](https://semver.org/spec/v2.0.0.html) conventions, though certain backwards-incompatible changes may be released as minor versions:

1. Changes to library internals which are technically public but not intended or documented for external use. _(Please open a GitHub issue to let us know if you are relying on such internals.)_
2. Changes that we do not expect to impact the vast majority of users in practice.

We take backwards-compatibility seriously and work hard to ensure you can rely on a smooth upgrade experience.

We are keen for your feedback; please open an [issue](https://www.github.com/anthropics/anthropic-sdk-java/issues) with questions, bugs, or suggestions.

## Requirements

This library requires Java 8 or later.
