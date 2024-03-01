package org.currytree.blocks

interface BlockModelFactory {
    fun accept(block: NormalBlock)
    fun accept(block: CodeBlock)
}
