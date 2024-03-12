package org.currytree.business

import org.currytree.PageBody
import java.nio.file.Path

class DriveBodyStore(root: Path) : DriveValueStore<PageBody>(root, PageBody::class.java)