package customersupportserver;

import java.rmi.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Tay Fen Yee A0138708X
 */

public class Main {
    public static void main(String args[]) {
        try {
            // Make rmi URL to name AddServer
            String customerSupportServerURL;
            customerSupportServerURL = "rmi://" + args[0] + "/CustomerSupportServer";
            
            // Obtain a reference to that remote object
            CustomerSupportServer customerSupportServer;
            customerSupportServer = (CustomerSupportServer) Naming.lookup(customerSupportServerURL);
            
            Scanner sc = new Scanner(System.in);
            int custID = 0;
            String reqDesc = "";
            String custName = "";
            ArrayList<Integer> requestID;
            
            while (!custName.equalsIgnoreCase("Q")) {
                System.out.print("Enter customer name ('*' for all request details, 'Q' or 'q' to exit): ");
                custName = sc.nextLine().trim();

            // Invoke remote method and display result
            
                if (custName.equalsIgnoreCase("Q")) {
                    System.out.println("\nTerminated. Exciting...");
                    System.exit(0);
                } else if (custName.equalsIgnoreCase("*")) {
                    requestID = customerSupportServer.getRequestIDs(0);
                    for (int i = 0; i < requestID.size(); i++) {
                        reqDesc = customerSupportServer.getRequestDesc(requestID.get(i));
                        System.out.println("Request ID: " + requestID.get(i) + " *** " + reqDesc);
                    }
                } else {
                    custID = customerSupportServer.getCustomerID(custName);
                    if (custID == 0) {
                        System.out.println("\nIncorrect customer name");
                    } else {
                        requestID = customerSupportServer.getRequestIDs(custID);
                        if (!requestID.isEmpty()) {
                            for (int i = 0; i < requestID.size(); i++) {
                                reqDesc = customerSupportServer.getRequestDesc(requestID.get(i));
                                System.out.println("Request ID: " + requestID.get(i) + " *** " + reqDesc);
                            }
                        } else {
                            System.out.println("Request ID: NULL");
                        }
                    }
                }
                System.out.print("\n");
            } 
            
            System.exit(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
