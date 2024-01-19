package com.hslee.coding_test1;

import com.hslee.coding_test1.solve.Question2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CodingTestApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CodingTestApplication.class, args);
        Question2 question2 = new Question2();
        question2.start();
    }

}
