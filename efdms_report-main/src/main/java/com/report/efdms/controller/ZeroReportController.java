package com.report.efdms.controller;

import com.report.efdms.respondBodyEntity.ZeroReportRes;
import com.report.efdms.service.ZeroReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/zero")
public class ZeroReportController {
    @Autowired
    ZeroReportService zeroReportService;

    @PostMapping("/report")
    ResponseEntity<byte[]> getAllzeroReportSS(@RequestBody ZeroReportRes zeroReportRes){

        long commissionerate= zeroReportRes.getCommissionarate();
        long division=zeroReportRes.getDivision();
        long circle= zeroReportRes.getCircle();
        String start_date=zeroReportRes.getStartDate();
        String end_date=zeroReportRes.getEndDate();

        return zeroReportService.getAllzeroReportS(commissionerate,division,circle, start_date, end_date);
    }
}
