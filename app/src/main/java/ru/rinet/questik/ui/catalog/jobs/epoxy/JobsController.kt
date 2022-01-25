package ru.rinet.questik.ui.catalog.jobs.epoxy

import com.airbnb.epoxy.TypedEpoxyController


class JobsController : TypedEpoxyController<JobsContainer>() {
    override fun buildModels(data: JobsContainer?) {
        data?.categories?.forEach {
            jobsHeader {
                id(it.category.toString())
                title(it.category.name)
                listener {
                    data.onCategoryExpanded(it.category)
                }
                expand(it.category.isExpand)
            }
            if (it.category.isExpand) {
                it.jobs.forEach { jobs ->
                    jobsChild {
                        id("${jobs.id}+${jobs.name}")
                        price(jobs.price)
                        name(jobs.name)
                        onJobsClickListener{
                            it.onJobsItemClickes(jobs)
                        }
                    }

                }

            }
        }
    }
}