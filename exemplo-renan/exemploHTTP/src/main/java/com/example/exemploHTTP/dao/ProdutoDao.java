package com.example.exemploHTTP.dao;

import com.example.exemploHTTP.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoDao extends JpaRepository<Produto, Long> {
}
