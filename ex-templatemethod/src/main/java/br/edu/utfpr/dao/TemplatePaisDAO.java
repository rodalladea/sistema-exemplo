package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Log
public abstract class TemplatePaisDAO {

    protected final void firstConnection(String sql) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(sql);


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected final Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:database");
    }

    protected abstract void insertPais(PaisDTO pais);

    protected abstract void removePais(int id);

    protected abstract void updatePais(PaisDTO pais);

    protected abstract List<PaisDTO> getListPais();

    protected abstract PaisDTO getPaisById(int id);

    protected abstract PaisDTO getPaisByNome(String nome);

}
