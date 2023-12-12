package br.infnet.service;

import br.infnet.dto.UsuarioDTOInput;
import br.infnet.dto.UsuarioDTOOutput;
import br.infnet.model.Usuario;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {
    private final ModelMapper modelMapper = new ModelMapper();
    private final List<Usuario> listaUsuarios = new ArrayList<Usuario>();

    public List<UsuarioDTOOutput> listarUsuarios() {
        Type listType = new TypeToken<List<UsuarioDTOOutput>>(){}.getType();
        return modelMapper.map(listaUsuarios, listType);
    }

    public void inserirUsuario(UsuarioDTOInput usuarioInput) {
        Usuario usuario = modelMapper.map(usuarioInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public UsuarioDTOOutput buscarUsuario(Integer id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId().equals(id)) {
                return modelMapper.map(usuario, UsuarioDTOOutput.class);
            }
        }
        return null;
    }

    public void alterarUsuario(UsuarioDTOInput usuarioInput) {
        int index = 0;

        for (Usuario usuarioLista : listaUsuarios) {
            if (usuarioLista.getId().equals(usuarioInput.getId())) {
                Usuario usuario = modelMapper.map(usuarioInput, Usuario.class);
                listaUsuarios.set(index, usuario);
                break;
            }
            index++;
        }
    }

    public void excluirUsuario(Integer id) {
        for (Usuario usuarioLista : listaUsuarios) {
            if (usuarioLista.getId().equals(id)) {
                listaUsuarios.remove(usuarioLista);
                break;
            }
        }
    }
}
