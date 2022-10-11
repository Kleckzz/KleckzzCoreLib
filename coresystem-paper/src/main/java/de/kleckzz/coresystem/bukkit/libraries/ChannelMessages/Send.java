package de.kleckzz.coresystem.bukkit.libraries.ChannelMessages;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;
import de.dytanic.cloudnet.driver.channel.ChannelMessage;
import de.dytanic.cloudnet.ext.bridge.BridgeConfigurationProvider;
import de.dytanic.cloudnet.ext.bridge.BridgeServiceProperty;
import de.dytanic.cloudnet.ext.bridge.listener.BridgeCustomChannelMessageListener;
import de.dytanic.cloudnet.wrapper.Wrapper;
import de.dytanic.cloudnet.wrapper.provider.service.WrapperGeneralCloudServiceProvider;


public class Send {
    WrapperGeneralCloudServiceProvider wrapperNodeInfoProvider = new WrapperGeneralCloudServiceProvider(Wrapper.getInstance());

    public WrapperGeneralCloudServiceProvider getWrapperNodeInfoProvider() {
        return wrapperNodeInfoProvider;
    }

    /**
     * Cloudnet Channel send to all Service(s)
     */

    public static void sendGobalChannelMessage(JsonDocument jsonDocument, String channel, String message){
        ChannelMessage.builder()
                .channel(channel)
                .message(message)
                .json(jsonDocument)
                //Example JSON: JsonDocument.newDocument().append("name", "Peter Parker").append("age", 17)
                .targetAll()
                .build()
                .send();
    }

    /**
     * Cloudnet Channel send to specific Service
     */

    public static void sendChannelMessage(JsonDocument jsonDocument, String channel, String message, String serviceName){
        ChannelMessage.builder()
                .channel(channel)
                .message(message)
                .json(jsonDocument)
                .targetService(serviceName)
                .build()
                .send();
    }

    /**
     * Cloudnet Channel send to specific Service and get response
     */

    public static JsonDocument sendChannelMessageQuery(JsonDocument jsonDocument, String channel, String message, String serviceName) {

        ChannelMessage response = ChannelMessage.builder()
                .channel(channel)
                .message(message)
                .json(jsonDocument)
                .targetService(serviceName)
                .build()
                .sendSingleQuery();

        if (response == null) {
            return null;
        }

        JsonDocument requested = response.getJson().getDocument("requested");

        /**
         * Example: Get String from Response getValueFromJsonByKey(response.getJson())
         */

        return requested;
    }

}
