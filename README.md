# Claude SDK for Java

[![Maven Central](https://img.shields.io/maven-central/v/com.anthropic/anthropic-java)](https://central.sonatype.com/artifact/com.anthropic/anthropic-java)

The Claude SDK for Java provides access to the [Claude API](https://docs.anthropic.com/en/api/) from Java applications.

## Documentation

Full documentation is available at **[platform.claude.com/docs/en/api/sdks/java](https://platform.claude.com/docs/en/api/sdks/java)**.

## Installation

<!-- x-release-please-start-version -->

### Gradle

```kotlin
implementation("com.anthropic:anthropic-java:2.27.0")
```

### Maven

```xml
<dependency>
  <groupId>com.anthropic</groupId>
  <artifactId>anthropic-java</artifactId>
  <version>2.27.0</version>
</dependency>
```

<!-- x-release-please-end -->

## Getting started

```java
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

// Configures using the `ANTHROPIC_API_KEY` environment variable
AnthropicClient client = AnthropicOkHttpClient.fromEnv();

MessageCreateParams params = MessageCreateParams.builder()
    .maxTokens(1024L)
    .addUserMessage("Hello, Claude")
    .model(Model.CLAUDE_OPUS_4_6)
    .build();
Message message = client.messages().create(params);
```

## Requirements

Java 8+

## Contributing

See [CONTRIBUTING.md](./CONTRIBUTING.md).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
