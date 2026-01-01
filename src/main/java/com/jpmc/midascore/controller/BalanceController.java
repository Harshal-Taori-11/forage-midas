package com.jpmc.midascore.controller;

import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController (BalanceService balanceService){
        this.balanceService = balanceService;
    }

    @GetMapping("/balance")
    public ResponseEntity<Balance> getBalance(@RequestParam long userId){
        return ResponseEntity.ok().body(balanceService.getBalance(userId));
    }
}
