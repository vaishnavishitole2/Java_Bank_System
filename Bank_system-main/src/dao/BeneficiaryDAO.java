package dao;

import model.Beneficiary;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BeneficiaryDAO {
    public void addBeneficiary(Beneficiary beneficiary) {
        String sql = "INSERT INTO Beneficiary (beneficiaryID, customerID, name, accountNumber, bankDetails) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, beneficiary.getBeneficiaryID());
            ps.setInt(2, beneficiary.getCustomerID());
            ps.setString(3, beneficiary.getName());
            ps.setString(4, beneficiary.getAccountNumber());
            ps.setString(5, beneficiary.getBankDetails());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Beneficiary> getBeneficiariesByCustomerId(int customerID) {
        String sql = "SELECT * FROM Beneficiary WHERE customerID = ?";
        List<Beneficiary> beneficiaries = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                beneficiaries.add(new Beneficiary(
                        rs.getInt("beneficiaryID"),
                        rs.getInt("customerID"),
                        rs.getString("name"),
                        rs.getString("accountNumber"),
                        rs.getString("bankDetails")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return beneficiaries;
    }
}
