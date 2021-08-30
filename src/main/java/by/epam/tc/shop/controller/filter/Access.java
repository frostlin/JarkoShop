package by.epam.tc.shop.controller.filter;

import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.controller.command.CommandType;
import static by.epam.tc.shop.controller.command.CommandType.*;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The {@code RoleAccess} enum represents lists of available commands for every role
 *
 * @author Pavel Voronin
 * @version 1.0
 */
public enum Access {
    GUEST(Stream.of(
            SIGN_IN,
            SIGN_UP,
            TO_SIGN_IN,
            TO_SIGN_UP,
            TO_MAIN,
            TO_CATALOG,
            CHANGE_LOCALE,
            TO_PRODUCT,
            SEARCH
    ).map(CommandType::getCommand).collect(Collectors.toList())),

    USER(Stream.of(
            TO_MAIN,
            TO_CATALOG,
            CHANGE_LOCALE,
            SEARCH,
            TO_CART,
            CHECKOUT,
            TO_PRODUCT,
            DELETE_CART_ITEM,
            COMMIT_REVIEW,
            TO_PROFILE,
            ADD_TO_CART,
            LOGOUT,
            TO_CHANGE_PASSWORD
    ).map(CommandType::getCommand).collect(Collectors.toList())),

    ADMIN(Stream.of(
            TO_MAIN,
            TO_CATALOG,
            CHANGE_LOCALE,
            SEARCH,
            TO_CART,
            DELETE_CART_ITEM,
            ADD_TO_CART,
            ADD_NEW_PRODUCT,
            ADD_NEW_CHARACTERISTIC,
            TO_ADD_NEW_PRODUCT,
            CHECKOUT,
            TO_PRODUCT,
            COMMIT_REVIEW,
            EDIT_PRODUCT_CHARACTERISTIC,
            EDIT_CHARACTERISTIC,
            TO_PROFILE,
            TO_ADMIN_USERS,
            TO_ADMIN_ORDERS,
            TO_ADMIN_PRODUCTS,
            TO_ADMIN_DISCOUNTS,
            LOGOUT,
            TO_CHANGE_PASSWORD
    ).map(CommandType::getCommand).collect(Collectors.toList()));

    private final List<Command> commands;

    Access(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return Collections.unmodifiableList(commands);
    }
}
