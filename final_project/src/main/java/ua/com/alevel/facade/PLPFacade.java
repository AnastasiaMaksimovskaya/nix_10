package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.persistence.entity.store.Cube;
import ua.com.alevel.view.dto.response.CubePLPDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface PLPFacade {

    PageData<CubePLPDto> search(WebRequest webRequest);
    List<String> searchCubeName(String query);
    List<Long> addToCart(Long id);
    List<Cube> getCart();
    void setCart(List<Long> cart);
    List<Long> getCartId();

}
