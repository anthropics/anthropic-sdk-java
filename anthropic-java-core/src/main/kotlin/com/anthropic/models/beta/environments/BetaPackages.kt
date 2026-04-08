// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Packages (and their versions) available in this environment. */
class BetaPackages
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val apt: JsonField<List<String>>,
    private val cargo: JsonField<List<String>>,
    private val gem: JsonField<List<String>>,
    private val go: JsonField<List<String>>,
    private val npm: JsonField<List<String>>,
    private val pip: JsonField<List<String>>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("apt") @ExcludeMissing apt: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("cargo") @ExcludeMissing cargo: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("gem") @ExcludeMissing gem: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("go") @ExcludeMissing go: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("npm") @ExcludeMissing npm: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("pip") @ExcludeMissing pip: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(apt, cargo, gem, go, npm, pip, type, mutableMapOf())

    /**
     * Ubuntu/Debian packages to install
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun apt(): List<String> = apt.getRequired("apt")

    /**
     * Rust packages to install
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cargo(): List<String> = cargo.getRequired("cargo")

    /**
     * Ruby packages to install
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun gem(): List<String> = gem.getRequired("gem")

    /**
     * Go packages to install
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun go(): List<String> = go.getRequired("go")

    /**
     * Node.js packages to install
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun npm(): List<String> = npm.getRequired("npm")

    /**
     * Python packages to install
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pip(): List<String> = pip.getRequired("pip")

    /**
     * Package configuration type
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun type(): Optional<Type> = type.getOptional("type")

    /**
     * Returns the raw JSON value of [apt].
     *
     * Unlike [apt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("apt") @ExcludeMissing fun _apt(): JsonField<List<String>> = apt

    /**
     * Returns the raw JSON value of [cargo].
     *
     * Unlike [cargo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cargo") @ExcludeMissing fun _cargo(): JsonField<List<String>> = cargo

    /**
     * Returns the raw JSON value of [gem].
     *
     * Unlike [gem], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("gem") @ExcludeMissing fun _gem(): JsonField<List<String>> = gem

    /**
     * Returns the raw JSON value of [go].
     *
     * Unlike [go], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("go") @ExcludeMissing fun _go(): JsonField<List<String>> = go

    /**
     * Returns the raw JSON value of [npm].
     *
     * Unlike [npm], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("npm") @ExcludeMissing fun _npm(): JsonField<List<String>> = npm

    /**
     * Returns the raw JSON value of [pip].
     *
     * Unlike [pip], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pip") @ExcludeMissing fun _pip(): JsonField<List<String>> = pip

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaPackages].
         *
         * The following fields are required:
         * ```java
         * .apt()
         * .cargo()
         * .gem()
         * .go()
         * .npm()
         * .pip()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPackages]. */
    class Builder internal constructor() {

        private var apt: JsonField<MutableList<String>>? = null
        private var cargo: JsonField<MutableList<String>>? = null
        private var gem: JsonField<MutableList<String>>? = null
        private var go: JsonField<MutableList<String>>? = null
        private var npm: JsonField<MutableList<String>>? = null
        private var pip: JsonField<MutableList<String>>? = null
        private var type: JsonField<Type> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPackages: BetaPackages) = apply {
            apt = betaPackages.apt.map { it.toMutableList() }
            cargo = betaPackages.cargo.map { it.toMutableList() }
            gem = betaPackages.gem.map { it.toMutableList() }
            go = betaPackages.go.map { it.toMutableList() }
            npm = betaPackages.npm.map { it.toMutableList() }
            pip = betaPackages.pip.map { it.toMutableList() }
            type = betaPackages.type
            additionalProperties = betaPackages.additionalProperties.toMutableMap()
        }

        /** Ubuntu/Debian packages to install */
        fun apt(apt: List<String>) = apt(JsonField.of(apt))

        /**
         * Sets [Builder.apt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.apt] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun apt(apt: JsonField<List<String>>) = apply { this.apt = apt.map { it.toMutableList() } }

        /**
         * Adds a single [String] to [Builder.apt].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addApt(apt: String) = apply {
            this.apt =
                (this.apt ?: JsonField.of(mutableListOf())).also { checkKnown("apt", it).add(apt) }
        }

        /** Rust packages to install */
        fun cargo(cargo: List<String>) = cargo(JsonField.of(cargo))

        /**
         * Sets [Builder.cargo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cargo] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun cargo(cargo: JsonField<List<String>>) = apply {
            this.cargo = cargo.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [Builder.cargo].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addCargo(cargo: String) = apply {
            this.cargo =
                (this.cargo ?: JsonField.of(mutableListOf())).also {
                    checkKnown("cargo", it).add(cargo)
                }
        }

        /** Ruby packages to install */
        fun gem(gem: List<String>) = gem(JsonField.of(gem))

        /**
         * Sets [Builder.gem] to an arbitrary JSON value.
         *
         * You should usually call [Builder.gem] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun gem(gem: JsonField<List<String>>) = apply { this.gem = gem.map { it.toMutableList() } }

        /**
         * Adds a single [String] to [Builder.gem].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addGem(gem: String) = apply {
            this.gem =
                (this.gem ?: JsonField.of(mutableListOf())).also { checkKnown("gem", it).add(gem) }
        }

        /** Go packages to install */
        fun go(go: List<String>) = go(JsonField.of(go))

        /**
         * Sets [Builder.go] to an arbitrary JSON value.
         *
         * You should usually call [Builder.go] with a well-typed `List<String>` value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun go(go: JsonField<List<String>>) = apply { this.go = go.map { it.toMutableList() } }

        /**
         * Adds a single [String] to [Builder.go].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addGo(go: String) = apply {
            this.go =
                (this.go ?: JsonField.of(mutableListOf())).also { checkKnown("go", it).add(go) }
        }

        /** Node.js packages to install */
        fun npm(npm: List<String>) = npm(JsonField.of(npm))

        /**
         * Sets [Builder.npm] to an arbitrary JSON value.
         *
         * You should usually call [Builder.npm] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun npm(npm: JsonField<List<String>>) = apply { this.npm = npm.map { it.toMutableList() } }

        /**
         * Adds a single [String] to [Builder.npm].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addNpm(npm: String) = apply {
            this.npm =
                (this.npm ?: JsonField.of(mutableListOf())).also { checkKnown("npm", it).add(npm) }
        }

        /** Python packages to install */
        fun pip(pip: List<String>) = pip(JsonField.of(pip))

        /**
         * Sets [Builder.pip] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pip] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun pip(pip: JsonField<List<String>>) = apply { this.pip = pip.map { it.toMutableList() } }

        /**
         * Adds a single [String] to [Builder.pip].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPip(pip: String) = apply {
            this.pip =
                (this.pip ?: JsonField.of(mutableListOf())).also { checkKnown("pip", it).add(pip) }
        }

        /** Package configuration type */
        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaPackages].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .apt()
         * .cargo()
         * .gem()
         * .go()
         * .npm()
         * .pip()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPackages =
            BetaPackages(
                checkRequired("apt", apt).map { it.toImmutable() },
                checkRequired("cargo", cargo).map { it.toImmutable() },
                checkRequired("gem", gem).map { it.toImmutable() },
                checkRequired("go", go).map { it.toImmutable() },
                checkRequired("npm", npm).map { it.toImmutable() },
                checkRequired("pip", pip).map { it.toImmutable() },
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaPackages = apply {
        if (validated) {
            return@apply
        }

        apt()
        cargo()
        gem()
        go()
        npm()
        pip()
        type().ifPresent { it.validate() }
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (apt.asKnown().getOrNull()?.size ?: 0) +
            (cargo.asKnown().getOrNull()?.size ?: 0) +
            (gem.asKnown().getOrNull()?.size ?: 0) +
            (go.asKnown().getOrNull()?.size ?: 0) +
            (npm.asKnown().getOrNull()?.size ?: 0) +
            (pip.asKnown().getOrNull()?.size ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /** Package configuration type */
    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val PACKAGES = of("packages")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            PACKAGES
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            PACKAGES,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                PACKAGES -> Value.PACKAGES
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                PACKAGES -> Known.PACKAGES
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPackages &&
            apt == other.apt &&
            cargo == other.cargo &&
            gem == other.gem &&
            go == other.go &&
            npm == other.npm &&
            pip == other.pip &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(apt, cargo, gem, go, npm, pip, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPackages{apt=$apt, cargo=$cargo, gem=$gem, go=$go, npm=$npm, pip=$pip, type=$type, additionalProperties=$additionalProperties}"
}
