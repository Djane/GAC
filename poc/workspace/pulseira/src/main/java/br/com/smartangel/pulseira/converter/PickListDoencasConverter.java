package br.com.smartangel.pulseira.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.smartangel.pulseira.vo.DoencaVO;

@FacesConverter(forClass=DoencaVO.class, value="pickListDoencaConverter")
public class PickListDoencasConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DoencaVO dispositivo = new DoencaVO();
       
        if ((value != null) && (!value.equals(""))) {
            dispositivo.setIdDoenca(Integer.parseInt(value));
        }
        return dispositivo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer retorno = null;
        if (!(value == null)) {
            DoencaVO dispositivo = new DoencaVO();
            dispositivo = (DoencaVO) value;
            retorno = dispositivo.getIdDoenca();
        }
        return retorno.toString();
    }

}
