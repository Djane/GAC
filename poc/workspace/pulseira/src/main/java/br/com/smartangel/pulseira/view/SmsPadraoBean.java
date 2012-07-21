package br.com.smartangel.pulseira.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.smartangel.pulseira.vo.SmsPadraoVO;

@ManagedBean
@ViewScoped
public class SmsPadraoBean extends BaseBean {

    private Integer idSms = 0;
    private String tituloMensagem = "";
    private String descricaoMensagem = "";
    private List<SmsPadraoVO> listaMensagens;

    public SmsPadraoBean() {
        this.listaMensagens = popularListaMensagens();
    }

    public String iniciarPagina() {
        setTituloCabecalho("Cadastro SMS Padrão");
        this.listaMensagens = popularListaMensagens();
        return "smspadrao";
    }

    public void novo(ActionEvent actionEvent) {
        this.idSms = 0;
        this.tituloMensagem = "";
        this.descricaoMensagem = "";
    }

    public void editar(ActionEvent actionEvent) {

       int id =  Integer.parseInt(getRequestParameter("idSms"));
       SmsPadraoVO edit = null;
       for (SmsPadraoVO item : this.listaMensagens) {           
           if (item.getIdSms().intValue() == id ) {
               edit = item;
               break;
           }           
       }    
        this.tituloMensagem = edit.getTitulo();
        this.descricaoMensagem = edit.getDescricao();
        this.idSms = edit.getIdSms();
    }

    public void salvar(ActionEvent actionEvent) {

        setFacesMessage("message.smspadrao.save.sucess");               
        if (this.idSms > 0) {                  
             for (SmsPadraoVO item : this.listaMensagens) {
                 if (item.getIdSms().equals(this.idSms)) {
                     item.setIdSms(this.idSms);
                     item.setTitulo(this.tituloMensagem);
                     item.setDescricao(this.descricaoMensagem);
                 }
             }
        } else {
            SmsPadraoVO vo = new SmsPadraoVO();
            vo.setTitulo(this.tituloMensagem);
            vo.setDescricao(this.descricaoMensagem);
            vo.setIdSms(this.listaMensagens.size());
            this.listaMensagens.add(vo);
        }
        
    }

    public void excluir(ActionEvent actionEvent) {
        SmsPadraoVO remover = null;
        for (SmsPadraoVO item : this.listaMensagens) {
            if (item.getIdSms().equals(this.idSms)) {
                remover = item;
            }
        }
        if (null != remover) {
            this.listaMensagens.remove(remover);
        }
    }

    private List<SmsPadraoVO> popularListaMensagens() {
        listaMensagens = new ArrayList<SmsPadraoVO>();
        SmsPadraoVO sms = new SmsPadraoVO();
        for (int i = 0; i < 10; i++) {
            sms = new SmsPadraoVO();
            sms.setIdSms(i);
            sms.setTitulo("Titulo Mensagem " + i);
            sms.setDescricao(" desccricao mensagem " + i);
            listaMensagens.add(sms);
        }
        return listaMensagens;
    }

    public String getTituloMensagem() {
        return tituloMensagem;
    }

    public void setTituloMensagem(String tituloMensagem) {
        this.tituloMensagem = tituloMensagem;
    }

    public String getDescricaoMensagem() {
        return descricaoMensagem;
    }

    public void setDescricaoMensagem(String descricaoMensagem) {
        this.descricaoMensagem = descricaoMensagem;
    }

    public List<SmsPadraoVO> getListaMensagens() {
        return listaMensagens;
    }

    public void setListaMensagens(List<SmsPadraoVO> listaMensagens) {
        this.listaMensagens = listaMensagens;
    }

    public Integer getIdSms() {
        return idSms;
    }

    public void setIdSms(Integer idSms) {
        this.idSms = idSms;
    }

}
