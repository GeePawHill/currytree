package org.currytree.business

import java.nio.file.Path

class ValueNotFound(path: Path) : RuntimeException("No value found at [${path.toAbsolutePath()}]")