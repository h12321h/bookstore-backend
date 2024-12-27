//package org.example.sparkservice.spark;
//import org.apache.spark.sql.SparkSession;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SparkConfig {
//
//    @Bean
//    public SparkSession sparkSession() {
//        return SparkSession.builder()
//                .appName("KeywordCountApp")
//                .master("spark://localhost:7077") // 使用 Standalone 集群 Master 的 URL
////                .config("spark.ui.enabled", "false") // 禁用 Spark UI
////                .config("spark.metrics.enabled", "false") // 禁用 Metrics
//                .getOrCreate();
//    }
//}