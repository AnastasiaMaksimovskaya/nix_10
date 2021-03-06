package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.BadRequestException;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.service.ElasticBookSearchService;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.service.store.CubeService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.response.CubePLPDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.*;

@Service
public class PLPFacadeImpl implements PLPFacade {

    private List<Long> cart = new ArrayList<>();

    private final PLPService plpService;
    private final CubeService cubeService;
    private final ElasticBookSearchService elasticBookSearchService;

    public PLPFacadeImpl(PLPService plpService, CubeService cubeService, ElasticBookSearchService elasticBookSearchService) {
        this.plpService = plpService;
        this.cubeService = cubeService;
        this.elasticBookSearchService = elasticBookSearchService;
    }

    @Override
    public PageData<CubePLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(webRequest);
        if (webRequest.getParameterMap().get(WebRequestUtil.BRAND_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebRequestUtil.BRAND_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            String[] brandNamesArray = params[0].split(",|-");
            List<String> brandNames = Arrays.asList(brandNamesArray);
            queryMap.put(WebRequestUtil.BRAND_PARAM, brandNames);
        }
        if (webRequest.getParameterMap().get(WebRequestUtil.SEARCH_CUBE_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebRequestUtil.SEARCH_CUBE_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            String searchBook = params[0];
            queryMap.put(WebRequestUtil.SEARCH_CUBE_PARAM, searchBook);
        }
        DataTableResponse<Cube> cubes = plpService.search(queryMap, dataTableRequest);
        List<CubePLPDto> cubePLPDtos = cubes.getItems().stream().map(CubePLPDto::new).toList();
        PageData<CubePLPDto> pageData = (PageData<CubePLPDto>) WebResponseUtil.initPageData(cubes);
        pageData.setItems(cubePLPDtos);
        return pageData;
    }

    @Override
    public List<String> searchCubeName(String query) {
        return elasticBookSearchService.searchCubeName(query);
    }

    @Override
    public List<Long> addToCart(Long id) {
        cart.add(id);
        return cart;
    }

    @Override
    public List<Cube> getCart() {
        List<Cube> cubes = new ArrayList<>();
        cart.stream().forEach(id -> cubes.add(cubeService.findById(id).get()));
        return cubes;
    }

    public void setCart(List<Long> cart) {
        this.cart = cart;
    }

    @Override
    public List<Long> getCartId() {
        return cart;
    }
}
