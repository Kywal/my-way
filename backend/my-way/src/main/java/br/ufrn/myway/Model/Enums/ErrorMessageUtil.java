package br.ufrn.myway.Model.Enums;

import java.text.MessageFormat;

public enum ErrorMessageUtil {

    ERROR_NOT_FOUND("{0} not found."),
    ERROR_ALREADY_EXISTS("{0} already exists with this {1}.");

    //Exemplo de como usar
    //throw new BusinessException(ErrorMessageUtil.ERROR_ALREADY_EXISTS.getMessage("User", "CPF"));


    private final String template;

    ErrorMessageUtil(String template) {
        this.template = template;
    }

    public String getMessage(Object... args) {
        return MessageFormat.format(template, args);
    }

}
