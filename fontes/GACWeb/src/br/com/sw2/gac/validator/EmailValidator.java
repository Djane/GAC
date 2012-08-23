package br.com.sw2.gac.validator;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * <b>Descrição: Validador para email.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

    /** Constante EMAIL_PATTERN. */
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
            + "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";

    /** Atributo pattern. */
    private Pattern pattern;

    /** Atributo matcher. */
    private Matcher matcher;

    /**
     * Construtor Padrao Instancia um novo objeto EmailValidator.
     */
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {

        if (null != value && !value.equals("")) {
            this.matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {

                ResourceBundle bundle = context.getApplication().getResourceBundle(context,
                        "messageBundle");
                String message = bundle.getString("message.generic.field.email.invalid");

                FacesMessage msg = new FacesMessage(message, message);
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msg);

            }

        }
    }

}
