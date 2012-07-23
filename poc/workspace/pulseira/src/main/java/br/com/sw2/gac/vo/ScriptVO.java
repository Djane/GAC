package br.com.sw2.gac.vo;

public class ScriptVO {

	private Integer idScript;
	private String tituloScript;
	private String descricaoScript;
	private String processoSeguir;
  
	
	public String getTituloScript() {
        return tituloScript;
    }
    public void setTituloScript(String tituloScript) {
        this.tituloScript = tituloScript;
    }
    public Integer getIdScript() {
        return idScript;
    }
    public void setIdScript(Integer idScript) {
        this.idScript = idScript;
    }
    public String getDescricaoScript() {
        return descricaoScript;
    }
    public void setDescricaoScript(String descricaoScript) {
        this.descricaoScript = descricaoScript;
    }
    public String getProcessoSeguir() {
        return processoSeguir;
    }
    public void setProcessoSeguir(String processoSeguir) {
        this.processoSeguir = processoSeguir;
    }
		
}
