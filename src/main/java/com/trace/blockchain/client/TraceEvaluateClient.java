package com.trace.blockchain.client;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.trace.blockchain.common.CommonClient;
import com.trace.blockchain.contract.TraceEvaluate;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TraceEvaluateClient extends CommonClient implements ApplicationRunner {

    public TransactionReceipt set(String nickName, String content){
        TraceEvaluate traceEvaluate = (TraceEvaluate) getContractMap().get("TraceEvaluate");
        return traceEvaluate.set(nickName, content);
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        BcosSDK bcosSDK = SpringUtils.getBean(BcosSDK.class);
        deploy("TraceEvaluate", TraceEvaluate.class, bcosSDK);
        logger.info("deploy contract TraceEvaluate success!");
    }
}
