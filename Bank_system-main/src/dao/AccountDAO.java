package dao;

import model.Account;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public void addAccount(Account account) {
        String sql = "INSERT INTO Account (accountID, customerID, type, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, account.getAccountID());
            ps.setInt(2, account.getCustomerID());
            ps.setString(3, account.getType());
            ps.setDouble(4, account.getBalance());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Account> getAccountsByCustomerId(int customerID) {
        String sql = "SELECT * FROM Account WHERE customerID = ?";
        List<Account> accounts = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("accountID"),
                        rs.getInt("customerID"),
                        rs.getString("type"),
                        rs.getDouble("balance")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void updateAccountBalance(int accountID, double newBalance) {
        String sql = "UPDATE Account SET balance = ? WHERE accountID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, accountID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
