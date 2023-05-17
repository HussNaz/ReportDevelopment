package com.report.efdms.repository;

import com.report.efdms.entity.EmployeeInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
//@EnableJpaRepositories({"com.report.efdms.*"})
public interface EmployeeInfoRepo extends JpaRepository<EmployeeInfoEntity,Long> {
    @Modifying
    @Transactional
    @Query(value = "select * from m_user_role",nativeQuery = true)
     List<EmployeeInfoEntity> getAllEmployees();
}
