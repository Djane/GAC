package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.business.ScriptBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.vo.ScriptVO;

/**
 * <b>Descrição: Controller da tela de cadastro de Scripts de atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ScriptAtendimentoBean extends BaseBean {

    /** Atributo alter. */
    private Boolean update = false;

    /** Atributo Data atual. */
    private java.util.Calendar now = java.util.Calendar.getInstance();

    /** Atributo scriptVO. */

    private ScriptVO script;

    /** Atributo lista scripts. */

    private List<ScriptVO> listaScripts;

    /** Atributo scriptDAO. */

    private ScriptBusiness scriptBusiness;

    /**
     * Construtor Padrao Instancia um novo objeto ScriptAtendimentoBean.
     */

    public ScriptAtendimentoBean() {
        this.scriptBusiness = new ScriptBusiness();
        this.script = new ScriptVO();
        this.listaScripts = popularlistaScripts();
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */

    public String iniciarPagina() {
        setTituloCabecalho("label.scripts.view.title", true);
        this.listaScripts = popularlistaScripts();
        return "scriptAtendimento";
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */

    public void novo(ActionEvent actionEvent) {
        limparAtributos();
    }

    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */

    public void editar(ActionEvent actionEvent) {
        Integer idScript = Integer.parseInt(getRequestParameter("idScript"));
        ScriptVO editar = (ScriptVO) findInListById(this.listaScripts, "idScript", idScript);
        this.script = new ScriptVO();
        this.script.setIdScript(editar.getIdScript());
        this.script.setTituloScript(editar.getTituloScript());
        this.script.setDescricaoScript(editar.getDescricaoScript());
        this.script.setProcessoSeguir(editar.getProcessoSeguir());
        this.script.setDtInicioValidade(editar.getDtInicioValidade());
        this.script.setDtFinalValidade(editar.getDtFinalValidade());
        this.setUpdate(true);
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */

    public void salvar(ActionEvent actionEvent) {

        ScriptVO item = new ScriptVO();

        if (this.update.equals(false)) {

            item.setIdScript(this.script.getIdScript());
            item.setTituloScript(this.script.getTituloScript());
            item.setDescricaoScript(this.script.getDescricaoScript());
            item.setProcessoSeguir(this.script.getProcessoSeguir());

            // /Verifica se a Data Final é menor que a Data Incial e vice-versa
            if (this.script.getDtInicioValidade().after(this.script.getDtFinalValidade())) {
                setFacesMessage("message.script.save.dtFim.invalid");
            } else {
                item.setDtInicioValidade(this.script.getDtInicioValidade());
                item.setDtFinalValidade(this.script.getDtFinalValidade());
            }

            try {

                this.scriptBusiness.salvarScript(item);
                // Atualiza lista
                this.listaScripts = this.scriptBusiness.obterListaScripts();
                setFacesMessage("message.scripts.save.sucess");
                limparAtributos();

            } catch (BusinessException e) {
                setFacesMessage("message.generic.system.unavailable");
            }
        } else {
            ScriptVO editar = (ScriptVO) findInListById(this.listaScripts, "idScript",
                    this.script.getIdScript());

            Date dtInicioOriginal = editar.getDtInicioValidade();
            Date dtfimOriginal = editar.getDtFinalValidade();
            Date dtInicioDigitada = this.script.getDtInicioValidade();
            Date dtFimDigitada = this.script.getDtFinalValidade();

            try {
                editar.setIdScript(this.script.getIdScript());
                editar.setTituloScript(this.script.getTituloScript());
                editar.setDescricaoScript(this.script.getDescricaoScript());
                editar.setProcessoSeguir(this.script.getProcessoSeguir());
                // Verfica se a Data Final é menor que a Data Inicial e vice-versa
                if (!this.script.getDtInicioValidade().after(this.script.getDtFinalValidade())) {

                    // Verfica se a Data Incial foi alterada
                    if ((!dtInicioOriginal.equals(dtInicioDigitada))) {

                        // Verifica o inicio do periodo vigente
                        if (periodoVigeteIncio(dtInicioOriginal)) {
                            editar.setDtInicioValidade(this.script.getDtInicioValidade());
                        }
                    }
                    //Verifica se a Data Final foi alterada
                    if ((!dtfimOriginal.equals(dtFimDigitada))) {

                        // Verfica o fim do periodo vigente
                        if (periodoVigenteFim(dtfimOriginal)) {
                            editar.setDtFinalValidade(this.script.getDtFinalValidade());
                        }
                    }

                } else {
                    setFacesMessage("message.script.save.dtFim.invalid");
                }

                this.scriptBusiness.salvarScript(editar);
                limparAtributos();
                this.setUpdate(false);
            } catch (BusinessException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Verifica se a DataIncial esta dentro do periodo vigente.
     * @param dtInicioOriginal dtInicioOriginal
     * @return data
     */
    public Boolean periodoVigeteIncio(Date dtInicioOriginal) {
        boolean data = false;

        if (dtInicioOriginal.before(now.getTime()) || now.getTime().equals(dtInicioOriginal)) {
            setFacesMessage("message.script.alter.dtInic.invalid");
        } else {
            data = true;
        }
        return data;
    }

    /**
     * Verifica se a DataFinal esta dentro do periodo vigente.
     * @param dtFimOriginal dtFimOriginal
     * @return data
     */
    public Boolean periodoVigenteFim(Date dtFimOriginal) {
        boolean data = false;

        if (dtFimOriginal.after(now.getTime()) || dtFimOriginal.equals(now.getTime())) {
            data = true;
        } else {
            setFacesMessage("message.script.alter.dtFim.invalid");
        }
        return data;
    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        ScriptVO remover = (ScriptVO) findInListById(this.listaScripts, "idScript",
                this.script.getIdScript());
        try {
            this.scriptBusiness.apagarScript(this.script.getIdScript());
            limparAtributos();
        } catch (BusinessException exception) {
            if (exception.getExceptionCode() == BusinessExceptionMessages.DELETE_SCRIPT_EM_USO
                    .getValue().intValue()) {
                setFacesMessage("message.script.delete.failed");
            } else {
                setFacesMessage("message.generic.system.unavailable");
            }

        }

        this.listaScripts.remove(remover);
    }

    /**
     * Nome: popularlistaScripts Popularlista scripts.
     * @return list
     * @see
     */

    private List<ScriptVO> popularlistaScripts() {
        List<ScriptVO> lista;
        try {
            lista = this.scriptBusiness.obterListaScripts();
        } catch (BusinessException e) {
            lista = new ArrayList<ScriptVO>();
        }
        return lista;
    }

    /**
     * Nome: limparAtributos Limpar atributos.
     * @see
     */
    private void limparAtributos() {
        this.script = new ScriptVO();
        this.script.setDtInicioValidade(null);
        this.script.setDtFinalValidade(null);
    }

    /**
     * @return the script
     */

    public ScriptVO getScript() {
        return script;
    }

    /**
     * @param script the script to set
     */

    public void setScript(ScriptVO script) {
        this.script = script;
    }

    /**
     * @return the listaScripts
     */

    public List<ScriptVO> getListaScripts() {
        return listaScripts;
    }

    /**
     * @param listaScripts the listaScripts to set
     */

    public void setListaScripts(List<ScriptVO> listaScripts) {
        this.listaScripts = listaScripts;
    }

    /**
     * @return the scriptBusiness
     */

    public ScriptBusiness getScriptBusiness() {
        return scriptBusiness;
    }

    /**
     * @param scriptBusiness the scriptBusiness to set
     */
    public void setScriptBusiness(ScriptBusiness scriptBusiness) {
        this.scriptBusiness = scriptBusiness;
    }

    /**
     * @return the update
     */
    public Boolean getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Boolean update) {
        this.update = update;
    }

}