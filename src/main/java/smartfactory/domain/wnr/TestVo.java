package smartfactory.domain.wnr;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TestVo {
    String wnrDate, categoryCd, categoryName, customerName, carCd, subcategoryName;
    BigDecimal wnrAmount;
    Long wnrPrice;
}
