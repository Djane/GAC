package br.com.sw2.gac.bean;

import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * Listener responsável pela autorização de acesso as páginas do GAC.
 * @see AutorizacaoPhaseEvent
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class AutorizacaoPhaseListener implements PhaseListener {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 8559775714652865590L;

    /*
     * (non-Javadoc)
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void afterPhase(PhaseEvent event) {

        FacesContext facesContext = event.getFacesContext();
        ExternalContext externalContext = facesContext.getExternalContext();

        if (null != facesContext.getViewRoot()) {
            String paginaAtual = facesContext.getViewRoot().getViewId();

            if (paginaAtual.equals("/error/c404.xhtml")) {
                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                nh.handleNavigation(facesContext, null, "c404");
            } else if (paginaAtual.equals("/error/c500.xhtml")) {
                NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                nh.handleNavigation(facesContext, null, "c500");
            } else {

                if (!paginaAtual.contains("login")) {
                    HttpSession session = (HttpSession) externalContext.getSession(true);
                    UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuariovo");

                    if (null == usuario) {
                        NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
                        nh.handleNavigation(facesContext, null, MenuItem.LOGIN.getViewID());
                    }
                }
            }
        } else {
            NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
            nh.handleNavigation(facesContext, null, "c404");
        }

    }

    /*
     * (non-Javadoc)
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    @Override
    public void beforePhase(PhaseEvent event) {

    }

    /*
     * (non-Javadoc)
     * @see javax.faces.event.PhaseListener#getPhaseId()
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
