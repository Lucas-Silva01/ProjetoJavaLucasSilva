package br.com.escolaweb.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.escolaweb.model.Funcionario;
import br.com.escolaweb.util.Encrypt;

public class FuncionarioService extends DAO {

    public void add(Funcionario funcionario) throws Exception {
        String id = Encrypt.sha256(funcionario.getEmail(), true);
        String cargo = String.valueOf(new Date().getTime());
        funcionario.setId(id);
        funcionario.setCargo(cargo);
        // Pessoa
        PessoaService pessoaService = new PessoaService();
        pessoaService.add(funcionario);
        // Funcionario
        String sql = "insert into funcionario (_id, _id_pessoa, cargo) values (?, ?, ?)";
        Conectar();
        PreparedStatement stman = conn.prepareStatement(sql);
        stman.setString(1, Encrypt.sha256(funcionario.getId()));
        stman.setString(2, funcionario.getId());
        stman.setString(3, cargo);
        stman.execute();
        stman.close();
    }

    public List<Funcionario> getAll() throws SQLException {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT pessoa._id, pessoa.nome, pessoa.email, pessoa.data_nasc, pessoa.cadastro, pessoa.ativo, funcionario.cargo, funcionario._id as _id_aluno FROM funcionario, pessoa WHERE funcionario._id_pessoa = pessoa._id;";
        Conectar();
        PreparedStatement stman = conn.prepareStatement(sql);
        ResultSet result = stman.executeQuery();
        while (result.next()) {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(result.getString("_id"));
            funcionario.setNome(result.getString("nome"));
            funcionario.setEmail(result.getString("email"));
            funcionario.setCargo(result.getString("cargo"));
            funcionarios.add(funcionario);
        }
        stman.close();
        return funcionarios;
    }

}
