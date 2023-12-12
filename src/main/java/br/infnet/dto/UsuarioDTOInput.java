package br.infnet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTOInput {
    private Integer id;
    private String nome;
    private String senha;
}
