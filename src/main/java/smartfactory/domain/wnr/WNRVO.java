package smartfactory.domain.wnr;

import com.chequer.axboot.core.utils.ModelMapperUtils;
import com.chequer.axboot.core.vo.BaseVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class WNRVO extends BaseVO {
    private String wnrDate;
    private String categoryCd;
    private String subcategoryCd;
    private String categoryName;
    private String subcategoryName;
    private BigDecimal inventoryAmount;
    private BigDecimal warehousingAmount;
    private BigDecimal releaseAmount;
    private BigDecimal remainAmount;

//    public static WNRVO of(WNR wnr) {
//        WNRVO wnrVO = ModelMapperUtils.map(wnr, WNRVO.class);
//        return wnrVO;
//    }
}