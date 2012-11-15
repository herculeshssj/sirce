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

import br.com.hslife.sirce.modelo.Consulta;
import br.com.hslife.sirce.modelo.Especialidade;
import br.com.hslife.sirce.modelo.Medico;

public class ConsultaDao extends GenericDao implements InterfaceDao<Consulta> {

	public void salvar(Consulta obj) {
		save(obj);
	}

	public void alterar(Consulta obj) {
		update(obj);
	}

	public void excluir(Consulta obj) {
		delete(obj);
	}

	public Consulta buscar(Integer id) {
		return (Consulta) find(Consulta.class, id);
	}

	public Consulta buscar(String campo, Object valor) {
		return (Consulta) find(Consulta.class, campo, valor);
	}

	public List<Consulta> listar(String campo, Object valor) {
		return (List) findAll(Consulta.class, campo, valor);
	}

	public List<Consulta> listarTodos() {
		return (List) findAll(Consulta.class);
	}

	public List<Consulta> procurar(String campo, Object valor) {
		return (List) search("Consulta", campo, (String) valor);
	}

	public List<Consulta> listarMarcados() {
		List lista = null;
		lista = new ArrayList<Consulta>();
		String consultaSQL = "select * from Consulta where compareceu = false";
		lista = query(Consulta.class, consultaSQL);
		return lista;
	}
	
	public List<Consulta> buscarConsultas(Integer id) {
		List lista = null;
		lista = new ArrayList<Consulta>();
		String consultaSQL = "select * from Consulta where id = " + id;
		lista = query(Consulta.class, consultaSQL);
		return lista;
	}
	
	public List<Consulta> buscarMedico(Integer id) {
		List lista = null;
		lista = new ArrayList<Consulta>();
		String consultaSQL = "select * from Consulta where idmedico = " + id;
		lista = query(Consulta.class, consultaSQL);
		return lista;
	}
	
	public List<Consulta> buscarEspecialidade(Integer id) {
		List lista = null;
		lista = new ArrayList<Consulta>();
		String consultaSQL = "select c.* from Consulta c, Medico m where m.id = c.idmedico and m.idespecialidade = " + id;
		lista = query(Consulta.class, consultaSQL);
		return lista;
	}
	
	public List<Especialidade> listarEspecialidades(Integer id) {
		List lista = null;
		lista = new ArrayList<Especialidade>();
		String consultaSQL = "select distinct e.* from Medico m, Especialidade e where idunidade = "+ id +" and m.idespecialidade = e.id";
		lista = query(Especialidade.class, consultaSQL);
		return lista;
	}
	
	public List<Medico> listarMedicos(Integer id) {
		List lista = null;
		lista = new ArrayList<Medico>();
		String consultaSQL = "select * from Medico m where idespecialidade = "+ id;
		lista = query(Medico.class, consultaSQL);
		return lista;
	}

}
