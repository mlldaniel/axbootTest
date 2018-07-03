package smartfactory.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import smartfactory.domain.wnr.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api/v1/wnr")
public class WNRController extends BaseController {

    @Inject
    private WNRService wnrService;

    @Inject
    private WNRRepository wnrRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse page(RequestParams<WNR> requestParams, Pageable pageable) {

        Page<WNR> inPage = wnrService.getPage(requestParams, pageable);

        List<TestVo> testVo = new ArrayList<>();

        for (Object obj : inPage.getContent()) {
            TestVo vo = new TestVo();

            Object[] tempArr = (Object[]) obj;
            vo.setCategoryCd(tempArr[0].toString());
            vo.setCategoryName(tempArr[1].toString());
            vo.setSubcategoryName(tempArr[2].toString());
            vo.setWnrDate(tempArr[3].toString());
            vo.setCustomerName(tempArr[4].toString());
            vo.setCarCd(tempArr[5].toString());
            vo.setWnrAmount(new BigDecimal(tempArr[6].toString()));
            vo.setWnrPrice((new BigDecimal(tempArr[7].toString())).longValue());

            testVo.add(vo);

        }

        return Responses.PageResponse.of(testVo, inPage);
    }

    @RequestMapping(value = "/listSum", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getList(RequestParams<WNR> requestParams) {

        List<WNR> inPage = wnrService.getListSum(requestParams);

        List<TestVo> testVo = new ArrayList<>();

        for (Object obj : inPage) {
            TestVo vo = new TestVo();

            Object[] tempArr = (Object[]) obj;
            vo.setCategoryCd(tempArr[0].toString());
            vo.setCategoryName(tempArr[1].toString());
            vo.setSubcategoryName(tempArr[2].toString());
            vo.setWnrDate(tempArr[3].toString());
            vo.setCustomerName(tempArr[4].toString());
            vo.setCarCd(tempArr[5].toString());
            vo.setWnrAmount(new BigDecimal(tempArr[6].toString()));
            vo.setWnrPrice((new BigDecimal(tempArr[7].toString())).longValue());

            testVo.add(vo);

        }

        return Responses.ListResponse.of(testVo);
    }

    @RequestMapping(value = "/page_wnr", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse pageWnr(RequestParams<WNR> requestParams, Pageable pageable) {

        Page<WNR> inPage = wnrService.getPageWNR(requestParams, pageable);

        List<WNRGridVO> list = new ArrayList<>();

        for (WNR item : inPage.getContent()) {
            WNRGridVO gridVo = new WNRGridVO();
            gridVo = gridVo.of(item);

            if (item.getWnrSep().equals("W")) {
                gridVo.setWnrSepName("입고");
            } else {
                gridVo.setWnrSepName("출고");
            }

            list.add(gridVo);
        }

        return Responses.PageResponse.of(list, inPage);
    }

    @RequestMapping(value = "/list_remain", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse page(RequestParams<WNR> requestParams) {
        String categoryCd = requestParams.getString("searchCategoryCd", "ALL");

        List<WNR> inList = wnrRepository.findWnrInventoryCategoryCdInAmount(categoryCd);
        List<WNR> outList = wnrRepository.findWnrInventoryCategoryCdOutAmount(categoryCd);

        List<WNRVO> list = new ArrayList<>();

        int count = 0;

        if (categoryCd.equals("01")) {
            count = 3;
        } else if (categoryCd.equals("03")) {
            count = 1;
        } else {
            count = 2;
        }

        for (int i = 0; i < count; ++i) {
            WNRVO sub = new WNRVO();
            sub.setRemainAmount(BigDecimal.ZERO);
            sub.setSubcategoryCd(String.format("%02d", i + 1));
            list.add(sub);
        }

        for (Object obj : inList) {
            Object[] tempArr = (Object[]) obj;
            String subCategoryCd = (tempArr[1].toString());
            BigDecimal amount = new BigDecimal(tempArr[4].toString());

            List<WNRVO> res = list.stream().filter(a-> a.getSubcategoryCd().equals(subCategoryCd.toString())).collect(Collectors.toList());
            if (!res.isEmpty()) {
                res.get(0).setRemainAmount(amount.add(res.get(0).getRemainAmount()));
            }
        }

        for (Object obj : outList) {
            Object[] tempArr = (Object[]) obj;
            String subCategoryCd = (tempArr[1].toString());
            BigDecimal amount = new BigDecimal(tempArr[4].toString());

            List<WNRVO> res = list.stream().filter(a-> a.getSubcategoryCd().equals(subCategoryCd.toString())).collect(Collectors.toList());
            if (!res.isEmpty()) {
                res.get(0).setRemainAmount(res.get(0).getRemainAmount().subtract(amount));
            }
        }

        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "/page_inventory", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse pageInventory(RequestParams<WNR> requestParams, Pageable pageable) {
        String categoryCd = requestParams.getString("categoryCd", "ALL");
        String searchStartDate = requestParams.getString("searchStartDate", "");
        String searchEndDate = requestParams.getString("searchEndDate", "");

        Page<WNR> resDummy;
        List<WNR> resWaresouingList;
        List<WNR> resReleaseList;
        List<WNR> resInventoryListR;
        List<WNR> resInventoryListW;

        /*
            1. 해당 기간의 더미 페이지를 생성
            2. 해당 기간 검색 시작날짜 이전의 재고량 계산(입고, 출고 가져 옮)
            3. 해당 기간의 입고, 출고값을 가져 옮
            4. 잔량은 ==> 재고 + (입고 - 출고)
         */

        if (categoryCd.equals("ALL")) {
            resDummy = wnrRepository.findAllDummyPage(searchStartDate, searchEndDate, pageable);
            resWaresouingList = wnrRepository.findAllCategoryWarehousing(searchStartDate, searchEndDate);
            resReleaseList = wnrRepository.findAllCategoryRelease(searchStartDate, searchEndDate);
            resInventoryListR = wnrRepository.findAllCategoryInventoryR(searchStartDate);
            resInventoryListW = wnrRepository.findAllCategoryInventoryW(searchStartDate);
        } else {
            resDummy = wnrRepository.findCategoryDummyPage(searchStartDate, searchEndDate, categoryCd, pageable);
            resWaresouingList = wnrRepository.findCategoryWarehousing(searchStartDate, searchEndDate, categoryCd);
            resReleaseList = wnrRepository.findCategoryRelease(searchStartDate, searchEndDate, categoryCd);
            resInventoryListR = wnrRepository.findCategoryInventoryR(searchStartDate, categoryCd);
            resInventoryListW = wnrRepository.findCategoryInventoryW(searchStartDate, categoryCd);
        }

        List<WNRVO> list = new ArrayList<>();
        List<WNRVO> listWaresousing = new ArrayList<>();
        List<WNRVO> listRelease = new ArrayList<>();
        List<WNRVO> listInventoryR = new ArrayList<>();
        List<WNRVO> listInventoryW = new ArrayList<>();

        for (Object obj : resWaresouingList) {
            WNRVO vo = new WNRVO();

            Object[] tempArr = (Object[]) obj;
            vo.setCategoryCd(tempArr[0].toString());
            vo.setSubcategoryCd(tempArr[1].toString());
            vo.setCategoryName(tempArr[2].toString());
            vo.setSubcategoryName(tempArr[3].toString());
            vo.setWarehousingAmount(new BigDecimal(tempArr[4].toString()));

            listWaresousing.add(vo);
        }

        for (Object obj : resReleaseList) {
            WNRVO vo = new WNRVO();

            Object[] tempArr = (Object[]) obj;
            vo.setCategoryCd(tempArr[0].toString());
            vo.setSubcategoryCd(tempArr[1].toString());
            vo.setCategoryName(tempArr[2].toString());
            vo.setSubcategoryName(tempArr[3].toString());
            vo.setReleaseAmount(new BigDecimal(tempArr[4].toString()));

            listRelease.add(vo);
        }

        for (Object obj : resInventoryListR) {
            WNRVO vo = new WNRVO();

            Object[] tempArr = (Object[]) obj;
            vo.setCategoryCd(tempArr[0].toString());
            vo.setSubcategoryCd(tempArr[1].toString());
            vo.setCategoryName(tempArr[2].toString());
            vo.setSubcategoryName(tempArr[3].toString());
            vo.setInventoryAmount(new BigDecimal(tempArr[4].toString()));

            listInventoryR.add(vo);
        }

        for (Object obj : resInventoryListW) {
            WNRVO vo = new WNRVO();

            Object[] tempArr = (Object[]) obj;
            vo.setCategoryCd(tempArr[0].toString());
            vo.setSubcategoryCd(tempArr[1].toString());
            vo.setCategoryName(tempArr[2].toString());
            vo.setSubcategoryName(tempArr[3].toString());
            vo.setInventoryAmount(new BigDecimal(tempArr[4].toString()));

            listInventoryW.add(vo);
        }

        for (Object obj : resDummy.getContent()) {
            WNRVO vo = new WNRVO();

            Object[] tempArr = (Object[]) obj;

            vo.setCategoryCd(tempArr[0].toString());
            vo.setSubcategoryCd(tempArr[1].toString());
            vo.setCategoryName(tempArr[2].toString());
            vo.setSubcategoryName(tempArr[3].toString());
            vo.setWarehousingAmount(BigDecimal.ZERO);
            vo.setRemainAmount(BigDecimal.ZERO);
            vo.setReleaseAmount(BigDecimal.ZERO);
            vo.setInventoryAmount(BigDecimal.ZERO);

            List<WNRVO> tempList = listWaresousing.stream().filter(a-> a.getCategoryCd().equals(vo.getCategoryCd()) &&
                            a.getSubcategoryCd().equals(vo.getSubcategoryCd())).collect(Collectors.toList());
            if (!tempList.isEmpty()) {
                vo.setWarehousingAmount(tempList.get(0).getWarehousingAmount());
            }

            tempList = listRelease.stream().filter(a-> a.getCategoryCd().equals(vo.getCategoryCd()) &&
                    a.getSubcategoryCd().equals(vo.getSubcategoryCd())).collect(Collectors.toList());
            if (!tempList.isEmpty()) {
                vo.setReleaseAmount(tempList.get(0).getReleaseAmount());
            }

            BigDecimal inventoryAmount = BigDecimal.ZERO;

            tempList = listInventoryW.stream().filter(a-> a.getCategoryCd().equals(vo.getCategoryCd()) &&
                    a.getSubcategoryCd().equals(vo.getSubcategoryCd())).collect(Collectors.toList());
            if (!tempList.isEmpty()) {
                inventoryAmount = tempList.get(0).getInventoryAmount();
            }

            tempList = listInventoryR.stream().filter(a-> a.getCategoryCd().equals(vo.getCategoryCd()) &&
                    a.getSubcategoryCd().equals(vo.getSubcategoryCd())).collect(Collectors.toList());
            if (!tempList.isEmpty()) {
                inventoryAmount = inventoryAmount.subtract(tempList.get(0).getInventoryAmount());
            }

            vo.setInventoryAmount(inventoryAmount);
            vo.setRemainAmount(inventoryAmount.add(vo.getWarehousingAmount()).subtract(vo.getReleaseAmount()));

            list.add(vo);

        }

        return Responses.PageResponse.of(list, resDummy);
    }

    @RequestMapping(value = "/graph", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse graph(RequestParams<WNR> requestParams) {

        String searchStartDate = requestParams.getString("searchStartDate", "");
        String searchEndDate = requestParams.getString("searchEndDate", "");

        String sql = "SELECT  K.WNR_DATE" +
                "      , K.CATEGORY_NAME" +
                "      , K.SUBCATEGORY_NAME" +
                "      , (K.SS_IN-K.SS_OUT) + (K.S_IN - K.S_OUT) S_STOCK" +
                " FROM(      " +
                "   SELECT A.WNR_DATE " +
                "         , A.CATEGORY_NAME" +
                "         , A.SUBCATEGORY_NAME" +
                "         , coalesce(SUM(CASE WHEN A.WNR_SEP='W' THEN A.WNR_AMOUNT END),0) S_IN" +
                "         , coalesce(SUM(CASE WHEN A.WNR_SEP='R' THEN A.WNR_AMOUNT END),0) S_OUT" +
                "         , (SELECT coalesce(SUM(CASE WHEN B.WNR_SEP='W' THEN B.WNR_AMOUNT END), 0)" +
                "            FROM WNR_TB B" +
                "            WHERE B.WNR_DATE < A.WNR_DATE" +
                "              AND B.CATEGORY_NAME = A.CATEGORY_NAME" +
                "             AND B.SUBCATEGORY_NAME = A.SUBCATEGORY_NAME) SS_IN " +
                "         , (SELECT coalesce(SUM(CASE WHEN B.WNR_SEP='R' THEN B.WNR_AMOUNT END), 0)" +
                "            FROM WNR_TB B" +
                "            WHERE B.WNR_DATE < A.WNR_DATE" +
                "              AND B.CATEGORY_NAME = A.CATEGORY_NAME" +
                "             AND B.SUBCATEGORY_NAME = A.SUBCATEGORY_NAME) SS_OUT " +
                "   FROM WNR_TB A" +
                "   WHERE A.WNR_DATE BETWEEN ? AND ? " +
                "   GROUP BY A.WNR_DATE, A.CATEGORY_NAME, A.SUBCATEGORY_NAME" +
                ") K";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, new Object[]{requestParams.getString("searchStartDate"), requestParams.getString("searchEndDate")});

        List<InventoryVo> inventoryVo = new ArrayList<>();

        for(Map row : rows) {
            InventoryVo vo = new InventoryVo();

            vo.setWnrDate((String)row.get("wnr_date"));
            vo.setCategoryName((String)row.get("category_name"));
            vo.setSubcategoryName((String)row.get("subcategory_name"));
            vo.setSStock(new BigDecimal(row.get("s_stock").toString()));

            inventoryVo.add(vo);
        }

        return Responses.ListResponse.of(inventoryVo);
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<WNR> request) {
        if (!request.isEmpty()) {
            for (WNR info : request) {
                //입출고 등록
                List<WNR> list = wnrService.getList(info.getWnrDate(), info.getCategoryCd(), info.getSubcategoryCd(), info.getWnrSep());

                Long chkCnt = list.stream().filter(a -> a.getWnrSeq().equals(info.getWnrSeq())).count();

                // 신규등록일 경우
                if (chkCnt == 0) {
                    int wnrNo = 1;
                    if (!list.isEmpty()) {
                        wnrNo += list.get(0).getWnrSeq();
                    }

                    info.setWnrSeq(wnrNo);
                }

                // 출고일경우
                if (info.getWnrSep().equals("R")) {
                    info.setCustomerName(null);
                    info.setWnrPrice(null);
                    info.setCarCd(null);
                }
            }

            wnrService.save(request);
        }
        return ok();
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestBody WNR.WNRId id) {
        wnrService.delete(id);
        return ok();
    }
}