package com.eci.sterling.api.webfluxintegration.poc.hazelcast;

import com.hazelcast.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Arnaldo Trujillo
 */
@Configuration
public class HazelcastConfigurator {

    @Bean
    public Config createLocalHazelcastConfig(@Autowired GroupConfig groupConfig, @Autowired NetworkConfig networkConfig,
                                             @Autowired MapConfig mapConfig
    ) {
        Config hazelcastConfig = new Config();
        hazelcastConfig.setInstanceName("localInstance");
        hazelcastConfig.setGroupConfig(groupConfig);
        hazelcastConfig.setNetworkConfig(networkConfig);
        hazelcastConfig.addMapConfig(mapConfig);

        return hazelcastConfig;
    }

    @Bean
    public GroupConfig buildGroupConfig() {
        return new GroupConfig("dev", "password");
    }

    @Bean
    public NetworkConfig buildNetworkConfig() {
        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPort(5701);
        networkConfig.setPortAutoIncrement(true);

        JoinConfig joinConfig = new JoinConfig();
        MulticastConfig multicastConfig = new MulticastConfig();
        multicastConfig.setEnabled(true);
        multicastConfig.setMulticastGroup("224.2.2.3");
        multicastConfig.setMulticastPort(54327);

        joinConfig.setMulticastConfig(multicastConfig);

        networkConfig.setJoin(joinConfig);
        return networkConfig;
    }

    @Bean
    public MapConfig buildMapConfig() {
        MapConfig mapConfig = new MapConfig("default");
        mapConfig.setBackupCount(1);
        mapConfig.setAsyncBackupCount(0);
        mapConfig.setTimeToLiveSeconds(0);
        mapConfig.setMaxIdleSeconds(0);
        mapConfig.setEvictionPolicy(EvictionPolicy.NONE);
//        mapConfig.setMergePolicyConfig(new MergePolicyConfig());

        return mapConfig;
    }

}