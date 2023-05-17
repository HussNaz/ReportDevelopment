package com.report.efdms.repository;

import com.report.efdms.entity.InactiveDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InactiveDeviceRepo extends JpaRepository<InactiveDevice,Long> {

    @Query(value = "WITH t1(office_id, parent_office_id) AS (\n" +
            "  -- Anchor member.\n" +
            "  SELECT office_id,\n" +
            "         parent_office_id\n" +
            "  FROM   r_office\n" +
            "  WHERE  parent_office_id = :comm  \n" +
            "  UNION ALL\n" +
            "  -- Recursive member.\n" +
            "  SELECT t2.office_id,\n" +
            "         t2.parent_office_id\n" +
            "  FROM   r_office t2, t1\n" +
            "  WHERE  t2.parent_office_id = t1.office_id\n" +
            ")\n" +
            "SELECT  ROW_NUMBER() OVER(ORDER BY a.taxpayer_bin) AS sl,\n" +
            "        a.taxpayer_name_en as name, a.taxpayer_bin as bin,\n" +
            "       d.device_id as deviceId,\n" +
            "       case when d.is_activated='Y' then 'Active' else 'Inactive' end as status \n" +
            "FROM   t1 inner join t_device d on d.office_id = t1.office_id\n" +
            "    inner join r_outlet ot on d.outlet_id = ot.outlet_id\n" +
            "     inner join t_taxpayer a  on a.taxpayer_bin=d.bin\n" +
            "     inner join r_office r on d.office_id=r.office_id\n" +
            "     where (case when ot.area_market=:area then 1 else 0 end )=:a \n" +
            "     and (case when ot.weekend=:week then 1 else 0 end)=:b\n" +
            "     and   d.is_activated ='Y' \n" +
            "     and (case when r.office_id=:circle then 1 else 0 end)=:c", nativeQuery = true)
    List<InactiveDevice> getAllInactiveDevice(@Param("comm") long commission,
                                              @Param("circle" ) long circle,
                                              @Param("a") long a,
                                              @Param("b") long b,
                                              @Param("c") long c,
                                              @Param("area") long area,
                                              @Param("week") long holiday);
}
