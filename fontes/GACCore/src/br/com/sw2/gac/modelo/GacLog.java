package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tblgaclog")
public class GacLog implements Serializable {
    
    private static final long serialVersionUID = -5533026362727442069L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRegistro")
    private Long idRegistro;          

    @Column(name = "dataHoraRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraRegistro;
    
    @Column(name = "mensagem")
    private String mensagem ;

    @Column(name = "usuarioGac")    
    private String usuarioGac;
    
    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(Date dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getUsuarioGac() {
        return usuarioGac;
    }

    public void setUsuarioGac(String usuarioGac) {
        this.usuarioGac = usuarioGac;
    }
}
