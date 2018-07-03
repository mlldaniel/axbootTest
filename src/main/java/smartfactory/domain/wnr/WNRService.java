package smartfactory.domain.wnr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import smartfactory.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import smartfactory.domain.customer.WarehousingCustomer;
import smartfactory.domain.customer.WarehousingCustomerService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class WNRService extends BaseService<WNR, WNR.WNRId> {
    private WNRRepository wnrRepository;

    @Inject
    public WNRService(WNRRepository wnrRepository) {
        super(wnrRepository);
        this.wnrRepository = wnrRepository;
    }

    public Page<WNR> getPageWNR(RequestParams<WNR> requestParams, Pageable pageable) {
        String categoryCd = requestParams.getString("searchCategoryCd", "");
        String searchDate = requestParams.getString("searchWnrDate", "");
        String wnrSep = requestParams.getString("searchWnrSep", "");

        return wnrRepository.findByWnrDateAndCategoryCdAndWnrSepContainingOrderByWnrDateDesc(searchDate, categoryCd, wnrSep, pageable);
    }

    public Page<WNR> getPage(RequestParams<WNR> requestParams, Pageable pageable) {
        String categoryCd = requestParams.getString("searchCategoryCd", "");
        String subcategoryCd = requestParams.getString("searchSubcategoryCd", "");
        String customerName = requestParams.getString("searchWarehousingCustomer", "");
        String carCd = requestParams.getString("searchWarehousingCarCd", "");
        String searchStartDate = requestParams.getString("searchStartDate", "");
        String searchEndDate = requestParams.getString("searchEndDate", "");

//        if (!(categoryCd.equals("ALL")) && subcategoryCd.equals("ALL") && customerName.equals("ALL")) {
//
//            return wnrRepository.findWnrByCategoryCd(searchStartDate, searchEndDate, categoryCd, pageable);
//        }
//        else if (!(categoryCd.equals("ALL")) && !(customerName.equals("ALL")) && subcategoryCd.equals("ALL")) {
//
//            return wnrRepository.findWnrByCategoryCdAndCustomerName(searchStartDate, searchEndDate, categoryCd, customerName, pageable);
//        }
//        else if (!(categoryCd.equals("ALL")) && !(subcategoryCd.equals("ALL")) && customerName.equals("ALL")) {
//
//            return wnrRepository.findWnrByCategoryCdAndSubcategoryCd(searchStartDate, searchEndDate, categoryCd, subcategoryCd, pageable);
//        }
//        else if (!(categoryCd.equals("ALL")) && !(subcategoryCd.equals("ALL")) && customerName.equals("ALL")) {
//
//            return wnrRepository.findWnrByCategoryCdAndSubcategoryCdAndCustomerName(searchStartDate, searchEndDate, categoryCd, subcategoryCd, customerName, pageable);
//        }
//        else if (!(customerName.equals("ALL")) && categoryCd.equals("ALL") && subcategoryCd.equals("ALL")) {
//
//            return wnrRepository.findWnrByCustomerName(searchStartDate, searchEndDate, customerName, pageable);
//        }
//
//        return wnrRepository.findWnrByAll(searchStartDate, searchEndDate, pageable);
        return wnrRepository.findByAllLike(searchStartDate, searchEndDate, categoryCd, subcategoryCd, customerName, carCd, pageable);
    }

    public List<WNR> getListSum(RequestParams<WNR> requestParams) {
        String categoryCd = requestParams.getString("searchCategoryCd", "");
        String subcategoryCd = requestParams.getString("searchSubcategoryCd", "");
        String customerName = requestParams.getString("searchWarehousingCustomer", "");
        String carCd = requestParams.getString("searchWarehousingCarCd", "");
        String searchStartDate = requestParams.getString("searchStartDate", "");
        String searchEndDate = requestParams.getString("searchEndDate", "");

        return wnrRepository.findByAllLikeList(searchStartDate, searchEndDate, categoryCd, subcategoryCd, customerName, carCd);
    }

    public List<WNR> getList(String wndDate, String categoryCd, String subcategoryCd, String wnrSep) {
        return wnrRepository.findByWnrDateAndCategoryCdAndSubcategoryCdAndWnrSepOrderByWnrSeqDesc(wndDate, categoryCd, subcategoryCd, wnrSep);
    }
}