package by.epam.tc.shop.controller.command;

import by.epam.tc.shop.controller.command.impl.UnknownCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;

/**
 * The {@code CommandProvider} class represents command provider
 *
 * @author Pavel Voronin
 * @version 1.0
 */
public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private CommandProvider(){}

    /**
     * Define command
     *
     * @param commandName command name
     * @return command type
     */
    public static Command getCommand(String commandName){
        Command currentCommand;

        if (commandName != null){
            try {
                CommandType commandType = CommandType.valueOf(commandName.toUpperCase());
                currentCommand = commandType.getCommand();
            } catch (IllegalArgumentException e){
                logger.error("No such command found {}", commandName, e);
                currentCommand = new UnknownCommand();
            }
        } else {
            currentCommand = new UnknownCommand();
        }
        return currentCommand;
    }
}
