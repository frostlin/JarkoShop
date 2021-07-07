package by.epam.tc.shop.controller;

public class PagePath {
        public static final String HOME = "/index.jsp";
        public static final String MAIN = "/WEB-INF/pages/main.jsp";
        public static final String SIGN_IN = "/WEB-INF/pages/signIn.jsp";
        public static final String SIGN_UP = "/WEB-INF/pages/signUp.jsp";
        public static final String SEARCH_RESULT = "/WEB-INF/pages/searchResult.jsp";
        public static final String CATALOG = "/WEB-INF/pages/catalog.jsp";
        public static final String CART = "/WEB-INF/pages/user/cart.jsp";
        public static final String TO_PRODUCT = "/WEB-INF/pages/productPage.jsp";
        public static final String TO_PROFILE = "/WEB-INF/pages/user/profile.jsp";

        public static final String ADMIN_ORDERS = "/WEB-INF/pages/admin/orders.jsp";
        public static final String ADMIN_PRODUCTS = "/WEB-INF/pages/admin/products.jsp";
        public static final String ADMIN_USERS = "/WEB-INF/pages/admin/users.jsp";
        public static final String ADMIN_DISCOUNTS = "/WEB-INF/pages/admin/discounts.jsp";
        public static final String ADD_NEW_PRODUCT = "/WEB-INF/pages/admin/addNewProduct.jsp";
        public static final String ERROR404 = "/404.jsp";



        public static final String REDIRECT_HOME = "/shop_war_exploded/controller?command=to_main";
        public static final String REDIRECT_MAIN = "/shop_war_exploded/controller?command=to_main";
        public static final String REDIRECT_SIGN_IN = "/shop_war_exploded/controller?command=to_sign_in";
        public static final String REDIRECT_SIGN_UP = "/shop_war_exploded/controller?command=to_sign_up";
        public static final String REDIRECT_CATALOG = "/shop_war_exploded/controller?command=to_catalog";
        public static final String REDIRECT_CART = "/shop_war_exploded/controller?command=to_cart";
        public static final String REDIRECT_TO_PRODUCT = "/shop_war_exploded/controller?command=to_product";
        public static final String REDIRECT_TO_PROFILE = "/shop_war_exploded/controller?command=to_profile";

        public static final String REDIRECT_ADMIN_ORDERS = "/shop_war_exploded/controller?command=to_admin_orders";
        public static final String REDIRECT_ADMIN_PRODUCTS = "/shop_war_exploded/controller?command=to_products";
        public static final String REDIRECT_ADMIN_USERS = "/WEB-INF/pages/admin/users.jsp";
        public static final String REDIRECT_ADMIN_DISCOUNTS = "/WEB-INF/pages/admin/discounts.jsp";
        public static final String REDIRECT_ADD_NEW_PRODUCT = "/shop_war_exploded/controller?command=to_add_new_product";

        private PagePath(){}
}
