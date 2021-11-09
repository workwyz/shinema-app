package cn.shinema.app.config;

import org.apache.dubbo.config.*;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@DubboComponentScan(basePackages = {"cn.shinema"})
public class DubboConfig {
    private Logger logger = LoggerFactory.getLogger(DubboConfig.class);

    @Value("${dubbo.application.name:cn.shinema.app}")
    private String applicationName;

    @Value("${zookeeper.address.cluster:127.0.0.1:2181}")
    private String zkAddress;

    @Value("${dubbo.protocol.name:dubbo}")
    private String protocolName;

    @Value("${dubbo.protocol.port:2109}")
    private Integer protocolPort;

    @Value("${dubbo.protocol.timeout:10000}")
    private Integer timeout;

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        applicationConfig.setQosEnable(false);
//		applicationConfig.setLogger("log4j2");
//		applicationConfig.setLogger("slf4j");

        logger.info("applicationConfig:{}", applicationConfig);
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setClient("curator");
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(zkAddress);
        registryConfig.setTimeout(timeout);

        logger.info("registryConfig:{}", registryConfig);
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setPort(protocolPort);
//		protocolConfig.setAccepts(protocolTimeout);
        protocolConfig.setAccesslog("true");

        logger.info("protocolConfig:{}", protocolConfig);
        return protocolConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(timeout);
        consumerConfig.setCheck(false);
        logger.info("consumerConfig:{}", consumerConfig);
        return consumerConfig;
    }

    @Bean
    public MetadataReportConfig metadataReportConfig() {
        MetadataReportConfig metadataReportConfig = new MetadataReportConfig();
        return metadataReportConfig;
    }

}
