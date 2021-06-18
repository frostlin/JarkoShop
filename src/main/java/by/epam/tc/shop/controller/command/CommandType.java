package by.epam.tc.shop.controller.command;

import by.epam.tc.shop.controller.command.impl.*;

public enum CommandType {
    TO_MAIN{
        {
            this.command = new ToMainPageCommand();
        }
    },
    CHANGE_LOCALE{
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    TO_SIGN_IN{
        {
            this.command = new ToSignInCommand();
        }
    },
    TO_SIGN_UP{
        {
            this.command = new ToSignUpCommand();
        }
    },
    SIGN_IN{
        {
            this.command = new SignInCommand();
        }
    },
    SIGN_UP{
        {
            this.command = new SignUpCommand();
        }
    },
    SEARCH{
        {
            this.command = new SearchCommand();
        }
    },
    TO_CATALOG{
        {
            this.command = new ToCatalogCommand();
        }
    };

    Command command;
    public Command getCommand(){
        return command;
    }
}
