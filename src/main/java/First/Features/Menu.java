package First.Features;

import First.BotLogic.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Menu implements Feature {
    private String help;
    private HashMap<String, Feature> commands = new HashMap<>();
    private String command = null;
    private String description = null;


    public Menu(ArrayList<Feature> features){
        init(features);
    }

    public HashSet<String> getCommands() { return new HashSet(commands.keySet()); }

    public Menu(ArrayList<Feature> features, String command, String description)
    {
        init(features);
        this.command = command;
        this.description = description;
    }

    private void init(ArrayList<Feature> features) {
        initHelp(features);
        initCommands(features);
    }

    private void initCommands(ArrayList<Feature> features){
        for (var feature : features) {
            commands.put(feature.getCommand(), feature);
            if (feature instanceof Menu)
                commands.putAll(((Menu) feature).commands);
        }
    }

    private void initHelp(ArrayList<Feature> features){
        var helpBuilder = new StringBuilder();
        for (var feature : features) {
            helpBuilder.append(feature.getDescription());
            helpBuilder.append(" ---> ");
            helpBuilder.append(feature.getCommand());
            helpBuilder.append("\n");
        }
        helpBuilder.append("Получить помощь по боту --> помощь\n");
        help = helpBuilder.toString();
    }

    private String getHelp(){
        return help;
    }

    public boolean commandAvailable(String command){
        return commands.containsKey(command) || command.equalsIgnoreCase("помощь");
    }

    public String useCommandFromMenu(String command, User user){
        if (command.equalsIgnoreCase("помощь")){
            return getHelp();
        }
        return commands.get(command).use(user, command);
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public String use(User user, String command) {
        return getHelp();
    }
}