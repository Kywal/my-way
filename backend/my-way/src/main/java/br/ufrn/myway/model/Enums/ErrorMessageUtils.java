package br.ufrn.myway.model.Enums;

import java.text.MessageFormat;

public enum ErrorMessageUtils {
    ERROR_NOT_FOUND("{0} not found."),
    ERROR_ALREADY_EXISTS("{0} already exists with this {1}."),
    ERROR_LOGIN("User not found or wrong password"),
    ERROR_USER_DONT_HAVE_DAILY_MISSION("User {0} does not have daily mission.");

    private final String template;

    ErrorMessageUtils(String template) {
        this.template = template;
    }

    public String getMessage(Object... args) {
        return MessageFormat.format(template, args);
    }

}
