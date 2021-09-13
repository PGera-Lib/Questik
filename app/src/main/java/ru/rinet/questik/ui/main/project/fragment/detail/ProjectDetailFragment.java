package ru.rinet.questik.ui.main.project.fragment.detail;



import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;


import org.joda.time.LocalDate;

import java.util.*;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.*;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.ui.base.BaseFragment;
import ru.rinet.questik.ui.main.project.fragment.Interacts;
import ru.rinet.questik.ui.main.project.activity.onDetailActivityDataListener;


public class ProjectDetailFragment extends BaseFragment implements ProjectDetailFragmentMvpView, onDetailActivityDataListener {

    @BindView (R.id.project_name_edt)
    EditText project_name_edt;
    @BindView (R.id.project_client_edt)
    EditText project_client_edt;
    @BindView (R.id.project_phone_edt)
    EditText project_phone_edt;
    @BindView (R.id.project_mail_edt)
    EditText project_mail_edt;
    @BindView (R.id.project_user_edt)
    TextView project_user_edt;
    @BindView (R.id.project_dataOn_edt)
    TextView project_dataOn_edt;
    @BindView (R.id.project_dataOff_edt)
    TextView project_dataOff_edt;

    Toolbar mToolbar;
    Menu menu;
    User user;
    Corp corp;
    Job job;
    LocalDate date;
    List<Job> joblist;
    Project project;

    private Interacts.onDetailFragmentDataListener onDetailFragmentDataListener;

    @Inject
    ProjectDetailFragmentMvpPresenter<ProjectDetailFragmentMvpView> mPresenter;


    @Inject
    LinearLayoutManager mLayoutManager;

    public static ProjectDetailFragment newInstance() {
        Bundle args = new Bundle();
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_detail_fragment, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        project_dataOn_edt.setText(java.time.LocalDateTime.now().toString());
        project_dataOff_edt.setText(java.time.LocalDateTime.now().toString());
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPresenter.onViewPrepared();


       // mPresenter.loadProjectData(DetailProjectPagerActivity.PROJECT_ID.longValue());
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Interacts.onDetailFragmentDataListener) {
            onDetailFragmentDataListener = (Interacts.onDetailFragmentDataListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onDetailFragmentDataListener");
        }
    }

    @Override
    public void onDetailActivityDataListener() {
        Log.i("ProjectDetailFragment", "----------is work");
        if (project != null) {
            project.setName(project_name_edt.getText().toString());
            if (user != null ){

            }else {
                user = new User();
                user.setName("NoName");
            }
            if (corp != null) {

            } else {
                corp = new Corp();
                corp.setName(project_client_edt.getText().toString());
                corp.setAccount(project_name_edt.getText().toString());
                corp.setMail(project_mail_edt.getText().toString());
                corp.setPhone(project_phone_edt.getText().toString());
            }
            project.setDataOn(java.time.LocalDateTime.now().toString());
            project.setDataOff(java.time.LocalDateTime.now().toString());
            onDetailFragmentDataListener.onDetailFragmentDataListener(project, corp, user);
        } else {project = new Project();
            project.setName(project_name_edt.getText().toString());
            if (user != null ){

            }else {
                user = new User();
                user.setName("NoName");
            }
            if (corp != null) {

            } else {
                corp = new Corp();
                corp.setName(project_client_edt.getText().toString());
                corp.setAccount(project_name_edt.getText().toString());
                corp.setMail(project_mail_edt.getText().toString());
                corp.setPhone(project_phone_edt.getText().toString());
            }
            project.setDataOn(java.time.LocalDateTime.now().toString());
            project.setDataOff(java.time.LocalDateTime.now().toString());
            onDetailFragmentDataListener.onDetailFragmentDataListener(project, corp, user);
        }
 //       onDetailFragmentDataListener.onDetailFragmentDataListener(project);
    }

    @Override
    public void onDetailActivityDataReciver(Project project, Corp corp, User user, List<Job> jobList) {

    }

}
