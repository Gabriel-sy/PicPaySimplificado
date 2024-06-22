package com.gabriel.picpaysimp.controller;

import com.gabriel.picpaysimp.domain.transferhistory.TransferHistory;
import com.gabriel.picpaysimp.dto.TransferDTO;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.repository.TransferHistoryRepository;
import com.gabriel.picpaysimp.service.TransferHistoryService;
import com.gabriel.picpaysimp.service.TransferService;
import com.gabriel.picpaysimp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping()
public class TransferController {
    private final TransferService transferService;
    private final UserService userService;
    private final TransferHistoryService transferHistoryService;


    public TransferController(TransferService transferService, UserService userService, TransferHistoryService transferHistoryService) {
        this.transferService = transferService;
        this.userService = userService;
        this.transferHistoryService = transferHistoryService;
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity<Void> transferApi(@RequestBody TransferDTO transferDTO){
        transferService.transfer(transferDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping(path = "/history")
    public ResponseEntity<List<TransferHistory>> listTransactionHistory(){
        return ResponseEntity.ok(transferHistoryService.findAll());
    }

}
