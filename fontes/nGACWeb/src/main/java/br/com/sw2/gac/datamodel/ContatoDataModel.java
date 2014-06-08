package br.com.sw2.gac.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.sw2.gac.vo.ContatoVO;

/**
 * <b>Descrição: Data Model para a grade de contatos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContatoDataModel extends ListDataModel<ContatoVO> implements
        SelectableDataModel<ContatoVO> {

    /**
     * Construtor Padrao Instancia um novo objeto ContatoDataModel.
     */
    public ContatoDataModel() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto ContatoDataModel.
     * @param data the data
     */
    public ContatoDataModel(List<ContatoVO> data) {
        super(data);
    }

    /*
     * (non-Javadoc)
     * @see org.primefaces.model.SelectableDataModel#getRowKey(java.lang.Object)
     */
    @Override
    public Object getRowKey(ContatoVO contato) {
        return contato.getIdContato();
    }

    /*
     * (non-Javadoc)
     * @see org.primefaces.model.SelectableDataModel#getRowData(java.lang.String)
     */
    @Override
    public ContatoVO getRowData(String rowKey) {
        List<ContatoVO> lista = (List<ContatoVO>) getWrappedData();

        for (ContatoVO item : lista) {
            if (item.getIdContato().intValue() == (Integer.parseInt(rowKey))) {
                return item;
            }
        }

        return null;
    }

}
