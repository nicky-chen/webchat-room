package cn.nicky.webchat.config;

import org.springframework.context.annotation.Configuration;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;

/**
 * @author nicky_chin
 * @description:
 * @date: 2020/3/12 下午2:19
 * @since JDK 1.8
 */
@Configuration
@EnableApolloConfig(value = {"application"}, order = 1)
public class ApolloConfig {

}
