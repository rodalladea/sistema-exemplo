package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomePaisNulo;
import lombok.extern.java.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log
public class PaisDAO extends TemplateDAO<PaisDTO> {

    public PaisDAO() {
        firstConnection();
    }

    @Override
    protected String getSQLCriaTabela() {
        return "CREATE TABLE pais (" +
                "id int NOT NULL GENERATED ALWAYS AS IDENTITY," +
                "nome varchar(255)," +
                "sigla varchar(3)," +
                "codTelefone int," +
                "CONSTRAINT id_pais_pk PRIMARY KEY (id))";
    }

    @Override
    protected String getSQLInsert() {
        return "INSERT INTO pais(nome, sigla, codTelefone) VALUES (?, ?, ?)";
    }

    @Override
    protected void setPstmInsert(PreparedStatement pstm, PaisDTO objeto) throws SQLException {
        pstm.setString(1, objeto.getNome());
        pstm.setString(2, objeto.getSigla());
        pstm.setInt(3, objeto.getCodTelefone());
    }

    @Override
    protected String getSQLRemove() {
        return "DELETE FROM pais WHERE id=?";
    }

    @Override
    protected String getSQLUpdate() {
        return "UPDATE pais SET nome=?, sigla=?, codTelefone=? WHERE id=?";
    }

    @Override
    protected void setPstmUpdate(PreparedStatement pstm, PaisDTO objeto) throws SQLException {
        pstm.setString(1, objeto.getNome());
        pstm.setString(2, objeto.getSigla());
        pstm.setInt(3, objeto.getCodTelefone());
        pstm.setInt(4, objeto.getId());
    }

    @Override
    protected String getSQLListPais() {
        return "SELECT * FROM pais";
    }

    @Override
    protected PaisDTO setRsListPais(ResultSet rs) throws SQLException {
        PaisDTO pais = new PaisDTO();

        try {

            pais.setId(rs.getInt(1));
            pais.setNome(rs.getString(2));
            pais.setSigla(rs.getString(3));
            pais.setCodTelefone(rs.getInt(4));

        } catch (NomePaisNulo nomePaisNulo) {
            nomePaisNulo.printStackTrace();
        }

        return pais;
    }

    @Override
    protected String getSQLGetById() {
        return "SELECT * FROM pais WHERE id=?";
    }

    @Override
    protected PaisDTO setRsGetById(ResultSet rs) throws SQLException {
        PaisDTO pais = new PaisDTO();

        try {

            pais.setId(rs.getInt(1));
            pais.setNome(rs.getString(2));
            pais.setSigla(rs.getString(3));
            pais.setCodTelefone(rs.getInt(4));

        } catch (NomePaisNulo nomePaisNulo) {
            nomePaisNulo.printStackTrace();
        }

        return pais;
    }

    @Override
    protected String getSQLGetByNome() {
        return "SELECT * FROM pais WHERE nome=?";
    }

    @Override
    protected PaisDTO setRsGetByNome(ResultSet rs) throws SQLException {
        PaisDTO pais = new PaisDTO();

        try {

            pais.setId(rs.getInt(1));
            pais.setNome(rs.getString(2));
            pais.setSigla(rs.getString(3));
            pais.setCodTelefone(rs.getInt(4));

        } catch (NomePaisNulo nomePaisNulo) {
            nomePaisNulo.printStackTrace();
        }

        return pais;
    }
}
