package client;

import compute.Compute;
import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ComputePi {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java client.ComputePi <host> <digits>");
            System.exit(1);
        }

        String host = args[0];
        int digits = Integer.parseInt(args[1]);

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry = LocateRegistry.getRegistry(host);
            String name = "Compute";
            Compute comp = (Compute) registry.lookup(name);
            Pi task = new Pi(digits);
            BigDecimal pi = comp.executeTask(task);
            System.out.println("Computed value of Pi: " + pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}
