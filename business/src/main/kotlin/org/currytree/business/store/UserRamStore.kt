package org.currytree.business.store

import org.currytree.business.User


class UserRamStore(
    private val base: Store<User> = RamStore(User::class.java)
) : Store<User> by base