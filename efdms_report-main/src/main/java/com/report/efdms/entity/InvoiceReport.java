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
public class InvoiceReport {

    @Id
    private long sl;
    private String invoice_no;
    private String DateT;
    private String bin;
    private  String Name;
    private String DeviceID;
    private double total_sale_amount;
    private double sd;
    private double vat;
    private double Total;
    private String mode_of_payment;
}
