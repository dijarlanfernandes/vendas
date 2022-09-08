package com.backend.vendas.SaleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.vendas.Models.Sale;
import com.backend.vendas.SaleRepository.ISaleRepository;

@Service
public class SaleServices {
    @Autowired
    private ISaleRepository salerepo;

    public SaleServices(ISaleRepository salerepo) {
        this.salerepo = salerepo;
    }

    public List<Sale> findAll() {
        return salerepo.findAll();
    }

}
