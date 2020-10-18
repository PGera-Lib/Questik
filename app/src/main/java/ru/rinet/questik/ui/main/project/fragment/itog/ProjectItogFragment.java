package ru.rinet.questik.ui.main.project.fragment.itog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.Nullable;

import androidx.recyclerview.widget.LinearLayoutManager;




import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.rinet.questik.*;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.ui.base.BaseFragment;

public class ProjectItogFragment extends BaseFragment implements ProjectItogMvpView {


    TextView txt1, txt2, txt3, txt4;

    @Inject
    ProjectItogMvpPresenter<ProjectItogMvpView> mPresenter;


    @Inject
    LinearLayoutManager mLayoutManager;

    public static ProjectItogFragment newInstance() {
        Bundle args = new Bundle();
        ProjectItogFragment fragment = new ProjectItogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.project_itog_fragment, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }
    private void setUpView(View view) {
        txt1 = (TextView) view.findViewById(R.id.textView2);
        txt2 = (TextView) view.findViewById(R.id.textView4);
        txt3 = (TextView) view.findViewById(R.id.textView7);
        txt4 = (TextView) view.findViewById(R.id.textView8);
    }

    @Override
    public void getProject(Project project) {

    }

    @Override
    protected void setUp(View view) {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPresenter.onViewPrepared();
        setUpView(view);
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

}
