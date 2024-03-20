package org.currytree.business

import org.currytree.PageBody
import org.currytree.business.store.DriveStore
import org.currytree.business.store.Store
import java.nio.file.Path

class BodyDriveStore(
    root: Path,
    private val base: Store<PageBody> = DriveStore(root, PageBody::class.java)
) : Store<PageBody> by base