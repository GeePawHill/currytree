package org.currytree.business.store

import org.currytree.PageBody
import java.nio.file.Path

class BodyDriveStore(
    root: Path,
    private val base: Store<PageBody> = DriveStore(root, PageBody::class.java)
) : Store<PageBody> by base