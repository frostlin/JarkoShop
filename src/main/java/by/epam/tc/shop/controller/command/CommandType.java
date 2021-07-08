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
    DELETE_CART_ITEM        {{ this.command = new DeleteCartItemCommand(); }},
    TO_CATALOG              {{ this.command = new ToCatalogCommand(); }},
    TO_CART                 {{ this.command = new ToCartCommand(); }},
    ADD_TO_CART             {{ this.command = new AddToCartCommand(); }},
    ADD_NEW_PRODUCT         {{ this.command = new AddNewProductCommand(); }},
    TO_ADD_NEW_PRODUCT      {{ this.command = new ToAddNewProductCommand(); }},
    CHECKOUT                {{ this.command = new CheckoutCommand(); }},
    TO_PRODUCT              {{ this.command = new ToProductPageCommand(); }},
    COMMIT_REVIEW           {{ this.command = new CommitReviewCommand(); }},
    TO_PROFILE              {{ this.command = new ToProfileCommand(); }},
    LOGOUT                  {{ this.command = new LogoutCommand(); }},
    TO_CHANGE_PASSWORD      {{ this.command = new ToChangePassword(); }},
    TO_ADMIN_USERS          {{ this.command = new ToAdminUsersCommand(); }},
    TO_ADMIN_ORDERS         {{ this.command = new ToAdminOrdersCommand(); }},
    TO_ADMIN_PRODUCTS       {{ this.command = new ToAdminProductsCommand(); }},
    TO_ADMIN_DISCOUNTS      {{ this.command = new ToAdminDiscountsCommand(); }};

    Command command;
    public Command getCommand(){
        return command;
    }
}
