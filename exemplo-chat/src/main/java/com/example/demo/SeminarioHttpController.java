package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class SeminarioHttpController {

    // TÓPICO: Ciclo HTTP e Status 200 (Sucesso)
    @GetMapping("/")
    public ResponseEntity<Map<String, String>> sucesso() {
        return ResponseEntity.ok(
                Map.of("mensagem", "Requisição recebida com sucesso! Este é o Body da resposta.")
        );
    }

    // TÓPICO: Observando Requisição, Headers e POST
    @PostMapping("/inspecionar")
    public ResponseEntity<Map<String, Object>> inspecionar(
            @RequestHeader Map<String, String> cabecalhos,
            @RequestBody Map<String, Object> corpo) {

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("aviso", "Veja o que o servidor recebeu de você:");
        resposta.put("cabecalhos_recebidos", cabecalhos);
        resposta.put("corpo_recebido", corpo);

        // Retorna Status 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
    }

    // TÓPICO: Idempotência e Atualização (PUT)
    @PutMapping("/atualizar")
    public ResponseEntity<Map<String, String>> atualizar() {
        return ResponseEntity.ok(
                Map.of("mensagem", "Recurso atualizado. Enviar isso 1 ou 100 vezes terá o mesmo efeito no servidor (Idempotência).")
        );
    }

    // TÓPICO: Headers na Resposta e Cache HTTP
    @GetMapping("/cache")
    public ResponseEntity<Map<String, String>> cache(HttpServletResponse response) {
        // Injetando Headers customizados e de controle de Cache
        response.setHeader("Cache-Control", "public, max-age=3600"); // Cache de 1 hora
        response.setHeader("X-Header-Seminario", "Informacao-Secreta-No-Cabecalho");

        return ResponseEntity.ok(
                Map.of("mensagem", "Abra a aba Network ou o Postman para ver os Headers que o servidor enviou de volta!")
        );
    }

    // TÓPICO: Provocando Erros (Status 400 - Bad Request)
    @PostMapping("/erro-cliente")
    public ResponseEntity<Map<String, String>> erroCliente(@RequestBody Map<String, Object> corpo) {
        // Verifica se o JSON enviado contém a chave "nome"
        if (!corpo.containsKey("nome")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("erro", "Faltou enviar o campo 'nome' no body. A culpa é do cliente (4xx).")
            );
        }
        return ResponseEntity.ok(Map.of("mensagem", "Tudo certo."));
    }
}