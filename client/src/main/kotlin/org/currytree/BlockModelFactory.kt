package org.currytree

interface BlockModelFactory {
    fun accept(block: NormalBlock)
    fun accept(block: CodeBlock)
}
