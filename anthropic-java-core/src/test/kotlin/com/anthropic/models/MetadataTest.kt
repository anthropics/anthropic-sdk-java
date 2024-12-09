// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MetadataTest {

    @Test
    fun createMetadata() {
        val metadata = Metadata.builder().userId("13803d75-b4b5-4c3e-b2a2-6f21399b021b").build()
        assertThat(metadata).isNotNull
        assertThat(metadata.userId()).contains("13803d75-b4b5-4c3e-b2a2-6f21399b021b")
    }
}
