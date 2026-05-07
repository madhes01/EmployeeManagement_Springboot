package com.madhes.EmployeeManagement.service;

import java.util.stream.Collectors;

import java.util.List;
import org.springframework.stereotype.Service;

import com.madhes.EmployeeManagement.dto.request.SectorRequestDTO;
import com.madhes.EmployeeManagement.dto.response.SectorResponseDTO;
import com.madhes.EmployeeManagement.entity.Sector;
import com.madhes.EmployeeManagement.repository.SectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorResponseDTO createSector(SectorRequestDTO requestDTO) {
        Sector sector = new Sector();
        sector.setName(requestDTO.getName());
        Sector savedSector = sectorRepository.save(sector);
        return mapToResponseDTO(savedSector);
    }

    public List<SectorResponseDTO> getAllSectors() {
        List<Sector> sectors = sectorRepository.findAll();
        return sectors.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    private SectorResponseDTO mapToResponseDTO(Sector sector) {
       
       SectorResponseDTO dto = new SectorResponseDTO();
       dto.setId(sector.getId());
       dto.setName(sector.getName());

       return dto;
       
    }

}
