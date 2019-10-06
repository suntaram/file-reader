package com.filereader.service;

import java.io.IOException;

import com.filreader.exception.ReferenceNotFoundException;

public interface FileReaderService {
	
	    public void CSVReport(String fileInputPath, String fileOututPath) throws IOException, ReferenceNotFoundException;
	    
	   

}
