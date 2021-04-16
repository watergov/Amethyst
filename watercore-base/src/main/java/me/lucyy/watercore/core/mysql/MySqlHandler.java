package me.lucyy.watercore.core.mysql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.lucyy.watercore.core.WaterCorePlugin;
import org.bukkit.Bukkit;

/**
 * @author Skippysunday
 * @version 1
 * <p>
 * This class handles a connection to an external MySQL database server. This is a raw connection,
 * so no parsing of the data is done.
 * @since 1.0.0-SNAPSHOT
 */
public class MySqlHandler {

  private Connection connection;

  //For accessing main config file
  private final WaterCorePlugin p = new WaterCorePlugin();

  /**
   * Simple constructors with no parameters. Data for connection such as username and password are
   * taken from external configuration file
   */
  public MySqlHandler() {

    String database = p.getConfig().getString("mysql.database");
    String ip = p.getConfig().getString("mysql.ip");
    String port = p.getConfig().getString("mysql.port");
    String username = p.getConfig().getString("mysql.username");
    String password = p.getConfig().getString("mysql.password");

    try {
      if (p.getConfig().getBoolean("mysql.enabled")) {
        Class.forName("com.mysql.jdbc.Driver");
        connection = (Connection) DriverManager
            .getConnection(String.format(
                "jdbc:mysql://%s:%s/%s?useServerPrepStmts=false&rewriteBatchedStatements=true", ip,
                port, database), username, password);
      }
    } catch (SQLException | ClassNotFoundException e) {
      Bukkit.getLogger().info("Could not log into MySQL database. Verify login info!");
      e.printStackTrace();
    }
  }

  /**
   * @throws SQLException Closes connection to database. Called on plugin disable.
   */
  public void close() throws SQLException {
    if (p.getConfig().getBoolean("mysql.enabled")) {
      connection.close();
    }
  }

  /**
   * @param statement MySQL statement to be passed to the database
   * @return ResultSet
   * @throws SQLException Returns ResultSet of query passed in as String.
   */
  public ResultSet getInfo(String statement) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(statement);
    return ps.executeQuery();
  }

  /**
   * @param statement Statement to be passed to the database Sends update to external MySQL server,
   *                  statement passed in as String.
   * @throws SQLException Thrown when connection error occurrs, or when data cannot be sent
   *                      properly
   */
  public void sendInfo(String statement) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(statement);
    ps.executeUpdate();
  }
}

