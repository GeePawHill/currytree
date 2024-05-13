package org.currytree.business.store

import org.currytree.business.Cohort
import java.nio.file.Path


class CohortDriveStore(
    private val root: Path,
    private val base: Store<Cohort> = DriveStore(root, Cohort::class.java)
) : Store<Cohort> by base

