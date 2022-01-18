package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.response.CubePLPDto;

import java.util.List;

public interface PLPFacade {

    List<CubePLPDto> search(WebRequest webRequest);
    List<String> searchCubeName(String query);
}
