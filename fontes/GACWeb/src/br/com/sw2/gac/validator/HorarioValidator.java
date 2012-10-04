package br.com.sw2.gac.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.sw2.gac.util.DateUtil;

/**
 * <b>Descrição: Classe responsável por validar compos de horario no formato hh:mm.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesValidator("horarioValidator")
public class HorarioValidator implements Validator {

    /*
     * (non-Javadoc)
     * @see javax.faces.validator.Validator#validate(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {

        if (!DateUtil.isHorario(value.toString())) {
            ResourceBundle bundle = context.getApplication().getResourceBundle(context,
                "messageBundle");
            String message = bundle.getString("message.generic.field.horario.invalid");

            FacesMessage msg = new FacesMessage(message, message);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
