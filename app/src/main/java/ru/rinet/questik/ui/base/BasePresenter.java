package ru.rinet.questik.ui.base;



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.utils.rx.SchedulerProvider;



public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;
    private DataManager dataManager;

    private FirebaseUser user;
    private DatabaseReference reference;
    private FirebaseAuth mAuth;

    private V mvpView;

    public BasePresenter(SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable,
                         DataManager dataManager) {
       // , FirebaseAuth mAuth, FirebaseUser user, DatabaseReference reference
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.dataManager = dataManager;
     /*   this.user = user;
        this.mAuth = mAuth;
        this.reference = reference;*/

    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }


    @Override
    public void onDetach() {
        compositeDisposable.dispose();
        this.mvpView = null;
    }

    public V getMvpView() {
        return mvpView;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

/*    public FirebaseUser getFirebaseUser() {
        return user;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
        }

        public DatabaseReference getDatabaseReference() {
        return reference;
    }*/


}
