package ru.rinet.questik.data;


import ru.rinet.questik.data.prefs.PreferenceHelper;
import ru.rinet.questik.data.retrofit.ApiHelper;
import ru.rinet.questik.data.room.DbHelper;



public interface DataManager extends DbHelper, PreferenceHelper, ApiHelper {

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(1),
        LOGGED_IN_MODE_LOGGED_SERVER(2);

        private final int type;

        LoggedInMode(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}
