package org.example;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ClientResponse;
import org.voltdb.client.ProcedureCallback;
import org.voltdb.VoltTable;
//"username ","secret"
public class Main {
    public static void main(String[] args) {
        // Configuration for the VoltDB client
        ClientConfig config = new ClientConfig();
        Client client = ClientFactory.createClient(config);

        try {
            // Connect to the VoltDB server
            client.createConnection("localhost:32769");

            // Call the stored procedure
            ClientResponse response = client.callProcedure("PROC");

            // Check the response status
            if (response.getStatus() == ClientResponse.SUCCESS) {
                VoltTable[] results = response.getResults();

                // Print the results
                for (VoltTable table : results) {
                    while (table.advanceRow()) {
                        System.out.printf("SUBSC_ID: %d, SUBSC_NAME: %s, SUBSC_SURNAME: %s, MSISDN: %s, TARIFF_ID: %d, START_DATE: %s%n",
                                table.getLong("SUBSC_ID"),
                                table.getString("SUBSC_NAME"),
                                table.getString("SUBSC_SURNAME"),
                                table.getString("MSISDN"),
                                table.getLong("TARIFF_ID"),
                                table.getTimestampAsTimestamp("START_DATE"));
                    }
                }
            } else {
                System.err.println("Procedure call failed: " + response.getStatusString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the client connection
                client.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
