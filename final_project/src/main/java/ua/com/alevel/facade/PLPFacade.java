package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.response.CubePLPDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface PLPFacade {

    PageData<CubePLPDto> search(WebRequest webRequest);
    List<String> searchCubeName(String query);
}
