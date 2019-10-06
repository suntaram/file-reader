package com.filereader.runner;

import static java.lang.System.exit;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.filereader.constants.FileConstants;
import com.filereader.records.CSVItemReader;
import com.filereader.records.CSVItemWriter;
import com.filereader.records.XMLItemReader;
import com.filereader.service.FileReaderService;
import com.filreader.exception.ReferenceNotFoundException;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan( {"com.filereader.*"})
public class DataReaderApplication implements CommandLineRunner {
	
	 @Autowired
	 FileReaderService filereaderservice;
	 
	 @Autowired
	 FileConstants fileConstant;
	
	public static void main(String[] args) throws IOException {
		SpringApplication app = new SpringApplication(DataReaderApplication.class);
        app.run(args);
	}
	
	@Override
	public void run(final String... args)  {
		try {
	        if (args.length == 0) {
	        	filereaderservice.CSVReport(fileConstant.XML_PATH, fileConstant.CSV_OUTPUT_PATH);
	        } else {
	            System.out.println("pls cross check the above args");
	        }
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(ReferenceNotFoundException ex2) {
			ex2.printStackTrace();
		}
		finally {
			exit(0);
		}
        
    }

	

}
