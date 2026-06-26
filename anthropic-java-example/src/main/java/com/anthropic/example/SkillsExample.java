package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.core.MultipartField;
import com.anthropic.models.beta.AnthropicBeta;
import com.anthropic.models.beta.skills.SkillCreateParams;
import com.anthropic.models.beta.skills.SkillRetrieveParams;
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams;
import java.io.InputStream;

/** Creates a skill from multiple local files, then retrieves the skill and its latest version. */
public final class SkillsExample {
    private SkillsExample() {}

    public static void main(String[] args) {
        // Configures using the `ANTHROPIC_API_KEY` environment variable
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // Display titles must be unique within the workspace, so suffix with a timestamp to keep
        // the example rerunnable.
        var skill = client.beta()
                .skills()
                .create(SkillCreateParams.builder()
                        .addBeta(AnthropicBeta.SKILLS_2025_10_02)
                        .displayTitle("greeting-" + System.currentTimeMillis())
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

        var retrieved = client.beta()
                .skills()
                .retrieve(SkillRetrieveParams.builder()
                        .skillId(skill.id())
                        .addBeta(AnthropicBeta.SKILLS_2025_10_02)
                        .build());
        String version = retrieved.latestVersion().orElseThrow();
        System.out.println("Retrieved skill: " + retrieved.displayTitle().orElse("(untitled)") + " v" + version);

        // The version's name and description are parsed by the API from the uploaded SKILL.md.
        var skillVersion = client.beta()
                .skills()
                .versions()
                .retrieve(VersionRetrieveParams.builder()
                        .skillId(skill.id())
                        .version(version)
                        .addBeta(AnthropicBeta.SKILLS_2025_10_02)
                        .build());
        System.out.println("Retrieved version: name="
                + skillVersion.name()
                + ", directory="
                + skillVersion.directory()
                + ", description="
                + skillVersion.description());
    }
}
