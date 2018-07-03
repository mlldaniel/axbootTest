package smartfactory.domain.customer;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehousingCustomerRepository extends AXBootJPAQueryDSLRepository<WarehousingCustomer, WarehousingCustomer.WarehousingCustomerId> {
    Page<WarehousingCustomer> findByCustomerNameContainingAndCarCdContainingOrderByCustomerNameAscCarCdAsc(String customerName, String carCd, Pageable pageable);

    @Query(value="select t from WarehousingCustomer t where t.categoryCd = :categoryCd and (t.customerName like concat('%', :customerName, '%') and t.carCd like concat('%', :carCd, '%'))" +
            "order by t.customerName asc, t.carCd asc")
    Page<WarehousingCustomer> findByCategoryCdAndCustomerNameContainingAndCarCdContainingOrderByCustomerNameAscCarCdAsc(@Param(value="categoryCd")String categoryCd,
                                                                                                                     @Param(value="customerName") String customerName,
                                                                                                                     @Param(value="carCd") String carCd, Pageable pageable);

    List<WarehousingCustomer> findByCustomerNameContainingOrCarCdContainingOrderByCustomerNameAscCarCdAsc(String customerName, String carCd);

    @Query(value="select t from WarehousingCustomer t where t.categoryCd = :categoryCd and (t.customerName like concat('%', :customerName, '%') or t.carCd like concat('%', :carCd, '%'))" +
            "order by t.customerName asc, t.carCd asc")
    List<WarehousingCustomer> findByCategoryCdAndCustomerNameContainingCarCdContainingOrderByCustomerNameAscCarCdAsc(@Param(value="categoryCd")String categoryCd,
                                                                                                                     @Param(value="customerName") String customerName,
                                                                                                                     @Param(value="carCd") String carCd);
    List<WarehousingCustomer> findByCategoryCdAndSubcategoryCdOrderByCustomerNameAscCarCdAsc(String categoryCd, String subcategoryCd);

    List<WarehousingCustomer> findByCategoryCdAndSubcategoryCdAndCustomerNameOrderByCarCdAsc(String categoryCd, String subcategoryCd, String customerName);
}
