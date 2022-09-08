package com.backend.vendas.SaleRepository;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.vendas.Models.Sale;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Id> {

}
