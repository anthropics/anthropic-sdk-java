package com.anthropic.detekt

import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtCallableDeclaration
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtNullableType
import org.jetbrains.kotlin.psi.KtObjectDeclaration
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject
import org.jetbrains.kotlin.renderer.DescriptorRenderer
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.calls.util.getType
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameOrNull
import org.jetbrains.kotlin.resolve.descriptorUtil.getAllSuperclassesWithoutAny
import org.jetbrains.kotlin.resolve.descriptorUtil.getSuperClassNotAny
import org.jetbrains.kotlin.types.KotlinType

/**
 * True when [this] is part of the published Java API surface: effectively public, not an `override`
 * (the supertype owns the contract), and not hidden via `@JvmSynthetic`.
 */
internal fun KtDeclaration.isPublishedJavaApi(): Boolean =
    isEffectivelyPublic() &&
        !hasModifier(KtTokens.OVERRIDE_KEYWORD) &&
        !hasAnnotation("JvmSynthetic")

/**
 * True when [this] is reachable as `public` from Java, accounting for enclosing classes. A `public`
 * member of a `private` nested class is not API surface.
 */
internal fun KtDeclaration.isEffectivelyPublic(): Boolean {
    if (!isExplicitlyPublic()) return false
    var outer: KtClassOrObject? = containingClassOrObject
    while (outer != null) {
        if (!outer.isExplicitlyPublic()) return false
        outer = outer.containingClassOrObject
    }
    return true
}

/** True when [this] is declared directly inside a `companion object`. */
internal fun KtDeclaration.isInCompanionObject(): Boolean =
    (containingClassOrObject as? KtObjectDeclaration)?.isCompanion() == true

/** Published-Java-API member functions when this is a public `Builder`; `null` otherwise. */
internal fun KtClass.publicBuilderFunctions(): List<KtNamedFunction>? {
    if (name != "Builder") return null
    if (!isEffectivelyPublic()) return null
    return body?.declarations.orEmpty().filterIsInstance<KtNamedFunction>().filter {
        it.isPublishedJavaApi()
    }
}

/**
 * True when [this] list contains a same-name sibling of [fn] whose single parameter's type
 * satisfies [matching].
 */
internal fun List<KtNamedFunction>.hasSingleParamSiblingOf(
    fn: KtNamedFunction,
    matching: (KtTypeReference) -> Boolean,
): Boolean = any { sibling ->
    sibling !== fn &&
        sibling.name == fn.name &&
        sibling.valueParameters.singleOrNull()?.typeReference?.let(matching) == true
}

/** The `@file:` annotation entry with [shortName], or `null` if absent. */
internal fun KtFile.fileAnnotationNamed(shortName: String): KtAnnotationEntry? =
    fileAnnotationList?.annotationEntries.orEmpty().firstOrNull {
        it.shortName?.asString() == shortName
    }

/** The inner type text of a nullable type reference (`String?` -> `"String"`), or `null`. */
internal fun KtTypeReference?.nullableInnerTypeText(): String? =
    (this?.typeElement as? KtNullableType)?.innerType?.text

internal fun KtModifierListOwner.hasAnnotation(shortName: String): Boolean =
    annotationEntries.any { it.shortName?.asString() == shortName }

internal fun KtModifierListOwner.hasUseSiteAnnotation(useSite: String, shortName: String): Boolean =
    annotationEntries.any {
        it.useSiteTarget?.getAnnotationUseSiteTarget()?.renderName == useSite &&
            it.shortName?.asString() == shortName
    }

// --- Type-resolution helpers (require a populated `bindingContext`) -----------------------------

/** The resolved fully-qualified name of this type reference, or `null` without type resolution. */
internal fun KtTypeReference.resolvedFqName(bindingContext: BindingContext): String? {
    if (bindingContext == BindingContext.EMPTY) return null
    return bindingContext[BindingContext.TYPE, this]?.fqNameOrNull()
}

/**
 * The resolved return type of this callable (explicit or inferred), or `null` without resolution.
 */
internal fun KtCallableDeclaration.resolvedReturnType(bindingContext: BindingContext): KotlinType? {
    if (bindingContext == BindingContext.EMPTY) return null
    return (bindingContext[BindingContext.DECLARATION_TO_DESCRIPTOR, this] as? CallableDescriptor)
        ?.returnType
}

/** Short rendering of this type with the trailing `?` stripped, for messages. */
internal fun KotlinType.nonNullRendering(): String =
    DescriptorRenderer.SHORT_NAMES_IN_TYPES.renderType(this).removeSuffix("?")

/** The resolved fully-qualified type name of this expression, or `null` without type resolution. */
internal fun KtExpression.resolvedTypeFqName(bindingContext: BindingContext): String? {
    if (bindingContext == BindingContext.EMPTY) return null
    return getType(bindingContext)?.fqNameOrNull()
}

private fun KotlinType.fqNameOrNull(): String? =
    constructor.declarationDescriptor?.fqNameOrNull()?.asString()

/** True when this class transitively extends `Throwable`; false without type resolution. */
internal fun KtClass.isThrowableSubclass(bindingContext: BindingContext): Boolean {
    val descriptor = classDescriptor(bindingContext) ?: return false
    // The Kotlin frontend models `java.lang.Throwable` as `kotlin.Throwable`.
    return descriptor.getAllSuperclassesWithoutAny().any {
        it.fqNameOrNull()?.asString() == "kotlin.Throwable"
    }
}

/** The resolved fully-qualified name of this class's direct superclass (not `Any`), or `null`. */
internal fun KtClass.directSuperClassFqName(bindingContext: BindingContext): String? =
    classDescriptor(bindingContext)?.getSuperClassNotAny()?.fqNameOrNull()?.asString()

private fun KtClass.classDescriptor(bindingContext: BindingContext): ClassDescriptor? {
    if (bindingContext == BindingContext.EMPTY) return null
    return bindingContext[BindingContext.CLASS, this]
}

private fun KtModifierListOwner.isExplicitlyPublic(): Boolean =
    !hasModifier(KtTokens.PRIVATE_KEYWORD) &&
        !hasModifier(KtTokens.PROTECTED_KEYWORD) &&
        !hasModifier(KtTokens.INTERNAL_KEYWORD)
