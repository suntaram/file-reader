package com.filereader.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileConstants {
	
	@Value("${datareader.CSV-EXTENSION}")
	public String CSV_EXT;
	@Value("${datareader.XML-EXTENSION}")
    public String XML_EXT;
	@Value("${datareader.CSV-PATH}")
    public String CSV_PATH;
	@Value("${datareader.XML-PATH}")
	public String XML_PATH;
	@Value("${datareader.CSV-OUTPUT-PATH}")
	public String CSV_OUTPUT_PATH;

}
