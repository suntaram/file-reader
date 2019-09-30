package com.filereader.config;


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
import com.filereader.model.Customer;
import com.filereader.recordfilter.RecordFilter;

public class CSVItemReader {
	
	public static List<Customer> readCSVData(String file){
		
		List<Customer> customerDataList = new ArrayList<>();
		List<Customer> invalidEndBalanceList = new ArrayList<>();
		
		try {
			Reader reader = Files.newBufferedReader(Paths.get(file));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());
		
			for (CSVRecord csvRecord : csvParser) {
				Customer customer = new Customer();
				if(csvRecord.get("Reference") != null && !csvRecord.get("Reference").isEmpty()) {
					
					long referenceNo = Long.valueOf(csvRecord.get("Reference"));
					customer.setReference(referenceNo);
					customer.setAccountNumber(csvRecord.get("AccountNumber"));
					customer.setDesc(csvRecord.get("Description"));
					customer.setMutation(new BigDecimal(csvRecord.get("Start Balance")));
					customer.setStartBal(new BigDecimal(csvRecord.get("Mutation")));
					customer.setEndBal(new BigDecimal(csvRecord.get("End Balance")));
					if(RecordFilter.findReference(customerDataList, referenceNo)==null) {
						if(RecordFilter.validateEndBalance(customer)){
							customerDataList.add(customer);
                        }
                        else{
                        	invalidEndBalanceList.add(customer);
                        }
						
					}
					else{
						invalidEndBalanceList.add(customer);
                    }
					
					
				}
			}
		
		
		}
		 catch (IOException e){
	            return null;
	     }
		return invalidEndBalanceList;
	
	}
	
	
}
