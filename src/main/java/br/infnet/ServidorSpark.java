package br.infnet;

import br.infnet.controller.UsuarioController;
import lombok.Getter;

@Getter
public class ServidorSpark {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.respostasRequisicoes();
    }
}
