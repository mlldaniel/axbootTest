<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.css"/>
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/inventory/inventory_graph.js' />"></script>
    </jsp:attribute>
    <jsp:body>
        <!-- 검색바 -->
        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='조회기간' width="30%">
                            <div class="form-inline">
                                <div class="input-group" data-ax5picker="search_date">
                                    <input type="text" class="form-control" id="search_start_date">
                                    <span class="input-group-addon">~</span>
                                    <input type="text" class="form-control" id="search_end_date">
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                            </div>
                        </ax:td>
                        <ax:td label='품목' width="70%">
                            <div class="form-inline">
                                <div data-ax5select="search_category" data-ax5select-config="{}"></div>
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
            <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
            <div class="H20"></div>
            <div id="chart"></div>
        </div>

    </jsp:body>
</ax:layout>