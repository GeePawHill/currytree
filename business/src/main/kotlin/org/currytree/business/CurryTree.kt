package org.currytree.business

import org.currytree.PageHeader
import org.currytree.blocks.CodeBlock
import org.currytree.blocks.NormalBlock
import org.currytree.business.store.Store
import org.currytree.io.Responder
import org.currytree.styled.InlineStyle
import org.currytree.styled.InlineStyleSpan
import org.currytree.styled.StyledField
import java.nio.file.Path

class CurryTree(private val users: Store<User>) {
    val sampleCode = """class CodeModel(block: NormalBlock) : BlockModel {
    val code = mutableStateOf(Styles.codeStyler.style(block.field))
}"""

    val pageHeaderTree = PageHeaderTree()

    fun fetchUserRoot(responder: Responder) {
        responder.ok(PageHeader("Welcome!"))
    }

    fun childrenFor(responder: Responder, slug: String) {
        responder.ok(pageHeaderTree.childrenFor(slug))
    }

    fun bodyFor(responder: Responder, slug: String) {
        responder.ok(
            listOf(
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
        )
    }

    fun initializeForMaker() {
        writeUser(User("geepaw"))
        writeUser(User("victim"))
    }

    fun writeUser(user: User) {
        users.write(Path.of(user.handle), user)
    }
}