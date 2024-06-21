package com.gabriel.picpaysimp.controller;

import com.gabriel.picpaysimp.domain.TransferDTO;
import com.gabriel.picpaysimp.domain.user.User;
import com.gabriel.picpaysimp.exception.UserNotFoundException;
import com.gabriel.picpaysimp.repository.UserRepository;
import com.gabriel.picpaysimp.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class TransferController {
    private final TransferService transferService;
    private final UserRepository userRepository;


    public TransferController(TransferService transferService, UserRepository userRepository) {
        this.transferService = transferService;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/transfer")
    public ResponseEntity<Void> transferApi(@RequestBody TransferDTO transferDTO){
        transferService.transfer(transferDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("ID não encontrado")));
    }


}
