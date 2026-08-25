package com.example.exemploHTTP.service;

import com.example.exemploHTTP.dao.ProdutoDao;
import com.example.exemploHTTP.model.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoDao produtoDao;

    public ProdutoService(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public List<Produto> listar() {
        return produtoDao.findAll();
    }

    public Produto buscar(Long id){
        return produtoDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto cadastrar(Produto produto) {
        return produtoDao.save(produto);
    }

    public Produto atualizar(Long id, Produto produto) {
        Produto produtoExistente = buscar(id);

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setValor(produto.getValor());
        produtoExistente.setPeso(produto.getPeso());

        return produtoDao.save(produtoExistente);
    }

    public void excluir(Long id){
        produtoDao.deleteById(id);
    }
}