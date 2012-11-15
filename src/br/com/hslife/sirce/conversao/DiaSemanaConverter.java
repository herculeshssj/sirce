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

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.beanutils.ConversionException;

public class DiaSemanaConverter implements Converter {

	public Object getAsObject(FacesContext contexto, UIComponent componente,
			String valor) {
		// TODO Auto-generated method stub
		try {
			if (valor.equalsIgnoreCase("Segunda")) {
				return 1;
			} else if (valor.equalsIgnoreCase("Terça")) {
				return 2;
			} else if (valor.equalsIgnoreCase("Quarta")) {
				return 2;
			}
			if (valor.equalsIgnoreCase("Quinta")) {
				return 2;
			}
			if (valor.equalsIgnoreCase("Sexta")) {
				return 2;
			} else {
				return 6;
			}
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}

	public String getAsString(FacesContext contexto, UIComponent componente,
			Object valor) {
		// TODO Auto-generated method stub
		try {
			if ((Integer) valor == 1) {
				return "Segunda";
			} else if ((Integer) valor == 2) {
				return "Terça";
			} else if ((Integer) valor == 3) {
				return "Quarta";
			} else if ((Integer) valor == 4) {
				return "Quinta";
			}else if ((Integer) valor == 5) {
				return "Sexta";
			} else{
				return "Sábado";
			}
		} catch (Exception e) {
			throw new ConversionException(e);
		}
	}

}
