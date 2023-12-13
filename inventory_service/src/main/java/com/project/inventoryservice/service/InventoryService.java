package com.project.inventoryservice.service;

import com.project.inventoryservice.dto.InventoryResponse;
import com.project.inventoryservice.entity.Inventory;
import com.project.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

//    public List<InventoryResponse> getAll(){
//        List<InventoryResponse> listOfInventory = inventoryRepository.findAll().stream()
//                .map(inventory -> mapToDto(inventory))
//                .toList();
//        return listOfInventory;
//    }
//
//    private InventoryResponse mapToDto(Inventory inventory) {
//        return InventoryResponse.builder()
//                .id(inventory.getId())
//                .skuCode(inventory.getSkuCode())
//                .quantity(inventory.getQuantity())
//                .build();
//    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> checkIsInStock(List<String> listOfSkuCode) {
        return inventoryRepository.findBySkuCodeIn(listOfSkuCode).stream()
                .map(inventory -> mapToDto(inventory))
                .toList();
}

    private InventoryResponse mapToDto(Inventory inventory){
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}