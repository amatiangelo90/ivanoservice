package com.amaticorp.test;

import com.amaticorp.test.model.ModelQuery;
import com.amatiservice.dao.impl.DaoImpl;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUnit {

    @Test
    public void testDao() throws Exception {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://51.77.174.68:3306/ventimq_newfood?serverTimezone=Europe/Rome","ventimq_user1","TycAsnOeL");


            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM ce_coda WHERE LINK IN (SELECT ID FROM ce_testa WHERE DATA BETWEEN '2021-08-31' AND '2021-09-06')");

            List<ModelQuery> queryList = new ArrayList<>();

            while(rs.next()){
                queryList.add(new ModelQuery(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6)
                        ));
            }

            Map<String, Double> map = new HashMap<>();

            for(int i = 0; i < queryList.size(); i++) {

                if(map.containsKey(queryList.get(i).getDescr())){
                    double currentQt = queryList.get(i).getQt() + map.get(queryList.get(i).getDescr());
                    map.put(queryList.get(i).getDescr(), currentQt);
                }else{
                    map.put(queryList.get(i).getDescr(), queryList.get(i).getQt());
                }

            }
            System.out.println(map);
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
