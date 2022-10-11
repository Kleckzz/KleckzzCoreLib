package de.kleckzz.coresystem.bukkit.libraries.CloudService;

import de.dytanic.cloudnet.driver.service.ServiceInfoSnapshot;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.dytanic.cloudnet.wrapper.provider.service.WrapperGeneralCloudServiceProvider;
import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;
import java.util.UUID;

import static de.kleckzz.coresystem.bukkit.libraries.Null.wrapperNodeInfoProvider;


public class CloudServiceAPI {

    /**
     * Get Service info by UUID
     */
    public static ServiceInfoSnapshot getServiceInfo(UUID serviceName){

        ServiceInfoSnapshot info = wrapperNodeInfoProvider.getCloudService(serviceName);
        return info;
    }


    /**
     * Get Services info by Name
     */
    public static ArrayList<ServiceInfoSnapshot> getServicesByName(String name){
        ArrayList<ServiceInfoSnapshot> serviceList = new ArrayList<ServiceInfoSnapshot>();
        serviceList = (ArrayList<ServiceInfoSnapshot>) wrapperNodeInfoProvider.getCloudServices(name);
        return serviceList;
    }

    /**
     * Get Services
     */
    public static ArrayList<ServiceInfoSnapshot> getServices(){
        ArrayList<ServiceInfoSnapshot> serviceList = new ArrayList<ServiceInfoSnapshot>();
        serviceList = (ArrayList<ServiceInfoSnapshot>) wrapperNodeInfoProvider.getCloudServices();
        return serviceList;
    }

    /**
     * Stop single Service
     */
    public static void stopService(UUID serviceUUID){
        ServiceInfoSnapshot info = getServiceInfo(serviceUUID);


    }
}
