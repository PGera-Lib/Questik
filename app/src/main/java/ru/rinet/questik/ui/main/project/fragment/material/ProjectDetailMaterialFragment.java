package ru.rinet.questik.ui.main.project.fragment.material;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.R;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.ui.base.BaseFragment;
import ru.rinet.questik.ui.main.project.fragment.Interacts;

public class ProjectDetailMaterialFragment extends BaseFragment implements
        ProjectDetailMaterialMvpView {
    //, Interacts.onDetailActivityDataListener
    @Inject
    ProjectDetailMaterialMvpPresenter<ProjectDetailMaterialMvpView> presenter;
    @Inject
    ProjectDetailMaterialAdapter projectDetailMaterialAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.project_job_view)
    RecyclerView rvCatalog;

    private Interacts.onDetailMaterialDataListener onDetailMaterialDataListener;

    private final static int REQUEST_CODE = 0;
    private final static int CHANGE_CODE = 1;
    private final String DIALOG_ADD = "DIALOG_ADD";
    private final String DIALOG_CHANGE = "DIALOG_CHANGE";

    public static ProjectDetailMaterialFragment newInstance() {
        Bundle args = new Bundle();
        ProjectDetailMaterialFragment fragment = new ProjectDetailMaterialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.project_job_fragment, container, false);
        Log.i("CatalogFragment", "Catalog");
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            presenter.onAttach(this);

            projectDetailMaterialAdapter.setCallback((ProjectDetailMaterialPresenter) presenter);
        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvCatalog.setLayoutManager(linearLayoutManager);
        rvCatalog.setItemAnimator(new DefaultItemAnimator());
        rvCatalog.setAdapter(projectDetailMaterialAdapter);

        // presenter.fetchJobList();
    }



    @Override
    public void onDestroyView() {
        Log.i("CatalogFragment", "onDestroyView");
        //presenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        Log.i("CatalogFragment", "onAttach");
        super.onAttach(context);
        if (context instanceof Interacts.onDetailMaterialDataListener) {
            onDetailMaterialDataListener = (Interacts.onDetailMaterialDataListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement onDetailFragmentDataListener");
        }
    }
    @Override
    public void onResume() {
        presenter.fetchJobList();
        super.onResume();
        projectDetailMaterialAdapter.setCallback((ProjectDetailMaterialPresenter) presenter);

    }

    @Override
    public void onDetach() {
        Log.i("CatalogFragment", "onDetach");
        // callback = null;
        // catalogFragmentAdapter.removeCallback();
        super.onDetach();
    }


/*    @Override
    public void updateProjectMaterialList(List<Job> list) {
        projectDetailMaterialAdapter.updateListItems(list);
    }*/

  /*  @Override
    public void openJobDetailActivity(Job job) {
        *//**
         * ToDo: переход в детализацию/редактирование позиции
         *//*

        CatalogAddEditFragment doDialog = CatalogAddEditFragment.newInstance(job);
        doDialog.setTargetFragment(ProjectDetailMaterialFragment.this, CHANGE_CODE);
        doDialog.show(getFragmentManager(), DIALOG_CHANGE);*/
/*        Intent intent = CatalogDetailsActivity.getStartIntent(getContext());
        intent.putExtra(CatalogDetailsActivity.KEY_PARCELABLE_BLOG, catalog);
        startActivity(intent);
    }
*/

    /*@Override
    public void onProjectMaterialListReFetched() {
        if (callback != null)
            callback.onCatalogListReFetched();
    }

    @Override
    public void onPullToRefreshEvent(boolean isVisible) {

    }*/

    @Override
    public void getMaterial(Job job) {

    }

/*    @Override
    public void onDetailActivityDataListener() {

    }*/
}
