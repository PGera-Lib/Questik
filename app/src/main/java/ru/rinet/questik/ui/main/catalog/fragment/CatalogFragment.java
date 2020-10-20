package ru.rinet.questik.ui.main.catalog.fragment;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.R;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.ui.base.BaseFragment;
import ru.rinet.questik.ui.main.Interact;
import ru.rinet.questik.ui.main.catalog.fragment.addedit.CatalogAddEditFragment;

public class CatalogFragment extends BaseFragment implements CatalogFragmentMvpView {

    @Inject
    CatalogFragmentMvpPresenter<CatalogFragmentMvpView> presenter;
    @Inject
    CatalogFragmentAdapter catalogFragmentAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @BindView(R.id.catalog_list)
    RecyclerView rvCatalog;
    @BindView(R.id.catalog_fab)
    FloatingActionButton cat_fab;

    private Interact.Catalog callback;

    private final static int REQUEST_CODE = 0;
    private final static int CHANGE_CODE = 1;
    private final String DIALOG_ADD = "DIALOG_ADD";
    private final String DIALOG_CHANGE = "DIALOG_CHANGE";

    public static CatalogFragment newInstance() {
        Bundle args = new Bundle();
        CatalogFragment fragment = new CatalogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        Log.i("CatalogFragment", "Catalog");
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            presenter.onAttach(this);

            catalogFragmentAdapter.setCallback((CatalogFragmentPresenter) presenter);



            cat_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Job job = new Job();
                    CatalogAddEditFragment doDialog = CatalogAddEditFragment.newInstance(job);
                    doDialog.setTargetFragment(CatalogFragment.this, CHANGE_CODE);
                    doDialog.show(getFragmentManager(), DIALOG_CHANGE);
                }
            });

        }

        return view;
    }

    @Override
    protected void setUp(View view) {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvCatalog.setLayoutManager(linearLayoutManager);
        rvCatalog.setItemAnimator(new DefaultItemAnimator());
        rvCatalog.setAdapter(catalogFragmentAdapter);

        presenter.fetchJobList();
    }


    @Override
    public void updateCatalogList(List<Job> jobList) {
        catalogFragmentAdapter.updateListItems(jobList);
    }



    @Override
    public void openCatalogDetailFragment(Job job) {

        CatalogAddEditFragment doDialog = CatalogAddEditFragment.newInstance(job);
        doDialog.setTargetFragment(CatalogFragment.this, CHANGE_CODE);
        doDialog.show(getFragmentManager(), DIALOG_CHANGE);
/*        Intent intent = CatalogDetailsActivity.getStartIntent(getContext());
        intent.putExtra(CatalogDetailsActivity.KEY_PARCELABLE_BLOG, catalog);
        startActivity(intent);*/
    }

    @Override
    public void onCatalogListReFetched() {
        if (callback != null)
            callback.onCatalogListReFetched();
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
    }
	@Override
	public void onResume() {
        presenter.fetchJobList();
        super.onResume();
        catalogFragmentAdapter.setCallback((CatalogFragmentPresenter) presenter);

    }

    @Override
    public void onDetach() {
        Log.i("CatalogFragment", "onDetach");
       // callback = null;
       // catalogFragmentAdapter.removeCallback();
        super.onDetach();
    }
}