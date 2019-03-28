package com.study.drools.model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author walle
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "risk_rule")
public class RiskRule {

    @Id
    private Integer id;

    private String name;

    private String description;

    private Integer category;

    private Integer approveStatus;

    private Integer status;

    private Integer level;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}