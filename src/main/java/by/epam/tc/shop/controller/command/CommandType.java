package by.epam.tc.shop.controller.command;

import by.epam.tc.shop.controller.command.impl.*;

public enum CommandType {
    TO_MAIN                 {{ this.command = new ToMainPageCommand(); }},
    CHANGE_LOCALE           {{ this.command = new ChangeLocaleCommand();}},
    TO_SIGN_IN              {{ this.command = new ToSignInCommand(); }},
    TO_SIGN_UP              {{ this.command = new ToSignUpCommand(); }},
    SIGN_IN                 {{ this.command = new SignInCommand(); }},
    SIGN_UP                 {{ this.command = new SignUpCommand(); }},
    SEARCH                  {{ this.command = new SearchCommand(); }},
    DELETE_CART_ITEM        {{ this.command = new DeleteCartItem(); }},
    TO_CATALOG              {{ this.command = new ToCatalogCommand(); }},
    TO_CART                 {{ this.command = new ToCartCommand(); }},
    ADD_TO_CART             {{ this.command = new AddToCartCommand(); }},
    CHECKOUT                {{ this.command = new CheckoutCommand(); }},
    TO_PRODUCT              {{ this.command = new ToProductPageCommand(); }},
    COMMIT_REVIEW           {{ this.command = new CommitReview(); }},
    TO_PROFILE              {{ this.command = new ToProfileCommand(); }},
    LOGOUT                  {{ this.command = new Logout(); }},
    TO_CHANGE_PASSWORD      {{ this.command = new ToChangePassword(); }},
    TO_ADMIN_USERS          {{ this.command = new toAdminUsers(); }},
    TO_ADMIN_ORDERS         {{ this.command = new toAdminOrders(); }},
    TO_ADMIN_PRODUCTS       {{ this.command = new toAdminProducts(); }},
    TO_ADMIN_DISCOUNTS      {{ this.command = new toAdminDiscounts(); }};

    Command command;
    public Command getCommand(){
        return command;
    }
}
