package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;

import java.util.Objects;

public final class WebRequestUtil {

    private static final String PAGE_PARAM = "page";
    private static final String SIZE_PARAM = "size";
    private static final String SORT_PARAM = "sort";
    private static final String ORDER_PARAM = "order";
    public static final String DEFAULT_SORT_PARAM_VALUE = "created";
    public static final String DEFAULT_ORDER_PARAM_VALUE = "desc";
    public static final int DEFAULT_PAGE_PARAM_VALUE = 1;
    public static final int DEFAULT_SIZE_PARAM_VALUE = 10;
    public static final String BRAND_PARAM = "brand";
    public static final String SEARCH_CUBE_PARAM = "searchCube";

    private WebRequestUtil() {
    }

    public static DataTableRequest initDataTableRequest(WebRequest request) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        PageAndSizeData pageAndSizeData = generatePageAndSizeData(request);
        SortData sortData = generateSortData(request);
        dataTableRequest.setSize(pageAndSizeData.getSize());
        dataTableRequest.setPage(pageAndSizeData.getPage());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setOrder(sortData.getOrder());
        return dataTableRequest;
    }

    public static PageAndSizeData generatePageAndSizeData(WebRequest webRequest) {
        int page = webRequest.getParameter(PAGE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(filterParameter(webRequest.getParameter(PAGE_PARAM)))) : DEFAULT_PAGE_PARAM_VALUE;
        int size = webRequest.getParameter(SIZE_PARAM) != null ? Integer.parseInt(Objects.requireNonNull(filterParameter(webRequest.getParameter(SIZE_PARAM)))) : DEFAULT_SIZE_PARAM_VALUE;
        return new PageAndSizeData(page, size);
    }

    public static PageAndSizeData defaultPageAndSizeData() {
        return new PageAndSizeData(DEFAULT_PAGE_PARAM_VALUE, DEFAULT_SIZE_PARAM_VALUE);
    }

    public static SortData generateSortData(WebRequest webRequest) {
        String sort = StringUtils.isNotBlank(webRequest.getParameter(SORT_PARAM)) ? Objects.requireNonNull(filterParameter(webRequest.getParameter(SORT_PARAM))) : DEFAULT_SORT_PARAM_VALUE;
        String order = StringUtils.isNotBlank(webRequest.getParameter(ORDER_PARAM)) ? Objects.requireNonNull(filterParameter(webRequest.getParameter(ORDER_PARAM))) : DEFAULT_ORDER_PARAM_VALUE;
        return new SortData(sort, order);
    }

    public static SortData defaultSortData() {
        return new SortData(DEFAULT_SORT_PARAM_VALUE, DEFAULT_ORDER_PARAM_VALUE);
    }

    private static String filterParameter(String param){
        if (param.contains("?")){
            return param.substring(0,param.indexOf("?"));
        }
        else return param;
    }
}
