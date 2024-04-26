package org.currytree.business

class SlugNotFoundException(val slug: String) : RuntimeException("Slug [$slug] not found.")