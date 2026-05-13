package com.trace.blockchain.client;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.trace.blockchain.common.CommonClient;
import com.trace.blockchain.contract.UserAccount;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserAccountClient extends CommonClient implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserAccountClient.class);

    public TransactionReceipt set(String tel, String account){
        UserAccount userAccount = (UserAccount) getContractMap().get("UserAccount");
        return userAccount.set(tel, account);
    }

    public Integer get(String tel) throws ContractException {
        UserAccount userAccount = (UserAccount) getContractMap().get("UserAccount");
        Tuple2<Boolean, String> tuple2 = userAccount.get(tel);
        if (tuple2.getValue1()){
            return Integer.valueOf(tuple2.getValue2());
        }
        return null;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BcosSDK bcosSDK = SpringUtils.getBean(BcosSDK.class);
        deploy("UserAccount", UserAccount.class,bcosSDK);
        logger.info("UserAccount deployed successfully");
    }
}
