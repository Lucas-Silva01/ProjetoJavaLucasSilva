package br.com.escolaweb.controller;

import br.com.escolaweb.model.Funcionario;
import br.com.escolaweb.services.FuncionarioService;

public class FuncionarioController {
    public void cadastrar(Funcionario funcionario) throws Exception {
        if (funcionario.getNome().equals(null)) {
            throw new Exception("Nome embranco!");
        }

        if (funcionario.getEmail().equals(null)) {
            throw new Exception("E-mail embranco!");
        } else if (funcionario.getEmail().indexOf("@") == -1
                || funcionario.getEmail().indexOf(".") == -1) {
            throw new Exception("E-mail invalida!");
        }

        if (funcionario.getSenha().equals(null)) {
            throw new Exception("Senha embranco!");
        } else if (funcionario.getEmail().length() < 8) {
            throw new Exception("Senha invalida!");
        }

        // if (funcionario.getDataNasc().equals(null)) {
        // throw new Exception("Data de nascimento embranco!");
        // }

        FuncionarioService funcionarioservice = new FuncionarioService();
        funcionarioservice.add(funcionario);

    }
}

