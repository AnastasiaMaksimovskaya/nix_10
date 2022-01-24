package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.store.Brand;
import ua.com.alevel.view.dto.request.BrandRequestDto;
import ua.com.alevel.view.dto.request.CubeRequestDto;
import ua.com.alevel.view.dto.response.BrandResponseDto;
import ua.com.alevel.view.dto.response.CubeResponseDto;

import java.util.List;

public interface BrandFacade extends BaseFacade<BrandRequestDto,BrandResponseDto>{
    List<Brand> findAll();
}
