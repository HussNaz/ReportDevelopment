package com.report.efdms.repository;

import com.report.efdms.entity.ZeroReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZeroReportRepo extends JpaRepository<ZeroReport,Long> {

    @Query(value = "WITH t1(office_id, parent_office_id) AS (\n" +
            "  -- Anchor member.\n" +
            "  SELECT office_id,\n" +
            "         parent_office_id\n" +
            "  FROM   r_office\n" +
            "  WHERE  parent_office_id =:comm  \n" +
            "  UNION ALL\n" +
            "  -- Recursive member.\n" +
            "  SELECT t2.office_id,\n" +
            "         t2.parent_office_id\n" +
            "  FROM   r_office t2, t1\n" +
            "  WHERE  t2.parent_office_id = t1.office_id\n" +
            ")\n" +
            "\n" +
            "select ROW_NUMBER() OVER(ORDER BY tp.taxpayer_bin) AS sl,  ind.bin ,\n" +
            "tp.taxpayer_name_en, \n" +
            "ot.outlet_name_en ,\n" +
            "ot.address_line1  from t1, t_invoice_data ind, r_outlet ot, t_taxpayer tp\n" +
            "where ind.total_sale_amount=0 \n" +
            "and ind.create_date BETWEEN :start_date and :end_date",nativeQuery = true)
    List<ZeroReport> getAllZeroReport(@Param("comm") long comm,
                                      @Param("start_date") String start_date,
                                      @Param("end_date") String end_date);


    @Query(value = "  select office_name_en from r_office where office_id=:officeId",
            nativeQuery = true)
    String officeName(@Param("officeId") long office);

}
