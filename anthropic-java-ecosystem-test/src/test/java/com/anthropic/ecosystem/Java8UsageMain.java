package com.anthropic.ecosystem;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.ObjectMappers;
import com.anthropic.models.messages.Base64ImageSource;
import com.fasterxml.jackson.databind.json.JsonMapper;

// Exercises basic SDK usage from Java and is executed on a real JDK 8 runtime to catch
// Java-8-only breakages that compiling to release 8 on a newer JDK can't surface. Kept free of
// AssertJ/JUnit so the only runtime surface is the SDK and its direct dependencies.
public final class Java8UsageMain {
    public static void main(String[] args) throws Exception {
        System.out.println("Java 8 usage check on JVM " + System.getProperty("java.version"));

        AnthropicClient client =
                AnthropicOkHttpClient.builder().apiKey("my-anthropic-api-key").build();
        require(client.completions() != null, "completions");
        require(client.messages() != null, "messages");
        require(client.models() != null, "models");
        require(client.beta() != null, "beta");

        JsonMapper mapper = ObjectMappers.jsonMapper();
        Base64ImageSource src = Base64ImageSource.builder()
                .data("U3RhaW5sZXNzIHJvY2tz")
                .mediaType(Base64ImageSource.MediaType.IMAGE_JPEG)
                .build();
        Base64ImageSource roundTripped = mapper.readValue(mapper.writeValueAsString(src), Base64ImageSource.class);
        require(roundTripped.equals(src), "Jackson round-trip equality");

        System.out.println("Java 8 usage check passed.");
    }

    private static void require(boolean condition, String what) {
        if (!condition) {
            throw new AssertionError("Java 8 usage check failed: " + what);
        }
    }
}
