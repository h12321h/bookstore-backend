package org.example.sparkservice;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.SparkConf;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordCountApp {
    public static void main(String[] args) {
        // 硬编码目录路径
        String directoryPath = "/Users/dingmuyun/Desktop/bookstore 后端/bookstore/spark-service/src/main/resources/book-descriptions";

        // 配置 Spark 应用
        SparkConf conf = new SparkConf()
                .setAppName("KeywordCountApp") // 应用名
                .setMaster("spark://localhost:7077"); // Standalone Master 地址
        JavaSparkContext sc = new JavaSparkContext(conf);

        // 读取目录下的所有文件
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.err.println("The provided path is not a directory.");
            System.exit(1);
        }

        // 创建一个空的 RDD
        JavaRDD<String> lines = sc.emptyRDD();

        // 遍历目录中的文件，并将文件内容添加到 RDD 中
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    JavaRDD<String> fileLines = sc.textFile(file.getAbsolutePath());
                    lines = lines.union(fileLines);
                }
            }
        }

        // 定义关键词列表
        List<String> keywords = Arrays.asList("software", "engineering", "network", "programming", "optimization", "database","computer", "artist", "theoretical");

        // 统计关键词出现次数
        Map<String, Long> keywordCounts = new HashMap<>();
        for (String keyword : keywords) {
            long count = lines
                    .flatMap(line -> Arrays.asList(line.split("\\s+")).iterator()) // 按空格分词
                    .map(word -> word.toLowerCase().replaceAll("[^a-zA-Z\\+\\#]", "")) // 去除标点符号，保留如C++等特殊关键词
                    .filter(word -> word.equalsIgnoreCase(keyword)) // 过滤出匹配的关键词
                    .count();
            keywordCounts.put(keyword, count);
        }

        // 输出统计结果
        keywordCounts.forEach((keyword, count) -> System.out.println(keyword + " : " + count));

        // 关闭 Spark Context
        sc.close();
    }
}
