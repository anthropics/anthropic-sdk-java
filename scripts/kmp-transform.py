#!/usr/bin/env python3
"""
Bulk transform script for KMP migration.
Transforms JVM Optional-based API calls to nullable Kotlin equivalents.
Handles multi-line patterns safely.
"""

import os
import re
import sys

SRC_DIR = os.path.join(os.path.dirname(__file__), '..', 'anthropic-java-core', 'src', 'main', 'kotlin')

# Simple single-line replacements (order matters)
SIMPLE_REPLACEMENTS = [
    # .asKnown().isPresent → .asKnown() != null
    (r'\.asKnown\(\)\.isPresent', '.asKnown() != null'),
    # .asKnown().getOrNull()  → .asKnown()
    (r'\.asKnown\(\)\.getOrNull\(\)', '.asKnown()'),
    # .asString().getOrNull() → .asString()
    (r'\.asString\(\)\.getOrNull\(\)', '.asString()'),
    # .asObject().getOrNull() → .asObject()
    (r'\.asObject\(\)\.getOrNull\(\)', '.asObject()'),
    # .asNumber().getOrNull() → .asNumber()
    (r'\.asNumber\(\)\.getOrNull\(\)', '.asNumber()'),
    # .asBoolean().getOrNull() → .asBoolean()
    (r'\.asBoolean\(\)\.getOrNull\(\)', '.asBoolean()'),
    # .asArray().getOrNull() → .asArray()
    (r'\.asArray\(\)\.getOrNull\(\)', '.asArray()'),
    # .asUnknown().getOrNull() → .asUnknown()
    (r'\.asUnknown\(\)\.getOrNull\(\)', '.asUnknown()'),
    # .filename().getOrNull() → .filename()
    (r'\.filename\(\)\.getOrNull\(\)', '.filename()'),
    # .asString().map { → .asString()?.let {
    (r'\.asString\(\)\.map \{', '.asString()?.let {'),
    # .asObject().get( → .asObject()?.get(  (when chained after nullable)
    (r'\.asObject\(\)\.get\(', '.asObject()?.get('),
    # .asString()?.let { ErrorType.of(it) } → needs Optional wrapping in some contexts
    # But in errorType() method it needs: .asString()?.let { ErrorType.of(it) }
]

# Multi-line pattern: .asString().orElseThrow { ... }
# Becomes: .asString() ?: throw ...
def transform_orElseThrow(content):
    """Transform .asString().orElseThrow { ExceptionClass(...) } patterns."""
    # Pattern: .asString().orElseThrow {\n            ExceptionClass("...")\n        }
    # Also handles single-line: .asString().orElseThrow { ExceptionClass("...") }

    # Single line pattern
    content = re.sub(
        r'\.asString\(\)\.orElseThrow\s*\{\s*(\w+)\(([^)]*)\)\s*\}',
        r'.asString() ?: throw \1(\2)',
        content
    )

    # Multi-line pattern for asString
    content = re.sub(
        r'\.asString\(\)\.orElseThrow\s*\{[^\}]*?(\w+(?:Exception|Error))\(([^)]*)\)[^\}]*?\}',
        r'.asString() ?: throw \1(\2)',
        content,
        flags=re.DOTALL
    )

    # .asKnown().orElseThrow { ... } → .asKnown() ?: throw ...
    content = re.sub(
        r'\.asKnown\(\)\.orElseThrow\s*\{[^\}]*?(\w+(?:Exception|Error|State))\(([^)]*)\)[^\}]*?\}',
        r'.asKnown() ?: throw \1(\2)',
        content,
        flags=re.DOTALL
    )

    return content

def transform_optional_returns(content):
    """Handle Optional.empty() and Optional.ofNullable() patterns in error methods."""
    # return Optional.empty() stays as-is (public API methods still return Optional)
    # return Optional.ofNullable(...asString()...) → keep Optional wrapping
    # These are in jvmMain code that still uses Optional for public API
    pass  # Keep as-is for now
    return content

def transform_file(filepath):
    """Transform a single .kt file."""
    with open(filepath, 'r') as f:
        original = f.read()

    content = original

    # Apply simple replacements
    for pattern, replacement in SIMPLE_REPLACEMENTS:
        content = re.sub(pattern, replacement, content)

    # Apply multi-line transforms
    content = transform_orElseThrow(content)

    # Remove unused import if no more .getOrNull() calls remain
    if 'getOrNull()' not in content and 'kotlin.jvm.optionals.getOrNull' in content:
        # Check if getOrNull is used for other Optional types (not just our methods)
        if content.count('getOrNull') <= 1:  # Only the import itself
            content = content.replace('import kotlin.jvm.optionals.getOrNull\n', '')

    if content != original:
        with open(filepath, 'w') as f:
            f.write(content)
        return True
    return False

def main():
    src_dir = os.path.abspath(SRC_DIR)
    if not os.path.isdir(src_dir):
        print(f"Source directory not found: {src_dir}")
        sys.exit(1)

    changed = 0
    total = 0
    for root, dirs, files in os.walk(src_dir):
        for fname in files:
            if fname.endswith('.kt'):
                total += 1
                filepath = os.path.join(root, fname)
                if transform_file(filepath):
                    changed += 1

    print(f"Transformed {changed}/{total} files")

if __name__ == '__main__':
    main()
