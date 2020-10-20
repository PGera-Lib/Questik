package ru.rinet.questik.ui.main.project;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.R;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.ui.base.BaseFragment;
import ru.rinet.questik.ui.main.Interact;
import ru.rinet.questik.ui.main.project.activity.DetailProjectPagerActivity;

public class ProjectFragment extends BaseFragment implements ProjectFragmentMvpView {

    @Inject
    ProjectFragmentMvpPresenter<ProjectFragmentMvpView> presenter;
    @Inject
    ProjectFragmentAdapter ProjectFragmentAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.projects_list)
    RecyclerView rvProject;
    @BindView(R.id.projects_fab)
    FloatingActionButton project_fab;

    Corp corpItem;
    User userItem;

    PackageManager pm;

    public Interact.Project callback1;

    public static ProjectFragment newInstance() {
        Bundle args = new Bundle();
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_projects, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            presenter.onAttach(this);

            ProjectFragmentAdapter.setCallback((ProjectFragmentPresenter) presenter);

            project_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), DetailProjectPagerActivity.class);
                    startActivity(intent);
                }
            });
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvProject.setLayoutManager(linearLayoutManager);
        rvProject.setItemAnimator(new DefaultItemAnimator());
        rvProject.setAdapter(ProjectFragmentAdapter);

        presenter.fetchProjectList();
    }

    public void setListScrollTop() {
        if (linearLayoutManager != null)
            linearLayoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override
    public void updateProjectList(List<Project> projectList) {
        ProjectFragmentAdapter.updateListItems(projectList);
    }


    @Override
    public void openProjectDetailActivity(Project project) {

        /**
         * ToDo: переход в детализацию/редактирование позиции
         */
        if (project != null){
            Log.i("ProjectFragment", "Project intent - "+project);
            Intent intent = new Intent(getContext(), DetailProjectPagerActivity.class);
            intent.putExtra(DetailProjectPagerActivity.KEY_PARCELABLE_PROJECT, project);
            startActivity(intent);

            if (intent.resolveActivity(pm) != null) {
                Log.d("Intent", "получается обработать намерение!");
            } else {
                Log.d("Intent", "Не получается обработать намерение!");
            }
        } else {
            Intent intent = new Intent(getContext(), DetailProjectPagerActivity.class);
            startActivity(intent);
        }


    }



    @Override
    public void onDestroyView() {
      //  presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
       // callback = null;
       // ProjectFragmentAdapter.removeCallback();
        super.onDetach();
    }
}