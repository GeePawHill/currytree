package org.currytree.business

import kotlinx.serialization.Serializable

@Serializable
data class Cohort(val pages: PageHeaderTree = PageHeaderTree()) {
}