package xyz.gmfatoom.questik.repo.local.shared

interface ISharedPrefsHelper {
    fun getCurrentUserId(): String?

    fun setCurrentUserId(userId: String?)

    fun getCurrentUserName(): String

    fun setCurrentUserName(userName: String)

    fun getCurrentUserEmail(): String

    fun setCurrentUserEmail(email: String)

    fun getCurrentUserProfilePicUrl(): String

    fun setCurrentUserProfilePicUrl(profilePicUrl: String)

    fun getAccessToken(): String?

    fun setAccessToken(accessToken: String)
}