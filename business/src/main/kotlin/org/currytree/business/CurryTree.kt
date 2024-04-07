package org.currytree.business

import org.currytree.PageBody
import org.currytree.PageHeader
import org.currytree.blocks.CodeBlock
import org.currytree.blocks.NormalBlock
import org.currytree.business.store.Store
import org.currytree.io.Responder
import org.currytree.styled.InlineStyle
import org.currytree.styled.InlineStyleSpan
import org.currytree.styled.StyledField
import java.nio.file.Path

class CurryTree(private val users: Store<User>, val bodies: Store<PageBody>) {
    val sampleCode = """class CodeModel(block: NormalBlock) : BlockModel {
    val code = mutableStateOf(Styles.codeStyler.style(block.field))
}"""

    fun childrenFor(responder: Responder, handle: String, slug: String) {
        val handlePath = Path.of(handle)
        if (users.exists(handlePath)) {
            val user = users.read(handlePath)
            responder.ok(user.pages.childrenFor(slug))
        }
    }

    fun bodyFor(responder: Responder, handle: String, slug: String) {
        val bodyPath = Path.of(slug)
        if (bodies.exists(bodyPath)) {
            val body = bodies.read(bodyPath)
            responder.ok(body.blocks)
        }
    }

    fun initializeForMaker() {
        val geepaw = User("geepaw")
        geepaw.pages.add("root", PageHeader("Welcome GeePaw!"))
        geepaw.pages.add("root", PageHeader("Shared"))
        writeUser(geepaw)
        val victim = User("victim")
        victim.pages.add("root", PageHeader("Welcome Victim!"))
        victim.pages.add("root", PageHeader("Shared"))
        writeUser(victim)
        writeBody(PageBody("welcome-geepaw", listOf(NormalBlock(StyledField("Welcome, GeePaw!")))))
        writeBody(PageBody("welcome-victim", listOf(NormalBlock(StyledField("Welcome, Victim!")))))
        val sharedBody = PageBody(
            "shared",
            listOf(
                NormalBlock(StyledField("This page is shared by both users.")),
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
        writeBody(sharedBody)
    }

    fun writeUser(user: User) {
        users.write(Path.of(user.handle), user)
    }

    fun writeBody(body: PageBody) {
        bodies.write(Path.of(body.slug), body)
    }
}