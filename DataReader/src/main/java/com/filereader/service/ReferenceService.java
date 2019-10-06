package com.filereader.service;

import com.filereader.model.Customer;
import com.filreader.exception.ReferenceNotFoundException;

public class ReferenceService {
	
	 public static void findByName(Long referenceNo) throws ReferenceNotFoundException  {

	        if(referenceNo==null || referenceNo.equals(""))
	            throw new ReferenceNotFoundException("Reference is empty!");
	        
	 }
	    public static void main(String[] args) {

	        try {
	        	ReferenceService reference=new ReferenceService();
	        	Customer customer=new Customer();
	        	reference.findByName(customer.getReference());
	        	} 
	        catch (ReferenceNotFoundException e) {
	            e.printStackTrace();
	        }

	    }

}
