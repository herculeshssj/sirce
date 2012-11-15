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

import br.com.hslife.sirce.modelo.Especialidade;

import java.util.List;

public class EspecialidadeDao extends GenericDao implements InterfaceDao<Especialidade> {

    public void salvar(Especialidade obj) {
        save(obj);
    }

    public void alterar(Especialidade obj) {
        update(obj);
    }

    public void excluir(Especialidade obj) {
        delete(obj);
    }

    public Especialidade buscar(Integer id) {
        return (Especialidade) find(Especialidade.class, id);
    }

    public Especialidade buscar(String campo, Object valor) {
        return (Especialidade) find(Especialidade.class, campo, valor);
    }

    public List<Especialidade> listar(String campo, Object valor) {
        return (List) findAll(Especialidade.class, campo, valor);
    }

    public List<Especialidade> listarTodos() {
        return (List) findAll(Especialidade.class);
    }

    public List<Especialidade> procurar(String campo, Object valor) {
        return (List) search("Especialidade", campo, (String)valor);
    }
}
