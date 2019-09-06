package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.springframework.stereotype.Component;

/**
 * Created by z on 2019/9/6.
 */
@JobHandler(value="myDemoJobHandler")
@Component
public class MyDemoJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        // 如果是一个需要分片的任务, 可以通过下面代码获取一个分片参数, 再整合业务进行分片. 分片会根据节点动态变化
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        XxlJobLogger.log("分片参数：当前分片序号 = {}, 总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());
        System.out.println("分片参数：当前分片序号 = "+shardingVO.getIndex()+", 总分片数 = {}"+ shardingVO.getTotal());


        XxlJobLogger.log("my demo job handler run...");
        System.out.println("receive param: "+ param);
        return SUCCESS;
    }
}
