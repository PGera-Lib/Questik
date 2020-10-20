package ru.rinet.questik.ui.main.catalog.fragment.addedit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.R;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.ui.base.BaseDialog;

public class CatalogAddEditFragment extends BaseDialog implements CatalogAddEditFragmentMvpView {



    private static final String TAG = "CatalogDialog";

    @Inject
    CatalogAddEditFragmentMvpPresenter<CatalogAddEditFragmentMvpView> mPresenter;

    @BindView(R.id.spinner_cat)
    Spinner catSpinner;

    @BindView(R.id.etxt_name)
    EditText etxt_name;

    @BindView(R.id.etxt_price)
    EditText etxt_price;

    @BindView(R.id.spinner_met)
    Spinner metSpinner;

    @BindView(R.id.button_add)
    Button button_add;

    @BindView(R.id.button_save)
    Button button_save;

    @BindView(R.id.button_add_more)
    Button button_add_more;


    @BindView(R.id.button_reset)
    Button button_reset;


    public static CatalogAddEditFragment newInstance(Job job) {
        CatalogAddEditFragment fragment = new CatalogAddEditFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_catalog_edit, container, false);

        ActivityComponent component = getActivityComponent();
        if (component != null) {

            component.inject(this);

            setUnBinder(ButterKnife.bind(this, view));

            mPresenter.onAttach(this);
        }

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    protected void setUp(View view) {


        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            dismiss();
            }
        });


    }

    @Override
    public void openCatalogFragment() {

    }

    @Override
    public void showCatalogFragmentView() {

    }

    @Override
    public void showCatalogFragmentMessageView() {

    }

    @Override
    public void hideSubmitButton() {

    }

    @Override
    public void dismissDialog() {

    }
}
