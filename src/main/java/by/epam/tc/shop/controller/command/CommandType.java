package by.epam.tc.shop.controller.command;

import by.epam.tc.shop.controller.*;
import by.epam.tc.shop.controller.command.impl.SignInCommand;
import by.epam.tc.shop.controller.command.impl.SignUpCommand;
import by.epam.tc.shop.controller.command.impl.ToMainPageCommand;

public enum CommandType {
    TO_MAIN{
        {
            this.command = new ToMainPageCommand();
        }
    };


    Command command;
    public Command getCommand(){
        return command;
    }
}
