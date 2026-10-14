package com.anthropic.core.opentelemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.http.HttpClient
import io.opentelemetry.api.trace.TracerProvider
import java.util.Optional
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.junit.jupiter.api.parallel.Resources
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.kotlin.mock

@ResourceLock(Resources.SYSTEM_PROPERTIES)
internal class OpenTelemetryOptionsTest {

    private companion object {
        const val ENABLED = "anthropic.openTelemetry"
        const val CONTENT_MODE = "anthropic.openTelemetryTracesContentMode"
        const val MAX_CONTENT_BYTES = "anthropic.openTelemetryTracesMaxContentBytes"
        const val PROPAGATION = "anthropic.openTelemetryPropagation"
        const val OLD_CONTENT_MODE = "anthropic.openTelemetryContentMode"

        val ENVIRONMENT_VARIABLES =
            listOf(
                "ANTHROPIC_OPEN_TELEMETRY",
                "ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE",
                "ANTHROPIC_OPEN_TELEMETRY_TRACES_MAX_CONTENT_BYTES",
                "ANTHROPIC_OPEN_TELEMETRY_PROPAGATION",
            )
    }

    @AfterEach
    fun afterEach() {
        listOf(ENABLED, CONTENT_MODE, MAX_CONTENT_BYTES, PROPAGATION, OLD_CONTENT_MODE).forEach {
            System.clearProperty(it)
        }
    }

    private fun assumeNoEnvironmentVariable() {
        assumeTrue(ENVIRONMENT_VARIABLES.all { System.getenv(it) == null })
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun tracesShorthand_setsOnlyWhetherTheClientEmitsSpans(enabled: Boolean) {
        val options = OpenTelemetryOptions.builder().traces(enabled).build()

        assertThat(options.traces())
            .isEqualTo(OpenTelemetryTraces.builder().enabled(enabled).build())
        assertThat(options.traces().enabled()).isEqualTo(enabled)
        assertThat(options.traces().contentMode()).isEqualTo(OpenTelemetryContentMode.METADATA_ONLY)
        assertThat(options.traces().maxContentBytes()).isEmpty
        assertThat(options.tracerProvider()).isEmpty
        assertThat(options.propagation()).isTrue()
        assertThat(OpenTelemetryOptions.ofEnabled(enabled)).isEqualTo(options)
    }

    @Test
    fun tracesShorthand_replacesTheTracesThatWereSet() {
        val options =
            OpenTelemetryOptions.builder()
                .traces(
                    OpenTelemetryTraces.builder()
                        .contentMode(OpenTelemetryContentMode.CONTENT)
                        .maxContentBytes(10L)
                        .build()
                )
                .traces(false)
                .build()

        assertThat(options.traces()).isEqualTo(OpenTelemetryTraces.builder().enabled(false).build())
    }

    @Test
    fun none_equalsAnUnsetBuilder() {
        assertThat(OpenTelemetryOptions.none()).isEqualTo(OpenTelemetryOptions.builder().build())
        assertThat(OpenTelemetryRequestOptions.none())
            .isEqualTo(OpenTelemetryRequestOptions.builder().build())
    }

    @Test
    fun none_defaultsToSpansWithoutContent() {
        val traces = OpenTelemetryOptions.none().traces()

        assertThat(traces).isEqualTo(OpenTelemetryTraces.builder().build())
        assertThat(traces.enabled()).isTrue()
        assertThat(traces.contentMode()).isEqualTo(OpenTelemetryContentMode.METADATA_ONLY)
        assertThat(traces.maxContentBytes()).isEmpty
    }

    @Test
    fun propagation_defaultsToOn() {
        assertThat(OpenTelemetryOptions.none().propagation()).isTrue()
        assertThat(OpenTelemetryOptions.builder().propagation(true).build())
            .isEqualTo(OpenTelemetryOptions.none())
    }

    @Test
    fun toBuilder_keepsEveryOption() {
        val options =
            OpenTelemetryOptions.builder()
                .tracerProvider(TracerProvider.noop())
                .propagation(false)
                .traces(false)
                .build()

        assertThat(options.toBuilder().build()).isEqualTo(options).hasSameHashCodeAs(options)
        assertThat(options.propagation()).isFalse()
        assertThat(options.toString()).contains("propagation=false", "enabled=false")
        assertThat(options.toBuilder().propagation(true).build()).isNotEqualTo(options)
    }

    @Test
    fun traces_toBuilder_keepsEverySetting() {
        val traces =
            OpenTelemetryTraces.builder()
                .enabled(false)
                .contentMode(OpenTelemetryContentMode.CONTENT)
                .maxContentBytes(2048L)
                .build()

        assertThat(traces.toBuilder().build()).isEqualTo(traces).hasSameHashCodeAs(traces)
        assertThat(traces.maxContentBytes()).contains(2048L)
        assertThat(traces.toString()).contains("enabled=false", "CONTENT", "maxContentBytes=2048")
        assertThat(traces.toBuilder().maxContentBytes(Optional.empty()).build())
            .isNotEqualTo(traces)
    }

    @ParameterizedTest
    @ValueSource(longs = [0L, -1L, Long.MIN_VALUE])
    fun maxContentBytes_underOne_isRejectedWithItsNameAndValue(maxContentBytes: Long) {
        val builder = OpenTelemetryTraces.builder()

        assertThatThrownBy { builder.maxContentBytes(maxContentBytes) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("`maxContentBytes` must be at least 1, but was $maxContentBytes")
        assertThatThrownBy { builder.maxContentBytes(Optional.of(maxContentBytes)) }
            .isInstanceOf(IllegalArgumentException::class.java)
        assertThat(builder.build().maxContentBytes()).isEmpty
    }

    @ParameterizedTest
    @ValueSource(longs = [1L, 750_000L, Long.MAX_VALUE])
    fun maxContentBytes_ofAtLeastOne_isKept(maxContentBytes: Long) {
        val traces = OpenTelemetryTraces.builder().maxContentBytes(maxContentBytes).build()

        assertThat(traces.maxContentBytes()).contains(maxContentBytes)
    }

    @Test
    fun maxContentBytes_null_unsetsIt() {
        val traces = OpenTelemetryTraces.builder().maxContentBytes(5L).maxContentBytes(null).build()

        assertThat(traces).isEqualTo(OpenTelemetryTraces.builder().build())
    }

    @Test
    fun fromEnv_readsTheSystemProperties() {
        System.setProperty(ENABLED, "False")
        System.setProperty(CONTENT_MODE, "Content")
        System.setProperty(MAX_CONTENT_BYTES, "4096")
        System.setProperty(PROPAGATION, "False")

        val options = OpenTelemetryOptions.fromEnv()
        val traces = options.traces()

        assertThat(traces.enabled()).isFalse()
        assertThat(traces.contentMode()).isEqualTo(OpenTelemetryContentMode.CONTENT)
        assertThat(traces.maxContentBytes()).contains(4096L)
        assertThat(options.propagation()).isFalse()
    }

    @Test
    fun fromEnv_anyOtherValue_selectsTheDefault() {
        System.setProperty(ENABLED, "0")
        System.setProperty(CONTENT_MODE, "everything")
        System.setProperty(PROPAGATION, "0")

        val options = OpenTelemetryOptions.fromEnv()
        val traces = options.traces()

        assertThat(traces.enabled()).isTrue()
        assertThat(traces.contentMode()).isEqualTo(OpenTelemetryContentMode.METADATA_ONLY)
        assertThat(options.propagation()).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "0", "-5", "1.5", "1k", "many", "9223372036854775808"])
    fun fromEnv_maxContentBytesThatIsNoWholeNumberOfAtLeastOne_selectsTheDefault(value: String) {
        System.setProperty(MAX_CONTENT_BYTES, value)

        assertThat(OpenTelemetryOptions.fromEnv().traces().maxContentBytes()).isEmpty
        // As for the content mode, the default also replaces a bound that the application set.
        val configured =
            OpenTelemetryOptions.builder()
                .traces(OpenTelemetryTraces.builder().maxContentBytes(7L).build())
                .build()
        val options = clientOptionsBuilder().openTelemetry(configured).fromEnv().build()
        assertThat(options.openTelemetry.traces().maxContentBytes()).isEmpty
    }

    @Test
    fun fromEnv_theOldContentModeProperty_isNotRead() {
        assumeTrue(System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE") == null)
        System.setProperty(OLD_CONTENT_MODE, "content")

        val traces = OpenTelemetryOptions.fromEnv().traces()

        assertThat(traces.contentMode()).isEqualTo(OpenTelemetryContentMode.METADATA_ONLY)
    }

    @Test
    fun fromEnv_nothingSet_leavesTheDefaults() {
        assumeNoEnvironmentVariable()

        assertThat(OpenTelemetryOptions.fromEnv()).isEqualTo(OpenTelemetryOptions.none())
    }

    @Test
    fun fromEnv_readsTheEnvironmentVariables() {
        val enabled = System.getenv("ANTHROPIC_OPEN_TELEMETRY")
        val contentMode = System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE")
        val maxContentBytes = System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_MAX_CONTENT_BYTES")
        val propagation = System.getenv("ANTHROPIC_OPEN_TELEMETRY_PROPAGATION")
        assumeTrue(
            enabled != null || contentMode != null || maxContentBytes != null || propagation != null
        )

        val options = OpenTelemetryOptions.fromEnv()
        val traces = options.traces()

        assertThat(traces.enabled()).isEqualTo(!enabled.equals("false", ignoreCase = true))
        assertThat(traces.contentMode() == OpenTelemetryContentMode.CONTENT)
            .isEqualTo(contentMode.equals("content", ignoreCase = true))
        assertThat(traces.maxContentBytes())
            .isEqualTo(Optional.ofNullable(maxContentBytes?.toLongOrNull()?.takeIf { it >= 1 }))
        assertThat(options.propagation()).isEqualTo(!propagation.equals("false", ignoreCase = true))
    }

    @Test
    fun fromEnv_theOldContentModeVariable_isNotRead() {
        assumeTrue(System.getenv("ANTHROPIC_OPEN_TELEMETRY_CONTENT_MODE") != null)
        assumeTrue(System.getenv("ANTHROPIC_OPEN_TELEMETRY_TRACES_CONTENT_MODE") == null)

        val traces = OpenTelemetryOptions.fromEnv().traces()

        assertThat(traces.contentMode()).isEqualTo(OpenTelemetryContentMode.METADATA_ONLY)
    }

    @Test
    fun fromEnv_systemPropertiesTakePrecedenceOverTheEnvironmentVariables() {
        assumeTrue(System.getenv("ANTHROPIC_OPEN_TELEMETRY").equals("false", ignoreCase = true))
        System.setProperty(ENABLED, "true")

        assertThat(OpenTelemetryOptions.fromEnv().traces().enabled()).isTrue()
    }

    @Test
    fun fromEnv_composesWithOtherOptions() {
        System.setProperty(ENABLED, "false")

        val fromEnv = OpenTelemetryOptions.fromEnv()
        val options =
            fromEnv
                .toBuilder()
                .traces(
                    fromEnv
                        .traces()
                        .toBuilder()
                        .contentMode(OpenTelemetryContentMode.CONTENT)
                        .build()
                )
                .build()

        assertThat(options.traces().enabled()).isFalse()
        assertThat(options.traces().contentMode()).isEqualTo(OpenTelemetryContentMode.CONTENT)
    }

    @Test
    fun clientFromEnv_nothingSet_keepsTheConfiguredOptions() {
        assumeNoEnvironmentVariable()
        val configured =
            OpenTelemetryOptions.builder()
                .tracerProvider(TracerProvider.noop())
                .propagation(false)
                .traces(
                    OpenTelemetryTraces.builder()
                        .enabled(false)
                        .contentMode(OpenTelemetryContentMode.CONTENT)
                        .maxContentBytes(512L)
                        .build()
                )
                .build()

        val options = clientOptionsBuilder().openTelemetry(configured).fromEnv().build()

        assertThat(options.openTelemetry).isEqualTo(configured)
    }

    @ParameterizedTest
    @CsvSource(
        "anthropic.openTelemetry, true, true, METADATA_ONLY, 512, false",
        "anthropic.openTelemetryTracesContentMode, content, false, CONTENT, 512, false",
        "anthropic.openTelemetryTracesMaxContentBytes, 64, false, METADATA_ONLY, 64, false",
        "anthropic.openTelemetryPropagation, true, false, METADATA_ONLY, 512, true",
    )
    fun clientFromEnv_onePropertySet_changesOnlyItsSetting(
        property: String,
        value: String,
        enabled: Boolean,
        contentMode: OpenTelemetryContentMode,
        maxContentBytes: Long,
        propagation: Boolean,
    ) {
        assumeNoEnvironmentVariable()
        System.setProperty(property, value)
        val tracerProvider = TracerProvider.noop()
        val configured =
            OpenTelemetryOptions.builder()
                .tracerProvider(tracerProvider)
                .propagation(false)
                .traces(OpenTelemetryTraces.builder().enabled(false).maxContentBytes(512L).build())
                .build()

        val options = clientOptionsBuilder().openTelemetry(configured).fromEnv().build()

        assertThat(options.openTelemetry.tracerProvider()).containsSame(tracerProvider)
        assertThat(options.openTelemetry.propagation()).isEqualTo(propagation)
        assertThat(options.openTelemetry.traces())
            .isEqualTo(
                OpenTelemetryTraces.builder()
                    .enabled(enabled)
                    .contentMode(contentMode)
                    .maxContentBytes(maxContentBytes)
                    .build()
            )
    }

    @Test
    fun clientFromEnv_beforeTheOption_theOptionReplacesWhatItSet() {
        System.setProperty(ENABLED, "false")
        System.setProperty(CONTENT_MODE, "content")
        System.setProperty(MAX_CONTENT_BYTES, "64")
        System.setProperty(PROPAGATION, "false")
        val configured =
            OpenTelemetryOptions.builder().tracerProvider(TracerProvider.noop()).build()

        val options = clientOptionsBuilder().fromEnv().openTelemetry(configured).build()

        assertThat(options.openTelemetry).isEqualTo(configured)
    }

    @Test
    fun clientBuilder_withoutFromEnv_readsNoSystemProperty() {
        System.setProperty(ENABLED, "false")
        System.setProperty(CONTENT_MODE, "content")
        System.setProperty(MAX_CONTENT_BYTES, "64")
        System.setProperty(PROPAGATION, "false")

        val options = clientOptionsBuilder().build()

        assertThat(options.openTelemetry).isEqualTo(OpenTelemetryOptions.none())
    }

    private fun clientOptionsBuilder(): ClientOptions.Builder =
        ClientOptions.builder().httpClient(mock<HttpClient>())
}
