package com.report.efdms.controller;

import com.report.efdms.entity.InvoiceReport;
import com.report.efdms.entity.ZeroReport;
import com.report.efdms.service.InvoiceReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/invoice")
public class InvoiceReportController {
    @Autowired
    InvoiceReportService invoiceReportService;

    @GetMapping("/report")
    List<InvoiceReport> getAllInvoiceReportSS(){
        List<InvoiceReport> list=invoiceReportService.getAllInvoiceReportS();
        System.out.println(list);
        return list;
    }
}
