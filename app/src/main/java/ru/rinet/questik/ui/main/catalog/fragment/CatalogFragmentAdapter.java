package ru.rinet.questik.ui.main.catalog.fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.rinet.questik.R;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.BaseViewHolder;

@PerActivity
public class CatalogFragmentAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_EMPTY = 1;


    private Callback callback;
    private List<Job> jobList;

    public CatalogFragmentAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    void removeCallback() {
        Log.i("FragmentAdapter", "remove callback and callback = null");
        callback = null;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.catalog_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {

        if (jobList != null && jobList.size() > 0)
            return VIEW_TYPE_NORMAL;
        else return VIEW_TYPE_EMPTY;
    }


    @Override
    public int getItemCount() {
        if (jobList != null && jobList.size() > 0)
            return jobList.size();
        else return 1;
    }

    /*
     * Update the current list to an assigned list
     *
     * This update method is called every time the list item has to be updated.
     * Previous List must be cleared to store new list, to avoid duplicates*/
    void updateListItems(List<Job> list) {
        this.jobList.clear();
        this.jobList.addAll(list);
        notifyDataSetChanged();
    }

    /*
     * Adds assigned list to the existing list*/
    public void addItems(List<Job> jobList) {
        this.jobList.addAll(jobList);
        notifyDataSetChanged();
    }


    public interface Callback {

        void onJobEmptyRetryClicked();

        void onJobItemClicked(Job job);
    }


    public class ViewHolder extends BaseViewHolder {

/*        @BindView(R.id.cover_image_view)
        ImageView ivCover;*/

        @BindView(R.id.position_name)
        TextView tvName;

        @BindView(R.id.position_price)
        TextView tvPrice;

        @BindView(R.id.price_count)
        TextView tvCount;

        @BindView(R.id.price_met)
        TextView tvMet;

        @BindView(R.id.position_chk)
        CheckBox tvChk;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
          //  ivCover.setImageDrawable(null);
            tvName.setText("");
            tvPrice.setText("");
            tvCount.setText("");
            tvMet.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final Job job = jobList.get(position);

/*            if (job.getCoverImgUrl() != null)
                Glide.with(itemView.getContext())
                        .load(job.getCoverImgUrl())
                        .asBitmap()
                        .fitCenter()
                        .placeholder(R.drawable.placeholder)
                        .into(ivCover);*/

            if (job.getName() != null)
                tvName.setText(job.getName());

            if (job.getPrice() != null)
                tvPrice.setText(job.getPrice());

            if (job.getCount() != null)
                tvCount.setText(job.getCount());

            if (job.getMetrics() != null)
                tvMet.setText(job.getMetrics()+"");

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (job != null && callback != null)
                        callback.onJobItemClicked(job);
                }
            });
        }
    }


    public class EmptyViewHolder extends BaseViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

       /* @OnClick(R.id.btn_retry)
        void onRetryClicked() {
            if (callback != null)
                callback.onJobEmptyRetryClicked();
        }*/
    }
}