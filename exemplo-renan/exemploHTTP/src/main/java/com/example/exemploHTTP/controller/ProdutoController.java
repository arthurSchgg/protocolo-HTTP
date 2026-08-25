package com.example.exemploHTTP.controller;

import com.example.exemploHTTP.model.Produto;
import com.example.exemploHTTP.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoService.listar();
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return produtoService.buscar(id);
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return produtoService.cadastrar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }

    @PatchMapping("/{id}")
    public Produto atualizarParcial(@PathVariable Long id, @RequestBody Produto produto) {

        Produto produtoExistente = produtoService.buscar(id);

        if (produto.getNome() != null) {
            produtoExistente.setNome(produto.getNome());
        }

        if (produto.getValor() >= 0) {
            produtoExistente.setValor(produto.getValor());
        }

        if (produto.getPeso() >= 0) {
            produtoExistente.setPeso(produto.getPeso());
        }

        return produtoService.cadastrar(produtoExistente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}
