package com.report.efdms.service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.report.efdms.entity.InactiveDevice;
import com.report.efdms.repository.InactiveDeviceRepo;
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
public class InactiveDeviceService {

    @Autowired
    InactiveDeviceRepo inactiveDeviceRepo;
    int a,b,c=0;
    long comm=0;

    public List<InactiveDevice> getAllDeviceS(long commision, long division, long circle,
                                              long area, long holiday){
        if(division!=0)
            comm=division;
        else comm=commision;

        if(area==0)
            a=0;
        else a=1;

        if(holiday==0)
            b=0;
        else b=1;

        if(circle==0)
            c=0;
        else c=1;

        List<InactiveDevice> list= inactiveDeviceRepo.getAllInactiveDevice(comm,circle,a,b,c,area,holiday);
        System.out.println(list);
        return list;
    }

    public ResponseEntity<byte[]> getInacticeDeviceReport(long commision, long division, long circle,
                                                          long area, long holiday){
            try {

                String filePath =
                        ResourceUtils.getFile("classpath:inactiveReport.jrxml")
                        .getAbsolutePath();
                List<InactiveDevice> list = null;

                if(area==0)
                    a=0;
                else a=1;

                if(holiday==0)
                    b=0;
                else b=1;

                if(division!=0)
                    comm=division;
                else comm=commision;

                if(circle==0)
                    c=0;
                else c=1;

                    list = inactiveDeviceRepo.getAllInactiveDevice(comm,circle, a, b,c, area, holiday);


                System.out.println(list);
                JRBeanCollectionDataSource dataSource=
                        new JRBeanCollectionDataSource(list);

                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("Parameter1",commision);

                JasperReport report= JasperCompileManager.compileReport(filePath);

                JasperPrint print=
                        JasperFillManager.fillReport(report, parameters ,dataSource);

                byte[] bytes= JasperExportManager.exportReportToPdf(print);

                HttpHeaders httpHeaders= new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_PDF);
                httpHeaders.setContentDispositionFormData("filename","inactiveReport.pdf");


                return new ResponseEntity<byte[]>(bytes,httpHeaders,HttpStatus.OK);
            } catch (Exception e){

                System.out.println("Error getting file");
                System.out.println(e);
                return new  ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

}
