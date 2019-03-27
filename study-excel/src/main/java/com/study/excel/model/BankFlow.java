package com.study.excel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author walle
 */
@Setter
@Getter
@Table(name = "bank_flow")
public class BankFlow {

    @Id
    private Integer bankId;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    private String description;

    private String refNumber;

    private BigDecimal amount;

    private String amountType;

    private BigDecimal balance;

    private String balanceType;

    @JsonIgnore
    private Date createTime;
}
