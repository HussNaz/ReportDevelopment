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
public class ZeroReport {
    @Id
    private long sl;

    private String bin;
    private String taxpayer_name_en;
    private String outlet_name_en;

    private String  address_line1;


}
