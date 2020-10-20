package ru.rinet.questik.data.retrofit.mapper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.models.Job;

public class JobMapper {
    @Inject
    public JobMapper() {
    }

    public List<Job> mapJobs(DataManager storage, QuestikResponse response) {
        List<Job> jobList = new ArrayList<>();

        if (response != null) {
            List<Job> jobik = response.getJobs();
            if (jobik != null) {
                for (Job myJob : jobik) {
                    Job job = new Job();
                    job.setId(myJob.getId());
                    job.setName(myJob.getName());
                    job.setCategory(myJob.getCategory());
                    job.setPrice(myJob.getPrice());
                    job.setMetrics(myJob.getMetrics());
                    job.setCount(myJob.getCount());
                    job.setDiscount(myJob.getDiscount());
                    storage.insertJob(job);
                    jobList.add(job);
                    Log.i("Mapper JOB", "Mapped in JSON: "+job);
                }
            }
        }
        return jobList;
    }
}

