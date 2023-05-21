package com.report.efdms.controller;

import com.report.efdms.entity.ZeroReport;
import com.report.efdms.respondBodyEntity.ZeroReportRes;
import com.report.efdms.service.ZeroReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/zero")
public class ZeroReportController {
    @Autowired
    ZeroReportService zeroReportService;

    @PostMapping("/report")
    ResponseEntity<byte[]> getAllzeroReportSS(@RequestBody ZeroReportRes zeroReportRes){
        long comm=0;
        long commissionerate= zeroReportRes.getCommissionarate();
        long division=zeroReportRes.getDivision();
        long circle= zeroReportRes.getCircle();
        String start_date=zeroReportRes.getStartDate();
        String end_date=zeroReportRes.getEndDate();
        if(circle!=0)
            comm=circle;
        else if (division!=0) {
            comm=division;
        } else comm= commissionerate;

        System.out.println("Commissionerate="+commissionerate+"\nDivision="+division+
                "\n circle="+circle+"\n Start Date="+start_date+"\n End Date="+end_date+
                "\nComm="+comm);
        return zeroReportService.getAllzeroReportS(comm,start_date,end_date);
    }
}
