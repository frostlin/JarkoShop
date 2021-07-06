package by.epam.tc.shop.controller.filter;

import by.epam.tc.shop.controller.command.Command;
import by.epam.tc.shop.controller.command.CommandType;
import static by.epam.tc.shop.controller.command.CommandType.*;


import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    ).map(CommandType::getCommand).collect(Collectors.toSet())),

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
    ).map(CommandType::getCommand).collect(Collectors.toSet())),

    ADMIN(Stream.of(
            TO_MAIN,
            TO_CATALOG,
            CHANGE_LOCALE,
            SEARCH,
            TO_CART,
            DELETE_CART_ITEM,
            ADD_TO_CART,
            CHECKOUT,
            TO_PRODUCT,
            COMMIT_REVIEW,
            TO_PROFILE,
            TO_ADMIN_USERS,
            TO_ADMIN_ORDERS,
            TO_ADMIN_PRODUCTS,
            TO_ADMIN_DISCOUNTS,
            LOGOUT,
            TO_CHANGE_PASSWORD
    ).map(CommandType::getCommand).collect(Collectors.toSet()));

    private final Set<Command> commands;

    Access(Set<Command> commands) {
        this.commands = commands;
    }

    public Set<Command> getCommands() {
        return Collections.unmodifiableSet(commands);
    }
}
