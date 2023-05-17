package com.report.efdms.service;

import com.report.efdms.entity.InvoiceReport;
import com.report.efdms.entity.ZeroReport;
import com.report.efdms.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceReportService {
    @Autowired
    InvoiceRepo invoiceRepo;
    public List<InvoiceReport> getAllInvoiceReportS(){
        List<InvoiceReport> list= invoiceRepo.getAllInvoiceReport();
        return list;
    }
}
