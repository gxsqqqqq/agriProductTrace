package com.trace.blockchain.client;

import com.trace.common.utils.spring.SpringUtils;
import com.trace.blockchain.contract.TraceRecords;
import com.trace.blockchain.common.CommonClient;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class TraceRecordsClient extends CommonClient implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(TraceRecordsClient.class);

    public TransactionReceipt insert(String productId, String name, String dataJson){
        TraceRecords traceRecords = (TraceRecords)getContractMap().get("TraceRecords");
        return traceRecords.insert(productId, name, dataJson);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BcosSDK bcosSDK = SpringUtils.getBean(BcosSDK.class);
        deploy("TraceRecords", TraceRecords.class, bcosSDK);
        logger.info("deploy contract TraceRecords success!");
    }
}
