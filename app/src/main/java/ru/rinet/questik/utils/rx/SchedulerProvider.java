package ru.rinet.questik.utils.rx;

import io.reactivex.Scheduler;



public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
