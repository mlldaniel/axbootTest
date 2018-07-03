package smartfactory.domain.wnr;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InventoryVo {
    private String wnrDate, categoryName, subcategoryName;
    private BigDecimal sStock;

}
