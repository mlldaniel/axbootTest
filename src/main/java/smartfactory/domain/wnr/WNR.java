package smartfactory.domain.wnr;

import smartfactory.domain.SimpleJpaModel;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "WNR_TB")
@Comment(value = "입출고관리")
@IdClass(WNR.WNRId.class)
@Alias("WNR")
public class WNR extends SimpleJpaModel<WNR.WNRId> {
    @Id
    @Column(name = "wnr_date", length = 8, nullable = false)
    @Comment(value = "입출고 날짜")
    private String wnrDate;

    @Id
    @Column(name = "category_cd", length = 2, nullable = false)
    @Comment(value = "품목코드")
    private String categoryCd;

    @Id
    @Column(name = "subcategory_cd", length = 2, nullable = false)
    @Comment(value = "품명코드")
    private String subcategoryCd;

    @Id
    @Column(name = "wnr_sep", length = 1, nullable = false)
    @Comment(value = "입출구분")
    private String wnrSep;

    @Id
    @Column(name = "wnr_seq", length = 1, nullable = false)
    @Comment(value = "시퀀스")
    private Integer wnrSeq;

    @Column(name = "category_name", length = 20, nullable = false)
    @Comment(value = "품목")
    private String categoryName;

    @Column(name = "subcategory_name", length = 20, nullable = false)
    @Comment(value = "품명")
    private String subcategoryName;

    @Column(name = "customer_name", length = 40)
    @Comment(value = "입고처명")
    private String customerName;

    @Column(name = "car_cd", length = 4)
    @Comment(value = "차량번호")
    private String carCd;

    @Column(name = "wnr_amount", precision = 10, scale = 1, nullable = false)
    @Comment(value = "입출고량")
    private BigDecimal wnrAmount;

    @Column(name = "wnr_price", precision = 10)
    @Comment(value = "단가")
    private Integer wnrPrice;

    @Override
    public WNRId getId() {
        return WNRId.of(wnrDate, categoryCd, subcategoryCd, wnrSep, wnrSeq);
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @RequiredArgsConstructor(staticName = "of")
    public static class WNRId implements Serializable {
        @NonNull
        private String wnrDate;

        @NonNull
        private String categoryCd;

        @NonNull
        private String subcategoryCd;

        @NonNull
        private String wnrSep;

        @NonNull
        private Integer wnrSeq;
    }
}