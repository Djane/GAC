package br.com.smartangel.pulseira.vo;

public class SmsPadraoVO {

	private Integer idSms;
    private String titulo;
	private String descricao;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String tituloMensagem) {
		this.titulo = tituloMensagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    public Integer getIdSms() {
        return idSms;
    }
    public void setIdSms(Integer idSms) {
        this.idSms = idSms;
    }	
	
}
