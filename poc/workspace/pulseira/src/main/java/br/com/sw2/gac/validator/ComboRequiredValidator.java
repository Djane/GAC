package br.com.sw2.gac.validator;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * <b>Descrição: Valida se um combo box foi selecionado.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesValidator("comboRequiredValidator")
public class ComboRequiredValidator implements Validator {

    /**
     * Construtor Padrao Instancia um novo objeto ComboRequiredValidator.
     */
    public ComboRequiredValidator() {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {

        UISelectOne combo = (UISelectOne) component;
        String message = combo.getValidatorMessage();
        if (null == message || "".equals(message.trim())) {
            ResourceBundle bundle = context.getApplication().getResourceBundle(context,
                    "messageBundle");
            String messageFromKey = bundle.getString("message.generic.field.required");
            message = MessageFormat.format(messageFromKey, combo.getId());
        }

        if (null == value || value.toString().equals("0") || "".equals(value.toString().trim())) {
            FacesMessage msg = new FacesMessage(message, message);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

}
