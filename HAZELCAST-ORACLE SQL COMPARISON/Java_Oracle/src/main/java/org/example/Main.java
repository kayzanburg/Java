import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String DB_URL = "jdbc:oracle:thin:@localhost:1521:TEST";
        String USER = "SYSTEM";
        String PASS = "123";

        // Oracle JDBC sürücüsünü kaydetme
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("Oracle JDBC Sürücüsü bulunamadı. JDBC jar dosyasını classpath'e ekleyin.");
            e.printStackTrace();
            return;
        }

        // Bağlantı oluşturma
        try {
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);
            props.setProperty("oracle.jdbc.defaultNChar", "true");
            props.setProperty("oracle.jdbc.convertNcharLiterals", "true");
            props.setProperty("NLS_LANG", "TURKISH_TURKEY.WE8ISO8859P9");

            conn = DriverManager.getConnection(DB_URL, props);

            if (conn != null) {
                System.out.println("Oracle DB'ye bağlanıldı!");

                //***********************************

                stmt = conn.createStatement();
                String sql = "CREATE TABLE RANDOMNUMBER " +
                        "(ID INTEGER GENERATED ALWAYS AS IDENTITY, " +
                        " VALUE INTEGER, " +
                        " PRIMARY KEY (ID))";

                stmt.executeUpdate(sql);
                System.out.println("SQL Table created successfully...");

                //------------------------------------
                //------------------------------------
                String insertSQL = "INSERT INTO RANDOMNUMBER (VALUE) VALUES (?)";
                pstmt = conn.prepareStatement(insertSQL);
                Random rand = new Random();

                long startInsertTime = System.currentTimeMillis();
                for (int i = 0; i < 20000; i++) {
                    int randomValue = rand.nextInt();
                    pstmt.setInt(1, randomValue);
                    pstmt.executeUpdate();
                }
                long endInsertTime = System.currentTimeMillis();
                long insertDuration = endInsertTime - startInsertTime;
                System.out.println("20000 - Insert işlemi süresi: " + insertDuration + " ms");

                // Verileri seç ve zamanı hesapla
                String selectSQL = "SELECT * FROM RANDOMNUMBER";
                long startSelectTime = System.currentTimeMillis();
                rs = stmt.executeQuery(selectSQL);
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    int value = rs.getInt("VALUE");
                    // Verileri işlemek için burada kod ekleyebilirsiniz
                }
                long endSelectTime = System.currentTimeMillis();
                long selectDuration = endSelectTime - startSelectTime;
                System.out.println("20000 - Select işlemi süresi: " + selectDuration + " ms");
                //------------------------------------
                //------------------------------------

                //***********************************

            } else {
                System.out.println("Bağlantı kurulamadı!");
            }

        } catch (SQLException e) {
            System.err.format("SQL Durumu: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // rs nesnesi başlatıldıysa kapatma
            if (rs != null) {
                rs.close();
            }
            // pstmt nesnesi başlatıldıysa kapatma
            if (pstmt != null) {
                pstmt.close();
            }
            // stmt nesnesi başlatıldıysa kapatma
            if (stmt != null) {
                stmt.close();
            }
            // conn nesnesini kapatma
            if (conn != null) {
                conn.close();
            }
        }
    }
}
