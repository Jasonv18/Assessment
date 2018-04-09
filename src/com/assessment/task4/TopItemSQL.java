package com.assessment.task4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by A221824 on 2018/04/05.
 * This solution is assuming there is a database such as sql holding the data and it is a well designed db.
 * The run method below then could be called to fetch the data required to display the top viewed item.
 * This would be in memory and avoid the need for data structures as we would only fetch the top 1 item from sql.
 */
class TopItemSQL implements Runnable{

  int timeOut;
  String sqlQuery;
  String topItem;
  boolean killSwitch = false;

  TopItemSQL(int TimeOut, String sqlQuery) {
    this.timeOut = TimeOut;
    this.sqlQuery = sqlQuery;
  }

  public void run()
  {
    while (!killSwitch)
    {
      try {
        String url = "jdbc:msql://10.0.0.1/MyConnection";
        Connection conn = DriverManager.getConnection(url, "", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        topItem = rs.getString("Product");
        wait(timeOut);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  void kill() {
    killSwitch = true;
  }
}
