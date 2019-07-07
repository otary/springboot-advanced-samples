package cn.chenzw.springboot.dubbo.api.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BillDto implements Serializable {

    private static final long serialVersionUID = -1L;

    private Long id;

    /**
     * 客户名称
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 欠费金额
     */
    private BigDecimal oweMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOweMoney() {
        return oweMoney;
    }

    public void setOweMoney(BigDecimal oweMoney) {
        this.oweMoney = oweMoney;
    }


    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "BillDto{" + "id=" + id + ", name='" + name + '\'' + ", idCard='" + idCard + '\'' + ", oweMoney="
                + oweMoney + '}';
    }
}
