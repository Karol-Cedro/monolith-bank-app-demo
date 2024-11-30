package com.kcedro.operations.controller;

import com.kcedro.operations.constants.OperationsConstants;
import com.kcedro.operations.dto.OperationDto;
import com.kcedro.operations.dto.ResponseDto;
import com.kcedro.operations.entity.Operation;
import com.kcedro.operations.service.IOperationsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/operations", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class OperationsController {

    IOperationsService operationsService;

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseDto> withdraw(@Valid @RequestBody OperationDto operationDto) {
        boolean status = operationsService.withdraw(operationDto);

        if (status) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(OperationsConstants.STATUS_200, OperationsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(OperationsConstants.STATUS_417, OperationsConstants.MESSAGE_417_WITHDRAW));
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseDto> deposit(@Valid @RequestBody OperationDto operationDto) {
        operationsService.deposit(operationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(OperationsConstants.STATUS_200, OperationsConstants.MESSAGE_200));
    }

    @GetMapping("/get-history")
    public ResponseEntity<List<Operation>> getOperationsHistory(@RequestParam Long accountId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(operationsService.getOperationsHistory(accountId));
    }
}
