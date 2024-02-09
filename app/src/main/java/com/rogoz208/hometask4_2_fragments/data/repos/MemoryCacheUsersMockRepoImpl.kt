package com.rogoz208.hometask4_2_fragments.data.repos

import com.rogoz208.hometask4_2_fragments.domain.entities.UserEntity
import com.rogoz208.hometask4_2_fragments.domain.repos.UsersRepo
import java.util.UUID

class MemoryCacheUsersMockRepoImpl : UsersRepo {

    private val cache = mutableListOf<UserEntity>()

    init {
        fillRepoWithMockValues()
    }

    override fun getUsers(callback: (List<UserEntity>) -> Unit) {
        callback(ArrayList<UserEntity>(cache))
    }

    override fun createUser(user: UserEntity) {
        val newId = UUID.randomUUID().toString()
        cache.add(user.copy(uId = newId))
    }

    override fun deleteUser(uId: String) {
        for (i in cache.indices) {
            if (cache[i].uId == uId) {
                cache.removeAt(i)
                return
            }
        }
    }

    override fun updateUser(uId: String, user: UserEntity, position: Int) {
        deleteUser(uId)
        cache.add(position, user.copy(uId = uId))
    }

    private fun fillRepoWithMockValues() {
        val firstNames = listOf("Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack", "Kate", "Liam", "Mia", "Noah", "Olivia", "Peter", "Quinn", "Rose", "Samuel", "Tara")
        val lastNames = listOf("Smith", "Johnson", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin")

        val avatarUrls = listOf(
            "https://img.freepik.com/free-vector/cute-alien-riding-ufo-with-love-sign-cartoon-vector-icon-illustration-science-technology-icon-concept-isolated-premium-vector-flat-cartoon-style_138676-3810.jpg?w=826&t=st=1707475694~exp=1707476294~hmac=1fb1adaedad0e8b1794fbbf31ba2a819905d26aaaaf635acb9613f6a4151b226",
            "https://img.freepik.com/premium-vector/magenta-alien-head-cartoon-illustration_665569-99.jpg?w=826",
            "https://img.freepik.com/premium-vector/alien-hold-earth-vector-illustration-design-isolated-dark_72076-409.jpg?w=826",
            "https://img.freepik.com/premium-vector/astronaut-flying-space_797981-22836.jpg?w=826",
            "https://img.freepik.com/free-vector/retro-alien-greeting-people-vector-illustration-space-camp-emblem-with-extraterrestrial-character_74855-10825.jpg?w=826&t=st=1707475828~exp=1707476428~hmac=e0b0f4fa1155003ac5d13277dd6333f188961d2fd979039df8a956acf865a204",
            "https://img.freepik.com/free-vector/friendly-green-alien-spacesuit-cartoon_1284-63005.jpg?w=826&t=st=1707475833~exp=1707476433~hmac=44eb5fa405056968c942146930a20806a6f7536cfa2a255d6ea14336aa8cc1ec"
        )

        repeat(100) {
            val firstName = firstNames.random()
            val lastName = lastNames.random()
            val phoneNumber = "+7${(9000000000..9999999999).random()}"
            val avatarUrl = avatarUrls.random()

            val user = UserEntity(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                avatarUrl = avatarUrl
            )
            createUser(user)
        }
    }
}