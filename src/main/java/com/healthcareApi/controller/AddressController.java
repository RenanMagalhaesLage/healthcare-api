package com.healthcareApi.controller;

import com.healthcareApi.domain.dto.request.AddressRequestDTO;
import com.healthcareApi.domain.dto.request.PatientRequestDTO;
import com.healthcareApi.domain.dto.response.AddressResponseDTO;
import com.healthcareApi.domain.dto.response.PatientResponseDTO;
import com.healthcareApi.service.AddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@Tag(name = "Address", description = "Endpoints for managing address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping(params = "addressId")
    public ResponseEntity<AddressResponseDTO> findById(@RequestParam Long addressId){
        return ResponseEntity.ok(addressService.findById(addressId));
    }

    @PostMapping()
    public ResponseEntity<AddressResponseDTO> create(@RequestBody AddressRequestDTO dto){
        return ResponseEntity.ok(addressService.create(dto));
    }

    @PutMapping()
    public ResponseEntity<AddressResponseDTO> update(@RequestBody AddressRequestDTO dto){
        return ResponseEntity.ok(addressService.update(dto));
    }

    @DeleteMapping()
    public ResponseEntity<String> delete(@RequestParam Long addressId){
        return ResponseEntity.ok(addressService.delete(addressId));
    }
}
