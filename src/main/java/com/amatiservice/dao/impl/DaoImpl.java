package com.amatiservice.dao.impl;

import com.amatiservice.dao.DaoInterface;
import com.amatiservice.dao.utils.ConnectionFactory;
import com.amatiservice.model.BranchModel;
import com.amatiservice.model.ProviderFatture;
import com.amatiservice.model.RecessedModel;
import com.amatiservice.model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImpl implements DaoInterface {

    private ConnectionFactory connectionFactory;

    public DaoImpl() {
        this.connectionFactory = new ConnectionFactory();
    }
    public DaoImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }


//    private final String INSER_INTO_CE_TESTA_TABLE = "INSERT INTO ce_testa (TAVOLO, COP, DATA, FATTO) VALUES (?,?,?,?)";
//    private final String INSER_INTO_CE_CODA_TABLE = "INSERT INTO ce_coda (LINK, CODICE, DESCR, QT, PREZZOU, ID_MENU) VALUES (?,?,?,?,?,?)";
//    private final String UPDATE_CE_TESTA_TABLE = "UPDATE ce_testa SET FATTO = ? WHERE ID = ? AND FATTO = ?";
//
//    private final String INSERT_INTO_CE_HISTORY_ORDER = "INSERT INTO ce_order_history (ORDER_ID, DATE) VALUES (?,?)";
//    private final String SELECT_ALL_FROM_CE_ORDER_HISTORY = "SELECT * FROM ce_order_history WHERE ORDER_ID = ?";
//
//    private final String SELECT_CE_TESTA_BY_DATE = "SELECT * FROM ce_testa WHERE DATA BETWEEN '?' AND '?'";
//    private final String SELECT_ALL_FROM_CE_CODA = "SELECT * FROM ce_coda";
//
//    private final String SELECT_ALL_FROM_ORDINI = "SELECT id, name, date, details FROM ORDINI";
//        private static final String TEMP_VALUE = "99";
//    private static final String FINAL_VALUE = "0";


    private final String INSERT_INTO_USERS = "INSERT INTO Users(Name, LastName, Phone, Mail) VALUES (?,?,?,?)";
    private final String INSERT_INTO_RECESSED = "INSERT INTO Recessed(Description, Amount,Vat, Date, DateInsert, Fk_Branch_Id) VALUES (?,?,?,?,?,?)";
    private final String INSERT_INTO_BRANCHES = "INSERT INTO Branches(Name, Email, VatNumber, Address, Phone, Provider, IdKey_User, IdUid_Password, Fk_User_Id) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String SELECT_ALL_FROM_USER_BY_EMAIL = "SELECT Pk_User_Id, Name, LastName, Phone, Mail FROM Users WHERE Mail = ?";
    private final String SELECT_ALL_FROM_BRANCHES_BY_USER_EMAIL = "SELECT Pk_Branches_Id, Name, Email, VatNumber, Address, Phone, Provider, IdKey_User, IdUid_Password, Fk_User_Id FROM Branches WHERE Email = ?";
    private final String SELECT_ALL_FROM_RECESSED_TABLE_BY_BRANCH_ID = "SELECT Pk_Recessed_Id, Description, Amount, Vat, Date, DateInsert, Fk_Branch_Id FROM `Recessed` WHERE Fk_Branch_id = ? ORDER BY Date DESC";

    @Override
    public void setupDatasource() throws Exception {
        this.connectionFactory.setupDatasource();
    }

    @Override
    public long createUser(UserModel userModel) throws Exception {

        try (Connection connection = connectionFactory.getVat01ConnectionFromDatasource()) {

            connection.setAutoCommit(false);


            PreparedStatement pstmt = connection.prepareStatement(INSERT_INTO_USERS, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, userModel.getName());
            pstmt.setString(2, userModel.getLastName());
            pstmt.setString(3, userModel.getPhone());
            pstmt.setString(4, userModel.getMail());

            pstmt.execute();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            connection.commit();

            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            return 0;
        } catch (Exception e) {
            throw new Exception("Exception in DaoImpl. Error message: " + e.getMessage(), e);
        }

    }

    @Override
    public long createRecessedAmountForSpecificBranch(RecessedModel recessedModel) throws Exception {

        try (Connection connection = connectionFactory.getVat01ConnectionFromDatasource()) {

            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(INSERT_INTO_RECESSED, Statement.RETURN_GENERATED_KEYS);
            // INSERT INTO Recessed(Description, Amount, Date, Fk_Branch_Id) VALUES (?,?,?,?,?)";

            pstmt.setString(1, recessedModel.getDescription());
            pstmt.setDouble(2, recessedModel.getAmount());
            pstmt.setInt(3, recessedModel.getVat());
            pstmt.setTimestamp(4, recessedModel.getDateTimeRecessed());
            pstmt.setTimestamp(5, recessedModel.getDateTimeRecessedInsert());
            pstmt.setInt(6, recessedModel.getFkBranchId());


            pstmt.execute();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            connection.commit();

            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            return 0;
        } catch (Exception e) {
            throw new Exception("Exception in DaoImpl. Error message: " + e.getMessage(), e);
        }

    }

    @Override
    public List<RecessedModel> retrieveRecessedByBranchId(long pkBranchId) throws Exception {

        try (Connection connection = connectionFactory.getVat01ConnectionFromDatasource()) {

            connection.setAutoCommit(false);
            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_FROM_RECESSED_TABLE_BY_BRANCH_ID);
            pstmt.setLong(1, pkBranchId);

            pstmt.execute();
            ResultSet resultSet = pstmt.executeQuery();

            System.out.println("Retrieved branches: " + resultSet);

            List<RecessedModel> recessedModelList = new ArrayList();

            while (resultSet.next()) {

                recessedModelList.add(
                        new RecessedModel(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4),
                                resultSet.getTimestamp(5),
                                resultSet.getTimestamp(6),
                                resultSet.getInt(7)
                        )
                );
            }
            connection.commit();
            return recessedModelList;

        } catch (Exception e) {
            throw new Exception("Exception in DaoImpl. Error message: " + e.getMessage(), e);
        }
    }

    @Override
    public long createBranch(BranchModel branchModel) throws Exception {
        try (Connection connection = connectionFactory.getVat01ConnectionFromDatasource()) {

            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement(INSERT_INTO_BRANCHES,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, branchModel.getName());
            pstmt.setString(2, branchModel.geteMail());
            pstmt.setString(3, branchModel.getVatNumber());
            pstmt.setString(4, branchModel.getAddress());
            pstmt.setString(5, branchModel.getPhone());
            pstmt.setString(6, branchModel.getProvider().toString());
            pstmt.setString(7, branchModel.getIdKeyUser());
            pstmt.setString(8, branchModel.getIdUidPassword());
            pstmt.setLong(9, branchModel.getFkUserId());

            pstmt.execute();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            connection.commit();

            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }
            return 0;
        } catch (Exception e) {
            throw new Exception("Exception in DaoImpl. Error message: " + e.getMessage(), e);
        }
    }

    @Override
    public UserModel retrieveUserByEmail(String email) throws Exception {

        try (Connection connection = connectionFactory.getVat01ConnectionFromDatasource()) {

            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_FROM_USER_BY_EMAIL);

            pstmt.setString(1, email);


            pstmt.execute();
            ResultSet resultSet = pstmt.executeQuery();
            connection.commit();
            UserModel userModelResult = new UserModel();
            System.out.println("Retrieved user: " + resultSet);
            if (resultSet.next()) {
                userModelResult.setId(resultSet.getInt(1));
                userModelResult.setName(resultSet.getString(2));
                userModelResult.setLastName(resultSet.getString(3));
                userModelResult.setPhone(resultSet.getString(4));
                userModelResult.setMail(resultSet.getString(5));
            }
            System.out.println("Retrieved user: " + userModelResult.toString());
            return userModelResult;

        } catch (Exception e) {
            throw new Exception("Exception in DaoImpl. Error message: " + e.getMessage(), e);
        }
    }

    @Override
    public List<BranchModel> retrieveBranchesByEmail(String email) throws Exception {


        try (Connection connection = connectionFactory.getVat01ConnectionFromDatasource()) {

            connection.setAutoCommit(false);

            PreparedStatement pstmt = connection.prepareStatement(SELECT_ALL_FROM_BRANCHES_BY_USER_EMAIL);
            pstmt.setString(1, email);

            pstmt.execute();
            ResultSet resultSet = pstmt.executeQuery();


            System.out.println("Retrieved branches: " + resultSet);

            List<BranchModel> branchModelList = new ArrayList();

            // Pk_Branches_Id, Name, Email, VatNumber, Address, Phone, Provider, IdKey_User, IdUid_Password, Fk_User_Id

            while (resultSet.next()) {

                branchModelList.add(
                        new BranchModel(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                ProviderFatture.valueOf(resultSet.getString(7)),
                                resultSet.getString(8),
                                resultSet.getString(9),
                                resultSet.getInt(10)
                        )
                );
            }
            connection.commit();
            return branchModelList;

        } catch (Exception e) {
            throw new Exception("Exception in DaoImpl. Error message: " + e.getMessage(), e);
        }
    }


}
