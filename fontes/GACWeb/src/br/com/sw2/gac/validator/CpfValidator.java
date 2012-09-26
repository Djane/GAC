/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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

    private static final int TAMANHO_CPF_SEM_DIGITO = 9;
    private static final int PESO = 10;
    private static final int TAMANHO_CPF = 11;

    @Override
    public void validate(FacesContext context, UIComponent component, Object valorTela) throws ValidatorException {

        if (!validaCPF(String.valueOf(valorTela))) {

            UIInput input = (UIInput) component;
            String message = input.getValidatorMessage();
            if (null == message || "".equals(message.trim())) {
                ResourceBundle bundle = context.getApplication().getResourceBundle(context,
                        "messageBundle");
                message = bundle.getString("message.generic.field.required");
            }
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
    private static boolean validaCPF(String numeroCpf) {

        String cpf = numeroCpf.replace(".", "").replace("/", "").replace("-", "");

        if (cpf == null || cpf.length() != TAMANHO_CPF || isCPFPadrao(cpf)) {
            return false;
        }

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }

        if (!calcDigVerif(cpf.substring(0, TAMANHO_CPF_SEM_DIGITO)).equals(cpf.substring(TAMANHO_CPF_SEM_DIGITO, TAMANHO_CPF))) {
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
        int soma = 0, peso = PESO;

        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % TAMANHO_CPF == 0 | soma % TAMANHO_CPF == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(TAMANHO_CPF - (soma % TAMANHO_CPF));
        }

        soma = 0;
        peso = TAMANHO_CPF;

        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig.intValue() * 2;

        if (soma % TAMANHO_CPF == 0 | soma % TAMANHO_CPF == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(TAMANHO_CPF - (soma % TAMANHO_CPF));
        }

        return primDig.toString() + segDig.toString();
    }
}
