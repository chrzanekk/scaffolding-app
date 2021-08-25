package pl.com.chrzanowski.scaffolding.auth;

public enum Permissions {
    ADMINISTRATION_DOTPAY_REPORTS("administration_dotpay_reports"),
    ADMINISTRATION_DOTPAY_SETTINGS("administration_dotpay_settings"),
    ADMINISTRATION_EMAIL_ACCOUNTS("administration_email_accounts"),
    ADMINISTRATION_FOOTER("administration_footer"),
    ADMINISTRATION_INDEX_PAGE("administration_index_page"),
    ADMINISTRATION_MAIL_SETTINGS("administration_mail_settings"),
    ADMINISTRATION_MENU("administration_menu"),
    ADMINISTRATION_NEWS("administration_news"),
    ADMINISTRATION_PAGE("administration_page"),
    ADMINISTRATION_PAGES("administration_pages"),
    ADMINISTRATION_REVIEWS("administration_reviews"),
    ADMINISTRATION_SMS_SETTINGS("administration_sms_settings"),
    ADMINISTRATION_TEMPLATE("administration_template"),
    ADMINISTRATION_USERS("administration_users"),
    ADMINISTRATION_ROLE("administration_role"),
    CAMPAGINS("campagins"),
    CAMPAGIN_SETTINGS("campagin_settings"),
    CHANGE_DRIVER_FOR_DELIVERY("change_driver_for_delivery"),
    CUSTOMERS("customers"),
    CUSTOMERS_GROUPS("customers_groups"),
    CUSTOMERS_STATS("cutomers_stats"),
    DASHBOARD("dashboard"),
    DELIVERY_CITY("delivery_city"),
    DELIVERY_DRIVER("delivery_driver"),
    DELIVERY_REPLACE("delivery_replace"),
    DELIVERY_REQUEST("delivery_request"),
    DELIVERY_VALUE("delivery_value"),
    DELIVERY_SETTINGS("delivery_settings"),
    DELIVERY_TIMETABLE("delivery_timetable"),
    DIETETICS_DISH("dietetics_dish"),
    DIETETICS_LIST("dietetics_list"),
    DIETETICS_MENU("dietetics_menu"),
    DIETETICS_PRODUCTS("dietetics_products"),
    DIETETICS_PRODUCT_TYPES("dietetics_product_types"),
    DIETETICS_SETS("dietetics_sets"),
    DIETETICS_SETTINGS_ALLERGEN("dietetics_settings_allergen"),
    DIET_DISCOUNT("diet_discount"),
    DISCOUNT_CODES("discount_codes"),
    DISH_STATUS("dish_status"),
    DRIVER_CALC("driver_calc"),
    EXTRAS("extras"),
    KITCHEN_COMMENTS("kitchen_comments"),
    KITCHEN_PACKING("kitchen_packing"),
    KITCHEN_PRODUCTION("kitchen_production"),
    LABELS("labels"),
    MENU_LABELS("menu_labels"),
    ORDERS("orders"),
    OUR_MENU("our_menu"),
    PRICE_LIST("price_list"),
    PRINT_MENU("print_menu"),
    GALLERY("gallery"),
    MENU_PREVIEW("menu_preview"),
    PRODUCTS("products"),
    PRODUCTS_CATEGORIES("products_categories"),
    SHOW_SUM_IN_DELIVERY("show_sum_in_delivery"),
    SIMPLIFIED_ORDERS("simplified_orders"),
    TEST_SET_PRICE("test_set_price"),
    VOUCHERS("vouchers"),
    ADVISER_ADVICES("adviser_advices"),
    ADVISER_TRIGGERED_ADVICES("adviser_triggered_advices"),
    ADVISER_ACCOUNTS("adviser_accounts"),
    ADVISER_APPLICATIONS("adviser_applications"),
    ADVISER_CONTEXT_VARIABLES("adviser_context_variables"),
    ADVISER_CONTEXT_CONFIGS("adviser_context_configs"),
    ADVISER_ADVICE_CATEGORIES("adviser_advice_categories"),

    LIFE_ADVISER_COMMON("life_adviser_common");

    private String code;

    Permissions(String code) {
        this.code = code;
    }

    String code() {
        return this.code;
    }
}
