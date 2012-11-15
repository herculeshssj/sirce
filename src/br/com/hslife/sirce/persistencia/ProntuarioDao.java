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

import br.com.hslife.sirce.modelo.Prontuario;

import java.util.List;

public class ProntuarioDao extends GenericDao implements InterfaceDao<Prontuario> {

    public void salvar(Prontuario obj) {
        save(obj);
    }

    public void alterar(Prontuario obj) {
        update(obj);
    }

    public void excluir(Prontuario obj) {
        delete(obj);
    }

    public Prontuario buscar(Integer id) {
        return (Prontuario) find(Prontuario.class, id);
    }

    public Prontuario buscar(String campo, Object valor) {
        return (Prontuario) find(Prontuario.class, campo, valor);
    }

    public List<Prontuario> listar(String campo, Object valor) {
        return (List) findAll(Prontuario.class, campo, valor);
    }

    public List<Prontuario> listarTodos() {
        return (List) findAll(Prontuario.class);
    }

    public List<Prontuario> procurar(String campo, Object valor) {
        return (List) search("Prontuario", campo, (String)valor);
    }

    public List<Prontuario> listarProntuarioPaciente(Integer id) {
        String consultaSQL = "select * from Prontuario where idpaciente = " + id;
        return (List) query(Prontuario.class, consultaSQL);
    }
}
