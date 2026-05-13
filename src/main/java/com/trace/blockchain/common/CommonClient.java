package com.trace.blockchain.common;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public abstract class CommonClient {

    public static final Logger logger = LoggerFactory.getLogger(CommonClient.class.getName());

    public CommonClient() {
    }

    private Map<String, Object> contractMap = new ConcurrentHashMap<>();

    private Map<String, Object> loadContractMap = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public <T> void deploy(String contractName, Class<T> clazz, BcosSDK sdk) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Client client = sdk.getClient();
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        Method method = clazz.getMethod("deploy", Client.class, CryptoKeyPair.class);
        T result = (T) method.invoke(null, client, cryptoKeyPair);
        logger.info("执行CommonClient的deploy方法");
        logger.info("部署合约 {} 成功:{}", contractName, result);
        contractMap.put(contractName, result);
    }

    public <T> void load(String contractAddress, Class<T> clazz, BcosSDK sdk) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Client client = sdk.getClient();
        CryptoKeyPair cryptoKeyPair = client.getCryptoSuite().getCryptoKeyPair();
        Method method = clazz.getMethod("load", Client.class, CryptoKeyPair.class);
        T result = (T) method.invoke(contractAddress, client, cryptoKeyPair);
        loadContractMap.put(contractAddress,result);
    }

    public Object getContractMap(String contractName) {
        if (getContractMap().containsKey(contractName)) {
            return getContractMap().get(contractName);
        }
        return null;
    }

    public Map<String, Object> getContractMap() {
        return contractMap;
    }

    public void setContractMap(Map<String, Object> contractMap) {
        this.contractMap = contractMap;
    }
}
