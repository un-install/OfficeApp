package boot.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Validated
public class OfficeRequest {
    @NotNull
    @Min(value = 10)
    private BigDecimal office;
    private String city;
    private String region;
    @NotNull
    @Min(101)
    private BigDecimal mgr;
    private BigDecimal target;
    @NotNull
    @Max(value = 99999)
    private BigDecimal sales;

    public OfficeRequest() {
    }

    public BigDecimal getOffice() {
        return office;
    }

    public void setOffice(BigDecimal office) {
        this.office = office;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getMgr() {
        return mgr;
    }

    public void setMgr(BigDecimal mgr) {
        this.mgr = mgr;
    }

    public BigDecimal getTarget() {
        return target;
    }

    public void setTarget(BigDecimal target) {
        this.target = target;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }
}
