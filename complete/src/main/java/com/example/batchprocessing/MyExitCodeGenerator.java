package com.example.batchprocessing;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.batch.JobExecutionEvent;
import org.springframework.context.ApplicationListener;

class MyExitCodeGenerator implements ExitCodeGenerator, ApplicationListener<JobExecutionEvent> {

    private JobExecution jobExecution;
	
    @Override
    public int getExitCode() {
    	if (jobExecution == null)
    		return 2;
        ExitStatus exitStatus = jobExecution.getExitStatus();
        if (ExitStatus.FAILED.getExitCode().equals(exitStatus.getExitCode())) {
            return 1;
        }
        return 0;
    }

    @Override
    public void onApplicationEvent(JobExecutionEvent jobExecutionEvent) {
    	System.out.println("PRINT" + jobExecutionEvent);
        this.jobExecution = jobExecutionEvent.getJobExecution();
    }
}