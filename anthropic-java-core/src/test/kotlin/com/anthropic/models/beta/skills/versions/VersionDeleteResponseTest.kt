// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionDeleteResponseTest {

    @Test
    fun create() {
        val versionDeleteResponse =
            VersionDeleteResponse.builder().id("1759178010641129").type("type").build()

        assertThat(versionDeleteResponse.id()).isEqualTo("1759178010641129")
        assertThat(versionDeleteResponse.type()).isEqualTo("type")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val versionDeleteResponse =
            VersionDeleteResponse.builder().id("1759178010641129").type("type").build()

        val roundtrippedVersionDeleteResponse =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(versionDeleteResponse),
                jacksonTypeRef<VersionDeleteResponse>(),
            )

        assertThat(roundtrippedVersionDeleteResponse).isEqualTo(versionDeleteResponse)
    }
}
