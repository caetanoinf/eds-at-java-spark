package br.infnet;

import br.infnet.dto.RandomUserDTO;
import br.infnet.dto.UsuarioDTOInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class EndpointTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testarListagem() throws IOException {
        URL url = new URL("http://localhost:4567/usuarios");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        int responseCode = conexao.getResponseCode();

        Assert.assertEquals(200, responseCode);
    }

    @Test
    public void testarInsercao() throws IOException {
        RandomUserDTO usuarioAleatorio = obterUsuarioAleatorio();

        URL url = new URL("http://localhost:4567/usuarios");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setDoOutput(true);

        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(usuarioAleatorio.getId());
        usuarioDTOInput.setNome(usuarioAleatorio.getName());
        usuarioDTOInput.setSenha(usuarioAleatorio.getPassword());

        String json = objectMapper.writeValueAsString(usuarioDTOInput);

        conexao.getOutputStream().write(json.getBytes());

        int responseCode = conexao.getResponseCode();
        Assert.assertEquals(201, responseCode);
    }

    public RandomUserDTO obterUsuarioAleatorio() throws IOException {
        URL url = new URL("https://randomuser.me/api/");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");

        if (conexao.getResponseCode() != 200) {
            throw new RuntimeException("Erro ao obter usuário: " + conexao.getResponseCode());
        }

        String resposta = new String(conexao.getInputStream().readAllBytes());
        RandomUserDTO randomUser = objectMapper.readValue(resposta, RandomUserDTO.class);

        return randomUser;
    }
}
