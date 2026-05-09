package com.madhes.EmployeeManagement.controller;

import com.madhes.EmployeeManagement.dto.request.SectorRequestDTO;
import com.madhes.EmployeeManagement.dto.response.SectorResponseDTO;
import com.madhes.EmployeeManagement.service.SectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SectorController — manages department/sector data.
 *
 * Bug fixed: was importing io.swagger.v3.oas.annotations.parameters.RequestBody
 * instead of org.springframework.web.bind.annotation.RequestBody.
 * The Swagger one is an annotation for documentation only — NOT for binding request body.
 * The Spring one is what actually reads the JSON body. Both can exist together but
 * the Spring one is what @PostMapping uses for binding.
 */
@RestController
@RequestMapping("/sector")
@RequiredArgsConstructor
@Validated
@Tag(name = "Sector", description = "Sector management endpoints")
public class SectorController {

    private final SectorService sectorService;

    @Operation(summary = "Create a new sector (ADMIN only)")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SectorResponseDTO> createSector(
            @Valid @RequestBody SectorRequestDTO requestDTO) {  // ← Spring's @RequestBody

        SectorResponseDTO created = sectorService.createSector(requestDTO);
        return ResponseEntity.status(201).body(created);
    }

    @Operation(summary = "Get all sectors")
    @GetMapping
    public ResponseEntity<List<SectorResponseDTO>> getAllSectors() {
        return ResponseEntity.ok(sectorService.getAllSectors());
    }
}