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
        <script type="text/javascript" src="<c:url value='/assets/js/view/code/warehousing_customer.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <%--<ax:page-buttons></ax:page-buttons>--%>

        <!-- 검색바 -->
        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label="구분" width="20%">
                            <div data-ax5select="search_category" data-ax5select-config="{}"></div>
                        </ax:td>
                        <ax:td label="거래처" width="35%">
                            <div class="form-inline">
                                <div data-ax5autocomplete="customer_name" data-ax5autocomplete-config="{}"></div>
                            </div>
                        </ax:td>
                        <ax:td label="차량번호" width="45%">
                            <div class="form-inline">
                                <div data-ax5autocomplete="car_cd" data-ax5autocomplete-config="{}"></div>
                                <button style="float: right;" type="button" class="btn btn-primary"
                                        data-page-btn="search">
                                    <i class="cqc-magnifier"></i>
                                    <ax:lang id="ax.admin.search"/>
                                </button>
                            </div>
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H20"></div>
        </div>
        <ax:form name="formView01">
            <ax:tbl clazz="ax-form-tbl" minWidth="500px">
                <ax:tr>
                    <ax:td label="거래처명*" width="40%">
                        <input type="text" data-ax-path="customerName" maxlength="40" class="form-control" data-ax-validate="required"/>
                    </ax:td>
                    <ax:td label="차량번호*" width="30%">
                        <input type="text" data-ax-path="carCd" maxlength="4" class="form-control" data-ax-validate="required"/>
                    </ax:td>
                    <ax:td label="사업자번호" width="30%">
                        <input type="text" data-ax-path="corporateregNo" maxlength="12" class="form-control"/>
                    </ax:td>
                </ax:tr>
                <ax:tr>
                    <ax:td label="우편번호" width="20%">
                        <input type="text" data-ax-path="zipCd" maxlength="5" class="form-control"/>
                    </ax:td>
                    <ax:td label="주소" width="80%">
                        <input type="text" data-ax-path="addressName" maxlength="200" class="form-control"/>
                    </ax:td>
                </ax:tr>
                <ax:tr>
                    <ax:td label="연락처" width="20%">
                        <input type="text" data-ax-path="phoneNo" maxlength="20" class="form-control"/>
                    </ax:td>
                    <ax:td label="품목" width="30%">
                        <div data-ax5select="select_category" data-ax5select-config="{}"></div>
                    </ax:td>
                    <ax:td label="품명" width="50%">
                        <div class="form-inline">
                            <div data-ax5select="select_subcategory" data-ax5select-config="{}"></div>
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
        <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 450px;"></div>
    </jsp:body>
</ax:layout>