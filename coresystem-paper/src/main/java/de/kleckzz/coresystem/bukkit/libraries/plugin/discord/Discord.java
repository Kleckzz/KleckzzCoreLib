/*
 * Copyright 2022 Paul Rakutt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.kleckzz.coresystem.bukkit.libraries.plugin.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;

/**
 * Author: KeksGauner
 */
@SuppressWarnings("unused")
public class Discord {
    private final String token;

    private JDA jda;

    private ArrayList<CommandData> commandDataArray = new ArrayList<>();
    private ArrayList<Object> eventsArray = new ArrayList<>();

    public Discord(String token) {
        this.token = token;
    }

    /**
     * Um den bot zu starten
     */
    public void start() {
        try {
            jda = jdaBuilder();
            jda.awaitReady();
            slashCommandDiscordBot(jda.updateCommands());
        }catch (LoginException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * To Update Slash Commands
     * @param commands jda updateCommand
     */
    private void slashCommandDiscordBot(CommandListUpdateAction commands) {
        if(!commandDataArray.isEmpty())
            for(CommandData commandData : commandDataArray)
                commands.addCommands(
                        commandData
                ).queue();
    }

    /**
     * Register a event
     * @param event ListenerAdapter Event
     */
    public void registerEvent(Object event) {
        eventsArray.add(event);
    }

    /**
     * Register a event
     * @param commandData A CommandData
     */
    public void registerCommand(CommandData commandData) {
        commandDataArray.add(commandData);
    }

    /**
     * Erstelle eine Instanz mit .build()
     * @return JDA
     * @throws LoginException
     */
    private JDA jdaBuilder() throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);

        builder.disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOJI, CacheFlag.STICKER);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);

        builder.setActivity(Activity.watching("MC.ASKEKU.DE"));

        if(!eventsArray.isEmpty())
            for(Object event : eventsArray)
                builder.addEventListeners(event);

        return builder.build();
    }

    /**
     * A way to get the JDA
     * @return The JDA
     */
    public JDA getJDA() {
        return jda;
    }

    public void stop() {

    }
}
