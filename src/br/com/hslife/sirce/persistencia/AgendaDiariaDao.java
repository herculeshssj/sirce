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

package br.com.hslife.sirce.persistencia;

import java.util.Date;
import java.util.List;

import br.com.hslife.sirce.modelo.AgendaDiaria;

public class AgendaDiariaDao extends GenericDao implements InterfaceDao<AgendaDiaria> {

	
	public void salvar(AgendaDiaria obj) {
		// TODO Auto-generated method stub
		save(obj);
	}

	public void alterar(AgendaDiaria obj) {
		// TODO Auto-generated method stub
		update(obj);
	}

	public void excluir(AgendaDiaria obj) {
		// TODO Auto-generated method stub
		delete(obj);
	}

	public AgendaDiaria buscar(Integer id) {
		// TODO Auto-generated method stub
		return (AgendaDiaria) find(AgendaDiaria.class, id);
	}

	public AgendaDiaria buscar(String campo, Object valor) {
		// TODO Auto-generated method stub
		return (AgendaDiaria) find(AgendaDiaria.class, campo, valor);
	}

	public List<AgendaDiaria> listar(String campo, Object valor) {
		// TODO Auto-generated method stub
		return (List) findAll(AgendaDiaria.class, campo, valor);
	}

	public List<AgendaDiaria> listarTodos() {
		// TODO Auto-generated method stub
		Date hoje = new Date();
		String sqlQuery = "select * from AgendaDiaria where data>='"+(hoje.getYear()+1900)+"-"+(hoje.getMonth()+1)+"-"+hoje.getDate()+"'" ;
		return (List) query(AgendaDiaria.class, sqlQuery);
	}

	public List<AgendaDiaria> procurar(String campo, Object valor) {
		// TODO Auto-generated method stub
		return (List) search("AgendaDiaria", campo, (String)valor);
	}
	
}
