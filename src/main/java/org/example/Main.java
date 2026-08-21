package org.example;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import org.example.dao.AlunoDao;
import org.example.model.Aluno;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;

public class Main {
    private static final AlunoDao dao = new AlunoDao();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        // Criando a rota /alunos
        server.createContext("/alunos", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                exchange.getResponseHeaders().add("Content-Type", "application/json");

                if ("GET".equals(exchange.getRequestMethod())) {
                    List<Aluno> alunos = dao.listarTodos();

                    // Montando o JSON manualmente
                    StringBuilder json = new StringBuilder("[");
                    for (int i = 0; i < alunos.size(); i++) {
                        Aluno a = alunos.get(i);
                        json.append(String.format("{\"id\":%d,\"nome\":\"%s\",\"curso\":\"%s\"}", a.getId(), a.getNome(), a.getCurso()));
                        if (i < alunos.size() - 1) json.append(",");
                    }
                    json.append("]");

                    byte[] responseBytes = json.toString().getBytes();
                    exchange.sendResponseHeaders(200, responseBytes.length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(responseBytes);
                    os.close();
                }
            }
        });

        server.start();
        System.out.println("Servidor rodando na porta 8081!");
    }
}