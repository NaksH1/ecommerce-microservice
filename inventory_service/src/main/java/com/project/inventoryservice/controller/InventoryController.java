package com.project.inventoryservice.controller;

import com.project.inventoryservice.dto.InventoryResponse;
import com.project.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

//    @GetMapping
//    public ResponseEntity<?> getALL(){
//        List<InventoryResponse> list = inventoryService.getAll();
//        if(list.isEmpty())
//           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>(list, HttpStatus.OK);
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam(value = "skuCode") List<String> skuCode){
        System.out.println("Inventory service is called");
        return inventoryService.checkIsInStock(skuCode);
    }
}
