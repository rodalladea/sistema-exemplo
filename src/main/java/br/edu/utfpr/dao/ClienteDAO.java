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

            log.info("Criando tabela cliente....");

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
            pstm.setInt(5, cliente.getPais());

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
            String sql = "UPDATE cliente SET nome=?, telefone=?, idade=?, limiteCredito=?, id_pais=?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTelefone());
            pstm.setInt(3, cliente.getIdade());
            pstm.setDouble(4, cliente.getLimiteCredito());
            pstm.setInt(5, cliente.getPais());

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

            String sql = "SELECT * FROM cliente";

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();

                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setTelefone(rs.getString(3));
                cliente.setIdade(rs.getInt(4));
                cliente.setLimiteCredito(rs.getDouble(5));
                cliente.setPais(rs.getInt(6));

                listCliente.add(cliente);
            }

            rs.close();
            stm.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listCliente;

    }

    public ClienteDTO getCliente(int id) {
        ClienteDTO cliente = new ClienteDTO();

        try (Connection conn = DriverManager.getConnection("jdbc:derby:database")) {

            String sql = "SELECT * FROM cliente WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setTelefone(rs.getString(3));
                cliente.setIdade(rs.getInt(4));
                cliente.setLimiteCredito(rs.getDouble(5));
                cliente.setPais(rs.getInt(6));
            }

            rs.close();
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cliente;
    }
}
