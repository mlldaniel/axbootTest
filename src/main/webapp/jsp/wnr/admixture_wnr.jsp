<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" var="LANG"/>
        <ax:script-lang key="ax.admin" var="COL"/>
        <script type="text/javascript" src="<c:url value='/assets/js/view/wnr/admixture_wnr.js' />"></script>
    </jsp:attribute>
    <jsp:attribute name="css">
        <style>
            input:focus::-webkit-input-placeholder, textarea:focus::-webkit-input-placeholder { /* WebKit browsers */ color:transparent; }
            input:focus:-moz-placeholder, textarea:focus:-moz-placeholder { /* Mozilla Firefox 4 to 18 */ color:transparent; }
            input:focus::-moz-placeholder, textarea:focus::-moz-placeholder { /* Mozilla Firefox 19+ */ color:transparent; }
            input:focus:-ms-input-placeholder, textarea:focus:-ms-input-placeholder { /* Internet Explorer 10+ */ color:transparent; }
        </style>
    </jsp:attribute>
    <jsp:body>

        <%--<ax:page-buttons></ax:page-buttons>--%>

        <!-- 검색바 -->
        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label="PC" width="300px">
                            <input type="text" style="text-align: right;" data-ax-path="sub1_inventory_total"
                                   class="form-control" readonly="readonly">
                        </ax:td>
                        <ax:td label="일반" width="300px">
                            <input type="text" style="text-align: right;" data-ax-path="sub2_inventory_total"
                                   class="form-control" readonly="readonly">
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label="일자" width="40%">
                            <div class="form-inline">
                                <div class="input-group" data-ax5picker="wnrDate">
                                    <input type="text" style="text-align: center;" class="form-control"
                                           data-ax-path="wnrSearchDate">
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                            </div>
                        </ax:td>
                        <ax:td label='입출구분' width="60%">
                            <div class="form-inline">
                                <div data-ax5select="search_wnr_sep" data-ax5select-config="{}"></div>
                                <button style="float: right;" type="button" class="btn btn-primary"
                                        data-page-btn="search">
                                    <i class="cqc-magnifier"></i>
                                    <ax:lang id="ax.admin.search"/>
                                </button>
                                    <%--<button style="float: right;" type="button" class="btn btn-primary"--%>
                                    <%--data-page-btn="excel">--%>
                                    <%--<i class="cqc-file-excel-o"></i>--%>
                                    <%--엑셀저장--%>
                                    <%--</button>--%>
                            </div>
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H20"></div>
            <ax:form name="formView01">
                <ax:tbl clazz="ax-form-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label="구분" width="100%">
                            <div class="form-inline">
                                <div class="radio" style="margin-top: 4px;">
                                    <div style="margin:0px 5px 0px 0px;display: inline-block">
                                        <label><input type="radio" name="radio" data-page-btn="warehousing"
                                                      id="chk1" data-ax-path="warehousing" value="W">입고</label>
                                    </div>
                                    <label><input type="radio" name="radio" id="chk2" data-page-btn="release"
                                                  data-ax-path="release"
                                                  value="R">출고</label>
                                </div>
                            </div>
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label="일자" width="300px">
                            <div class="form-inline">
                                <div class="input-group" data-ax5picker="wnrDate">
                                    <input type="text" style="text-align: center;" class="form-control"
                                           data-ax-path="wnrDate">
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                            </div>
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label="PC" width="100%">
                            <div class="form-inline">
                                <input type="text" style="text-align: right;width: 185px;" placeholder="0.0"
                                       data-ax-path="sub1_amount"
                                       data-ax5formatter="money" class="form-control"/>
                                <span style="margin-right: 30px;">kg</span>
                                <input type="text" style="text-align: right;width: 185px;" placeholder="0"
                                       data-ax-path="sub1_price"
                                       data-ax5formatter="money(int)" class="form-control"/>
                                <span style="margin-right: 30px;">원</span>
                                <div data-ax5select="select_warehousing_customer_1" data-ax5select-config="{}"></div>
                                <div data-ax5select="select_warehousing_car_1" data-ax5select-config="{}"></div>
                            </div>
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label="일반" width="100%">
                            <div class="form-inline">
                                <input type="text" style="text-align: right;width: 185px;" placeholder="0.0"
                                       data-ax-path="sub2_amount"
                                       data-ax5formatter="money" class="form-control"/>
                                <span style="margin-right: 30px;">kg</span>
                                <input type="text" style="text-align: right;width: 185px;" placeholder="0"
                                       data-ax-path="sub2_price"
                                       data-ax5formatter="money(int)" class="form-control"/>
                                <span style="margin-right: 30px;">원</span>
                                <div data-ax5select="select_warehousing_customer_2" data-ax5select-config="{}"></div>
                                <div data-ax5select="select_warehousing_car_2" data-ax5select-config="{}"></div>
                                <button style="float: right;margin-left:5px;" type="button" class="btn btn-default"
                                        data-page-btn="delete">
                                    <i class="cqc-minus"></i>
                                    <ax:lang id="ax.admin.delete"/>
                                </button>
                                <button style="float: right;margin-left:5px;" type="button" class="btn btn-default"
                                        data-page-btn="save">
                                    <i class="cqc-save"></i>
                                    등록
                                </button>
                                <button style="float: right;margin-left:5px;" type="button" class="btn btn-default"
                                        data-page-btn="new">
                                    <i class="cqc-erase"></i>
                                    <ax:lang id="ax.admin.clear"/>
                                </button>
                            </div>
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H20"></div>
        </div>
        <div role="page-content">
            <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height:300px;"></div>
        </div>
    </jsp:body>
</ax:layout>