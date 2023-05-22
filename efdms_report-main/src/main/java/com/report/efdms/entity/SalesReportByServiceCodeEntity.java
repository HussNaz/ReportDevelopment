package com.report.efdms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SalesReportByServiceCodeEntity {
    @Id
    private long sl;
    private String bin;
    private String taxpayerName;
    private String serviceCode;
    private String serviceDescription;
    private double supplyRate;
    private double supplyValue;
    private double applicableTax;

}
