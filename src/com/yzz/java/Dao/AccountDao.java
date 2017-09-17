package com.yzz.java.Dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.yzz.java.bean.Account;
import com.yzz.java.util.Log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


/**
 * Created by yzz on 2017/9/10.
 * mail:yzzstyle@163.com
 */

public class AccountDao {
    public static void main(String[] args){
        try {
            Account sendmoney = new Account();
            sendmoney.setAcc_name("尹忠政");
            Account receiveMoney = new Account();
            receiveMoney.setAcc_name("张磊");
            transef(sendmoney,receiveMoney,100);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void transef(Account sendmoney,Account receiveMoney,double money){
        Connection connection = null;
        try {
            DataSource dataSource = new ComboPooledDataSource("yzz");
             connection = dataSource.getConnection();
             QueryRunner runner = new QueryRunner();

            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            //查询金额
            String checkMoney = "select acc_money from account where acc_name='"+sendmoney.getAcc_name()+"'";
            ResultSet set = statement.executeQuery(checkMoney);
            double m = 0.0;
            if (set.next()){
                m = set.getDouble("acc_money");
            }
            if (m<money){
                Log.e("余额不足");
                return;
            }
            String acc = "update account set acc_money = "+(m-money)+"where acc_name = '"+sendmoney.getAcc_name()+"'";
            statement.executeUpdate(acc);
            String getMoney = "select acc_money from account where acc_name='"+receiveMoney.getAcc_name()+"'";
            double mm = 0.0 ;
            ResultSet s = statement.executeQuery(getMoney);
            if (s.next()){
                mm = s.getDouble("acc_money");
            }
            String add = "update account set acc_money = "+(mm+money)+"where acc_name = '"+receiveMoney.getAcc_name()+"'";
            statement.executeUpdate(add);

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
