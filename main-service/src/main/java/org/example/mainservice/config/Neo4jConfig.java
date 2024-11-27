package org.example.mainservice.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

import org.neo4j.driver.Driver;
import org.springframework.transaction.TransactionManager;

@Configuration
@EnableNeo4jRepositories(basePackages = "org.example.mainservice.repository") // 指定 Neo4j Repository 的包路径
public class Neo4jConfig {
    @Bean("neo4jTemplate")
    public Neo4jTemplate neo4jTemplate(
            Neo4jClient neo4jClient,
            Neo4jMappingContext neo4jMappingContext,
            Driver driver,
            DatabaseSelectionProvider databaseNameProvider) {

        // 手动创建并配置 Neo4jTransactionManager
        Neo4jTransactionManager transactionManager = new Neo4jTransactionManager(driver, databaseNameProvider);

        // 返回一个绑定到 Neo4jTransactionManager 的 Neo4jTemplate
        return new Neo4jTemplate(neo4jClient, neo4jMappingContext, transactionManager);
    }
}
