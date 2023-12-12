package br.infnet.controller;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.*;

public class UsuarioController {
    private final UsuarioService usuarioService = new UsuarioService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void respostasRequisicoes() {
        get("/usuarios", (req, res) -> {
            String json = objectMapper.writeValueAsString(usuarioService.listarUsuarios());
            res.status(200);
            return json;
        });

        get("/usuarios/:id", (req, res) -> {
            String idParam = req.params("id");
            Integer id = Integer.parseInt(idParam);
            String json = objectMapper.writeValueAsString(usuarioService.buscarUsuario(id));
            res.status(200);
            return json;
        });

        post("/usuarios", (req, res) -> {
            String body = req.body();
            UsuarioDTOInput usuarioInput = objectMapper.readValue(body, UsuarioDTOInput.class);
            usuarioService.inserirUsuario(usuarioInput);
            res.status(201);
            return "Usuário inserido com sucesso!";
        });

        put("/usuarios", (req, res) -> {
            String body = req.body();
            UsuarioDTOInput usuarioInput = objectMapper.readValue(body, UsuarioDTOInput.class);
            usuarioService.alterarUsuario(usuarioInput);
            res.status(200);
            return "Usuário alterado com sucesso!";
        });

        delete("/usuarios/:id", (req, res) -> {
            String idParam = req.params("id");
            Integer id = Integer.parseInt(idParam);
            usuarioService.excluirUsuario(id);
            res.status(200);
            return "Usuário excluído com sucesso!";
        });
    }
}
