package by.epam.tc.shop.controller.command;

import by.epam.tc.shop.controller.command.impl.*;

/**
 * The {@code CommandType} enum represents command type
 *
 * @author Pavel Voronin
 * @version 1.0
 */
public enum CommandType {
    CHANGE_LOCALE           {{ this.command = new ChangeLocaleCommand();}},
    SIGN_IN                 {{ this.command = new SignInCommand(); }},
    SIGN_UP                 {{ this.command = new SignUpCommand(); }},
    SEARCH                  {{ this.command = new SearchCommand(); }},
    DELETE_CART_ITEM        {{ this.command = new DeleteCartItemCommand(); }},
    ADD_TO_CART             {{ this.command = new AddToCartCommand(); }},
    ADD_NEW_PRODUCT         {{ this.command = new AddNewProductCommand(); }},
    ADD_NEW_CHARACTERISTIC  {{ this.command = new AddNewCharacteristicCommand(); }},
    CHECKOUT                {{ this.command = new CheckoutCommand(); }},
    COMMIT_REVIEW           {{ this.command = new CommitReviewCommand(); }},
    LOGOUT                  {{ this.command = new LogoutCommand(); }},
    EDIT_PRODUCT_CHARACTERISTIC{{ this.command = new EditProductCharacteristicCommand(); }},
    EDIT_CHARACTERISTIC     {{ this.command = new EditCharacteristicCommand(); }},
    TO_MAIN                 {{ this.command = new ToMainPageCommand(); }},
    TO_SIGN_IN              {{ this.command = new ToSignInCommand(); }},
    TO_SIGN_UP              {{ this.command = new ToSignUpCommand(); }},
    TO_CATALOG              {{ this.command = new ToCatalogCommand(); }},
    TO_CART                 {{ this.command = new ToCartCommand(); }},
    TO_ADD_NEW_PRODUCT      {{ this.command = new ToAddNewProductCommand(); }},
    TO_PRODUCT              {{ this.command = new ToProductPageCommand(); }},
    TO_PROFILE              {{ this.command = new ToProfileCommand(); }},
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
