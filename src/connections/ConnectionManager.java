package connections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectionManager extends Thread {

    private static ArrayList<PingCheck> pingChecks = new ArrayList<>();
    private static ArrayList<DeviceMonitor> deviceMonitors = new ArrayList<>();

    public void importPingChecks() {
        if (pingChecks.isEmpty()) {
            this.stopPingChecks();
            pingChecks.clear();
        }

        try {
            Scanner pingScanner = new Scanner(new File("PingChecks.ini"));

            while (pingScanner.hasNext())
                pingChecks.add(new PingCheck(pingScanner.nextLine(), Integer.parseInt(pingScanner.nextLine()),
                        Integer.parseInt(pingScanner.nextLine())));

            this.startPingChecks();
        } catch (FileNotFoundException fnf) {
            System.out.println("\n[ERROR] PingChecks.ini not found");
            System.exit(0);
        } catch (NullPointerException n) {
            System.out.println("\n[ERROR] PingChecks.ini has missing data. Make sure every device has an IP, " +
                    "timeout time, and sleep time. Please refer to the documentation for more information.");
            System.exit(0);
        }
    }

    private void startPingChecks() {
        for (PingCheck pingCheck : pingChecks) {
            if (!pingCheck.isAlive())
                pingCheck.start();
        }
    }

    private void stopPingChecks() {
        for (PingCheck pingCheck : pingChecks) {
            if (pingCheck.isAlive())
                pingCheck.interrupt();
        }
    }

    public void importDeviceMonitors() {
        if (!deviceMonitors.isEmpty()) {
            this.stopDeviceMonitors();
            deviceMonitors.clear();
        }

        try {
            Scanner deviceScanner = new Scanner(new File("Devices.ini"));

            while (deviceScanner.hasNext())
                deviceMonitors.add(new DeviceMonitor(deviceScanner.nextLine(), deviceScanner.nextLine(),
                        deviceScanner.nextLine(), deviceScanner.nextLine(), deviceScanner.nextLine()));

            this.startDeviceMonitors();
        } catch (FileNotFoundException fnf) {
            System.out.println("\n[ERROR] Devices.ini not found");
            System.exit(0);
        } catch (NullPointerException n) {
            System.out.println("\n[ERROR] Devices.ini has missing data. Make sure every device has an IP, " +
                    "device type, username, and password. Please refer to the documentation for more information.");
            System.exit(0);
        }
    }

    private void startDeviceMonitors() {
        for (DeviceMonitor networkDevice : deviceMonitors) {
            if (!networkDevice.isReachable())
                networkDevice.start();
        }
    }

    private void stopDeviceMonitors() {
        for (DeviceMonitor networkDevice : deviceMonitors) {
            if (networkDevice.isReachable())
                networkDevice.interrupt();
        }
    }

    @Override
    public void run() {

    }

}
