package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.ScriptVO;

/**
 * <b>Descrição: Controller da tela de cadastro de Scripts de atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class ScriptAtendimentoBean extends BaseBean {

    /** Atributo id script. */
    private Integer idScript = 0;

    /** Atributo titulo. */
    private String titulo = "";

    /** Atributo descricao. */
    private String descricao = "";

    /** Atributo processo. */
    private String processo = "";

    /** Atributo lista scripts. */
    private List<ScriptVO> listaScripts;

    /**
     * Construtor Padrao Instancia um novo objeto ScriptAtendimentoBean.
     */
    public ScriptAtendimentoBean() {
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
        return "scriptatendimento";
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

        int id = Integer.parseInt(getRequestParameter("idScript"));
        ScriptVO edit = null;
        for (ScriptVO item : this.listaScripts) {
            if (item.getIdScript().intValue() == id) {
                edit = item;
                break;
            }
        }
        this.idScript = edit.getIdScript();
        this.titulo = edit.getTituloScript();
        this.descricao = edit.getDescricaoScript();
        this.processo = edit.getProcessoSeguir();

    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        setFacesMessage("message.scripts.save.sucess");

        if (this.idScript > 0) {
            for (ScriptVO item : this.listaScripts) {
                if (item.getIdScript().equals(this.idScript)) {
                    item.setTituloScript(this.titulo);
                    item.setDescricaoScript(this.descricao);
                    item.setProcessoSeguir(this.processo);
                    item.setIdScript(this.idScript);
                }
            }
        } else {
            ScriptVO vo = new ScriptVO();
            vo.setTituloScript(this.titulo);
            vo.setDescricaoScript(this.descricao);
            vo.setProcessoSeguir(this.processo);
            vo.setIdScript(this.listaScripts.size());
            this.listaScripts.add(vo);
        }
        limparAtributos();

    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        ScriptVO remover = null;
        for (ScriptVO item : this.listaScripts) {
            if (item.getIdScript().equals(this.idScript)) {
                remover = item;
            }
        }
        if (null != remover) {
            this.listaScripts.remove(remover);
            limparAtributos();
        }
    }

    /**
     * Nome: popularlistaScripts Popularlista scripts.
     * @return list
     * @see
     */
    private List<ScriptVO> popularlistaScripts() {
        this.listaScripts = new ArrayList<ScriptVO>();
        ScriptVO script = new ScriptVO();
        script.setIdScript(1);
        script.setTituloScript("Titulo 1");
        script.setDescricaoScript("Descricao 1");
        script.setProcessoSeguir("Processo a seguir 1");
        this.listaScripts.add(script);
        return listaScripts;
    }

    /**
     * Nome: limparAtributos Limpar atributos.
     * @see
     */
    private void limparAtributos() {
        this.idScript = 0;
        this.titulo = "";
        this.descricao = "";
        this.processo = "";
    }

    /**
     * Nome: getIdScript Recupera o valor do atributo 'idScript'.
     * @return valor do atributo 'idScript'
     * @see
     */
    public Integer getIdScript() {
        return idScript;
    }

    /**
     * Nome: setIdScript Registra o valor do atributo 'idScript'.
     * @param idScript valor do atributo id script
     * @see
     */
    public void setIdScript(Integer idScript) {
        this.idScript = idScript;
    }

    /**
     * Nome: getTitulo Recupera o valor do atributo 'titulo'.
     * @return valor do atributo 'titulo'
     * @see
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Nome: setTitulo Registra o valor do atributo 'titulo'.
     * @param titulo valor do atributo titulo
     * @see
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Nome: getDescricao Recupera o valor do atributo 'descricao'.
     * @return valor do atributo 'descricao'
     * @see
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Nome: setDescricao Registra o valor do atributo 'descricao'.
     * @param descricao valor do atributo descricao
     * @see
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Nome: getProcesso Recupera o valor do atributo 'processo'.
     * @return valor do atributo 'processo'
     * @see
     */
    public String getProcesso() {
        return processo;
    }

    /**
     * Nome: setProcesso Registra o valor do atributo 'processo'.
     * @param processo valor do atributo processo
     * @see
     */
    public void setProcesso(String processo) {
        this.processo = processo;
    }

    /**
     * Nome: getListaScripts Recupera o valor do atributo 'listaScripts'.
     * @return valor do atributo 'listaScripts'
     * @see
     */
    public List<ScriptVO> getListaScripts() {
        return listaScripts;
    }

    /**
     * Nome: setListaScripts Registra o valor do atributo 'listaScripts'.
     * @param listaScripts valor do atributo lista scripts
     * @see
     */
    public void setListaScripts(List<ScriptVO> listaScripts) {
        this.listaScripts = listaScripts;
    }

}
