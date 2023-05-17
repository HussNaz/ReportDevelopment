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
public class EmployeeInfoEntity {

    @Id
    private long user_id;
    private long role_id;
}
