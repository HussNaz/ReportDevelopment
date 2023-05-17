package com.report.efdms.respondBodyEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InactiveDeviceRes {

    @Id
    private long sl;
    private long commision;
    private long division;
    private  long circle;
    private long area;
    private long holiday;
}
