import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.quartz.CronTrigger;

public class Demo {

	public void go() throws Exception {
		//ͨ��SchedulerFactory����ȡһ��������  
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		//jobs������scheduled��sched.start()����ǰ������ 
        //job 1��ÿ��20��ִ��һ�� 
		
		JobDetail job = newJob(MyJob.class).withIdentity("job1", "group1").build();
		CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.withSchedule(cronSchedule("0/2 * * * * ?")).build();
		Date ft = sched.scheduleJob(job, trigger);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		System.out.println(
				job.getKey() + " �ѱ�����ִ����: " + sdf.format(ft) + "�������������ظ������ظ�ִ��: " + trigger.getCronExpression());
		sched.start();

	}

	public static void main(String[] args) throws Exception {
		Demo test = new Demo();
		test.go();
	}
}
