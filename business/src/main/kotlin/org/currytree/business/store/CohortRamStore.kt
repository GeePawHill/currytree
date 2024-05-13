package org.currytree.business.store

import org.currytree.business.Cohort

class CohortRamStore(
    private val base: Store<Cohort> = RamStore(Cohort::class.java)
) : Store<Cohort> by base

