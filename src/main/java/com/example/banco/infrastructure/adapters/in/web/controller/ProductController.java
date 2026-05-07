package com.example.banco.infrastructure.adapters.in.web.controller;

import com.example.banco.application.dto.ProductRequest;
import com.example.banco.domain.model.Product;
import com.example.banco.domain.ports.in.CreateProductUseCase;
import com.example.banco.domain.ports.in.UpdateGmfExemptUseCase;
import com.example.banco.domain.ports.in.UpdateProductStatusUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductStatusUseCase updateProductStatusUseCase;
    private final UpdateGmfExemptUseCase updateGmfExemptUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, UpdateProductStatusUseCase updateProductStatusUseCase, UpdateGmfExemptUseCase updateGmfExemptUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.updateProductStatusUseCase = updateProductStatusUseCase;
        this.updateGmfExemptUseCase = updateGmfExemptUseCase;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest request) {
        Product product = createProductUseCase.execute(request.clientId(), request.type());
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PatchMapping("/{accountNumber}/status")
    public ResponseEntity<Product> updateStatus(
            @PathVariable String accountNumber,
            @RequestParam String newStatus) {

        Product updatedProduct = updateProductStatusUseCase.execute(accountNumber, newStatus);
        return ResponseEntity.ok(updatedProduct);
    }

    @PatchMapping("/{accountNumber}/gmf-status")
    public ResponseEntity<Map<String, Object>> updateGmfStatus(
            @PathVariable String accountNumber,
            @RequestParam boolean exempt) {

        updateGmfExemptUseCase.execute(accountNumber, exempt);

        return ResponseEntity.ok(Map.of(
                "message", "Estado de exención GMF actualizado correctamente",
                "accountNumber", accountNumber,
                "isExempt", exempt
        ));
    }
}
