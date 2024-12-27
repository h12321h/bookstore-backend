//package org.example.mainservice.spark;
//
//import org.apache.spark.sql.SparkSession;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SparkInitializer implements CommandLineRunner {
//
//    private final SparkSession sparkSession;
//
//    public SparkInitializer(SparkSession sparkSession) {
//        this.sparkSession = sparkSession;
//    }
//
//    @Override
//    public void run(String... args) {
//        System.out.println("Starting Spark Initialization...");
//
//        // 示例：设置 Spark 配置参数（可选）
//        sparkSession.conf().set("spark.executor.memory", "1g");
//        sparkSession.conf().set("spark.driver.memory", "1g");
//
//        System.out.println("Spark Initialization Complete!");
//    }
//}
