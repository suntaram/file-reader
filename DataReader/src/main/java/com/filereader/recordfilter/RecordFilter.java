package com.filereader.recordfilter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.filereader.model.Customer;



public class RecordFilter {
	
	public static Optional<String> findExtension(String fileName)
    {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));

    }
	public static Customer findReference(List<Customer> customerData, final long reference)
    {
        Customer customerReport = null;
        if(customerData != null && !customerData.isEmpty()) {
            customerReport = customerData.stream()
                    .filter(report -> reference == report.getReference())
                    .findAny()
                    .orElse(null);
        }
        return customerReport;
    }
    public static boolean validateEndBalance(Customer customerData){
        boolean bal = false;
        BigDecimal addition = new BigDecimal(0);
        addition=addition.add(customerData.getStartBal().add(customerData.getMutation()));
        if(addition.equals(customerData.getEndBal())){
            bal = true;
        }
        else{
        	customerData.setReason("End balance is invalid");
        }
        return bal;
    }

}
