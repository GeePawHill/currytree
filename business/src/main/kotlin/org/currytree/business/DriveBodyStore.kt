package org.currytree.business

import org.currytree.PageBody
import java.nio.file.Path

class DriveBodyStore(
    root: Path,
    private val base: ValueStore<PageBody> = DriveValueStore(root, PageBody::class.java)
) : ValueStore<PageBody> by base {

}