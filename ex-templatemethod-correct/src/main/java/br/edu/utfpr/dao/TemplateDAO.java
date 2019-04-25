package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log
public abstract class TemplateDAO<T> {

    protected abstract String getSQLCriaTabela();
    protected final void firstConnection() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database;create=true")) {

            log.info("Criando tabela pais ...");
            conn.createStatement().executeUpdate(getSQLCriaTabela());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected final Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:derby:database");
    }

    protected abstract String getSQLInsert();
    protected abstract void setPstmInsert(PreparedStatement pstm, T objeto) throws SQLException;
    public void insert(T objeto) {
        try (Connection conn = connect()) {
            String sql = getSQLInsert();

            PreparedStatement pstm = conn.prepareStatement(sql);

            setPstmInsert(pstm, objeto);

            log.info("Inserindo pais ...");
            int rows = pstm.executeUpdate();
            if(rows > 0)
                System.out.println("Pais inserido com sucesso.");

            pstm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected abstract String getSQLRemove();
    public void remove(int id) {
        try (Connection conn = connect()) {
            String sql = getSQLRemove();

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            log.info("Removendo pais ...");
            int rows = pstm.executeUpdate();
            if (rows > 0)
                System.out.println("Pais excluido com sucesso.");

            pstm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected abstract String getSQLUpdate();
    protected abstract void setPstmUpdate(PreparedStatement pstm, T objeto) throws SQLException;
    public void update(T objeto) {
        try (Connection conn = connect()) {
            String sql = getSQLUpdate();

            PreparedStatement pstm = conn.prepareStatement(sql);

            setPstmUpdate(pstm, objeto);

            log.info("Atualizando pais ...");
            int rows = pstm.executeUpdate();
            if (rows > 0)
                System.out.println("Pais atualizado com sucesso");

            pstm.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected abstract String getSQLListPais();
    protected abstract T setRsListPais(ResultSet rs) throws SQLException;
    public List<T> getList() {
        List<T> list = new ArrayList<>();

        try (Connection conn = connect()) {

            String sql = getSQLListPais();

            Statement stm = conn.createStatement();
            log.info("Selecionando todos paises ...");
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {

                list.add(setRsListPais(rs));
            }

            rs.close();
            stm.close();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    protected abstract String getSQLGetById();
    protected abstract T setRsGetById(ResultSet rs) throws SQLException;
    public T getById(int id) {
        T objeto = null;

        try (Connection conn = connect()) {

            String sql = getSQLGetById();

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            log.info("Selecionando um pais ...");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                objeto = setRsGetById(rs);
            }

            rs.close();
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return objeto;
    }

    protected abstract String getSQLGetByNome();
    protected abstract T setRsGetByNome(ResultSet rs) throws SQLException;
    public T getByNome(String nome) {
        T objeto = null;

        try (Connection conn = connect()) {

            String sql = getSQLGetByNome();

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            log.info("Selecionando um pais ...");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                objeto = setRsGetByNome(rs);
            }

            rs.close();
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return objeto;
    }

}
