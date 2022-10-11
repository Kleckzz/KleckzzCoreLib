package de.kleckzz.coresystem.bukkit.libraries.CloudJSON;

import de.dytanic.cloudnet.common.document.gson.JsonDocument;

public class Functions {
    public static String getValueFromJsonByKey (JsonDocument json, String key){
        String jsonResponse_string = json.getString(key);
        return jsonResponse_string;
    }
}
