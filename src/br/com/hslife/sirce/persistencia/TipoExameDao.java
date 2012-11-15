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

import br.com.hslife.sirce.modelo.TipoExame;

import java.util.List;

public class TipoExameDao extends GenericDao implements InterfaceDao<TipoExame>{

    public void salvar(TipoExame obj) {
         save(obj);
    }

    public void alterar(TipoExame obj) {
        update(obj);
    }

    public void excluir(TipoExame obj) {
        delete(obj);
    }

    public TipoExame buscar(Integer id) {
        return (TipoExame) find(TipoExame.class, id);
    }

    public TipoExame buscar(String campo, Object valor) {
        return (TipoExame) find(TipoExame.class, campo, valor);
    }

    public List<TipoExame> listar(String campo, Object valor) {
        return (List) findAll(TipoExame.class, campo, valor);
    }

    public List<TipoExame> listarTodos() {
        return (List) findAll(TipoExame.class);
    }

    public List<TipoExame> procurar(String campo, Object valor) {
        return (List) search("TipoExame", campo, (String)valor);
    }

}
