package com.backend.vendas.SaleController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.backend.vendas.Models.Sale;
import com.backend.vendas.SaleService.SaleServices;

import antlr.Utils;
import ch.qos.logback.classic.pattern.Util;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api")
public class SaleController {
    @Autowired
    private SaleServices services;

    public SaleController(SaleServices services) {
        this.services = services;
    }

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getAll(Pageable page) {        
        page.setMaxPageSize(20);
        var itens = services.findAll();
        if (itens.isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nothin shower");
        }
        return ResponseEntity.status(HttpStatus.OK).body(itens);
    }
           @PostMapping
           public ResponseEntity<Object> saveSale(@RequestBody Sale sale, Long id) {
              Optional<Sale> saleOpt = services.findById(id);
                if(saleOpt.isPresent()){                   
                   ResponseEntity.status(HttpStatus.CONFLICT).body("already exists in DataBase");
                }                
               return ResponseEntity.status(HttpStatus.CREATED).body(services.save(sale));
           }
       
 
        @PutMapping("/sales/{id}")
        public ResponseEntity<Object> updateSale(@PathVariable("id") Long id, @RequestBody Sale sale) {
            Optional<Sale> saleOptional = services.findById(id);
            if (saleOptional.isPresent()) {
                var saleOpt = saleOptional.get();        
                return new ResponseEntity<>(services.save(saleOpt), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        
            @DeleteMapping("{id}")
            public ResponseEntity<HttpStatus> delete(@PathVariable("id") Sale id) {
                try {
                    services.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
            }
        
    
}
