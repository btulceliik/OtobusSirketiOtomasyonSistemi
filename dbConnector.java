    package com.example.otobussirketiotomasyonu;

       import java.sql.Connection;
       import java.sql.DriverManager;
       import java.sql.SQLException;

    public class dbConnector {

    private static Connection connection;

    public static Connection getConnection() {
        String connectionURL = "jdbc:sqlserver://localhost:1434;databaseName=TerminalVeritabani;user=sa;password=1;encrypt=true;trustServerCertificate=true";

        try {
            // SQL Server JDBC sürücüsünü açıkça yükle
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            connection = DriverManager.getConnection(connectionURL);
            System.out.println("Bağlantı başarılı");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Bağlanılamadı");
            e.printStackTrace();
        }
        return connection;
    }
}
