package com.filereader.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.filereader.config.CSVItemReader;
import com.filereader.config.CSVItemWriter;
import com.filereader.config.XMLItemReader;
import com.filereader.constants.FileConstants;
import com.filereader.model.Customer;
import com.filereader.recordfilter.RecordFilter;


@Service
public class FileReaderServiceImpl implements FileReaderService {
 
	List<Customer> invalidReportList = new ArrayList<>();
	  public void CSVReport(String fileInputPath, String fileOutputPath) {
	        
	        String extension = RecordFilter.findExtension(fileInputPath).get();
	        if (extension.equalsIgnoreCase(FileConstants.CSV_EXT)) {
	          
	        	invalidReportList = CSVItemReader.readCSVData(fileInputPath);
	        	CSVItemWriter.writeCSVData(invalidReportList, fileOutputPath + "/failed-records.csv");
	           
	        } else if (extension.equalsIgnoreCase(FileConstants.XML_EXT)) {
	           
	            invalidReportList = XMLItemReader.readFile(fileInputPath);
	        }
	        CSVItemWriter.writeCSVData(invalidReportList, fileOutputPath + "/failed-records.csv");
	    }
   
 }
	
	


