package br.com.sw2.gac.business;

import br.com.sw2.gac.dao.SmsDao;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.SMS;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.vo.SmsVO;

/**
 * <b>Descrição: Classe de negócio, responsável por ações referentes a SMS</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class SmsBusiness {

    /** Atributo sms dao. */
    private SmsDao smsDAO = new SmsDao();

    /**
     * Nome: adicionarNovaMensagem Adicionar nova mensagem.
     * @param vo the vo
     * @throws BusinessException the business exception
     * @see
     */
    public void adicionarNovaMensagem(SmsVO vo) throws BusinessException {

        SMS entity = ObjectUtils.parse(vo);

        try {
            SMS jaExiste = this.smsDAO.getSmsByTituloDescricao(entity);

            if (null == jaExiste) {
                this.smsDAO.gravar(entity);
            } else {
                throw new BusinessException(BusinessExceptionMessages.SMS_JA_CADASTRADO);
            }

        } catch (BusinessException e) {

            throw e;

        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
    }

    /**
     * Nome: getSms Recupera o valor do atributo 'sms'.
     * @param vo the vo
     * @return valor do atributo 'sms'
     * @throws BusinessException the business exception
     * @see
     */
    public SmsVO getSms(SmsVO vo) throws BusinessException {

        SmsVO smsVO = null;
        SMS entity = ObjectUtils.parse(vo);
        try {

            SMS sms = this.smsDAO.getSmsByTituloDescricao(entity);
            if (null != sms) {
                smsVO = ObjectUtils.parse(sms);
            }

        } catch (BusinessException e) {

            throw e;

        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }

        return smsVO;

    }

    /**
     * Nome: apagarSms Apagar sms.
     * @param vo the vo
     * @throws BusinessException the business exception
     * @see
     */
    public void apagarSms(SmsVO vo) throws BusinessException {
        try {
            this.smsDAO.apagar(vo.getIdSms());
        } catch (DataBaseException exception) {
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                throw new BusinessException(BusinessExceptionMessages.DELETE_SMS_EM_USO);
            }
        }
    }

}
