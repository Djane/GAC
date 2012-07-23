package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.ScriptVO;

@ManagedBean
@ViewScoped
public class ScriptAtendimentoBean extends BaseBean {

    private Integer idScript = 0;
    private String titulo = "";
    private String descricao = "";
    private String processo = "";
    private List<ScriptVO> listaScripts;

    public ScriptAtendimentoBean() {
        this.listaScripts = popularlistaScripts();
    }
    
    public String iniciarPagina() {
        setTituloCabecalho("Cadastro de pacotes oferecidos");
        this.listaScripts = popularlistaScripts();
        return "scriptatendimento";
    }

    
    public void novo(ActionEvent actionEvent) {
        limparAtributos();
    }

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
    
    
    private void limparAtributos() {
        this.idScript = 0;
        this.titulo = "";
        this.descricao = "";
        this.processo = "";
    }

    public Integer getIdScript() {
        return idScript;
    }

    public void setIdScript(Integer idScript) {
        this.idScript = idScript;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProcesso() {
        return processo;
    }

    public void setProcesso(String processo) {
        this.processo = processo;
    }

    public List<ScriptVO> getListaScripts() {
        return listaScripts;
    }

    public void setListaScripts(List<ScriptVO> listaScripts) {
        this.listaScripts = listaScripts;
    }
  
}
