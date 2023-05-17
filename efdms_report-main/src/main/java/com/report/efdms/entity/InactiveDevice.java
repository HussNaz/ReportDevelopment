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
public class InactiveDevice {
    @Id
    private long sl;
    private String name;
    private String bin;
    private long deviceid;
    private String status;
}
