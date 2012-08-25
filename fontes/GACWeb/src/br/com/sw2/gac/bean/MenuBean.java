package br.com.sw2.gac.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: controller do menu principal.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@SessionScoped
public class MenuBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -1506925064205437167L;

    /**
     * Nome: invokarPagina Método que invoca uma página baseada em seu codigo.
     * @return string
     * @see
     */
    public String invokarPagina() {
        String toViewId = "";
        Integer codigo = Integer.parseInt(getRequestParameter("codigoModulo"));

        for (MenuItem item : MenuItem.values()) {

            if (codigo.intValue() == item.getCodigoModulo().intValue()) {
                toViewId = item.getViewID();
            }
        }
        return toViewId;
    }


    /**
     * Nome: verificarPermissaoPerfil
     * Verificar permissao perfil.
     *
     * @param codigoModulo the codigo modulo
     * @return true, se sucesso, senão false
     * @see
     */
    public boolean verificarPermissaoPerfil(Integer codigoModulo) {
        UsuarioVO usuario = getUsuarioLogado();
        boolean temPermissao = false;
        for (MenuItem item : MenuItem.values()) {
            if (item.getCodigoModulo().intValue() == codigoModulo.intValue()) {
                for (Integer codigoPeril : item.getPerfilAutorizado()) {

                    if (codigoPeril.intValue() == usuario.getPerfil().getIdPerfil().intValue()) {
                        temPermissao = true;
                        break;
                    }
                }
            }
        }
        return temPermissao;

    }

    /**
     * Nome: imprimirDispositivosPorEstado
     * Imprimir dispositivos por estado.
     *
     * @param event the event
     * @see
     */
    public void imprimirDispositivosPorEstado(ActionEvent event) {

     /*   JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(JasperBeanFactory.createBeanCollection());
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        //httpServletResponse.addHeader("content-disposition", "attachment; filename=dispositivoEstado.pdf");
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport("c:\\temp\\dispositivoEstado.jasper", new HashedMap(), beanCollectionDataSource);
            ServletOutputStream servletOutputStream = (ServletOutputStream) httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        } catch (JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */

    }
}
