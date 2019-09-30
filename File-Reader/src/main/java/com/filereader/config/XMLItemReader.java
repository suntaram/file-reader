package com.filereader.config;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import com.filereader.model.Customer;
import com.filereader.model.Record;
import com.filereader.model.Records;
import com.filereader.recordfilter.RecordFilter;

public class XMLItemReader {
	
	public static List<Customer> readFile(final String filePath) {
		List<Customer> customerDataList = new ArrayList<>();
		List<Customer> invalidEndBalanceList = new ArrayList<>();
        File file = new File(filePath);
        
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Records.class, Record.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Records records = (Records) jaxbUnmarshaller.unmarshal(file);
            List<Record> xmlcustomer = records.getRecords();
            System.out.println("size - " + xmlcustomer.size());
            for (Record xmlcustlist : xmlcustomer) {	
                Customer customer = new Customer();
                long referenceNo = xmlcustlist.getReference();
                customer.setReference(referenceNo);
                customer.setAccountNumber(xmlcustlist.getAccountNumber());
                customer.setDesc(xmlcustlist.getDescription());
                customer.setStartBal(new BigDecimal(xmlcustlist.getStartBalance()));
                customer.setMutation(new BigDecimal(xmlcustlist.getMutation()));
                customer.setEndBal(new BigDecimal(xmlcustlist.getEndBalance()));
                if (RecordFilter.findReference(customerDataList, referenceNo) == null) {
                    if (RecordFilter.validateEndBalance(customer)) {
                    	customerDataList.add(customer);
                    } else {
                    	invalidEndBalanceList.add(customer);
                    }

                } else {
                	invalidEndBalanceList.add(customer);
                }

            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return invalidEndBalanceList;
            }
        }
	
