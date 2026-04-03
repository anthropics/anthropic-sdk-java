#!/usr/bin/env python3
"""
Fix duplicate method overloads created by the Optional->nullable transformation.
When an Optional<T> overload becomes T? it conflicts with the existing T? method.
"""

import re
import os
import glob

BASE = "/home/user/anthropic-sdk-java"

def remove_duplicate_methods(content):
    """Remove duplicate methods that have identical signatures."""
    lines = content.split('\n')
    new_lines = []
    i = 0

    while i < len(lines):
        line = lines[i]
        stripped = line.strip()

        # Look for a fun declaration
        fun_match = re.match(r'^(\s+)((?:/\*\*.*\*/\s*)?fun \w+\([^)]*\))\s*=\s*(.+)', line)
        if not fun_match:
            # Try multi-line: fun declaration on one line, body on next
            fun_match2 = re.match(r'^(\s+)(?:/\*\*.*\*/\s*)?fun (\w+\([^)]*\))\s*=\s*apply\s*\{', line)
            if fun_match2:
                # Collect the full method (it may span multiple lines)
                method_lines = [line]
                brace_depth = line.count('{') - line.count('}')
                j = i + 1
                while j < len(lines) and brace_depth > 0:
                    method_lines.append(lines[j])
                    brace_depth += lines[j].count('{') - lines[j].count('}')
                    j += 1

                method_text = '\n'.join(method_lines)
                sig = fun_match2.group(2)

                # Look ahead for a duplicate with same signature
                k = j
                # skip blank lines and comments
                while k < len(lines) and (lines[k].strip() == '' or lines[k].strip().startswith('/**') or lines[k].strip().startswith('*') or lines[k].strip().startswith('*/')):
                    k += 1

                if k < len(lines):
                    next_fun = re.match(r'^(\s+)(?:/\*\*.*\*/\s*)?fun (' + re.escape(sig) + r')\s*=', lines[k])
                    if next_fun:
                        # Found duplicate - keep the first one, skip the second
                        # Find the doc comment before the duplicate
                        dup_start = j
                        while dup_start < k:
                            dup_start += 1

                        # Actually, just add the first method and skip ahead
                        for ml in method_lines:
                            new_lines.append(ml)
                        i = j

                        # Now skip the duplicate
                        # Find where the duplicate comment starts
                        dup_comment_start = j
                        while dup_comment_start < len(lines) and lines[dup_comment_start].strip() == '':
                            dup_comment_start += 1

                        if dup_comment_start < len(lines) and lines[dup_comment_start].strip().startswith('/**'):
                            i = dup_comment_start  # start from the comment
                        else:
                            i = k  # start from the fun

                        # skip the duplicate method
                        dup_brace = lines[i].count('{') - lines[i].count('}')
                        i += 1
                        while i < len(lines) and dup_brace > 0:
                            dup_brace += lines[i].count('{') - lines[i].count('}')
                            i += 1
                        continue

        new_lines.append(line)
        i += 1

    return '\n'.join(new_lines)


def fix_structured_file(filepath):
    """Remove duplicate overloads from a Structured*Params file."""
    with open(filepath, 'r') as f:
        content = f.read()

    # Find all method signatures and their line ranges
    lines = content.split('\n')
    new_lines = []
    seen_signatures = set()
    i = 0

    while i < len(lines):
        line = lines[i]
        stripped = line.strip()

        # Match a method signature: fun name(params) = ...
        # Could be single-line or multi-line
        sig_match = re.match(r'\s+(?:/\*\*.*\*/\s*)?fun (\w+\([^)]*\))', line)
        if sig_match:
            sig = sig_match.group(1)

            # Determine the full extent of this method
            method_start = i

            # Check for doc comment above (already consumed - it's in new_lines)
            doc_start_in_new = len(new_lines)
            if new_lines and new_lines[-1].strip().startswith('/**'):
                doc_start_in_new = len(new_lines) - 1
            elif len(new_lines) >= 1 and new_lines[-1].strip() == '' and len(new_lines) >= 2 and new_lines[-2].strip().startswith('/**'):
                # blank line between comment and method? unlikely but check
                pass

            # Find the end of this method
            brace_depth = line.count('{') - line.count('}')
            j = i + 1
            if brace_depth > 0:
                while j < len(lines) and brace_depth > 0:
                    brace_depth += lines[j].count('{') - lines[j].count('}')
                    j += 1
            # j is now one past the end of the method

            if sig in seen_signatures:
                # This is a duplicate - remove it and its doc comment
                # Remove the doc comment we already added
                while new_lines and (new_lines[-1].strip().startswith('/**') or new_lines[-1].strip().startswith('*') or new_lines[-1].strip().startswith('*/') or new_lines[-1].strip() == ''):
                    if new_lines[-1].strip().startswith('/**'):
                        new_lines.pop()
                        break
                    elif new_lines[-1].strip() == '':
                        new_lines.pop()
                        continue
                    else:
                        # some other comment line that's not the start
                        break

                i = j
                continue

            seen_signatures.add(sig)
            # Add all method lines
            for k in range(i, j):
                new_lines.append(lines[k])
            i = j
            continue

        new_lines.append(line)
        i += 1

    result = '\n'.join(new_lines)
    if result != content:
        with open(filepath, 'w') as f:
            f.write(result)
        return True
    return False


def main():
    # Find files with conflicting overloads
    # These are the Structured* files and any other file with duplicate methods
    files_to_fix = glob.glob(os.path.join(BASE, "anthropic-java-core/src/commonMain/**/Structured*.kt"), recursive=True)
    files_to_fix += glob.glob(os.path.join(BASE, "anthropic-java-core/src/commonMain/**/BetaMessageTokensCount.kt"), recursive=True)
    files_to_fix += glob.glob(os.path.join(BASE, "anthropic-java-core/src/commonMain/**/AnthropicBackend.kt"), recursive=True)

    for f in sorted(set(files_to_fix)):
        if fix_structured_file(f):
            print(f"Fixed: {f}")
        else:
            print(f"No changes: {f}")


if __name__ == '__main__':
    main()
