package br.com.sw2.gac.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.business.SmsBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.DataExpiradaException;
import br.com.sw2.gac.exception.InformacaoDuplicadaException;
import br.com.sw2.gac.exception.InformacaoEmUsoException;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.vo.SmsVO;

/**
 * <b>Descrição: Controller da tela de cadastro de SMS.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class SmsPadraoBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -5346368551432946592L;

    /** Atributo lista mensagens. */
    private List<SmsVO> listaMensagens;

    /** Atributo sms. */
    private SmsVO sms = new SmsVO();

    /** Atributo sms business. */
    private SmsBusiness smsBusiness = new SmsBusiness();

    /** Atributo editar data inicio. */
    private boolean desativarCampoDataInicio = false;

    /** Atributo editar titulo. */
    private boolean desativarCampoTitulo = false;

    /** Atributo editar descricao. */
    private boolean editarCampoDescricao = false;

    /**
     * Construtor Padrao Instancia um novo objeto SmsPadraoBean.
     */
    public SmsPadraoBean() {
        this.sms = new SmsVO();
        this.listaMensagens = popularListaMensagens();
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {

        resetForm();

    }

    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {

        int id = Integer.parseInt(getRequestParameter("idSms"));
        for (SmsVO item : this.listaMensagens) {
            if (item.getIdSms().intValue() == id) {
                this.sms.setIdSms(item.getIdSms());
                this.sms.setTitulo(item.getTitulo());
                this.sms.setTexto(item.getTexto());
                this.sms.setDtInicioValidade(item.getDtInicioValidade());
                if (null != this.sms.getDtInicioValidade()
                        && DateUtil.compareIgnoreTime(this.sms.getDtInicioValidade(),
                                this.getDataAtual()) < 1) {
                    this.desativarCampoDataInicio = true;
                    this.desativarCampoTitulo = true;
                    this.editarCampoDescricao = true;
                } else {
                    this.desativarCampoDataInicio = false;
                    this.desativarCampoTitulo = false;
                    this.editarCampoDescricao = false;
                }
                this.sms.setDtTerminoValidade(item.getDtTerminoValidade());
                break;
            }
        }
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {
        try {
            if (validarDatas()) {
                if (null == this.sms.getIdSms() || this.sms.getIdSms() < 1) {
                    salvarNovo();
                } else {
                    salvarEditar();
                }
                this.sms = new SmsVO();
                this.listaMensagens = popularListaMensagens();
            }
        } catch (InformacaoDuplicadaException e) {
            setFacesErrorMessage("message.smspadrao.save.failed.duplicated");
        } catch (Exception e) {
            setFacesErrorMessage("message.smspadrao.save.failed");
            this.getLogger().error(e);
        }
    }

    /**
     * Nome: salvarEditar Salvar editar.
     * @see
     */
    private void salvarEditar() {
        if (validarDatas()) {
            for (SmsVO item : this.listaMensagens) {
                if (item.getIdSms().equals(this.sms.getIdSms())) {
                    item.setIdSms(this.sms.getIdSms());

                    if (null == this.sms.getDtInicioValidade()
                            || DateUtil.compareIgnoreTime(this.sms.getDtInicioValidade(),
                                    this.getDataAtual()) > 0) {
                        item.setTitulo(this.sms.getTitulo());
                        item.setTexto(this.sms.getTexto());
                        item.setDtInicioValidade(this.sms.getDtInicioValidade());
                    }
                    item.setDtTerminoValidade(this.sms.getDtTerminoValidade());
                    try {
                        this.smsBusiness.atualizarMensagem(item);
                        setFacesMessage("message.smspadrao.save.sucess");
                    } catch (DataExpiradaException e) {
                        setFacesErrorMessage("message.smspadrao.save.failed.expired");
                    } catch (InformacaoDuplicadaException e) {
                        setFacesErrorMessage("message.smspadrao.save.failed.duplicated");
                    } catch (BusinessException e) {
                        setFacesErrorMessage("message.smspadrao.save.failed.expired");
                    } catch (Exception e) {
                        setFacesErrorMessage("message.smspadrao.save.failed");
                        this.getLogger().error(e);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Nome: salvarNovo Salvar novo.
     * @see
     */
    private void salvarNovo() {
        SmsVO vo = new SmsVO();
        vo.setTitulo(this.sms.getTitulo());
        vo.setTexto(this.sms.getTexto());
        vo.setDtInicioValidade(this.sms.getDtInicioValidade());
        vo.setDtTerminoValidade(this.sms.getDtTerminoValidade());
        try {
            this.smsBusiness.adicionarNovaMensagem(vo);
            setFacesMessage("message.smspadrao.save.sucess");
        } catch (InformacaoDuplicadaException e) {
            setFacesErrorMessage("message.smspadrao.save.failed.duplicated");
        } catch (Exception e) {
            setFacesErrorMessage("message.smspadrao.save.failed");
            this.getLogger().error(e);
        }
    }

    /**
     * Nome: validarDatas Validar datas.
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean validarDatas() {
        boolean retorno = true;
        if (null != this.sms.getDtInicioValidade() && null != this.sms.getDtTerminoValidade()) {
            if (DateUtil.compareIgnoreTime(this.sms.getDtInicioValidade(),
                    this.sms.getDtTerminoValidade()) > 0) {
                setFacesErrorMessage("message.smspadrao.field.datainicioinferiordinal");
                retorno = false;
            }
        }
        return retorno;
    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        SmsVO remover = null;

        try {
            for (SmsVO item : this.listaMensagens) {
                if (item.getIdSms().equals(this.sms.getIdSms())) {
                    remover = item;
                    this.smsBusiness.apagarSms(remover);
                }
            }

            if (null != remover) {
                this.listaMensagens.remove(remover);
            }
            this.resetForm();
            this.listaMensagens = popularListaMensagens();

        } catch (InformacaoEmUsoException e) {
            setFacesErrorMessage("message.smspadrao.delete.falied.inuse");
        } catch (BusinessException e) {
            setFacesErrorMessage("message.smspadrao.delete.falied");
            this.getLogger().error(e);
        }
    }

    /**
     * Nome: resetForm
     * Limpa e deixa a tela pronta para a digitação de um novo registro.
     *
     * @see
     */
    private void resetForm() {
        this.sms = new SmsVO();

        this.desativarCampoDataInicio = false;
        this.desativarCampoTitulo = false;
        this.editarCampoDescricao = false;
    }

    /**
     * Nome: popularListaMensagens Popular lista mensagens.
     * @return list
     * @see
     */
    private List<SmsVO> popularListaMensagens() {
        SmsVO sms = new SmsVO();
        sms.setDtTerminoValidade(DateUtil.getDataAtual());
        return smsBusiness.obterListaMensagensAtivas(sms);
    }

    /**
     * Nome: getListaMensagens Recupera o valor do atributo 'listaMensagens'.
     * @return valor do atributo 'listaMensagens'
     * @see
     */
    public List<SmsVO> getListaMensagens() {
        return listaMensagens;
    }

    /**
     * Nome: setListaMensagens Registra o valor do atributo 'listaMensagens'.
     * @param listaMensagens valor do atributo lista mensagens
     * @see
     */
    public void setListaMensagens(List<SmsVO> listaMensagens) {
        this.listaMensagens = listaMensagens;
    }

    /**
     * Nome: getSms Recupera o valor do atributo 'sms'.
     * @return valor do atributo 'sms'
     * @see
     */
    public SmsVO getSms() {
        return sms;
    }

    /**
     * Nome: setSms Registra o valor do atributo 'sms'.
     * @param sms valor do atributo sms
     * @see
     */
    public void setSms(SmsVO sms) {
        this.sms = sms;
    }

    /**
     * Nome: getSmsBusiness Recupera o valor do atributo 'smsBusiness'.
     * @return valor do atributo 'smsBusiness'
     * @see
     */
    public SmsBusiness getSmsBusiness() {
        return smsBusiness;
    }

    /**
     * Nome: setSmsBusiness Registra o valor do atributo 'smsBusiness'.
     * @param smsBusiness valor do atributo sms business
     * @see
     */
    public void setSmsBusiness(SmsBusiness smsBusiness) {
        this.smsBusiness = smsBusiness;
    }

    /**
     * Nome: isDesativarCampoDataInicio Verifica se e desativar campo data inicio.
     * @return true, se for desativar campo data inicio senão retorna false
     * @see
     */
    public boolean isDesativarCampoDataInicio() {
        return desativarCampoDataInicio;
    }

    /**
     * Nome: setDesativarCampoDataInicio Registra o valor do atributo 'desativarCampoDataInicio'.
     * @param desativarCampoDataInicio valor do atributo desativar campo data inicio
     * @see
     */
    public void setDesativarCampoDataInicio(boolean desativarCampoDataInicio) {
        this.desativarCampoDataInicio = desativarCampoDataInicio;
    }

    /**
     * Nome: isDesativarCampoTitulo Verifica se e desativar campo titulo.
     * @return true, se for desativar campo titulo senão retorna false
     * @see
     */
    public boolean isDesativarCampoTitulo() {
        return desativarCampoTitulo;
    }

    /**
     * Nome: setDesativarCampoTitulo Registra o valor do atributo 'desativarCampoTitulo'.
     * @param desativarCampoTitulo valor do atributo desativar campo titulo
     * @see
     */
    public void setDesativarCampoTitulo(boolean desativarCampoTitulo) {
        this.desativarCampoTitulo = desativarCampoTitulo;
    }

    /**
     * Nome: isEditarCampoDescricao Verifica se e editar campo descricao.
     * @return true, se for editar campo descricao senão retorna false
     * @see
     */
    public boolean isEditarCampoDescricao() {
        return editarCampoDescricao;
    }

    /**
     * Nome: setEditarCampoDescricao Registra o valor do atributo 'editarCampoDescricao'.
     * @param editarCampoDescricao valor do atributo editar campo descricao
     * @see
     */
    public void setEditarCampoDescricao(boolean editarCampoDescricao) {
        this.editarCampoDescricao = editarCampoDescricao;
    }

}
