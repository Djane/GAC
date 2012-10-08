package br.com.sw2.gac.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sw2.gac.dao.SmsDao;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.SMS;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.ParseUtils;
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

        SMS entity = ParseUtils.parse(vo);

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
     * Nome: atualizarMensagem Atualizar mensagem.
     * @param sms the sms
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarMensagem(SmsVO sms) throws BusinessException {

        Date dataAtual = new Date();

        SMS smsOriginal = this.smsDAO.getEntityById(sms.getIdSms());
        if (null != smsOriginal.getDtTerminoValidade() && DateUtil.compareIgnoreTime(smsOriginal.getDtTerminoValidade(), dataAtual) < 0) {

            throw new BusinessException(BusinessExceptionMessages.SMS_VENCIDA);

        } else {

            if (DateUtil.compareIgnoreTime(smsOriginal.getDtInicioValidade(), dataAtual) > -1) {
                // Ja iniciou a vigencia da mensagem.
                // posso alterar o titulo, a descrição

                smsOriginal.setTpMensagem(sms.getTitulo());
                smsOriginal.setDsMensagem(sms.getTexto());
                smsOriginal.setDtTerminoValidade(sms.getDtTerminoValidade());

            } else {
                // Não começou a vigencia, pode alterar tudo
                smsOriginal.setTpMensagem(sms.getTitulo());
                smsOriginal.setDsMensagem(sms.getTexto());
                smsOriginal.setDtInicioValidade(sms.getDtInicioValidade());
                smsOriginal.setDtTerminoValidade(sms.getDtTerminoValidade());

            }

            this.smsDAO.gravar(smsOriginal);

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
        SMS entity = ParseUtils.parse(vo);
        try {

            SMS sms = this.smsDAO.getSmsByTituloDescricao(entity);
            if (null != sms) {
                smsVO = ParseUtils.parse(sms);
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

    /**
     * Nome: obterListaMensagensAtivas
     * Obter lista mensagens ativas.
     *
     * @param vo the vo
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<SmsVO> obterListaMensagensAtivas(SmsVO vo) throws BusinessException {

        SMS entity = ParseUtils.parse(vo);

        List<SmsVO> retorno = new ArrayList<SmsVO>();
        try {
            List<SMS> resultado = this.smsDAO.getListaSmsByDataTermino(entity);

            for (SMS itemEntity : resultado) {
                SmsVO smsVO = ParseUtils.parse(itemEntity);
                retorno.add(smsVO);
            }

        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
        return retorno;

    }
}
