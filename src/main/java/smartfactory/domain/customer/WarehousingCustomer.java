package smartfactory.domain.customer;

import smartfactory.domain.SimpleJpaModel;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "WAREHOUSING_CUSTOMER_TB")
@Comment(value = "거래처정보")
@IdClass(WarehousingCustomer.WarehousingCustomerId.class)
@Alias("WarehousingCustomer")
public class WarehousingCustomer extends SimpleJpaModel<WarehousingCustomer.WarehousingCustomerId> {
    @Id
    @Column(name = "customer_name", length = 40, nullable = false)
    @Comment(value = "")
    private String customerName;

    @Id
    @Column(name = "car_cd", length = 4, nullable = false)
    @Comment(value = "")
    private String carCd;

    @Id
    @Column(name = "category_cd", length = 2, nullable = false)
    @Comment(value = "")
    private String categoryCd;

    @Id
    @Column(name = "subcategory_cd", length = 2, nullable = false)
    @Comment(value = "")
    private String subcategoryCd;

    @Column(name = "corporatereg_no", length = 12)
    @Comment(value = "")
    private String corporateregNo;

    @Column(name = "zip_cd", length = 5)
    @Comment(value = "")
    private String zipCd;

    @Column(name = "address_name", length = 200)
    @Comment(value = "")
    private String addressName;

    @Column(name = "phone_no", length = 20)
    @Comment(value = "")
    private String phoneNo;

    @Column(name = "category_name", length = 20)
    @Comment(value = "")
    private String categoryName;

    @Column(name = "subcategory_name", length = 20)
    @Comment(value = "")
    private String subcategoryName;

    @Override
    public WarehousingCustomerId getId() {
        return WarehousingCustomerId.of(customerName, carCd, categoryCd, subcategoryCd);
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @RequiredArgsConstructor(staticName = "of")
    public static class WarehousingCustomerId implements Serializable {
        @NonNull
        private String customerName;

        @NonNull
        private String carCd;

        @NonNull
        private String categoryCd;

        @NonNull
        private String subcategoryCd;
    }
}