package me.lucyy.watercore.core.mysql;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import me.lucyy.watercore.core.WaterCorePlugin;
import org.bukkit.Bukkit;

/**
 * @author Skippysunday
 * @version 1 This class handles a connection to an external MySQL database server through the
 *      Hikari Connection Pool. This is a raw connection, so no parsing of the data is done.
 * @since 1.0.0-SNAPSHOT
 */
public class MySqlHandler {

	private HikariDataSource hikari;

	/**
	 * Initializes a Hikari Connection Pool with the parameters passed in the config.yml
	 */
	public MySqlHandler() {
		final WaterCorePlugin w = new WaterCorePlugin();
		if (w.getConfig().getBoolean("mysql.enabled")) {
			hikari = new HikariDataSource();
			hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
			hikari.addDataSourceProperty("user", w.getConfig().getString("mysql.username"));
			hikari.addDataSourceProperty("password", w.getConfig().getString("mysql.password"));
			hikari.addDataSourceProperty("port", w.getConfig().getString("mysql.port"));
			hikari.addDataSourceProperty("databaseName", w.getConfig().getString("mysql.database"));
			hikari.addDataSourceProperty("serverName", w.getConfig().getString("mysql.ip"));
		} else {
			Bukkit.getLogger().info("[WaterCore] MySQL is not enabled!");
		}
	}

	/**
	 * @param statement MySQL statement to be executed Sends update statement to MySQL server
	 */
	public void sendUpdate(String statement) {
		Connection connection = null;
		PreparedStatement p = null;

		try {
			connection = hikari.getConnection();
			p = connection.prepareStatement(statement);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (p != null) {
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param statement MySQL statement to be executed
	 * @return ResultSet Returns resultset of data
	 */
	public ResultSet getInfo(String statement) {

		Connection connection = null;
		PreparedStatement p = null;

		try {
			connection = hikari.getConnection();
			p = connection.prepareStatement(statement);
			return p.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (p != null) {
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * Closes hikari
	 */
	public void close() {
		if (!hikari.isClosed()) {
			hikari.close();
		}
	}

}