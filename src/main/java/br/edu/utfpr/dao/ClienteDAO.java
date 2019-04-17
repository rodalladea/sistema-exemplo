package br.edu.utfpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;
import lombok.extern.java.Log;

@Log
public class ClienteDAO {

    public ClienteDAO() {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database;create=true")) {

            log.info("Criando tabela cliente ...");
            conn.createStatement().execute("CREATE TABLE cliente (" +
                    "id int NOT NULL GENERATED ALWAYS AS IDENTITY," +
                    "nome varchar(255)," +
                    "telefone varchar(30)," +
                    "idade int," +
                    "limiteCredito double," +
                    "id_pais int," +
                    "CONSTRAINT id_cliente_pk PRIMARY KEY (id))");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void insertCliente(ClienteDTO cliente) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database")) {
            String sql = "INSERT INTO cliente(nome, telefone, idade, limiteCredito, id_pais) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTelefone());
            pstm.setInt(3, cliente.getIdade());
            pstm.setDouble(4, cliente.getLimiteCredito());
            pstm.setInt(5, cliente.getPais().getId());

            log.info("Inserindo cliente ...");
            int rows = pstm.executeUpdate();
            if(rows > 0)
                System.out.println("Cliente inserido com sucesso.");

            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeCliente(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database")) {
            String sql = "DELETE FROM cliente WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            log.info("Removendo cliente ...");
            int rows = pstm.executeUpdate();
            if (rows > 0)
                System.out.println("Cliente excluido com sucesso.");

            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateCliente(ClienteDTO cliente) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:database")) {
            String sql = "UPDATE cliente SET nome=?, telefone=?, idade=?, limiteCredito=?, id_pais=? WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTelefone());
            pstm.setInt(3, cliente.getIdade());
            pstm.setDouble(4, cliente.getLimiteCredito());
            pstm.setInt(5, cliente.getPais().getId());
            pstm.setInt(6, cliente.getId());

            log.info("Atualizando cliente ...");
            int rows = pstm.executeUpdate();
            if (rows > 0)
                System.out.println("Cliente atualizado com sucesso");

            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<ClienteDTO> getListCliente() {
        List<ClienteDTO> listCliente = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:derby:database")) {

            PaisDAO paisDao = new PaisDAO();
            String sql = "SELECT * FROM cliente";

            Statement stm = conn.createStatement();
            log.info("Selecionando todos clientes ...");
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();

                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setTelefone(rs.getString(3));
                cliente.setIdade(rs.getInt(4));
                cliente.setLimiteCredito(rs.getDouble(5));
                cliente.setPais(paisDao.getPaisById(rs.getInt(6)));

                listCliente.add(cliente);
            }

            rs.close();
            stm.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listCliente;

    }

    public ClienteDTO getClienteById(int id) {
        ClienteDTO cliente = new ClienteDTO();

        try (Connection conn = DriverManager.getConnection("jdbc:derby:database")) {

            PaisDAO paisDao = new PaisDAO();
            String sql = "SELECT * FROM cliente WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            log.info("Selecionando um cliente ...");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setTelefone(rs.getString(3));
                cliente.setIdade(rs.getInt(4));
                cliente.setLimiteCredito(rs.getDouble(5));
                cliente.setPais(paisDao.getPaisById(rs.getInt(6)));
            }

            rs.close();
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }

    public ClienteDTO getClienteByNome(String nome) {
        ClienteDTO cliente = new ClienteDTO();

        try (Connection conn = DriverManager.getConnection("jdbc:derby:database")) {

            PaisDAO paisDao = new PaisDAO();
            String sql = "SELECT * FROM cliente WHERE nome=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, nome);
            log.info("Selecionando um cliente ...");
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setTelefone(rs.getString(3));
                cliente.setIdade(rs.getInt(4));
                cliente.setLimiteCredito(rs.getDouble(5));
                cliente.setPais(paisDao.getPaisById(rs.getInt(6)));
            }

            rs.close();
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }
}
