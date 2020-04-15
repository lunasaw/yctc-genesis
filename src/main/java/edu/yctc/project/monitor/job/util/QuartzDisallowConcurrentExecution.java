package edu.yctc.project.monitor.job.util;

import edu.yctc.project.monitor.job.domain.Job;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;

/**
 * 定时任务处理（禁止并发执行）
 * 
 * @author luna
 *
 */
@DisallowConcurrentExecution
/**禁止并发执行的控制操作注解 默认允许 */
public class QuartzDisallowConcurrentExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, Job job) throws Exception
    {
        JobInvokeUtil.invokeMethod(job);
    }
}
