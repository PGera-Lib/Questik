package ru.rinet.questik.ui.main.project;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.R;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.BaseViewHolder;

@PerActivity
public class ProjectFragmentAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_NORMAL = 0;
    private static final int VIEW_TYPE_EMPTY = 1;


    private Callback callback;
    private List<Project> projectList;
    private List<Corp> corpList;
    private List<User> userList;



    public ProjectFragmentAdapter(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    void removeCallback() {
        callback = null;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_listitem, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_listitem, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {

        if (projectList != null && projectList.size() > 0)
            return VIEW_TYPE_NORMAL;
        else return VIEW_TYPE_EMPTY;
    }


    @Override
    public int getItemCount() {
        if (projectList != null && projectList.size() > 0)
            return projectList.size();
        else return 1;
    }

    void updateListItems(List<Project> list) {
        this.projectList.clear();
        this.projectList.addAll(list);
        notifyDataSetChanged();
    }

    /*
     * Adds assigned list to the existing list*/
    public void addItems(List<Project> projectList) {
        this.projectList.addAll(projectList);
        notifyDataSetChanged();
    }


    public interface Callback {
        void onProjectEmptyRetryClicked();
        void onProjectItemClicked(Project Project);
    }


    public class ViewHolder extends BaseViewHolder {

/*        @BindView(R.id.cover_image_view)
        ImageView ivCover;*/

        @BindView(R.id.created_date)
        TextView tvCreatedDate;

        @BindView(R.id.project_user)
        TextView tvUser;

        @BindView(R.id.end_date)
        TextView tvEndDate;

        @BindView(R.id.project_name)
        TextView tvName;

        @BindView(R.id.project_chk)
        CheckBox tvChk;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
          //  ivCover.setImageDrawable(null);
            tvName.setText("");
            tvCreatedDate.setText("");
            tvEndDate.setText("");
            tvUser.setText("");
        }

        @Override
        public void onBind(int position) {
            super.onBind(position);

            final Project project = projectList.get(position);
          // final User user = userList.get(project.getUser().intValue());
/*            if (Project.getCoverImgUrl() != null)
                Glide.with(itemView.getContext())
                        .load(Project.getCoverImgUrl())
                        .asBitmap()
                        .fitCenter()
                        .placeholder(R.drawable.placeholder)
                        .into(ivCover);*/

            if (project.getName() != null)
                tvName.setText(project.getName());

            if (project.getUser() != null)
                tvUser.setText(project.getUser().toString());

            if (project.getDataOn() != null)
                tvCreatedDate.setText(project.getDataOn());

            if (project.getDataOff() != null)
                tvEndDate.setText(project.getDataOff());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (project != null && callback != null)
                        callback.onProjectItemClicked(project);
                    Log.i("ProjectFragmentAdapter", "catalog item selected is - " + project);
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
                callback.onProjectEmptyRetryClicked();
        }*/
    }
}