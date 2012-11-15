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

import java.util.ArrayList;
import java.util.List;

import br.com.hslife.sirce.modelo.AgendaMedica;
import br.com.hslife.sirce.modelo.Consulta;
import br.com.hslife.sirce.modelo.Medico;

public class AgendaMedicaDao extends GenericDao implements InterfaceDao<AgendaMedica>{

	public void salvar(AgendaMedica obj) {
        save(obj);
    }

    public void alterar(AgendaMedica obj) {
        update(obj);
    }

    public void excluir(AgendaMedica obj) {
        delete(obj);
    }

    public AgendaMedica buscar(Integer id) {
        return (AgendaMedica) find(AgendaMedica.class, id);
    }

    public AgendaMedica buscar(String campo, Object valor) {
        return (AgendaMedica) find(AgendaMedica.class, campo, valor);
    }

    public List<AgendaMedica> listar(String campo, Object valor) {
        return (List) findAll(AgendaMedica.class, campo, valor);
    }

    public List<AgendaMedica> listarTodos() {
        return (List) findAll(AgendaMedica.class);
    }

    public List<AgendaMedica> procurar(String campo, Object valor) {
        return (List) search("AgendaMedica", campo, (String)valor);
    }
    
	public List<AgendaMedica> listarAgendaMedico(String id) {
    	List lista = null;
    	if (id == null || id.isEmpty()) {
    		lista = new ArrayList<AgendaMedica>();
    	} else {
    		String consultaSQL = "select a.* from AgendaMedica a, Medico m where m.crm = '" + id + "' and m.id = a.idmedico";
            lista = query(AgendaMedica.class, consultaSQL);
    	}
    	return lista;
        
    }
	
	public List<AgendaMedica> buscarMedico(Integer id) {
		List lista = null;
		lista = new ArrayList<AgendaMedica>();
		String consultaSQL = "select * from AgendaMedica where idmedico = " + id;
		lista = query(AgendaMedica.class, consultaSQL);
		return lista;
	}
	
}
