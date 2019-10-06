package com.filereader.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filereader.constants.FileConstants;
import com.filereader.model.Customer;
import com.filereader.recordfilter.RecordFilter;
import com.filereader.records.CSVItemReader;
import com.filereader.records.CSVItemWriter;
import com.filereader.records.XMLItemReader;
import com.filreader.exception.ReferenceNotFoundException;

@Service
public class FileReaderServiceImpl implements FileReaderService {
	
	@Autowired
	FileConstants fileConstant;
 
	List<Customer> invalidReportList = new ArrayList<>();
	  public void CSVReport(String fileInputPath, String fileOutputPath) throws IOException, ReferenceNotFoundException  {
	        
	        String extension = RecordFilter.findExtension(fileInputPath).get();
	        if (extension.equalsIgnoreCase(fileConstant.CSV_EXT)) {
	          invalidReportList = CSVItemReader.readCSVData(fileInputPath);
				CSVItemWriter.writeCSVData(invalidReportList, fileOutputPath + "/failed-records.csv");
	        } else if (extension.equalsIgnoreCase(fileConstant.XML_EXT)) {
	        	
	           invalidReportList = XMLItemReader.readFile(fileInputPath);
			CSVItemWriter.writeCSVData(invalidReportList, fileOutputPath + "/failed-records.csv");
	        }
	        
	    }
   
 }
	
	


