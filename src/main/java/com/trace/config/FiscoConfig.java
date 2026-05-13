package com.trace.config;

import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.config.ConfigOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FiscoConfig {
    private static final Logger logger = LoggerFactory.getLogger(FiscoConfig.class);

    @Bean
    public BcosSDK bcosSDK() throws Exception {
        String configPath = System.getProperty("user.dir") + "/src/main/resources/fisco-config.toml";
        File configFile = new File(configPath);
        if (!configFile.exists()) {
            configPath = "classpath:fisco-config.toml";
        }

        ConfigOption configOption = ConfigOption.load(configPath);
        logger.info("FISCO BCOS SDK 初始化完成，连接节点: {}", configOption.getNetwork().getPeers());
        return new BcosSDK(configOption);
    }
}
