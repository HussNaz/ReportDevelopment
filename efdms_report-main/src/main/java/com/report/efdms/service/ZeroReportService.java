package com.report.efdms.service;

import com.report.efdms.entity.InactiveDevice;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ZeroReportService {
    @Autowired
    ZeroReportRepo zeroReportRepo;


     public ResponseEntity<byte[]> getAllzeroReportS(long comm, String start_date, String end_date){
         try {

             String filePath =
                     ResourceUtils.getFile("classpath:ZeroReport.jrxml")
                             .getAbsolutePath();
             List<ZeroReport> list = null;


             list = zeroReportRepo.getAllZeroReport(comm,start_date,end_date);


             System.out.println(list);

             JRBeanCollectionDataSource dataSource=
                     new JRBeanCollectionDataSource(list);

             Map<String, Object> parameters = new HashMap<String, Object>();
             parameters.put("Parameter1",comm);

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
