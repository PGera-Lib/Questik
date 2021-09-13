package ru.rinet.questik.data.prefs;


import ru.rinet.questik.data.DataManager;



public interface PreferenceHelper {

    void setCurrentUserId(Long id);

    Long getCurrentUserId();

    void setCurrentUserName(String userName);

    String getCurrentUserName();

    void setCurrentUserEmail(String email);

    String getCurrentUserEmail();

    void updateUserInfoInPrefs(Long userId,
                               String userName,
                               String userEmail,
                               DataManager.LoggedInMode mode);

    void setCurrentUserLoggedInMode(DataManager.LoggedInMode mode);

    int getCurrentUserLoggedInMode();

    void setCurrentUserLoggedOut();
}
