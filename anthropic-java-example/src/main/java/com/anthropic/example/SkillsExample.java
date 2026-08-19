package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.MultipartField;
import com.anthropic.models.skills.SkillCreateParams;
import com.anthropic.models.skills.versions.VersionRetrieveParams;
import java.io.InputStream;

/** Creates a skill from multiple local files, then retrieves the skill and its latest version. */
public final class SkillsExample {
    private SkillsExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        var skill = client.skills()
                .create(SkillCreateParams.builder()
                        .displayName("greeting-" + System.currentTimeMillis())
                        // Each file's `filename` is its path inside the skill, including the
                        // skill's top-level directory.
                        .addFile(MultipartField.<InputStream>builder()
                                .value(classloader.getResourceAsStream("greeting-SKILL.md"))
                                .filename("greeting/SKILL.md")
                                .build())
                        .addFile(MultipartField.<InputStream>builder()
                                .value(classloader.getResourceAsStream("greeting-reference.md"))
                                .filename("greeting/reference.md")
                                .build())
                        .build());
        System.out.println("Created skill: " + skill.id());

        var retrieved = client.skills().retrieve(skill.id());
        String versionId = retrieved.latestVersionId();
        System.out.println("Retrieved skill: " + retrieved.displayName() + " (latest version " + versionId + ")");

        // The version's name and description are parsed by the API from the uploaded SKILL.md.
        var skillVersion = client.skills()
                .versions()
                .retrieve(VersionRetrieveParams.builder()
                        .skillId(skill.id())
                        .version(versionId)
                        .build());
        System.out.println(
                "Retrieved version: name=" + skillVersion.name() + ", description=" + skillVersion.description());
    }
}
