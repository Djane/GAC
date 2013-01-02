package br.com.sw2.gac.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;

import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.util.StringUtil;

/**
 * <b>Descrição: VAlidator para validação dos dados preenchidos de forma de contato.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class FormularioFormaPagamentoValidator {

    /** Atributo faces context. */
    private FacesContext facesContext = null;

    /**
     * Construtor Padrao
     * Instancia um novo objeto FormularioFormaPagamentoValidator.
     *
     * @param facesContext the faces context
     */
    public FormularioFormaPagamentoValidator(FacesContext facesContext) {
        this.facesContext = facesContext;
    }


    /**
     * Nome: validarCamposFormaContat
     * Validar campos forma contat.
     *
     * @param uiSelectOne the ui select one
     * @param uiTelefone the ui telefone
     * @param uiEmail the ui email
     * @see
     */
    public void validarCamposFormaContat(UISelectOne uiSelectOne, UIInput uiTelefone,
        UIInput uiEmail) {

        FacesMessage facesMessage = null;
        String key = validarCamposFormaContato(uiSelectOne, uiTelefone, uiEmail);

        if (null != key) {
            ResourceBundle bundle = this.facesContext.getApplication().getResourceBundle(this.facesContext, "messageBundle");
            String message = bundle.getString(key);
            facesMessage = new FacesMessage(message, message);
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            this.facesContext.addMessage(null, facesMessage);
            this.facesContext.renderResponse();
        }
    }

    /**
     * Nome: validarCamposFormaContato Recebe os dados de um formulario de formas de pagamento e
     * retorna uma mensagem de validação.
     * @param uiSelectOne Valor de um combo informando se a forma de contato é telefone ou email.
     * @param uiTelefone Telefone informado.
     * @param uiEmail Email informado.
     * @return string Key do mesage budle contendo a mensagem de validação
     * @see
     */
    public String validarCamposFormaContato(UISelectOne uiSelectOne, UIInput uiTelefone,
        UIInput uiEmail) {

        String valorTelefone = uiTelefone.getLocalValue().toString();
        String valorEmail = uiEmail.getLocalValue().toString();
        String valueCombo = uiSelectOne.getLocalValue().toString();
        String retorno = null;

        if (TipoContato.Email.getValue().equals(valueCombo)) {
            if (StringUtil.isEmpty(valorEmail, true)) {
                retorno = "message.generic.field.email.required";

            } else {
                EmailValidator emailValidator = new EmailValidator();
                if (!emailValidator.isEmailValido(valorEmail)) {
                    retorno = "message.generic.field.email.invalid";
                }
            }
        } else {
            if (StringUtil.isEmpty(valorTelefone, true)) {
                retorno = "message.generic.field.telefone.required";

            }
        }

        return retorno;
    }

}
