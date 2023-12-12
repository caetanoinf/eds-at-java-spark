package br.infnet.service;

import br.infnet.dto.UsuarioDTOInput;
import org.junit.Assert;
import org.junit.Test;

public class UsuarioServiceTest {
    @Test
    public void testarInsercao() {
        UsuarioService usuarioService = new UsuarioService();

        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Lucio");
        usuarioDTOInput.setSenha("123");

        usuarioService.inserirUsuario(usuarioDTOInput);

        Assert.assertEquals(1, usuarioService.listarUsuarios().size());
    }
}
