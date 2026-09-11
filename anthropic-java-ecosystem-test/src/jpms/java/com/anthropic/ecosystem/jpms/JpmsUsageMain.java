package com.anthropic.ecosystem.jpms;

import com.anthropic.backends.AnthropicBackend;
import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.JsonValue;
import com.anthropic.core.ObjectMappers;
import com.anthropic.models.messages.JsonOutputFormat;
import com.anthropic.models.messages.MessageCreateParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleDescriptor.Requires;
import java.util.Arrays;

// Exercises the SDK from an explicit JPMS module, executed on the module path, to catch broken
// module descriptors (a missing `requires transitive`, an unopened package) that classpath tests
// can't surface. Kept free of AssertJ/JUnit so the only modules involved are the SDK's and its
// dependencies'.
public final class JpmsUsageMain {
    private static final String SDK_MODULE = "com.anthropic";
    private static final String CORE_MODULE = "com.anthropic.core";
    private static final String OKHTTP_MODULE = "com.anthropic.client.okhttp";

    public static void main(String[] args) throws Exception {
        Module self = JpmsUsageMain.class.getModule();
        System.out.println("JPMS usage check in " + self + " on JVM " + Runtime.version());
        require(self.isNamed(), "usage runs in a named module");

        AnthropicClient client =
                AnthropicOkHttpClient.builder().apiKey("my-anthropic-api-key").build();
        require(client.completions() != null, "completions");
        require(client.messages() != null, "messages");
        require(client.models() != null, "models");
        require(client.files() != null, "files");
        require(client.skills() != null, "skills");
        require(client.beta() != null, "beta");

        requireModule(AnthropicOkHttpClient.class, OKHTTP_MODULE);
        requireModule(JsonValue.class, CORE_MODULE);

        JsonMapper mapper = ObjectMappers.jsonMapper();
        String json = "{\"values\":[1,2.5,\"three\",true,null],\"nested\":{\"key\":\"value\"}}";
        JsonValue value = mapper.readValue(json, JsonValue.class);
        JsonNode roundTripped = mapper.readTree(mapper.writeValueAsString(value));
        require(roundTripped.equals(mapper.readTree(json)), "Jackson round-trip equality");

        // Hand-written packages need their own `exports` in the core descriptor.
        AnthropicBackend backend =
                AnthropicBackend.builder().apiKey("my-anthropic-api-key").build();
        require(backend.baseUrl().equals("https://api.anthropic.com"), "backend base URL");

        // Schema derivation is hand-written and calls the JSON Schema generator, so the core
        // descriptor needs its own `requires` for it.
        JsonOutputFormat format = MessageCreateParams.builder()
                .model("claude-sonnet-4-5")
                .maxTokens(1024L)
                .addUserMessage("What is the capital of France?")
                .outputConfig(Answer.class)
                .build()
                .rawParams()
                .outputConfig()
                .get()
                .format()
                .get();
        require(format.schema()._additionalProperties().containsKey("properties"), "structured output schema");

        ModuleLayer layer = self.getLayer();
        for (String name : Arrays.asList(SDK_MODULE, CORE_MODULE, OKHTTP_MODULE)) {
            Module module = layer.findModule(name).orElse(null);
            require(module != null, name + " is in the boot layer");
            ModuleDescriptor descriptor = module.getDescriptor();
            require(!descriptor.isAutomatic(), name + " has an explicit descriptor");
            require(descriptor.isOpen(), name + " is an open module");
            require(self.canRead(module), self.getName() + " reads " + name);
        }
        ModuleDescriptor core = layer.findModule(CORE_MODULE).get().getDescriptor();
        requireTransitive(core, "com.fasterxml.jackson.databind");
        requireTransitive(core, "kotlin.stdlib");

        System.out.println("JPMS usage check passed.");
    }

    public static final class Answer {
        public String capital;
    }

    private static void requireModule(Class<?> type, String module) {
        String actual = type.getModule().getName();
        require(module.equals(actual), type.getName() + " is in module " + actual);
    }

    private static void requireTransitive(ModuleDescriptor descriptor, String module) {
        boolean transitive = descriptor.requires().stream()
                .filter(requires -> requires.name().equals(module))
                .anyMatch(requires -> requires.modifiers().contains(Requires.Modifier.TRANSITIVE));
        require(transitive, descriptor.name() + " requires transitive " + module);
    }

    private static void require(boolean condition, String what) {
        if (!condition) {
            throw new AssertionError("JPMS usage check failed: " + what);
        }
    }
}
