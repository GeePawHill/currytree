package org.currytree.business

import java.nio.file.Path

class BodyNotFound(path: Path) : RuntimeException("No body found at [${path.toAbsolutePath()}]")