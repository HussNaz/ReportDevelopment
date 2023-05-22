package com.report.efdms.service;

import com.report.efdms.entity.ZeroReport;
import com.report.efdms.repository.ZeroReportRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZeroReportService {
    @Autowired
    ZeroReportRepo zeroReportRepo;


     public ResponseEntity<byte[]> getAllzeroReportS(long commissionerate, long division, long circle, String start_date, String end_date){
         try {

             String filePath =
                     ResourceUtils.getFile("classpath:ZeroReport.jrxml")
                             .getAbsolutePath();


             System.out.println("Start Date="+start_date+"\n End Date="+end_date+
                     "\nComm="+ commissionerate);
             List<ZeroReport>  list = zeroReportRepo.getAllZeroReport(commissionerate,start_date,end_date);


             System.out.println(list);

             JRBeanCollectionDataSource dataSource=
                     new JRBeanCollectionDataSource(list);

             String CommissionarateP= zeroReportRepo.officeName(commissionerate);
             String divisionP= zeroReportRepo.officeName(division);
             String circleP= zeroReportRepo.officeName(circle);

             Map<String, Object> parameters = new HashMap<String, Object>();
             parameters.put("Commissionarate",CommissionarateP);
             parameters.put("division",divisionP);
             parameters.put("circle",circleP);
             parameters.put("start_date",start_date);
             parameters.put("end_date",end_date);

             JasperReport report= JasperCompileManager.compileReport(filePath);

             JasperPrint print=
                     JasperFillManager.fillReport(report, parameters ,dataSource);

             byte[] bytes= JasperExportManager.exportReportToPdf(print);

             HttpHeaders httpHeaders= new HttpHeaders();
             httpHeaders.setContentType(MediaType.APPLICATION_PDF);
             httpHeaders.setContentDispositionFormData("filename","ZeroReport.pdf");


             return new ResponseEntity<byte[]>(bytes,httpHeaders, HttpStatus.OK);
         } catch (Exception e){

             System.out.println("Error getting file");
             System.out.println(e);
             return new  ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }
}
