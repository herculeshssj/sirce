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
import br.com.hslife.sirce.modelo.Exame;

public class ExameDao extends GenericDao implements InterfaceDao<Exame> {

	public void salvar(Exame obj) {
		save(obj);
	}

	public void alterar(Exame obj) {
		update(obj);
	}

	public void excluir(Exame obj) {
		delete(obj);
	}

	public Exame buscar(Integer id) {
		return (Exame) find(Exame.class, id);
	}

	public Exame buscar(String campo, Object valor) {
		return (Exame) find(Exame.class, campo, valor);
	}

	public List<Exame> listar(String campo, Object valor) {
		return (List) findAll(Exame.class, campo, valor);
	}

	public List<Exame> listarTodos() {
		return (List) findAll(Exame.class);
	}

	public List<Exame> procurar(String campo, Object valor) {
		return (List) search("Exame", campo, (String) valor);
	}

	public List<Exame> listarMarcados() {
		List lista = null;
		lista = new ArrayList<AgendaMedica>();
		String consultaSQL = "select * from Exame where compareceu = false";
		lista = query(Exame.class, consultaSQL);
		return lista;
	}
	
	public List<Exame> buscarExames(Integer id) {
		List lista = null;
		lista = new ArrayList<Exame>();
		String consultaSQL = "select * from Exame where id = " + id;
		lista = query(Exame.class, consultaSQL);
		return lista;
	}
	
	public List<Exame> buscarPaciente(String nome) {
		List lista = null;
		lista = new ArrayList<Exame>();
		String consultaSQL = "select e.* from Exame e, Paciente p where e.idpaciente = p.id and p.nome like '%"+ nome +"%'";
		lista = query(Exame.class, consultaSQL);
		return lista;
	}

}
