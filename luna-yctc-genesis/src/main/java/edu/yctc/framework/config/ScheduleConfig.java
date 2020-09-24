package edu.yctc.framework.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 定时任务配置
 * 
 * @author luna
 *
 */
@Configuration
public class ScheduleConfig
{
	private static final Logger log = LoggerFactory.getLogger(ScheduleConfig.class);

	@Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setDataSource(dataSource);

        // quartz参数
        Properties prop = new Properties();
	    log.info("定时任务配置");
	    // 放入实例名称
        prop.put("org.quartz.scheduler.instanceName", "RuoyiScheduler");
	    // 放入实例Id
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        // 线程池配置
	    log.info("线程迟配置");
	    // 线程池实现类名
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        // 线程数量
        prop.put("org.quartz.threadPool.threadCount", "20");
        // 线程优先级 1-10
        prop.put("org.quartz.threadPool.threadPriority", "5");
        // JobStore配置
	    log.info("数据持久化配置");
        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        // 集群配置
	    log.info("集群配置  实例名称需要一致");
        prop.put("org.quartz.jobStore.isClustered", "true");
        // 失效时间检查
        prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
	    log.info("优化配置");
	    // 处理错过触发器tager的最大数量
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        // 高负载和长时间运行有一个优化
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");

        // sqlserver 启用 sqlserver 锁不一致 需要启用
        //prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS UPDLOCK WHERE LOCK_NAME = ?");
        prop.put("org.quartz.jobStore.misfireThreshold", "12000");
        // 数据库表
        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        factory.setQuartzProperties(prop);

        //选择名称
        factory.setSchedulerName("RuoyiScheduler");
        // 延时启动 项目启动后1秒开始执行定时任务
        factory.setStartupDelay(1);
        // 调度上下文
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 可选，QuartzScheduler
        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        factory.setAutoStartup(true);

        return factory;
    }
}
