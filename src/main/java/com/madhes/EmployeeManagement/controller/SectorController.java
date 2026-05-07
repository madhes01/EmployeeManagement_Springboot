package com.madhes.EmployeeManagement.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhes.EmployeeManagement.dto.request.SectorRequestDTO;
import com.madhes.EmployeeManagement.dto.response.SectorResponseDTO;
import com.madhes.EmployeeManagement.service.SectorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/sector")
@RequiredArgsConstructor
@Validated
public class SectorController {

    private final SectorService sectorService;

    @PostMapping
    public SectorResponseDTO createSector (@Valid @RequestBody SectorRequestDTO requestDTO) {
        return sectorService.createSector(requestDTO);
    }

    @GetMapping
    public List<SectorResponseDTO> getAllSectors() {
        return sectorService.getAllSectors();
    }

}
