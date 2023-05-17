package com.report.efdms.controller;

import com.report.efdms.entity.EmployeeInfoEntity;
import com.report.efdms.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeInfoController {

    @Autowired
    EmployeeInfoService employeeInfoService;

    @GetMapping("/all-employees")
//    @RequestMapping(method = RequestMethod.GET)
    List<EmployeeInfoEntity> getAllEmployees(){
        return employeeInfoService.getAllEmployees();
    }



    @GetMapping("/employee-report")
    public void createReport(){

         employeeInfoService.createReport();
    }

}
