package com.report.efdms.respondBodyEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ZeroReportRes {
    @Id
    private long sl;
    private long commissionarate;
    private long division;
    private long circle;
    private String startDate;
    private String endDate;

}
