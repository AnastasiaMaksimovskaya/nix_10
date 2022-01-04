package ua.com.alevel.facade;

import ua.com.alevel.entity.Category;
import ua.com.alevel.view.dto.request.OperationRequestDto;
import ua.com.alevel.view.dto.response.OperationResponseDto;

import java.sql.SQLException;

public interface OperationFacade extends BaseFacade<OperationRequestDto, OperationResponseDto> {
    Category findCategoryByName(String name) throws SQLException;

}
