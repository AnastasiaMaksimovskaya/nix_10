package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.ProductFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Product;
import ua.com.alevel.service.ProductService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.ProductRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ProductResponseDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductFacadeImpl implements ProductFacade {

    private final ProductService productService;

    public ProductFacadeImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void create(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getProductName());
        product.setBrand(productRequestDto.getBrand());
        product.setPrice(productRequestDto.getPrice());
        productService.create(product);
    }

    @Override
    public void update(ProductRequestDto productRequestDto, Long id) {
        productService.update(productService.findById(id));
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        return new ProductResponseDto(productService.findById(id));
    }

    @Override
    public PageData<ProductResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Product> tableResponse = productService.findAll(dataTableRequest);

        List<ProductResponseDto> products = tableResponse.getItems().stream().
                map(ProductResponseDto::new).
                peek(productResponseDto -> productResponseDto.setShopsCount((Integer) tableResponse.
                        getOtherParamMap().get(productResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<ProductResponseDto> pageData = (PageData<ProductResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(products);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllByShopId(Long shopId) {
        return productService.findAllByShopId(shopId);
    }
}
