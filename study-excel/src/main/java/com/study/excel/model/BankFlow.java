package com.study.excel.model;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author walle
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bank_flow")
public class BankFlow {

    @Id
    private Integer bankId;

    private LocalDate startDate;

    private String description;

    private String refNumber;

    private BigDecimal amount;

    private Integer amountType;

    private BigDecimal balance;

    private Integer balanceType;

    private LocalDateTime createTime;
}
