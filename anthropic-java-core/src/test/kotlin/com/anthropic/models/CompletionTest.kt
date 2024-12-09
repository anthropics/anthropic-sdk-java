// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CompletionTest {

    @Test
    fun createCompletion() {
        val completion =
            Completion.builder()
                .id("id")
                .completion(" Hello! My name is Claude.")
                .model(Model.CLAUDE_3_5_HAIKU_LATEST)
                .stopReason("stop_sequence")
                .type(Completion.Type.COMPLETION)
                .build()
        assertThat(completion).isNotNull
        assertThat(completion.id()).isEqualTo("id")
        assertThat(completion.completion()).isEqualTo(" Hello! My name is Claude.")
        assertThat(completion.model()).isEqualTo(Model.CLAUDE_3_5_HAIKU_LATEST)
        assertThat(completion.stopReason()).contains("stop_sequence")
        assertThat(completion.type()).isEqualTo(Completion.Type.COMPLETION)
    }
}
