package com.trace.config;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.objenesis.ObjenesisHelper;

@Configuration
public class FiscoConfig {
    private static final Logger logger = LoggerFactory.getLogger(FiscoConfig.class);

    @Bean
    @Primary
    public BcosSDK bcosSDK() {
        try {
            logger.warn("FISCO BCOS SDK 未配置，使用空实例");
            return (BcosSDK) ObjenesisHelper.newInstance(BcosSDK.class);
        } catch (Exception e) {
            throw new RuntimeException("无法创建 BcosSDK 实例", e);
        }
    }
}
