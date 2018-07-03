package smartfactory.domain.wnr;

import com.chequer.axboot.core.annotations.Comment;
import com.chequer.axboot.core.utils.ModelMapperUtils;
import com.chequer.axboot.core.vo.BaseVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class WNRGridVO extends BaseVO {
    private String wnrDate;
    private String categoryCd;
    private String subcategoryCd;
    private String wnrSep;
    private Integer wnrSeq;
    private String categoryName;
    private String subcategoryName;
    private String customerName;
    private String carCd;
    private BigDecimal wnrAmount;
    private Integer wnrPrice;
    private String wnrSepName;

    public static WNRGridVO of(WNR wnr) {
        WNRGridVO wnrVO = ModelMapperUtils.map(wnr, WNRGridVO.class);
        return wnrVO;
    }
}