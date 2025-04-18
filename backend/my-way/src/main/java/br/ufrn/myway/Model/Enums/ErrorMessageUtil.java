package br.ufrn.myway.Model.Enums;

import java.text.MessageFormat;

public enum ErrorMessageUtil {

    ERROR_NOT_FOUND("{0} não encontrado."),
    ERROR_ALREADY_EXISTS("{0} já cadastrado.");


    private final String template;

    ErrorMessageUtil(String template) {
        this.template = template;
    }

    public String getMessage(Object... args) {
        return MessageFormat.format(template, args);
    }

}
