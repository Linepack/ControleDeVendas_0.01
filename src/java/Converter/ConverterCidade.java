package Converter;

import DAO.CidadeDAO;
import Model.Cidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author leandro
 */
@FacesConverter(forClass = Cidade.class)
public class ConverterCidade implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String id) {
        Cidade cidade = null;
        try {
            cidade = CidadeDAO.getCidadeByID(Integer.valueOf(id));
        } catch (Exception e) {
        }

        try {
            if (cidade == null) {
                cidade = new Cidade();
                int posicaoTraco = id.indexOf("-");
                cidade.setCidade(id.substring(0, posicaoTraco));
                cidade.setUf(id.substring(posicaoTraco+1));
            }
        } catch (Exception e) {
            
        }
        return cidade;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object cidadeObj) {
        if (cidadeObj != null && cidadeObj instanceof Cidade) {
            Cidade cidade = (Cidade) cidadeObj;
            return String.valueOf(cidade.getId());
        }
        return "";
    }

}
