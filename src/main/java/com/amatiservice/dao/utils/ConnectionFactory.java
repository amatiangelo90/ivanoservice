package com.amatiservice.dao.utils;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {

//    private static final String JNDI_TERRAZZAMENTI = "java:/terrazzamentidb";
    private static final String JNDI_TEST_01 = "java:/test_db_01";
//    private DataSource terrazzamentiDatasource;
    private DataSource vat01Datasource;
    private InitialContext ctx;

    public void setupDatasource() throws Exception {

        try {
            System.out.println("setupdb");
            this.ctx = new InitialContext();
            this.vat01Datasource = (DataSource) ctx.lookup(JNDI_TEST_01);
        } catch (Exception e) {
            throw new Exception("Error performing lookup for datasources - " + e.getMessage(), e);
        }

        if(vat01Datasource == null){
            throw new Exception("Terrazzamenti datasource is not valorized! Initialization will be aborted!");
        }
    }
//
//    public Connection getTerrazzamentiConnectionFromDatasource() throws Exception {
//        try {
//            if(terrazzamentiDatasource == null){
//                System.out.println("Error - Unable to create connection. UdbDataSource is null.");
//                throw new Exception("Error - Unable to create connection. UdbDataSource is null.");
//            }
//            Connection connection = terrazzamentiDatasource.getConnection();
//            if(connection != null
//                    && !connection.isClosed()) {
//                return connection;
//            } else {
//                System.out.println("Connection is NOT OK. Closing it");
//                try {
//                    connection.close();
//                } catch(Exception e) {
//                    System.out.println("Error closing connection..." + e);
//                }
//                throw new Exception("Error - Connection closed ");
//            }
//        } catch (Exception e) {
//            System.out.println("Error - " + e);
//            throw new Exception("Error - ", e);
//        }
//    }

    public Connection getVat01ConnectionFromDatasource() throws Exception {
        try {
            if(vat01Datasource == null){
                System.out.println("Error - Unable to create connection. UdbDataSource is null.");
                throw new Exception("Error - Unable to create connection. UdbDataSource is null.");
            }
            Connection connection = vat01Datasource.getConnection();
            if(connection != null
                    && !connection.isClosed()) {
                return connection;
            } else {
                System.out.println("Connection is NOT OK. Closing it");
                try {
                    connection.close();
                } catch(Exception e) {
                    System.out.println("Error closing connection..." + e);
                }
                throw new Exception("Error - Connection closed ");
            }
        } catch (Exception e) {
            System.out.println("Error - " + e);
            throw new Exception("Error - ", e);
        }
    }


}
