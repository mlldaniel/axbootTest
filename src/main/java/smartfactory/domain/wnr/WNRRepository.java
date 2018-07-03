package smartfactory.domain.wnr;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface WNRRepository extends AXBootJPAQueryDSLRepository<WNR, WNR.WNRId> {
    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' " +
            "and t.categoryName like concat('%', :categoryName, '%') and t.subcategoryName like concat('%', :subcategoryName, '%') " +
            "and t.customerName like concat('%', :customerName, '%') and t.carCd like concat('%', :carCd, '%') " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd " +
            "order by t.wnrDate desc, t.customerName asc, t.carCd asc")
    Page<WNR> findByAllLike(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate,
                            @Param(value="categoryName") String categoryName, @Param(value="subcategoryName") String subcategoryName,
                            @Param(value="customerName") String customerName, @Param(value="carCd") String carCd,
                            Pageable pageable);

    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' " +
            "and t.categoryName like concat('%', :categoryName, '%') and t.subcategoryName like concat('%', :subcategoryName, '%') " +
            "and t.customerName like concat('%', :customerName, '%') and t.carCd like concat('%', :carCd, '%') " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd " +
            "order by t.wnrDate desc, t.customerName asc, t.carCd asc")
    List<WNR> findByAllLikeList(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate,
                                @Param(value="categoryName") String categoryName, @Param(value="subcategoryName") String subcategoryName,
                                @Param(value="customerName") String customerName, @Param(value="carCd") String carCd);


    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd")
    Page<WNR> findWnrByAll(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, Pageable pageable);

    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' and t.categoryCd=:categoryCd " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd")
    Page<WNR> findWnrByCategoryCd(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="categoryCd") String categoryCd, Pageable pageable);

    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' and t.customerName=:customerName " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd")
    Page<WNR> findWnrByCustomerName(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="customerName") String customerName, Pageable pageable);

    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' and t.categoryCd=:categoryCd and t.subcategoryCd=:subcategoryCd " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd")
    Page<WNR> findWnrByCategoryCdAndSubcategoryCd(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="categoryCd") String categoryCd, @Param(value="subcategoryCd") String subcategoryCd, Pageable pageable);

    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' and t.categoryCd=:categoryCd and t.customerName=:customerName " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd")
    Page<WNR> findWnrByCategoryCdAndCustomerName(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="categoryCd") String categoryCd, @Param(value="customerName") String customerName, Pageable pageable);

    @Query(value="select t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd, sum(t.wnrAmount) as wnrAmount, sum(t.wnrAmount*t.wnrPrice) as wnrPrice from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' and t.categoryCd=:categoryCd and t.subcategoryCd=:subcategoryCd and t.customerName=:customerName " +
            "group by t.categoryCd, t.categoryName, t.subcategoryName, t.wnrDate, t.customerName, t.carCd")
    Page<WNR> findWnrByCategoryCdAndSubcategoryCdAndCustomerName(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="categoryCd") String categoryCd, @Param(value="subcategoryCd") String subcategoryCd, @Param(value="customerName") String customerName, Pageable pageable);



    List<WNR> findByWnrDateAndCategoryCdAndSubcategoryCdAndWnrSepOrderByWnrSeqDesc(
            String wndDate, String categoryCd, String subcategoryCd,String wnrSep);

    // 전체품목의 품명별 해당 기간의 총 입고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='W' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findAllCategoryWarehousing(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate);

    // 전체품목의 품명별 해당 기간의 총 출고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.wnrSep='R' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findAllCategoryRelease(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate);

    // 전체품목의 품명별 해당 기간의 총 재고량계산중 입고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate < :chkDate) and t.wnrSep='W' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findAllCategoryInventoryW(@Param(value="chkDate") String chkDate);

    // 전체품목의 품명별 해당 기간의 총 재고량계산중 출고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate < :chkDate) and t.wnrSep='R' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findAllCategoryInventoryR(@Param(value="chkDate") String chkDate);

    // 품목별 해당 기간의 총 입고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.categoryCd =:categoryCd and t.wnrSep='W' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findCategoryWarehousing(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="categoryCd") String categoryCd);

    // 품목별 해당 기간의 총 출고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.categoryCd =:categoryCd and t.wnrSep='R' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findCategoryRelease(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="categoryCd") String categoryCd);

    // 품목별 해당 기간의 총 재고량계산중 입고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate < :chkDate) and t.categoryCd =:categoryCd and t.wnrSep='W' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findCategoryInventoryW(@Param(value="chkDate") String chkDate, @Param(value="categoryCd") String categoryCd);

    // 품목별 해당 기간의 총 재고량계산중 출고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate < :chkDate) and t.categoryCd =:categoryCd and t.wnrSep='R' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findCategoryInventoryR(@Param(value="chkDate") String chkDate, @Param(value="categoryCd") String categoryCd);

    // 품목별 총 잔량계산중 입고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where t.categoryCd =:categoryCd and t.wnrSep='W' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findWnrInventoryCategoryCdInAmount(@Param(value="categoryCd") String categoryCd);

    // 품목별 총 잔량계산중 출고량
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where t.categoryCd =:categoryCd and t.wnrSep='R' " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    List<WNR> findWnrInventoryCategoryCdOutAmount(@Param(value="categoryCd") String categoryCd);

    // 전체 품목의 해당기간의 페이지개수를 구하기 위한 더미 데이터
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    Page<WNR> findAllDummyPage(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, Pageable pageable);

    // 품목별 해당기간의 페이지개수를 구하기 위한 더미 데이터
    @Query(value="select t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName, sum(t.wnrAmount) as wnrAmount from WNR t " +
            "where (t.wnrDate between :startDate and :endDate) and t.categoryCd =:categoryCd " +
            "group by t.categoryCd, t.subcategoryCd, t.categoryName, t.subcategoryName")
    Page<WNR> findCategoryDummyPage(@Param(value="startDate") String searchStartDate, @Param(value="endDate") String searchEndDate, @Param(value="categoryCd") String categoryCd, Pageable pageable);


    /* 입출고 검색 */
    Page<WNR> findByWnrDateAndCategoryCdAndWnrSepContainingOrderByWnrDateDesc(String wndDate, String categoryCd, String wndSep, Pageable pageable);
}
