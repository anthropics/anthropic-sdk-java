package com.github.victools.jsonschema.generator
import kotlinx.kmp.util.core.json.ObjectNode
enum class SchemaVersion { DRAFT_2020_12, DRAFT_2019_09, DRAFT_7 }
enum class Option { FORBIDDEN_ADDITIONAL_PROPERTIES_BY_DEFAULT, DEFINITIONS_FOR_ALL_OBJECTS, PLAIN_DEFINITION_KEYS }
class OptionPreset private constructor() { companion object { val PLAIN_JSON = OptionPreset() } }
class SchemaGeneratorConfigBuilder(schemaVersion: SchemaVersion, optionPreset: OptionPreset) { fun with(option: Option): SchemaGeneratorConfigBuilder = this; fun with(module: com.fasterxml.jackson.databind.Module): SchemaGeneratorConfigBuilder = this; fun forFields(): FieldConfigPart = FieldConfigPart(); fun build(): SchemaGeneratorConfig = SchemaGeneratorConfig() }
class FieldConfigPart { fun withRequiredCheck(check: (Any?) -> Boolean): FieldConfigPart = this }
class SchemaGeneratorConfig
class SchemaGenerator(config: SchemaGeneratorConfig) { fun generateSchema(type: Any): ObjectNode = TODO("JS stub") }
