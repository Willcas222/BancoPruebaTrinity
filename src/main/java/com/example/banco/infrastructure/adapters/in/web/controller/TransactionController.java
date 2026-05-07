package com.example.banco.infrastructure.adapters.in.web.controller;


import com.example.banco.application.dto.TransactionRequest;
import com.example.banco.application.dto.TransferRequest;
import com.example.banco.domain.model.Movement;
import com.example.banco.domain.ports.in.GetMovementsUseCase;
import com.example.banco.domain.ports.in.TransactionUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionUseCase transactionUseCase;
    private final GetMovementsUseCase getMovementsUseCase;

    public TransactionController(TransactionUseCase transactionUseCase, GetMovementsUseCase getMovementsUseCase) {
        this.transactionUseCase = transactionUseCase;
        this.getMovementsUseCase = getMovementsUseCase;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Map<String, String>> deposit(@RequestBody TransactionRequest request) {
        transactionUseCase.deposit(request.accountNumber(), request.amount());
        return ResponseEntity.ok(Map.of("message", "Consignación realizada con éxito"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Map<String, String>> withdraw(@RequestBody TransactionRequest request) {
        transactionUseCase.withdraw(request.accountNumber(), request.amount());
        return ResponseEntity.ok(Map.of("message", "Retiro realizado con éxito"));
    }

    @PostMapping("/transfer")
    public ResponseEntity<Map<String, String>> transfer(@RequestBody TransferRequest request) {
        transactionUseCase.transfer(
                request.originAccount(),
                request.destinationAccount(),
                request.amount()
        );
        return ResponseEntity.ok(Map.of("message", "Transferencia realizada con éxito"));
    }

    @GetMapping("/{accountNumber}/statement")
    public ResponseEntity<List<Movement>> getStatement(@PathVariable String accountNumber) {
        return ResponseEntity.ok(getMovementsUseCase.execute(accountNumber));
    }
}
