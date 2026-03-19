package fm.pim.storage

import fm.pim.tax.AangifteInput
import kotlinx.serialization.Serializable
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Serializable
data class UserProfile(
    val id: String,
    val name: String,
    val defaultInput: AangifteInput? = null
)

object ProfileStore {
    private val store = ConcurrentHashMap<String, UserProfile>()

    fun save(profile: UserProfile): UserProfile {
        val id = profile.id.ifBlank { UUID.randomUUID().toString() }
        val saved = profile.copy(id = id)
        store[id] = saved
        return saved
    }

    fun get(id: String): UserProfile? = store[id]

    fun all(): List<UserProfile> = store.values.toList()
}
