<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/inventory/inventory_status.js' />"></script>
    </jsp:attribute>
    <jsp:body>
        <!-- 검색바 -->
        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='조회기간' width="40%">
                            <div class="form-inline">
                                <div class="input-group" data-ax5picker="search_date">
                                    <input type="text" class="form-control" id="search_start_date">
                                    <span class="input-group-addon">~</span>
                                    <input type="text" class="form-control" id="search_end_date">
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                            </div>
                        </ax:td>
                        <ax:td label='품목' width="30%">
                            <%--<div data-ax5select="search_category" data-ax5select-config="{}"></div>--%>
                            <div data-ax5autocomplete="search_category" data-ax5autocomplete-config="{}"></div>
                        </ax:td>
                        <ax:td label='품명' width="30%">
                            <%--<div data-ax5select="search_subcategory" data-ax5select-config="{}"></div>--%>
                            <div data-ax5autocomplete="search_subcategory" data-ax5autocomplete-config="{}"></div>
                        </ax:td>
                    </ax:tr>
                    <ax:tr>
                        <ax:td label='입고처' width="40%">
                            <%--<div data-ax5select="search_warehousing_customer" data-ax5select-config="{}"></div>--%>
                            <div data-ax5autocomplete="search_warehousing_customer" data-ax5autocomplete-config="{}"></div>
                        </ax:td>
                        <ax:td label='차량번호' width="60%">
                            <div class="form-inline">
                                    <%--<div data-ax5select="search_warehousing_car_cd" data-ax5select-config="{}"></div>--%>
                                <div data-ax5autocomplete="search_warehousing_car_cd" data-ax5autocomplete-config="{}"></div>
                                <button style="float: right;" type="button" class="btn btn-primary"
                                        data-page-btn="search">
                                    <i class="cqc-magnifier"></i>
                                    <ax:lang id="ax.admin.search"/>
                                </button>
                                <%--<button style="float: right;margin-right:5px;" type="button" class="btn btn-primary"--%>
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
        </div>
        <div role="page-content">
            <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 550px;"></div>
            <div data-ax5grid="grid-view-02" style="display:none;" data-fit-height-content="grid-view-02" style="height: 550px;"></div>
        </div>

    </jsp:body>
</ax:layout>