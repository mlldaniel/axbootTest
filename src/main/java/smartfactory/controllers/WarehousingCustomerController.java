package smartfactory.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import smartfactory.domain.customer.WarehousingCustomer;
import smartfactory.domain.customer.WarehousingCustomerService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/warehousing_customer")
public class WarehousingCustomerController extends BaseController {

    @Inject
    private WarehousingCustomerService customerInfoService;

    @RequestMapping(value="/page", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse page(RequestParams<WarehousingCustomer> requestParams, Pageable pageable) {
        Page<WarehousingCustomer> pages = customerInfoService.getPage(requestParams, pageable);
        return Responses.PageResponse.of(pages.getContent(), pages);
    }

    @RequestMapping(value="/list", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<WarehousingCustomer> requestParams) {
        List<WarehousingCustomer> list = customerInfoService.getList(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<WarehousingCustomer> request, @RequestParam(value = "bCheckInsert") boolean bCheckInsert) {
        if (bCheckInsert == true) {
            if (customerInfoService.findOne(
                    WarehousingCustomer.WarehousingCustomerId.of(
                    request.get(0).getCustomerName(),
                    request.get(0).getCarCd(),
                    request.get(0).getCategoryCd(),
                    request.get(0).getSubcategoryCd())) != null) {
                return ok("exist");
            }
        }
        customerInfoService.save(request);
        return ok();
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestBody WarehousingCustomer.WarehousingCustomerId id) {
        customerInfoService.delete(id);
        return ok();
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST, produces = APPLICATION_JSON)
    public List<WarehousingCustomer> findAllCustomers() {
        return customerInfoService.findAll();
    }

}