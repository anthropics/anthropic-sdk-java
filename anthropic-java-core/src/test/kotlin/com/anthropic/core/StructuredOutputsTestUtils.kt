package com.anthropic.core

import java.lang.reflect.Method
import kotlin.collections.all
import kotlin.collections.contains
import kotlin.collections.drop
import kotlin.collections.find
import kotlin.collections.firstOrNull
import kotlin.collections.indices
import kotlin.collections.joinToString
import kotlin.collections.map
import kotlin.jvm.javaClass
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KVisibility
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.jvm.javaMethod
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.fail

// Constants for values that can be used in many of the tests as sample input or output values.
//
// Where a function returns `Optional<T>`, `JsonField<T>` or `JsonValue` There is no need to provide
// a value that matches the type `<T>`, a simple `String` value of `"a-string"` will work OK.
internal const val STRING = "a-string"
internal val JSON_VALUE = JsonValue.from(STRING)
internal const val LONG: Long = 42L

/**
 * Defines a test case where a function in a delegator passes its parameters through to a
 * corresponding function in a delegate.
 */
// Want `vararg`, so cannot use `data class`. Needs a custom `toString`, anyway.
internal class DelegationWriteTestCase(
    val functionName: String,
    /**
     * The values to pass to the function being tested. If the first input value is `null`, it must
     * be the only value. Only the first input value may be `null`, all others must be non-`null`.
     * This is not enforced by this class, but is assumed by the related utility functions.
     */
    vararg val inputValues: Any?,
) {
    /** Gets the string representation that identifies the test function when running JUnit. */
    override fun toString(): String =
        "$functionName(${inputValues.joinToString(", ") {
            it?.javaClass?.simpleName ?: "null"
        }})"
}

/** A basic class used as the generic type when testing. */
internal class X(val s: String) {
    override fun equals(other: Any?) = other is X && other.s == s

    override fun hashCode() = s.hashCode()
}

/**
 * Checks that all functions in one class have a corresponding function with the same name and
 * parameter types in another class. A list of function names that should be allowed as exceptions
 * can be given. Non-public functions are ignored, as they are considered to be implementation
 * details of each class.
 *
 * Call this function twice, changing the order of the two classes to ensure that both classes
 * contain the same set of functions (barring exceptions), should that be the expectation.
 *
 * @param subsetClass The class whose functions should be a subset of the functions of the other
 *   class.
 * @param supersetClass The class whose functions should be a superset of the functions of the other
 *   class.
 */
internal fun checkAllDelegation(
    subsetClass: KClass<*>,
    supersetClass: KClass<*>,
    vararg exceptFunctionNames: String,
) {
    assertThat(subsetClass != supersetClass)
        .describedAs { "The two classes should not be the same." }
        .isTrue

    val subsetFunctions = subsetClass.declaredFunctions
    val missingFunctions = mutableListOf<KFunction<*>>()

    for (subsetFunction in subsetFunctions) {
        if (subsetFunction.visibility != KVisibility.PUBLIC) {
            continue
        }

        if (subsetFunction.name in exceptFunctionNames) {
            continue
        }

        // Drop the first parameter from each function, as it is the implicit "this" object and has
        // the type of the class declaring the function, which will never match. Compare only the
        // "classifiers" of the types, so that generic type parameters are ignored. For example,
        // one `java.lang.Class<T>` is then considered equal to another `java.lang.Class<T>`. For
        // the data set being processed, this simplification does not cause any problems.
        val supersetFunction =
            supersetClass.declaredFunctions.find {
                it.name == subsetFunction.name &&
                    it.parameters.drop(1).map { it.type.classifier } ==
                        subsetFunction.parameters.drop(1).map { it.type.classifier }
            }

        if (supersetFunction == null) {
            missingFunctions.add(subsetFunction)
        }
    }

    assertThat(missingFunctions)
        .describedAs {
            "Function(s) not found in ${supersetClass.simpleName}:\n" +
                missingFunctions.joinToString("\n") { " - $it" }
        }
        .isEmpty()
}

/**
 * Finds the java method matching the test case's function name and parameter types in the delegator
 * or delegate `target`.
 */
internal fun findDelegationMethod(target: Any, testCase: DelegationWriteTestCase): Method {
    val numParams = testCase.inputValues.size
    val inputValue1: Any? = if (numParams > 0) testCase.inputValues[0] else null
    val inputValue2 = if (numParams > 1) testCase.inputValues[1] else null

    val method =
        when (numParams) {
            0 -> findJavaMethod(target.javaClass, testCase.functionName)
            1 ->
                if (inputValue1 != null) {
                    findJavaMethod(
                        target.javaClass,
                        testCase.functionName,
                        toJavaType(inputValue1.javaClass),
                    )
                } else {
                    // Only the first parameter may be nullable and only if it is the only
                    // parameter. If the first parameter is nullable, it will be the only function
                    // of the same name with a nullable first parameter. To handle the potentially
                    // nullable first parameter, Kotlin reflection is needed. This allows a function
                    // `f(Boolean)` to be distinguished from `f(Boolean?)`. For the tests, if the
                    // parameter type is nullable, the parameter value will always be `null` (if
                    // not, the function with the nullable parameter would not be matched).
                    //
                    // Using Kotlin reflection, the first parameter (zero index) is `this` object,
                    // so start matching from the second parameter onwards.
                    target::class
                        .declaredFunctions
                        .find {
                            it.name == testCase.functionName &&
                                it.parameters[1].type.isMarkedNullable
                        }
                        ?.javaMethod
                }

            2 ->
                if (inputValue1 != null && inputValue2 != null) {
                    findJavaMethod(
                        target.javaClass,
                        testCase.functionName,
                        toJavaType(inputValue1.javaClass),
                        toJavaType(inputValue2.javaClass),
                    )
                } else {
                    // There are no instances where there are two parameters and one of them is
                    // nullable.
                    fail { "Function $testCase second parameter must not be null." }
                }

            else -> fail { "Function $testCase has unsupported number of parameters." }
        } ?: fail { "Function $testCase cannot be found in $target." }

    // Using `fail` conditionally above, so the compiler knows the code will not continue and can
    // infer that `method` is not null. It cannot do that for `assertThat...isNotNull`.
    return method
}

/** Finds a Java method in a class that matches a method name and a list of parameter types. */
private fun findJavaMethod(
    clazz: Class<*>,
    methodName: String,
    vararg parameterTypes: Class<*>,
): Method? =
    clazz.declaredMethods.firstOrNull { method ->
        method.name == methodName &&
            method.parameterTypes.size == parameterTypes.size &&
            method.parameterTypes.indices.all { index ->
                (parameterTypes[index].isPrimitive &&
                    method.parameterTypes[index] == parameterTypes[index]) ||
                    method.parameterTypes[index].isAssignableFrom(parameterTypes[index])
            }
    }

/**
 * Returns the Java type to use when matching type parameters for a Java method. The type is the
 * type of the input value that will be used when the method is invoked. For most types, the given
 * type is returned. However, if the type represents a Kotlin primitive, it will be converted to a
 * Java primitive. This allows matching of methods with parameter types that are non-nullable Kotlin
 * primitives. If not translated, methods with parameter types that are nullable Kotlin primitives
 * would always be matched instead.
 */
private fun toJavaType(type: Class<*>) =
    when (type) {
        // This only needs to cover the types used in the test cases.
        Long::class.javaObjectType -> Long::class.javaPrimitiveType!!
        Boolean::class.javaObjectType -> Boolean::class.javaPrimitiveType!!
        Double::class.javaObjectType -> Double::class.javaPrimitiveType!!
        else -> type
    }
