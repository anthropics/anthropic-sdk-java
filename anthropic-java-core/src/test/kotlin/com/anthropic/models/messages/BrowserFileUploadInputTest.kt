package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import kotlin.jvm.optionals.getOrNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BrowserFileUploadInputTest {

    @Test
    fun create() {
        val browserFileUploadInput =
            BrowserFileUploadInput.builder()
                .target(BrowserRefTarget.of("ref"))
                .addDocumentId("string")
                .addPath("string")
                .tabId("tab_id")
                .build()

        assertThat(browserFileUploadInput.target()).isEqualTo(BrowserRefTarget.of("ref"))
        assertThat(browserFileUploadInput.documentIds().getOrNull()).containsExactly("string")
        assertThat(browserFileUploadInput.paths().getOrNull()).containsExactly("string")
        assertThat(browserFileUploadInput.tabId()).contains("tab_id")
    }

    @Test
    fun addToUnsetListsOnToBuilder() {
        val baseBrowserFileUploadInput = BrowserFileUploadInput.of(BrowserRefTarget.of("ref"))

        val browserFileUploadInput =
            baseBrowserFileUploadInput.toBuilder().addDocumentId("string").addPath("string").build()

        assertThat(browserFileUploadInput.documentIds().getOrNull()).containsExactly("string")
        assertThat(browserFileUploadInput.paths().getOrNull()).containsExactly("string")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val browserFileUploadInput =
            BrowserFileUploadInput.builder()
                .target(BrowserRefTarget.of("ref"))
                .addDocumentId("string")
                .addPath("string")
                .tabId("tab_id")
                .build()

        val roundtrippedBrowserFileUploadInput =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(browserFileUploadInput),
                jacksonTypeRef<BrowserFileUploadInput>(),
            )

        assertThat(roundtrippedBrowserFileUploadInput).isEqualTo(browserFileUploadInput)
    }
}
