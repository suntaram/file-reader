package com.filereader.config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.filereader.model.Customer;



public class CSVItemWriter {
	
	public static void writeCSVData(final List<Customer> records, final String path) {

        try (
                FileWriter fileWriter = new FileWriter(path);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Reference", "Description","Reason"));
         ){
            for (Customer customer:records) {
                csvPrinter.printRecord(customer.getReference(),customer.getDesc(),customer.getReason());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
