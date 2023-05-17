package com.report.efdms.controller;

import com.report.efdms.entity.InactiveDevice;
import com.report.efdms.respondBodyEntity.InactiveDeviceRes;
import com.report.efdms.service.InactiveDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class InactiveDeviceController {
    @Autowired
    InactiveDeviceService inactiveDeviceService;


    @PostMapping("/inactiveDevice")
    public List<InactiveDevice> getAllDeviceSS(@RequestBody InactiveDeviceRes  inactiveDeviceRes){
        System.out.println("I am ok");
        long commison=inactiveDeviceRes.getCommision();
        long division=inactiveDeviceRes.getDivision();
        long circle=inactiveDeviceRes.getCircle();
        long area=inactiveDeviceRes.getArea();
        long holiday=inactiveDeviceRes.getHoliday();
        List<InactiveDevice> list= inactiveDeviceService.getAllDeviceS(commison,division,circle,area,holiday);
        System.out.println(list);

        return list;
    }

    @PostMapping("/inactiveDeviceReport")
    public ResponseEntity<byte[]> getInacticeDeviceReportSS(@RequestBody InactiveDeviceRes inactiveDeviceRes){
        long commison=inactiveDeviceRes.getCommision();
        long division=inactiveDeviceRes.getDivision();
        long circle=inactiveDeviceRes.getCircle();
        long area=inactiveDeviceRes.getArea();
        long holiday=inactiveDeviceRes.getHoliday();
        System.out.println(inactiveDeviceRes);
        return inactiveDeviceService.getInacticeDeviceReport(commison,division,circle,area,holiday);
    }


}
