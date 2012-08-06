package br.com.sw2.gac.datamodel;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import br.com.sw2.gac.vo.OcorrenciaVO;

/**
 * <b>Descrição: DataModel para a tabela de ocorrências.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaDataModel extends ListDataModel<OcorrenciaVO> implements
        SelectableDataModel<OcorrenciaVO> {

    /**
     * Construtor Padrao Instancia um novo objeto ContatoDataModel.
     */
    public OcorrenciaDataModel() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto ContatoDataModel.
     * @param data the data
     */
    public OcorrenciaDataModel(List<OcorrenciaVO> data) {
        super(data);
    }

    /*
     * (non-Javadoc)
     * @see org.primefaces.model.SelectableDataModel#getRowKey(java.lang.Object)
     */
    @Override
    public Object getRowKey(OcorrenciaVO contato) {
        return contato.getIdOcorrencia();
    }

    @Override
    public OcorrenciaVO getRowData(String rowKey) {
        List<OcorrenciaVO> lista = (List<OcorrenciaVO>) getWrappedData();

        for (OcorrenciaVO item : lista) {
            if (item.getIdOcorrencia().intValue() == (Integer.parseInt(rowKey))) {
                return item;
            }
        }

        return null;
    }

}
