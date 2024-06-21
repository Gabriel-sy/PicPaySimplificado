package com.gabriel.picpaysimp.controller;

import com.gabriel.picpaysimp.dto.TransferDTO;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.service.TransferService;
import com.gabriel.picpaysimp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class TransferController {
    private final TransferService transferService;
    private final UserService userService;


    public TransferController(TransferService transferService, UserService userService) {
        this.transferService = transferService;
        this.userService = userService;
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


}
