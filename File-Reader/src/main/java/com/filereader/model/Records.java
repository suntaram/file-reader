package com.filereader.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;


	
@XmlRootElement(name = "records")
public class Records {
		
	
	private List records;

    public List getRecords() {
        return records;
    }

    @XmlAnyElement(lax = true)
    public void setRecords(final List records) {
        this.records = records;
    }
	    
}
