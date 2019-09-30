package com.filereader;

import static java.lang.System.exit;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.filereader.config.CSVItemReader;
import com.filereader.config.CSVItemWriter;
import com.filereader.config.XMLItemReader;
import com.filereader.constants.FileConstants;
import com.filereader.service.FileReaderService;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan( {"com.filereader.*"})
public class DataReaderApplication implements CommandLineRunner {
	
	 @Autowired
	 FileReaderService filereaderservice;
	
	public static void main(String[] args) throws IOException {
		SpringApplication app = new SpringApplication(DataReaderApplication.class);
        app.run(args);
	}
	
	@Override
	public void run(final String... args) throws Exception {
        if (args.length == 0) {
        	filereaderservice.CSVReport("E:\\csvfile\\records.csv", "F:\\fullpath");
        } else {
            System.out.println("pls cross check the above args");
        }

        exit(0);
    }

	

}
