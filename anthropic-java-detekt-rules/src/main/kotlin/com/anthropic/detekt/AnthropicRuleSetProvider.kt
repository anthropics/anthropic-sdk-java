package com.anthropic.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class AnthropicRuleSetProvider : RuleSetProvider {

    override val ruleSetId: String = "anthropic"

    override fun instance(config: Config): RuleSet =
        RuleSet(
            ruleSetId,
            listOf(
                AcronymCasing(config),
                BuilderEntryPoints(config),
                BuilderNullablePrimitiveSetterMissingUnboxedOverload(config),
                BuilderNullableSetterMissingOptionalOverload(config),
                CompanionConstantMissingJvmField(config),
                CompanionFunctionMissingJvmStatic(config),
                DefaultArgsInvisibleToJava(config),
                FileJvmSynthetic(config),
                InternalMemberMissingJvmSynthetic(config),
                KotlinOnlyTypeInPublicApi(config),
                NullablePublicReturn(config),
                OptionalOrElseNull(config),
                PublicConstructor(config),
                PublicPropertyMissingJvmName(config),
                PublicSuspendFunction(config),
                RuntimeExceptionSubclass(config),
                TestClassMustBeInternal(config),
                ThrowableSubclassNaming(config),
                TopLevelPublicWithoutFileJvmName(config),
            ),
        )
}
