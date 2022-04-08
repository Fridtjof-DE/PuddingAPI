package me.fridtjof.puddingapi.jda;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class GuildCommand {

    public MessageReceivedEvent event;
    public String commandName;

    public GuildCommand(String commandName, MessageReceivedEvent event) {
        this.event = event;
        this.commandName = commandName;

        if(arguments()[0].equalsIgnoreCase(commandName)) {
            logic();
        }
    }

    public String[] arguments() {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        return args;
    }

    public abstract void logic();
}
