package customersupportserver;

import java.rmi.Naming;

/**
 *
 * @author Tay Fen Yee A0138708X
 */

public class CustomerSupportServerApp {
    
    public static void main(String[] args) {
        try{
            CustomerSupportServerImpl customerSupportServerImpl = new CustomerSupportServerImpl();
            Naming.rebind("CustomerSupportServer", customerSupportServerImpl);
            System.out.println("CustomerSupportServer Started \n");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
