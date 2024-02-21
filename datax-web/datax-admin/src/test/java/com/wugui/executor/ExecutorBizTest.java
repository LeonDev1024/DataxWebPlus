package com.wugui.executor;

import com.pji.cloud.datatx.core.biz.ExecutorBiz;
import com.pji.cloud.datatx.core.biz.model.ReturnT;
import com.pji.cloud.datatx.core.biz.model.TriggerParam;
import com.pji.cloud.datatx.core.enums.ExecutorBlockStrategyEnum;
import com.pji.cloud.datatx.core.glue.GlueTypeEnum;
import com.pji.cloud.datax.rpc.remoting.invoker.XxlRpcInvokerFactory;
import com.pji.cloud.datax.rpc.remoting.invoker.call.CallType;
import com.pji.cloud.datax.rpc.remoting.invoker.reference.XxlRpcReferenceBean;
import com.pji.cloud.datax.rpc.remoting.invoker.route.LoadBalance;
import com.pji.cloud.datax.rpc.remoting.net.impl.netty.http.client.NettyHttpClient;
import com.pji.cloud.datax.rpc.serialize.impl.HessianSerializer;

/**
 * executor-api client, test
 *
 * @author  xuxueli on 17/5/12.
 */
public class ExecutorBizTest {

    public static void main(String[] args) throws Exception {

        // param
        String jobHandler = "demoJobHandler";
        String params = "";

        runTest(jobHandler, params);
    }

    /**
     * run jobhandler
     *
     * @param jobHandler
     * @param params
     */
    private static void runTest(String jobHandler, String params) throws Exception {
        // trigger data
        TriggerParam triggerParam = new TriggerParam();
        triggerParam.setJobId(1);
        triggerParam.setExecutorHandler(jobHandler);
        triggerParam.setExecutorParams(params);
        triggerParam.setExecutorBlockStrategy(ExecutorBlockStrategyEnum.COVER_EARLY.name());
        triggerParam.setGlueType(GlueTypeEnum.DATAX.name());
        triggerParam.setGlueSource(null);
        triggerParam.setGlueUpdatetime(System.currentTimeMillis());
        triggerParam.setLogId(1);
        triggerParam.setLogDateTime(System.currentTimeMillis());

        // do remote trigger
        String accessToken = null;

        XxlRpcReferenceBean referenceBean = new XxlRpcReferenceBean();
        referenceBean.setClient(NettyHttpClient.class);
        referenceBean.setSerializer(HessianSerializer.class);
        referenceBean.setCallType(CallType.SYNC);
        referenceBean.setLoadBalance(LoadBalance.ROUND);
        referenceBean.setIface(ExecutorBiz.class);
        referenceBean.setVersion(null);
        referenceBean.setTimeout(3000);
        referenceBean.setAddress("127.0.0.1:9999");
        referenceBean.setAccessToken(accessToken);
        referenceBean.setInvokeCallback(null);
        referenceBean.setInvokerFactory(null);

        ExecutorBiz executorBiz = (ExecutorBiz) referenceBean.getObject();

        ReturnT<String> runResult = executorBiz.run(triggerParam);

        System.out.println(runResult);
        XxlRpcInvokerFactory.getInstance().stop();
    }

}
