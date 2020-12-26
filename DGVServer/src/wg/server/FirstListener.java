package wg.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class FirstListener implements JobListener {
	public String getName(){
		return "FirstListener";
	}

	public void jobExecutionVetoed(JobExecutionContext arg0) {
		System.out.println("Vetoed. " + nowDateStr());
	}

	public void jobToBeExecuted(JobExecutionContext arg0) {
		System.out.println("To Be Executed. " + nowDateStr());
	}

	public void jobWasExecuted(JobExecutionContext arg0, JobExecutionException arg1) {
		System.out.println("Was Executed. " + nowDateStr());
	}

	public String nowDateStr(){
		SimpleDateFormat SdfTimer = new SimpleDateFormat("yyyyMMddHHmmss");
		return SdfTimer.format(new Date());
	}

}
