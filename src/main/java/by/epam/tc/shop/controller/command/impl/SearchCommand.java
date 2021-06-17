package by.epam.tc.shop.controller.command.impl;

import by.epam.tc.shop.controller.PagePath;
import by.epam.tc.shop.controller.RequestAttribute;
import by.epam.tc.shop.controller.RequestParameter;
import by.epam.tc.shop.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String searchString = request.getParameter("searchString");

//        searchString = searchString.chars().map(x -> (byte)x+256).collect(StringBuilder::new,
//                StringBuilder::appendCodePoint,
//                StringBuilder::append).toString();
        Charset cs = StandardCharsets.UTF_8;
        String s = new String(searchString.getBytes(cs), cs);
        String page = PagePath.SEARCH_RESULT;
        request.setAttribute(RequestAttribute.SEARCH_RESULT, "Search string is " + searchString);

        return page;
    }
}
