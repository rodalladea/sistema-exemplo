package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public class PaisDAO {

    public PaisDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate("CREATE TABLE pais (" +
                            "id int NOT NULL GENERATED ALWAYS AS IDENTITY," +
                            "nome varchar(255)," +
                            "sigla varchar(3)," +
                            "codTelefone int," +
                            "CONSTRAINT id_pais_pk PRIMARY KEY (id))");


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:database");
    }

    public void insertPais(PaisDTO pais) {
        try (Connection conn = getConn()) {
            String sql = "INSERT INTO pais(nome, sigla, codTelefone) VALUES (?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, pais.getNome());
            pstm.setString(2, pais.getSigla());
            pstm.setInt(3, pais.getCodTelefone());

            log.info("Inserindo pais ...");
            int rows = pstm.executeUpdate();
            if(rows > 0)
                System.out.println("Pais inserido com sucesso.");

            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removePais(int id) {
        try (Connection conn = getConn()) {
            String sql = "DELETE FROM pais WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            log.info("Removendo pais ...");
            int rows = pstm.executeUpdate();
            if (rows > 0)
                System.out.println("Pais excluido com sucesso.");

            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updatePais(PaisDTO pais) {
        try (Connection conn = getConn()) {
            String sql = "UPDATE pais SET nome=?, sigla=?, codTelefone=? WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, pais.getNome());
            pstm.setString(2, pais.getSigla());
            pstm.setInt(3, pais.getCodTelefone());
            pstm.setInt(4, pais.getId());

            log.info("Atualizando pais ...");
            int rows = pstm.executeUpdate();
            if (rows > 0)
                System.out.println("Pais atualizado com sucesso");

            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<PaisDTO> getListPais() {
        List<PaisDTO> listPais = new ArrayList<>();

        try (Connection conn = getConn()) {

            String sql = "SELECT * FROM pais";

            Statement stm = conn.createStatement();
            log.info("Selecionando todos paises ...");
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                PaisDTO pais = new PaisDTO();

                pais.setId(rs.getInt(1));
                pais.setNome(rs.getString(2));
                pais.setSigla(rs.getString(3));
                pais.setCodTelefone(rs.getInt(4));

                listPais.add(pais);
            }

            rs.close();
            stm.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listPais;

    }

    public PaisDTO getPaisById(int id) {
        PaisDTO pais = new PaisDTO();

        try (Connection conn = getConn()) {

            String sql = "SELECT * FROM pais WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            log.info("Selecionando um pais ...");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                pais.setId(rs.getInt(1));
                pais.setNome(rs.getString(2));
                pais.setSigla(rs.getString(3));
                pais.setCodTelefone(rs.getInt(4));
            }

            rs.close();
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pais;
    }

    public PaisDTO getPaisByNome(String nome) {
        PaisDTO pais = new PaisDTO();

        try (Connection conn = getConn()) {

            String sql = "SELECT * FROM pais WHERE nome=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            log.info("Selecionando um pais ...");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                pais.setId(rs.getInt(1));
                pais.setNome(rs.getString(2));
                pais.setSigla(rs.getString(3));
                pais.setCodTelefone(rs.getInt(4));
            }

            rs.close();
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pais;
    }
}
