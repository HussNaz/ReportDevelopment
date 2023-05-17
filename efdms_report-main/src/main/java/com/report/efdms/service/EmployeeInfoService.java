package com.report.efdms.service;

import com.report.efdms.entity.EmployeeInfoEntity;
import com.report.efdms.repository.EmployeeInfoRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeInfoService {

    @Autowired
    EmployeeInfoRepo employeeInfoRepo;


    public List<EmployeeInfoEntity> getAllEmployees(){

        List<EmployeeInfoEntity> empList = employeeInfoRepo.getAllEmployees();
        System.out.println(empList);
        return empList;
    }

    public void createReport() {
        try {
            List<EmployeeInfoEntity> empList = employeeInfoRepo.getAllEmployees();

            String filePath = "D:\\EFDMS\\Report_Development\\efdms_report\\src\\main\\resources\\Employee.jrxml";

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("reportName", "Department wise Employee");

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(empList);

            JasperReport report = JasperCompileManager.compileReport(filePath);

            JasperPrint print =
                    JasperFillManager.fillReport(report, parameters, dataSource);

            JasperExportManager.exportReportToPdfFile(print, "D:\\EFDMS\\Report_Development\\Exported-Reports\\Employee.pdf");

            System.out.println("Report Created...");
        } catch (Exception e) {
            System.out.println("Exception while creating report");
        }
    }
}
