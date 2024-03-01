package org.currytree

class DirectConnection(val server: Server) : Connection {

    private val someBlocks = mutableListOf<NormalBlock>()

    override suspend fun fetchUserRoot(): PageHeader {
        return server.fetchUserRoot()
    }

    override suspend fun childrenFor(slug: String): List<PageHeader> {
        return server.childrenFor(slug)
    }

    override suspend fun bodyFor(slug: String): List<NormalBlock> {
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
            )
        )
    }
}
