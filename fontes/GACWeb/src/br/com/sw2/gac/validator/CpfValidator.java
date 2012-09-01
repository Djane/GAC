/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * <b>Descrição: Validador para cpf.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesValidator("cpfValidator")
public class CpfValidator implements Validator {

    private static final int NUMERO_01 = 1;
    private static final int NUMERO_09 = 9;
    private static final int NUMERO_10 = 10;
    private static final int NUMERO_11 = 11;

    @Override
    public void validate(FacesContext context, UIComponent component, Object valorTela) throws ValidatorException {

        if (!validaCPF(String.valueOf(valorTela))) {
            ResourceBundle bundle = context.getApplication().getResourceBundle(context,
                    "messageBundle");
            String message = bundle.getString("message.generic.field.cpf.invalid");

            FacesMessage msg = new FacesMessage(message, message);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }

    /**
     * Valida CPF do usuário. Não aceita CPF's padrões como
     * 11111111111 ou 22222222222
     *
     * @param cpf String valor com 11 dígitos
     */
    private static boolean validaCPF(String cpf) {

        if (cpf == null || cpf.length() != NUMERO_11 || isCPFPadrao(cpf)) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }

        if (!calcDigVerif(cpf.substring(0, NUMERO_09)).equals(cpf.substring(NUMERO_09, NUMERO_11))) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param cpf String valor a ser testado
     * @return boolean indicando se o usuário entrou com um CPF padrão
     */
    private static boolean isCPFPadrao(String cpf) {

        if (cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")) {
            return true;
        }

        return false;
    }

    /**
     * Calcula o digito verificador.
     * @param num
     * @return
     */
    private static String calcDigVerif(String num) {

        Integer primDig, segDig;
        int soma = 0, peso = NUMERO_10;

        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % NUMERO_11 == 0 | soma % NUMERO_11 == NUMERO_01) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(NUMERO_11 - (soma % NUMERO_11));
        }

        soma = 0;
        peso = NUMERO_11;

        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig.intValue() * 2;

        if (soma % NUMERO_11 == 0 | soma % NUMERO_11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(NUMERO_11 - (soma % NUMERO_11));
        }

        return primDig.toString() + segDig.toString();
    }
}
