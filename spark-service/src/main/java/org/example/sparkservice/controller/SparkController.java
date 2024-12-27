package org.example.sparkservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import scala.Tuple2;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SparkController {

//    @Autowired
//    private SparkKeywordCounter sparkKeywordCounter;

    @GetMapping("/count-keywords")
//    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Long> countKeywords() {
        Map<String, Long> result = new HashMap<>();
        try {
            // 构建 spark-submit 命令
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "/opt/spark/bin/spark-submit",
                    "--class", "org.example.sparkservice.KeywordCountApp",
                    "--master", "spark://localhost:7077",
                    "/Users/dingmuyun/Desktop/bookstore 后端/bookstore/spark-service/target/spark-service-0.0.1-SNAPSHOT.jar"
            );

            // 将进程输出和错误输出合并到一起
            processBuilder.redirectErrorStream(true);

            // 启动进程
            Process process = processBuilder.start();

            // 读取进程的输出
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // 等待进程执行完毕
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Spark job failed with exit code " + exitCode);
            }

            // 解析 Spark 输出
            String[] outputLines = output.toString().split("\n");
            for (String outputLine : outputLines) {
                // 假设 Spark 输出格式为：keyword : count
                if (outputLine.contains(":")) {
                    String[] parts = outputLine.split(":");
                    if (parts.length == 2) {
                        String keyword = parts[0].trim();
                        long count = Long.parseLong(parts[1].trim());
                        result.put(keyword, count);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to execute Spark job", e);
        }

        // 返回结果
        return result;
    }

}
