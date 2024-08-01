package org.example;

import org.apache.ignite.Ignition;
import org.apache.ignite.Ignite;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.query.QueryCursor;

import java.sql.Timestamp;
import java.util.List;

public class IgniteExample {
    public static void main(String[] args) {
        try (Ignite ignite = Ignition.start()) {
            // Cache konfigürasyonunu oluştur
            CacheConfiguration<Integer, Object> cacheCfg = new CacheConfiguration<>("SUBSCRIBER_CACHE");
            cacheCfg.setCacheMode(CacheMode.PARTITIONED);
            cacheCfg.setAtomicityMode(CacheAtomicityMode.ATOMIC);

            // Cache'i oluştur veya var olanı al
            ignite.getOrCreateCache(cacheCfg);

            // Tablo oluşturma sorgusu
            String createTableSql = "CREATE TABLE IF NOT EXISTS SUBSCRIBER " +
                    "(SUBSC_ID INT PRIMARY KEY, " +
                    "SUBSC_NAME VARCHAR, " +
                    "SUBSC_SURNAME VARCHAR, " +
                    "MSISDN VARCHAR, " +
                    "TARIFF_ID INT, " +
                    "START_DATE TIMESTAMP)";

            System.out.println("\n");

            // SQL sorgusunu çalıştır
            SqlFieldsQuery createTableQuery = new SqlFieldsQuery(createTableSql);
            ignite.cache("SUBSCRIBER_CACHE").query(createTableQuery).getAll();

            // Veri ekleme
            String insertDataSql = "INSERT INTO SUBSCRIBER (SUBSC_ID, SUBSC_NAME, SUBSC_SURNAME, MSISDN, TARIFF_ID, START_DATE) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            // 10 ayrı veri ekleme işlemi
            SqlFieldsQuery insertDataQuery1 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(1, "Alice", "Smith", "555-0101", 1, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery1).getAll();

            SqlFieldsQuery insertDataQuery2 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(2, "Bob", "Johnson", "555-0102", 2, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery2).getAll();

            SqlFieldsQuery insertDataQuery3 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(3, "Charlie", "Williams", "555-0103", 3, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery3).getAll();

            SqlFieldsQuery insertDataQuery4 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(4, "David", "Brown", "555-0104", 4, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery4).getAll();

            SqlFieldsQuery insertDataQuery5 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(5, "Eva", "Davis", "555-0105", 1, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery5).getAll();

            SqlFieldsQuery insertDataQuery6 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(6, "Frank", "Miller", "555-0106", 2, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery6).getAll();

            SqlFieldsQuery insertDataQuery7 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(7, "Grace", "Wilson", "555-0107", 3, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery7).getAll();

            SqlFieldsQuery insertDataQuery8 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(8, "Hannah", "Moore", "555-0108", 4, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery8).getAll();

            SqlFieldsQuery insertDataQuery9 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(9, "Ivy", "Taylor", "555-0109", 1, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery9).getAll();

            SqlFieldsQuery insertDataQuery10 = new SqlFieldsQuery(insertDataSql)
                    .setArgs(10, "Jack", "Anderson", "555-0110", 2, new Timestamp(System.currentTimeMillis()));
            ignite.cache("SUBSCRIBER_CACHE").query(insertDataQuery10).getAll();

            // SQL sorgusu oluştur
            String selectSql = "SELECT * FROM SUBSCRIBER";

            // SQL sorgusunu çalıştır
            SqlFieldsQuery selectQuery = new SqlFieldsQuery(selectSql);

            // Sorguyu çalıştır ve sonuçları al
            QueryCursor<List<?>> cursor = ignite.cache("SUBSCRIBER_CACHE").query(selectQuery);
            List<List<?>> results = cursor.getAll();

            // Sonuçları yazdır
            for (List<?> row : results) {
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
