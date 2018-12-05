package li.angu.airplay;


import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AirPlayServer {

    private static String MAC_ADDRESS;

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(AirPlayServer.class.getName());

        try {
            InetAddress ip = InetAddress.getLocalHost();

            JmDNS jmdns = JmDNS.create(ip);
            jmdns.addServiceListener("_airplay._tcp.local.", new SampleListener());
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ip);

            byte[] mac = networkInterface.getHardwareAddress();

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < mac.length; i++) {
                stringBuilder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? ":" : ""));
            }

            MAC_ADDRESS = stringBuilder.toString();
            logger.log(Level.INFO, "Mac Address is " + MAC_ADDRESS);


            Map<String, String> properties = new HashMap<>();

            properties.put("deviceid", MAC_ADDRESS);
            properties.put("features", "0x5A7FFFF7");
            properties.put("model", "AppleTV3,2");
            properties.put("srcvers", "220.68");
            properties.put("vv", "2");
            properties.put("pw", "false");
            properties.put("flags", "0x4");
            properties.put("rhd", "3.0.1.2");

            ServiceInfo serviceInfo = ServiceInfo.create("_airplay._tcp.local.", "TestAirPlay", 7000, 0, 0, properties);
            jmdns.registerService(serviceInfo);

        } catch (IOException exception) {
            logger.log(Level.WARNING, exception.getMessage());
        }
    }

}
