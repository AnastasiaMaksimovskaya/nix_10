package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.entity.Category;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.ResponseDto;

import java.sql.SQLException;

public interface OperationFacade<OperationResponseDto extends ResponseDto,OperationRequestDto extends ua.com.alevel.view.dto.request.OperationRequestDto> {
    PageData<OperationResponseDto> findAll(WebRequest request);
    Category findCategoryByName(String name) throws SQLException;
    void writeOutByAccId(Long id);
    void writeOutByUserId(Long id);
    void create(OperationRequestDto req) throws SQLException;
}
