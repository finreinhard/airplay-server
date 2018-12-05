package li.angu.airplay;

import sun.rmi.runtime.Log;

import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SampleListener implements ServiceListener {

    private final Logger logger = Logger.getLogger(SampleListener.class.getName());

    @Override
    public void serviceAdded(ServiceEvent serviceEvent) {
        logger.log(Level.INFO, "Service added: " + serviceEvent.getInfo());
    }

    @Override
    public void serviceRemoved(ServiceEvent serviceEvent) {
        logger.log(Level.INFO, "Service removed: " + serviceEvent.getInfo());
    }

    @Override
    public void serviceResolved(ServiceEvent serviceEvent) {
        logger.log(Level.INFO, "Service resolved: " + serviceEvent.getInfo());
    }
}
