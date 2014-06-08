package br.com.sw2.gac.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.vo.DoencaVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@FacesConverter(forClass = DoencaVO.class, value = "pickListDoencaConverter")
public class PickListDoencasConverter implements Converter {

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.String)
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DoencaVO findDoenca = new DoencaVO();
        try {
            if ((value != null) && (!value.equals(""))) {
                Object dualList = ((PickList) component).getValue();
                DualListModel<DoencaVO> dl = (DualListModel<DoencaVO>) dualList;
                DoencaVO doenca = (DoencaVO) CollectionUtils.findByAttribute(dl.getTarget(),
                    "codigoCID", value);
                if (null == doenca) {
                    doenca = (DoencaVO) CollectionUtils.findByAttribute(dl.getSource(),
                        "codigoCID", value);
                }
                findDoenca.setCodigoCID(doenca.getCodigoCID());
                findDoenca.setNomeDoenca(doenca.getNomeDoenca());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return findDoenca;
    }

    /*
     * (non-Javadoc)
     * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext,
     * javax.faces.component.UIComponent, java.lang.Object)
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String retorno = null;
        try {
            if (!(value == null)) {
                DoencaVO dispositivo = new DoencaVO();
                dispositivo = (DoencaVO) value;
                retorno = dispositivo.getCodigoCID();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retorno.toString();
    }

}
