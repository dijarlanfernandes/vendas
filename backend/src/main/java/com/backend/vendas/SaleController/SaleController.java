package com.backend.vendas.SaleController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.vendas.Models.Sale;
import com.backend.vendas.SaleService.SaleServices;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleServices services;

    public SaleController(SaleServices services) {
        this.services = services;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAll() {
        var itens = services.findAll();
        if (itens.isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nothin shower");
        }
        return ResponseEntity.status(HttpStatus.OK).body(itens);
    }
}
