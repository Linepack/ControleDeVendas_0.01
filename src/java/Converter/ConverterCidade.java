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
public class ConverterCidade implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String id) {
        return CidadeDAO.getCidadeByID(Integer.valueOf(id));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object cidadeObj) {
        if (cidadeObj != null && cidadeObj instanceof Cidade){
            Cidade cidade = (Cidade) cidadeObj;
            return String.valueOf(cidade.getId());
        }
        return "";        
    }

  
}
