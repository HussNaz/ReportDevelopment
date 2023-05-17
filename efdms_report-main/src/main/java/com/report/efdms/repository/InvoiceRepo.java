package com.report.efdms.repository;

import com.report.efdms.entity.InvoiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepo extends JpaRepository<InvoiceReport,Long> {

    @Query(value = "SELECT \n" +
            "ROW_NUMBER() OVER(ORDER BY t_invoice_data.invoice_no) AS SL,\n" +
            "t_invoice_data.invoice_no , t_invoice_data.create_date as \"DateT\" ,\n" +
            "t_invoice_data.bin as \"BIN\" , t_taxpayer.taxpayer_name_en as \"Name\", t_invoice_data.device_id as \"DeviceID\",\n" +
            "t_invoice_data.total_sale_amount ,t_invoice_data.total_sd_amount as SD\n" +
            ", t_invoice_data.total_vat_amount as VAT , (t_invoice_data.total_sd_amount+t_invoice_data.total_vat_amount) as Total,\n" +
            "t_invoice_data.mode_of_payment  FROM t_invoice_data inner join t_taxpayer\n" +
            "on t_invoice_data.bin= t_taxpayer.taxpayer_bin ", nativeQuery = true)
    List<InvoiceReport> getAllInvoiceReport();
}
