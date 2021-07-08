package by.epam.tc.shop.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {
    private int totalPageCount;
    private int currentPage;
    private String command;

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public int doStartTag() throws JspException {
        try {

                pageContext.getOut().write("<ul class=\"pagination\">" );
                for (int i = 1; i <= totalPageCount; ++i){
                    pageContext.getOut().write("<li><form action=\"controller\" method=\"post\" class=\"my-auto\">" );
                    if (i == currentPage){
                        pageContext.getOut().write("<button class=\"btn btn-primary mx-1\" type=\"submit\" disabled>" + i + "</button></form></li>");
                    } else {
                        pageContext.getOut().write("<button class=\"btn btn-outline-primary mx-1\" type=\"submit\" name=\"nextItemPage\" value=\"" + i + "\">" + i + "</button>" +
                                "<input type=\"hidden\" name=\"command\" value=\"" + command + "\">\n</form></li>");
                    }
                }
            pageContext.getOut().write("</ul>" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
