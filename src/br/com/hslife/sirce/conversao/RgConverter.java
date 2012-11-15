/*
  
  	Copyright (c) 2010 JOSE, Hércules S. S. et al.

    Este arquivo é parte do programa SIRCE.
    

    SIRCE é um software livre; você pode redistribui-lo e/ou modificá-lo 

    dentro dos termos da Licença Pública Geral Menor GNU como publicada 

    pela Fundação do Software Livre (FSF); na versão 2.1 da Licença. 


    Este programa é distribuído na esperança que possa ser útil, 

    mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÂO a 
    
    qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a Licença Pública 
    
    Geral Menor GNU em português para maiores detalhes.
    

    Você deve ter recebido uma cópia da Licença Pública Geral Menor GNU sob o 

    nome de "LICENSE.TXT" junto com este programa, se não, acesse o site HSlife
    
    no endereco www.hslife.com.br ou escreva para a Fundação do Software 
    
    Livre(FSF) Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301, USA.
    

    Para mais informações sobre o programa SIRCE e seus autores acesse o 

    endereço www.hslife.com.br, pelo e-mail contato@hslife.com.br ou escreva para 

    Hércules S. S. José, Av. Ministro Lafaeyte de Andrade, 1683 - Bl. 3 Apt 404, 

    Marco II - Nova Iguaçu, RJ, Brasil.
  
*/

package br.com.hslife.sirce.conversao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.beanutils.ConversionException;

public class RgConverter implements Converter {

	public Object getAsObject(FacesContext contexto, UIComponent componente,
			String valor) {
		// TODO Auto-generated method stub
		try {
			if (soNumeros(valor)){
				return valor;
			} else {
				return "";			
			}
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}

	public String getAsString(FacesContext contexto, UIComponent componente,
			Object valor) {
		// TODO Auto-generated method stub
		try {
			if (soNumeros((String)valor)){
				return (String)valor;
			} else {
				throw new ConversionException("Erro na conversão!");				
			}
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}
	
	// Retorna true se a string enviada contiver somente números
	private boolean soNumeros(String campo) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(campo);
        boolean matchFound = m.matches();
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }

}
