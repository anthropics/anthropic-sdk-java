package com.anthropic.models.messages

import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ServerToolCallerTest {

    @Test
    fun create() {
        val serverToolCaller = ServerToolCaller.of("srvtoolu_c")

        assertThat(serverToolCaller.toolId()).isEqualTo("srvtoolu_c")
    }

    @Test
    fun roundtrip() {
        val jsonMapper = jsonMapper()
        val serverToolCaller = ServerToolCaller.of("srvtoolu_c")

        val roundtrippedServerToolCaller =
            jsonMapper.readValue(
                jsonMapper.writeValueAsString(serverToolCaller),
                jacksonTypeRef<ServerToolCaller>(),
            )

        assertThat(roundtrippedServerToolCaller).isEqualTo(serverToolCaller)
    }
}
