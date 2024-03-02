package org.currytree.business

import org.currytree.PageHeader
import org.currytree.blocks.Block
import org.currytree.blocks.CodeBlock
import org.currytree.blocks.InlineStyle
import org.currytree.blocks.InlineStyleSpan
import org.currytree.blocks.NormalBlock
import org.currytree.blocks.StyledField

class CurryTree {

    val pageHeaderTree = PageHeaderTree()

    fun fetchUserRoot(): PageHeader {
        return PageHeader("Welcome!")
    }

    fun childrenFor(slug: String): List<PageHeader> {
        return pageHeaderTree.childrenFor(slug)
    }

    val sampleCode = """class CodeModel(block: NormalBlock) : BlockModel {
    val code = mutableStateOf(Styles.codeStyler.style(block.field))
}"""

    fun bodyFor(slug: String): List<Block> {
        println(slug)
        return listOf(
            NormalBlock(StyledField(slug)),
            NormalBlock(StyledField("Hi Mom!")),
            NormalBlock(StyledField("This is purposefully a longer block so we can see the effect of the paragraph padding that it needs to have.")),
            NormalBlock(
                StyledField(
                    "Every word is different.",
                    listOf(
                        InlineStyleSpan(0, 5, InlineStyle.bold),
                        InlineStyleSpan(6, 10, InlineStyle.italic),
                        InlineStyleSpan(11, 13, InlineStyle.underline),
                        InlineStyleSpan(14, 23, InlineStyle.code)
                    )
                )
            ),
            CodeBlock(StyledField(sampleCode))
        )
    }

}