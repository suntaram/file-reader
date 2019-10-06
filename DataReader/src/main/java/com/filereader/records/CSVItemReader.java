package com.filereader.records;


import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;

import com.filereader.constants.FileConstants;
import com.filereader.model.Customer;
import com.filereader.recordfilter.RecordFilter;
import com.filereader.service.ReferenceService;
import com.filreader.exception.ReferenceNotFoundException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVItemReader {
	
	
	public static List<Customer> readCSVData(String file) {
		
		List<Customer> customerDataList = new ArrayList<>();
		List<Customer> invalidEndBalanceList = new ArrayList<>();
		
		try (
	            Reader reader = Files.newBufferedReader(Paths.get(file));
	        ) {
	            CsvToBean<Customer> csvToBean = new CsvToBeanBuilder(reader)
	                    .withType(Customer.class)
	                    .withIgnoreLeadingWhiteSpace(true)
	                    .build();
	            
	            List<Customer> csvUsers = csvToBean.parse();

	            for(Customer csvUser: csvUsers) {
	            	Long referenceNo=csvUser.getReference();
	            	if(referenceNo==null && referenceNo.equals("")) {
	            		ReferenceService.findByName(referenceNo);
	            	}
	            	if(RecordFilter.findReference(customerDataList,referenceNo) == null) {
	            		if(RecordFilter.validateEndBalance(csvUser)){
	            			customerDataList.add(csvUser);
	            		}
	            		else{
		            		invalidEndBalanceList.add(csvUser);	
		            	}
	            	}
	            	else{
                        invalidEndBalanceList.add(csvUser);
                        csvUser.setReason("It is duplicate record");
                    }
	            	
	
	}
		}
		catch(IOException ex1) {
			ex1.printStackTrace();
		}
		catch(ReferenceNotFoundException ex2) {
			ex2.printStackTrace();
		}
		return invalidEndBalanceList;
	}
}
		
	
	
	

