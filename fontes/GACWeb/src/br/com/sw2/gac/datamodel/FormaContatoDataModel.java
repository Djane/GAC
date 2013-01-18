package br.com.sw2.gac.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.sw2.gac.vo.FormaContatoVO;

/**
 * <b>Descrição: Data Model para a grade de formas de contato.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class FormaContatoDataModel extends ListDataModel<FormaContatoVO> implements
        SelectableDataModel<FormaContatoVO> {

    /**
     * Construtor Padrao Instancia um novo objeto ContatoDataModel.
     */
    public FormaContatoDataModel() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto ContatoDataModel.
     * @param data the data
     */
    public FormaContatoDataModel(List<FormaContatoVO> data) {
        super(data);
    }

    /*
     * (non-Javadoc)
     * @see org.primefaces.model.SelectableDataModel#getRowKey(java.lang.Object)
     */
    @Override
    public Object getRowKey(FormaContatoVO formaContato) {
        return formaContato.getIdFormaContato();
    }

    /*
     * (non-Javadoc)
     * @see org.primefaces.model.SelectableDataModel#getRowData(java.lang.String)
     */
    @Override
    public FormaContatoVO getRowData(String rowKey) {
        if (null != rowKey && !rowKey.equals("null")) {
            List<FormaContatoVO> lista = (List<FormaContatoVO>) getWrappedData();
            for (FormaContatoVO item : lista) {
                if (item.getIdFormaContato().intValue() == (Integer.parseInt(rowKey))) {
                    return item;
                }
            }
        }
        return null;
    }

}
