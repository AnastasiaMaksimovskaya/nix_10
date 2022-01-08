package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.Category;
import ua.com.alevel.entity.Operation;
import ua.com.alevel.exception.NotEnoughMoneyException;
import ua.com.alevel.facade.OperationFacade;
import ua.com.alevel.service.OperationService;
import ua.com.alevel.util.Parser;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.OperationRequestDto;
import ua.com.alevel.view.dto.request.RequestDto;
import ua.com.alevel.view.dto.response.OperationResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class OperationFacadeImpl implements OperationFacade {

    private final OperationService operationService;

    public OperationFacadeImpl(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void create(OperationRequestDto operationRequestDto) throws SQLException {
        Long sum = (Parser.convertToKopeyka(operationRequestDto.getSum()));
        Account account = operationRequestDto.getAccount();
        Category category = findCategoryByName(operationRequestDto.getCategoryName());
        try {
            operationService.changeAccBalance(sum, account.getId(), category.getIncome());
        } catch (NotEnoughMoneyException e) {
            throw new NotEnoughMoneyException(e.getMessage());
        }
        Operation operation = new Operation();
        operation.setCreated(new Date(System.currentTimeMillis()));
        operation.setCategory(category);
        operation.setSum(sum);
        operation.setAccount(account);
        operationService.create(operation);
        operationService.update(operation);
    }

    @Override
    public PageData<OperationResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Operation> tableResponse = operationService.findAll(dataTableRequest);

        List<OperationResponseDto> operations = tableResponse.getItems().stream().
                map(OperationResponseDto::new).
                collect(Collectors.toList());

        PageData<OperationResponseDto> pageData = (PageData<OperationResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(operations);
        return pageData;
    }

    @Override
    public Category findCategoryByName(String name) throws SQLException {
        return operationService.findCategoryByName(name);
    }

    @Override
    public void writeOutByAccId(Long id) {
        operationService.writeOutByAccId(id);
    }

    @Override
    public void writeOutByUserId(Long id) {
        operationService.writeOutByUserId(id);
    }

}
