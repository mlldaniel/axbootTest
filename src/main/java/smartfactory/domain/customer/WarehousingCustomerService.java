package smartfactory.domain.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import smartfactory.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;

import java.util.List;

@Service
public class WarehousingCustomerService extends BaseService<WarehousingCustomer, WarehousingCustomer.WarehousingCustomerId> {
    private WarehousingCustomerRepository customerRepository;

    @Inject
    public WarehousingCustomerService(WarehousingCustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    public Page<WarehousingCustomer> getPage(RequestParams<WarehousingCustomer> requestParams, Pageable pageable) {

        String categoryCd = requestParams.getString("search_category", "ALL");
        String customerName = requestParams.getString("customer_name", "");
        String carCd = requestParams.getString("car_cd", "");

        Page<WarehousingCustomer> pages = null;

        if (categoryCd.equals("ALL")) {

                pages = customerRepository.findByCustomerNameContainingAndCarCdContainingOrderByCustomerNameAscCarCdAsc(customerName, carCd, pageable);

        } else {

                pages = customerRepository.findByCategoryCdAndCustomerNameContainingAndCarCdContainingOrderByCustomerNameAscCarCdAsc(categoryCd, customerName, carCd, pageable);

        }

        return pages;
    }

    public List<WarehousingCustomer> getList(RequestParams<WarehousingCustomer> requestParams) {

        String categoryCd = requestParams.getString("search_category", "ALL");
        String subcategoryCd = requestParams.getString("search_subcategory", "ALL");
        String customerName = requestParams.getString("customer_name", "");
        String carCd = requestParams.getString("car_cd", "");

        List<WarehousingCustomer> list;

        if (categoryCd.equals("ALL")) {
            list = customerRepository.findByCustomerNameContainingOrCarCdContainingOrderByCustomerNameAscCarCdAsc(customerName, carCd);
        } else {
            if (subcategoryCd.equals("ALL")) {
                list = customerRepository.findByCategoryCdAndCustomerNameContainingCarCdContainingOrderByCustomerNameAscCarCdAsc(categoryCd, customerName, carCd);
            } else {
                if (customerName.equals("")) {
                    list = customerRepository.findByCategoryCdAndSubcategoryCdOrderByCustomerNameAscCarCdAsc(categoryCd, subcategoryCd);
                } else {
                    list = customerRepository.findByCategoryCdAndSubcategoryCdAndCustomerNameOrderByCarCdAsc(categoryCd, subcategoryCd, customerName);
                }
            }
        }

        return list;
    }

    public List<WarehousingCustomer> findAll() {
        return customerRepository.findAll();
    }
}