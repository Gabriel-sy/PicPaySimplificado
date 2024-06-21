package com.gabriel.picpaysimp.controller;

import com.gabriel.picpaysimp.domain.TransferDTO;
import com.gabriel.picpaysimp.domain.common.CommonUser;
import com.gabriel.picpaysimp.domain.retailer.Retailer;
import com.gabriel.picpaysimp.exception.UserNotFoundException;
import com.gabriel.picpaysimp.repository.CommonUserRepository;
import com.gabriel.picpaysimp.repository.RetailerRepository;
import com.gabriel.picpaysimp.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class TransferController {
    private final TransferService transferService;
    private final RetailerRepository retailerRepository;
    private final CommonUserRepository commonUserRepository;


    public TransferController(TransferService transferService, RetailerRepository retailerRepository, CommonUserRepository commonUserRepository) {
        this.transferService = transferService;
        this.retailerRepository = retailerRepository;
        this.commonUserRepository = commonUserRepository;
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity<Void> transferApi(@RequestBody TransferDTO transferDTO){
        transferService.transfer(transferDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "common/{id}")
    public ResponseEntity<CommonUser> findCommonUserById(@PathVariable Long id){
        return ResponseEntity.ok(commonUserRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("ID não encontrado")));
    }

    @GetMapping(path = "retailer/{id}")
    public ResponseEntity<Retailer> findRetailerById(@PathVariable Long id){
        return ResponseEntity.ok(retailerRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("ID não encontrado")));
    }
}
