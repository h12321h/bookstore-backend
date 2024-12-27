//package org.example.sparkservice.spark;
//
//import org.apache.spark.api.java.JavaRDD;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.SparkConf;
//
//import java.io.File;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class SparkKeywordCounter {
//    private JavaSparkContext sc;
//
//    public SparkKeywordCounter() {
//        SparkConf conf = new SparkConf()
//                .setAppName("KeywordCountApp")
//                .setMaster("spark://localhost:7077");
//        this.sc = new JavaSparkContext(conf);
//    }
//
//    public Map<String, Long> countKeywords(String directoryPath, List<String> keywords) {
//        File directory = new File(directoryPath);
//        if (!directory.isDirectory()) {
//            throw new IllegalArgumentException("The provided path is not a directory.");
//        }
//
//        JavaRDD<String> lines = sc.emptyRDD();
//        File[] files = directory.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                if (file.isFile()) {
//                    JavaRDD<String> fileLines = sc.textFile(file.getAbsolutePath());
//                    lines = lines.union(fileLines);
//                }
//            }
//        }
//
//        Map<String, Long> keywordCounts = new HashMap<>();
//        for (String keyword : keywords) {
//            long count = lines
//                    .flatMap(line -> Arrays.asList(line.split("\\s+")).iterator())
//                    .map(word -> word.toLowerCase().replaceAll("[^a-zA-Z\\+\\#]", ""))
//                    .filter(word -> word.equalsIgnoreCase(keyword))
//                    .count();
//            keywordCounts.put(keyword, count);
//        }
//
//        return keywordCounts;
//    }
//
//    public void close() {
//        sc.close();
//    }
//}
