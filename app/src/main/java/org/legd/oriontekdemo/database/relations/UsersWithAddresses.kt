package org.legd.oriontekdemo.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import org.legd.oriontekdemo.database.model.Address
import org.legd.oriontekdemo.database.model.User

/**
 * This class is an alternative way to return users and addresses together in one bulk of data.
 */
data class UsersWithAddresses(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val addresses: List<Address>
) {
}