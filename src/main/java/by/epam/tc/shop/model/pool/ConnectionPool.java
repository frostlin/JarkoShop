package by.epam.tc.shop.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    private final Logger logger = LogManager.getLogger();

    private static final int POOL_SIZE = 10;

    private final BlockingDeque<ProxyConnection> freeConnections;
    private final BlockingDeque<ProxyConnection> busyConnections;

    ConnectionPool() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        try {
            String driverName = databaseConfig.getDriverName();
            String url = databaseConfig.getUrl();
            String username = databaseConfig.getUsername();
            String password = databaseConfig.getPassword();
            Class.forName(driverName);

            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            busyConnections = new LinkedBlockingDeque<>();
            for (int i = 0; i < POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(url, username, password);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.fatal("Cant register driver", e);
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            busyConnections.put(connection);
        } catch (InterruptedException e) {
            logger.warn("Thread was interrupted", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            busyConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.error("Connection {} is not instance of ProxyConnection", connection);
        }
    }

    public void destroyPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().proxyClose();
            } catch (InterruptedException | SQLException e) {
                logger.warn("Connection is not deleted");
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Driver wasn't deregister");
            }
        });
    }


}
