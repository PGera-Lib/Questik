package ru.rinet.questik.ui.main;



public interface Interact {

    interface Catalog {
        void onCatalogListReFetched();
/*        void updateSwipeRefreshCatalogLayout(boolean isVisible);*/
    }

    interface AuthDialog {
        void onAuthSwitch (int n);
    }
/*
    interface AuthDialogCallback {
         void onSwitch (int n);
    }
*/

    interface Chat {
        void onChatListReFetched();
      //  void updateSwipeRefreshLayoutTwo(boolean isVisible);
    }


    interface Project {
        void onProjectListReFetched();
        void onProjectDetailClicked();
/*        void updateSwipeRefreshProjectLayout(boolean isVisible);*/
    }
}
