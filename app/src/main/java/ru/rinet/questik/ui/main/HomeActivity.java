package ru.rinet.questik.ui.main;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/*import io.reactivex.android.BuildConfig;*/
import ru.rinet.questik.BuildConfig;
import ru.rinet.questik.R;
import ru.rinet.questik.ui.base.BaseActivity;
import ru.rinet.questik.ui.custom.RoundedImageView;
import ru.rinet.questik.ui.login.LoginActivity;
import ru.rinet.questik.ui.main.catalog.fragment.CatalogFragmentAdapter;
import ru.rinet.questik.ui.main.project.ProjectFragmentAdapter;
import ru.rinet.questik.ui.main.project.activity.DetailProjectPagerActivity;


public class HomeActivity extends BaseActivity implements MainMvpView, Interact.Catalog, Interact.Project {
    private AppBarConfiguration mAppBarConfiguration;
    ActionBarDrawerToggle drawerToggle;

    @Inject
    FirebaseAuth mAuth;

    @Inject
    MainMvpPresenter<MainMvpView> presenter;
    @BindView(R.id.tv_app_version)
    TextView tvAppVersion;
/*    @BindView(R.id.tv_name)
    TextView tvUserName;
    @BindView(R.id.tv_email)
    TextView tvUserEmail;*/
    @BindView(R.id.toolbar)
    Toolbar toolbar;
/*    @BindView(R.id.nav_view)
    NavigationView navigationView;*/
    //@BindView(R.id.nav_header)
    View navHeaderView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    @Inject
    ProjectFragmentAdapter projectAdapter;

    @Inject
    CatalogFragmentAdapter catalogAdapter;


    TextView tvUserName;
    TextView tvUserEmail;

    private boolean catalogRefreshFlag = true, projectRefreshFlag = true;
    private Menu menu;
    private RoundedImageView ivProfilePic;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        Log.i("HomeActivity", "OnCreate");

        presenter.onAttach(this);

        mAuth = FirebaseAuth.getInstance();


        setUp();
    }

    @Override
    public void updateAppVersion() {
       String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        tvAppVersion.setText(version);
        Log.i("HomeActivity", "UpdateVersion");
    }

    @Override
    public void updateUserName(String name) {

        tvUserName.setText(name);

    }

    @Override
    public void updateUserEmail(String email) {
        tvUserEmail.setText(email);
    }

    @Override
    public void updateUserProfilePicture(String picPath) {
        //TODO
    }



    @Override
    public void openLoginActivity() {
        Log.i("HomeActivity", "OpenLoginActivity");

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if (user != null) {
            mAuth.signOut();
            startActivity(LoginActivity.getStartIntent(getBaseContext()));
            finish();
        }
        startActivity(LoginActivity.getStartIntent(this));
        finish();
    }

   /* @Override
    public void openProjectFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_project, new ProjectFragment())
                .commit();

    }

    @Override
    public void openCatalogFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_catalog, new CatalogFragment())
                .commit();
    }

    @Override
    public void openChernFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_chern, new ChernFragment())
                .commit();
    }

    @Override
    public void openSettingsFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_settings, new SettingsFragment())
                .commit();
    }

    @Override
    public void openChatFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_chat, new ChatFragment())
                .commit();
    }

    @Override
    public void openGameFragment() {

    }

    @Override
    public void openDocFragment() {

    }

    @Override
    public void openHowtoFragment() {

    }

    @Override
    public void openProjectDetailActivity() {
        startActivity(DetailProjectPagerActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void openCatalogDetailAtivity() {

    }*/

  /*  @Override
    public void openFeedActivity() {
        startActivity(FeedActivity.getStartIntent(this));
    }*/

    @Override
    public void closeNavigationDrawer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void unlockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void lockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }


    @Override
    protected void setUp() {
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.open_drawer,
                R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
               // hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        setUpNavMenu();
        presenter.onNavMenuCreated();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
     //   Log.i("HOME ACTIVITY", "firebase user is ---"+user);
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
                String providerId = profile.getProviderId();
                // UID specific to the provider
                String uid = profile.getUid();
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName().trim();
                String email = profile.getEmail().trim();
                Log.i("HOME ACTIVITY", "firebase user name is ---"+name+"  mail:"+email);
                updateUserName(name);
                updateUserEmail(email);

            }

        }

    }



    private void setUpNavMenu() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
       // navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_home);
        tvUserName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tvUserEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_email);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_project, R.id.nav_catalog,
                R.id.nav_chern, R.id.nav_manage, R.id.nav_chat, R.id.nav_game, R.id.nav_doc, R.id.nav_howto)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

/*        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        presenter.onDrawerOptionHomeClicked();

                    case R.id.nav_project:

*//*                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.flContent, new FragmentContact())
                                .commit();
                        break;*//*
                        presenter.onDrawerOptionProjectClicked();

                    case R.id.nav_catalog:
                        presenter.onDrawerOptionCatalogClicked();

                    case R.id.nav_chern:
                        presenter.onDrawerOptionChernClicked();

                    case R.id.nav_manage:
                        presenter.onDrawerOptionSettingsClicked();

                    case R.id.nav_chat:
                        presenter.onDrawerOptionChatClicked();

                    case R.id.nav_game:
                        presenter.onDrawerOptionGameClicked();

                    case R.id.nav_doc:
                        presenter.onDrawerOptionDocClicked();

                    case R.id.nav_howto:
                        presenter.onDrawerOptionHowToClicked();

                    case R.id.nav_item_logout:
                        presenter.onDrawerOptionLogoutClicked();

                }

                drawer.closeDrawers();
                return false;
            }


        });*/


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }

        switch (item.getItemId()) {
            case R.id.action_auth:
                FirebaseUser user = mAuth.getInstance().getCurrentUser();
                if (user != null) {
                    mAuth.signOut();
                    startActivity(LoginActivity.getStartIntent(getBaseContext()));
                    finish();
                }
                startActivity(LoginActivity.getStartIntent(this));
                finish();
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        presenter.onAttach(this);
        Log.i("HomeActivity", "OnResume");
        unlockDrawer();
    }

    @Override
    protected void onDestroy() {
        Log.i("HomeActivity", "OnDestroy and presenter.onDetach");
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onCatalogListReFetched() {
    }



    @Override
    public void onProjectListReFetched() {
    }

    @Override
    public void onProjectDetailClicked() {
        Log.i("HomeActivity", "отправляемся в проджект детейл");
        startActivity(DetailProjectPagerActivity.getStartIntent(getBaseContext()));
        finish();
    }
}
