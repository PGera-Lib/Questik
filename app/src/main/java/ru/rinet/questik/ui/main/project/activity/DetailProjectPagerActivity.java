package ru.rinet.questik.ui.main.project.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.R;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.BaseActivity;
import ru.rinet.questik.ui.main.HomeActivity;
import ru.rinet.questik.ui.main.project.fragment.Interacts;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragment;

/*
public class DetailProjectPagerActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {
*/
    public class DetailProjectPagerActivity extends BaseActivity implements DetailProjectPagerMvpView, Interacts.onDetailFragmentDataListener, Interacts.onDetailJobDataListener, Interacts.onDetailMaterialDataListener {

    @Inject
    DetailProjectPagerMvpPresenter<DetailProjectPagerMvpView> mPresenter;

    @Inject
    DetailProjectPagerAdapter mPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.project_view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    Menu menu;
    Project project;
    Corp corp;
    User user;
    List<Job> jobik;
    private onDetailActivityDataListener listener;

    public static String KEY_PARCELABLE_PROJECT = "hell";
    public static String PROJECT_ID = "0";
    private ProjectDetailFragment projectDetailFragment;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context,  DetailProjectPagerActivity.class);
        return intent;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.project_page);

        getActivityComponent().inject(this);
    setUnBinder(ButterKnife.bind(this));

    mPresenter.onAttach(this);
        if(project==null){
            project = (Project) getIntent().getParcelableExtra(KEY_PARCELABLE_PROJECT);
        }

        setUp();

    }

    @Override
    protected void setUp() {

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mToolbar.inflateMenu(R.menu.project);
        menu = mToolbar.getMenu();

        mPagerAdapter.setCount(4);

        mViewPager.setAdapter(mPagerAdapter);

       /* FragmentManager fm = getSupportFragmentManager();

        //Fragment fragment = fm.findFragmentById(R.id.project_view_pager);
        if (mViewPager == null) {
            //mViewPager = new ProjectDetailFragment();

            if (mViewPager instanceof onDetailActivityDataListener) {
                listener = (onDetailActivityDataListener) this;
            } else {
                throw new RuntimeException("projectDetailFragment"
                        + " must implement onDetailActivityDataListener");
            }

        }*/

        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.project_info)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.project_tab_job)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.project_tab_material)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.project_tab_itog)));


        mViewPager.setOffscreenPageLimit(4);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
/*    public DetailProjectPagerActivity(onDetailActivityDataListener listener) {
        this.listener=listener;
    }*/


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof onDetailActivityDataListener) {
            listener = (onDetailActivityDataListener) fragment;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.project, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                startActivity(HomeActivity.getStartIntent(getBaseContext()));
/*                Intent upIntent = NavUtils.getParentActivityIntent(this);
                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;*/
            case R.id.action_save:
                listener.onDetailActivityDataListener();
               // Log.i("OnSaveButtonClicked", "разбираем что собрали"+"проект: "+project.getName()+" Corp: "+ corp.getId()+" user: "+ user.getId());



                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onDetailFragmentDataListener(Project project, Corp corp, User user) {
/*this.project=project;
this.corp=corp;
this.user=user;*/

        if (project != null) {
            mPresenter.onSaveButtonClicked(project, corp, user, jobik);
            Log.i("PagerActivity", "проект приходящий" + project.getName().toString());
        } else {
            Log.i("Detail Project Activity", "не получилось получить, то что надо" );
        }
    }

    @Override
    public void onDetailFragmentDataReciver() {

    }

    @Override
    public void onDetailJobDataListener(List<Job> joblist) {
        for (Job job : joblist) {
            jobik.add(job);
        }
    }

    @Override
    public void onDetailMaterialDataListener(List<Job> matlist) {
        for (Job job : matlist) {
            jobik.add(job);
        }
    }
}
