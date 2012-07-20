package br.com.smartangel.pulseira.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.smartangel.pulseira.vo.DispositivoVO;

@FacesConverter(forClass=DispositivoVO.class, value="dispositivoConverter")
public class PickListDispositivoConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        DispositivoVO dispositivo = new DispositivoVO();
       
        if ((value != null) && (!value.equals(""))) {
            dispositivo.setIdDispositivo(Integer.parseInt(value));
        }
        return dispositivo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Integer retorno = null;
        if (!(value == null)) {
            DispositivoVO dispositivo = new DispositivoVO();
            dispositivo = (DispositivoVO) value;
            retorno = dispositivo.getIdDispositivo();
        }
        return retorno.toString();
    }

}
