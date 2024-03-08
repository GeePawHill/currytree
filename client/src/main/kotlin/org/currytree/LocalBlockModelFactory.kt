package org.currytree

import org.currytree.blocks.Block
import org.currytree.blocks.BlockModelFactory
import org.currytree.blocks.CodeBlock
import org.currytree.blocks.NormalBlock
import org.currytree.code.CodeModel
import org.currytree.normal.BlockModel
import org.currytree.normal.NormalModel

class LocalBlockModelFactory : BlockModelFactory {
    val body = mutableListOf<BlockModel>()

    override fun accept(block: NormalBlock) {
        body.add(NormalModel(block))
    }

    override fun accept(block: CodeBlock) {
        body.add(CodeModel(block))
    }

    fun makeBodyFrom(bodyFor: List<Block>) {
        body.clear()
        bodyFor.forEach { it.callMe(this) }
    }
}