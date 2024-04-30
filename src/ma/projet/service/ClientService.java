package ma.projet.service;

import ma.projet.beans.client;
import ma.projet.dao.IDao;
import ma.projet.connexion.connexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements IDao<client> {
    @Override
    public boolean create(client o) {
        try (Connection connection = connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO client (nom, prenom) VALUES (?, ?)")) {
            statement.setString(1, o.getNom());
            statement.setString(2, o.getPrenom());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(client o) {
        try (Connection connection = connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM client WHERE id = ?")) {
            statement.setInt(1, o.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(client o) {
        try (Connection connection = connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE client SET nom = ?, prenom = ? WHERE id = ?")) {
            statement.setString(1, o.getNom());
            statement.setString(2, o.getPrenom());
            statement.setInt(3, o.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public client findById(int id) {
        try (Connection connection = connexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<client> findAll() {
        List<client> clients = new ArrayList<>();
        try (Connection connection = connexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM client")) {
            while (resultSet.next()) {
                clients.add(new client(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}