package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import io.gitlab.arturbosch.detekt.test.lint
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
internal class AnthropicRulesTest(private val env: KotlinCoreEnvironment) {

    @Test
    fun acronymCasing() {
        val findings =
            AcronymCasing(Config.empty)
                .lint(
                    """
                    class SSEMessage
                    class SseMessage
                    class IOExceptionWrappingSequence
                    class HTTPServer
                    fun parseHTTP2Response() = 1
                    fun getSSE() = 1
                    fun getID() = 1
                    fun getId() = 1
                    """
                        .trimIndent()
                )
        assertThat(findings.map { it.message })
            .hasSize(4)
            .anyMatch { it.contains("SSEMessage") && it.contains("`SSE`") && it.contains("`Sse`") }
            .anyMatch { it.contains("HTTPServer") && it.contains("`HTTP`") }
            .anyMatch { it.contains("parseHTTP2Response") && it.contains("`HTTP`") }
            .anyMatch { it.contains("getSSE") && it.contains("`SSE`") }
            .noneMatch { it.contains("IOException") }
            .noneMatch { it.contains("getID") }
            .noneMatch { it.contains("HTT`") }
    }

    @Test
    fun builderEntryPoints() {
        val findings =
            BuilderEntryPoints(Config.empty)
                .lint(
                    """
                    class Missing private constructor() {
                        class Builder internal constructor()
                    }
                    class NoToBuilder private constructor() {
                        companion object { @JvmStatic fun builder() = Builder() }
                        class Builder internal constructor()
                    }
                    class Good private constructor() {
                        // `@JvmStatic` is CompanionFunctionMissingJvmStatic's job, not this rule's.
                        companion object { fun builder() = Builder() }
                        fun toBuilder() = Builder()
                        class Builder internal constructor()
                    }
                    class NoBuilderClass private constructor()
                    """
                        .trimIndent()
                )
        assertThat(findings.map { it.message })
            .hasSize(3)
            .anyMatch { it.contains("Missing") && it.contains("builder()") }
            .anyMatch { it.contains("Missing") && it.contains("toBuilder") }
            .anyMatch { it.contains("NoToBuilder") && it.contains("toBuilder") }
    }

    @Test
    fun builderNullablePrimitiveSetterMissingUnboxedOverload() {
        val findings =
            BuilderNullablePrimitiveSetterMissingUnboxedOverload(Config.empty)
                .lint(
                    """
                    class Thing private constructor() {
                        class Builder internal constructor() {
                            fun badLong(x: Long?) = apply { }
                            fun badDouble(x: Double?) = apply { }
                            fun goodLong(x: Long?) = apply { }
                            fun goodLong(x: Long) = goodLong(x as Long?)
                            fun intOk(x: Int?) = apply { }
                            fun boolOk(x: Boolean?) = apply { }
                            fun ref(x: String?) = apply { }
                        }
                    }
                    """
                        .trimIndent()
                )
        assertThat(findings.map { it.message })
            .hasSize(2)
            .anyMatch { it.contains("badLong(Long?)") }
            .anyMatch { it.contains("badDouble(Double?)") }
    }

    @Test
    fun builderNullableSetterMissingOptionalOverload() {
        val findings =
            BuilderNullableSetterMissingOptionalOverload(Config.empty)
                .compileAndLintWithContext(
                    env,
                    """
                    import java.util.Optional
                    import kotlin.jvm.optionals.getOrNull
                    class Thing private constructor() {
                        class Builder internal constructor() {
                            fun bad(x: String?) = apply { }
                            @JvmOverloads fun fromEnv(x: String? = null) = apply { }
                            @JvmSynthetic internal fun hiddenHelper(x: String?) = apply { }
                            fun good(x: Int?) = apply { }
                            fun good(x: Optional<Int>) = good(x.getOrNull())
                            fun required(x: Long) = apply { }
                        }
                    }
                    """
                        .trimIndent(),
                )
        assertThat(findings).hasSize(1)
        assertThat(findings.single().message).contains("bad(String?)")
    }

    @Test
    fun companionConstantMissingJvmField() {
        val findings =
            CompanionConstantMissingJvmField(Config.empty)
                .lint(
                    """
                    class Thing {
                        companion object {
                            val BAD = of("x")
                            @JvmField val GOOD = of("y")
                            const val ALSO_GOOD = "z"
                            @JvmStatic val STATIC_OK = of("s")
                            private val PRIVATE_OK = of("p")
                            val notAConstant = of("n")
                        }
                    }
                    """
                        .trimIndent()
                )
        assertThat(findings).hasSize(1)
        assertThat(findings.single().message).contains("BAD")
    }

    @Test
    fun companionFunctionMissingJvmStatic() {
        val findings =
            CompanionFunctionMissingJvmStatic(Config.empty)
                .lint(
                    """
                    class Thing {
                        companion object {
                            fun bad() = 1
                            @JvmStatic fun good() = 1
                            @JvmSynthetic fun hidden() = 1
                            internal fun internalSkipped() = 1
                        }
                    }
                    object NotCompanion { fun fine() = 1 }
                    """
                        .trimIndent()
                )
        assertThat(findings).hasSize(1)
        assertThat(findings.single().message).contains("bad")
    }

    @Test
    fun defaultArgsInvisibleToJava() {
        val findings =
            DefaultArgsInvisibleToJava(Config.empty)
                .lint(
                    """
                    interface Provider {
                        fun bad(force: Boolean = false): String
                        fun covered(a: Int, force: Boolean = false): String
                        fun covered(renamed: Int): String
                        fun typeMismatch(a: Int, force: Boolean = false): String
                        fun typeMismatch(a: Long): String
                        fun ok(force: Boolean): String
                    }
                    class Impl {
                        fun alsoBad(force: Boolean = false) = ""
                        @JvmOverloads fun ok(force: Boolean = false) = ""
                        @JvmSynthetic fun hidden(x: Int = 0) = ""
                        private fun priv(x: Int = 0) = ""
                        companion object {
                            fun companionCovered(x: Int = 0) = x
                            fun companionCovered() = 0
                        }
                    }
                    fun topLevelBad(x: Int = 0) = x
                    fun topLevelCovered(x: Int = 0) = x
                    fun topLevelCovered() = 0
                    """
                        .trimIndent()
                )
        assertThat(findings.map { it.message })
            .hasSize(4)
            .anyMatch { it.contains("`bad`") && it.contains("explicit overload") }
            .anyMatch { it.contains("`typeMismatch`") }
            .anyMatch { it.contains("`alsoBad`") && it.contains("@JvmOverloads") }
            .anyMatch { it.contains("`topLevelBad`") }
            .noneMatch { it.contains("`covered`") }
    }

    @Test
    fun fileJvmSynthetic() {
        val findings =
            FileJvmSynthetic(Config.empty)
                .lint(
                    """
                    @file:JvmSynthetic
                    @file:JvmName("Stuff")
                    package com.example
                    @JvmSynthetic internal fun ok() = 1
                    """
                        .trimIndent()
                )
        assertThat(findings).hasSize(1)
    }

    @Test
    fun fileJvmSyntheticAbsent() {
        assertThat(
                FileJvmSynthetic(Config.empty)
                    .lint(
                        """
                        @file:JvmName("Stuff")
                        package com.example
                        @JvmSynthetic internal fun ok() = 1
                        """
                            .trimIndent()
                    )
            )
            .isEmpty()
    }

    @Test
    fun internalMemberMissingJvmSynthetic() {
        val findings =
            InternalMemberMissingJvmSynthetic(Config.empty)
                .lint(
                    """
                    internal fun leakFun() = 1
                    @JvmSynthetic internal fun okFun() = 1
                    private fun privFun() = 1
                    fun publicFun() = 1

                    class Thing internal constructor() {
                        internal val leakVal = 1
                        @get:JvmSynthetic internal val okVal = 1
                        @JvmSynthetic internal val okVal2 = 1
                        internal var leakVar = 1
                        var leakSetter = 1
                            internal set
                        var okSetter = 1
                            @JvmSynthetic internal set
                    }

                    private class Hidden { internal fun unreachable() = 1 }
                    internal class InternalContainer { internal fun unreachable() = 1 }
                    """
                        .trimIndent()
                )
        assertThat(findings.map { it.message })
            .hasSize(4)
            .anyMatch { it.contains("leakFun") }
            .anyMatch { it.contains("leakVal") && it.contains("@get:JvmSynthetic") }
            .anyMatch { it.contains("leakVar") && it.contains("@set:JvmSynthetic") }
            .anyMatch { it.contains("internal set on leakSetter") }
    }

    @Test
    fun kotlinOnlyTypeInPublicApi() {
        val findings =
            KotlinOnlyTypeInPublicApi(Config.empty)
                .compileAndLintWithContext(
                    env,
                    """
                    class Thing {
                        fun seq(): Sequence<String> = emptySequence()
                        fun pair(): Pair<Int, Int> = 1 to 1
                        fun lambda(f: (Int) -> String) = f(1)
                        fun nestedSeq(): List<Sequence<Int>> = emptyList()
                        fun ok(): java.util.stream.Stream<String> = java.util.stream.Stream.empty()
                        fun okFn(f: java.util.function.Function<Int, String>) = f.apply(1)
                        private fun privSeq(): Sequence<String> = emptySequence()
                        @JvmSynthetic fun hidden(): Pair<Int, Int> = 1 to 1
                        class Result
                        fun localResult(): Result = Result()
                    }
                    """
                        .trimIndent(),
                )
        assertThat(findings.map { it.message })
            .hasSize(4)
            .anyMatch { it.contains("`seq`") && it.contains("kotlin.sequences.Sequence") }
            .anyMatch { it.contains("`pair`") && it.contains("kotlin.Pair") }
            .anyMatch { it.contains("`lambda`") && it.contains("->") }
            .anyMatch { it.contains("`nestedSeq`") && it.contains("kotlin.sequences.Sequence") }
            .noneMatch { it.contains("localResult") }
    }

    @Test
    fun nullablePublicReturn() {
        val findings =
            NullablePublicReturn(Config.empty)
                .compileAndLintWithContext(
                    env,
                    """
                    class Thing {
                        private val backing: String? = null
                        fun bad(): String? = null
                        val alsoBad: Int? = null
                        fun inferredBad() = backing
                        fun ok(): java.util.Optional<String> = java.util.Optional.empty()
                        fun inferredOk() = ""
                        fun apply() = apply { }
                        internal fun internalOk(): String? = null
                        @JvmSynthetic fun syntheticOk(): String? = null
                    }
                    """
                        .trimIndent(),
                )
        assertThat(findings.map { it.message })
            .hasSize(3)
            .anyMatch { it.contains("`bad`") }
            .anyMatch { it.contains("`alsoBad`") }
            .anyMatch { it.contains("`inferredBad`") && it.contains("Optional<String>") }
    }

    @Test
    fun optionalOrElseNull() {
        val findings =
            OptionalOrElseNull(Config.empty)
                .compileAndLintWithContext(
                    env,
                    """
                    import java.util.Optional
                    class Other { fun orElse(x: String?): String? = x }
                    fun f(o: Optional<String>, d: String, other: Other) {
                        val a = o.orElse(null)
                        val b = o.orElse("x")
                        val c = o.orElse(d)
                        val e = other.orElse(null)
                    }
                    """
                        .trimIndent(),
                )
        assertThat(findings).hasSize(1)
    }

    @Test
    fun publicConstructor() {
        val findings =
            PublicConstructor(Config.empty)
                .compileAndLintWithContext(
                    env,
                    """
                    class Bad(val x: Int)
                    class AlsoBad
                    class Good private constructor(val x: Int)
                    class GoodInternal internal constructor()
                    class GoodSecondary private constructor() { internal constructor(x: Int) : this() }
                    abstract class AbstractOk(val x: Int)
                    sealed class SealedOk
                    enum class EnumOk { A }
                    annotation class AnnOk
                    open class FooException(m: String) : RuntimeException(m)
                    class IndirectFoo : FooException("x")
                    internal class InternalOk(val x: Int)
                    """
                        .trimIndent(),
                )
        assertThat(findings.map { it.message }).hasSize(2).allMatch { it.contains("Bad") }
    }

    @Test
    fun publicPropertyMissingJvmName() {
        val findings =
            PublicPropertyMissingJvmName(Config.empty)
                .lint(
                    """
                    class Thing {
                        val bad: String = ""
                        @get:JvmName("good") val good: String = ""
                        val goodAccessor: String @JvmName("goodAccessor") get() = ""
                        private val hidden: String = ""
                        @JvmField val field: String = ""
                    }
                    interface Iface { val ifaceProp: String }
                    abstract class Base { abstract val absProp: String; open val openProp: String = "" }
                    object Constants { val skip: String = "" }
                    private class Hidden { val skip: String = "" }
                    """
                        .trimIndent()
                )
        assertThat(findings.map { it.message })
            .hasSize(4)
            .anyMatch { it.contains("`bad`") && it.contains("@get:JvmName") }
            .anyMatch { it.contains("`ifaceProp`") && it.contains("fun ifaceProp()") }
            .anyMatch { it.contains("`absProp`") && it.contains("fun absProp()") }
            .anyMatch { it.contains("`openProp`") && it.contains("fun openProp()") }
    }

    @Test
    fun publicSuspendFunction() {
        val findings =
            PublicSuspendFunction(Config.empty)
                .lint(
                    """
                    class Thing {
                        suspend fun bad() = 1
                        private suspend fun ok() = 1
                        internal suspend fun alsoOk() = 1
                        @JvmSynthetic suspend fun hidden() = 1
                        fun notSuspend() = 1
                    }
                    """
                        .trimIndent()
                )
        assertThat(findings).hasSize(1)
        assertThat(findings.single().message).contains("bad")
    }

    @Test
    fun runtimeExceptionSubclass() {
        val findings =
            RuntimeExceptionSubclass(Config.empty)
                .compileAndLintWithContext(
                    env,
                    """
                    open class AnthropicException : RuntimeException()
                    class BadException : RuntimeException()
                    class StrayThrowable : Throwable()
                    class GoodException : AnthropicException()
                    """
                        .trimIndent(),
                )
        assertThat(findings.map { it.message })
            .hasSize(2)
            .anyMatch { it.contains("BadException") }
            .anyMatch { it.contains("StrayThrowable") }
    }

    @Test
    fun testClassMustBeInternal() {
        val findings =
            TestClassMustBeInternal(Config.empty)
                .lint(
                    """
                    class BadTest { @Test fun foo() {} }
                    internal class GoodTest { @Test fun foo() {} }
                    class NotATest { fun foo() {} }
                    """
                        .trimIndent()
                )
        assertThat(findings).hasSize(1)
        assertThat(findings.single().message).contains("BadTest")
    }

    @Test
    fun throwableSubclassNaming() {
        val findings =
            ThrowableSubclassNaming(Config.empty)
                .compileAndLintWithContext(
                    env,
                    """
                    open class Base
                    class FooError : RuntimeException()
                    open class FooException : RuntimeException()
                    class IndirectError : FooException()
                    class NotThrowable : Base()
                    """
                        .trimIndent(),
                )
        assertThat(findings.map { it.message })
            .hasSize(2)
            .anyMatch { it.contains("FooError") }
            .anyMatch { it.contains("IndirectError") }
            .noneMatch { it.contains("NotThrowable") }
    }

    @Test
    fun topLevelPublicWithoutFileJvmName() {
        val bad =
            TopLevelPublicWithoutFileJvmName(Config.empty)
                .lint(
                    """
                    package com.example
                    fun publicTopLevel() = 1
                    """
                        .trimIndent()
                )
        assertThat(bad).hasSize(1)

        val good =
            TopLevelPublicWithoutFileJvmName(Config.empty)
                .lint(
                    """
                    @file:JvmName("Stuff")
                    package com.example
                    fun publicTopLevel() = 1
                    """
                        .trimIndent()
                )
        assertThat(good).isEmpty()

        val onlyClasses =
            TopLevelPublicWithoutFileJvmName(Config.empty)
                .lint(
                    """
                    package com.example
                    class Thing
                    interface Stuff
                    """
                        .trimIndent()
                )
        assertThat(onlyClasses).isEmpty()

        val onlyInternal =
            TopLevelPublicWithoutFileJvmName(Config.empty)
                .lint(
                    """
                    package com.example
                    @JvmSynthetic internal fun helper() = 1
                    """
                        .trimIndent()
                )
        assertThat(onlyInternal).isEmpty()
    }
}
