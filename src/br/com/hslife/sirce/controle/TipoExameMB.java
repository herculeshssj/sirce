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

package br.com.hslife.sirce.controle;

import br.com.hslife.sirce.modelo.TipoExame;
import br.com.hslife.sirce.persistencia.TipoExameDao;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class TipoExameMB {

    private TipoExame tipoExame;
    private TipoExameDao dao;
    private Integer idTipoExame;
    private String busca;
    private static List<TipoExame> listaTipoExames;

    public TipoExameMB() {
        tipoExame = new TipoExame();
        dao = new TipoExameDao();
        if (listaTipoExames == null) {
            listaTipoExames = new ArrayList<TipoExame>();
        }        
    }

    public String cadastrar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
			msg = "Você não tem permissão para cadastrar!";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstTipoExame", mensagem);
		} else {
			resultado = "editTipoExame";			
		}		
		return resultado;
	}
    
    public String salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        if (getTipoExame().getId() == null || getTipoExame().getId() == 0) {
            getDao().salvar(getTipoExame());
            if (getDao().getErrorMessage() == null) {
                msg = "Cadastro realizado com sucesso!";
            } else {
                msg = "Erro ao cadastrar: " + getDao().getErrorMessage();
            }
            setTipoExame(new TipoExame());
        } else {
        	if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
        		msg = "Você não tem permissão para alterar!";
        	} else {
            getDao().alterar(getTipoExame());
            if (getDao().getErrorMessage() == null) {
                msg = "Alteração realizada com sucesso!";
                setListaTipoExames(getDao().listarTodos());
            } else {
                msg = "Erro ao alterar: " + getDao().getErrorMessage();
            }
        }}
        setListaTipoExames(new ArrayList<TipoExame>());
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmTipoExame", mensagem);
        return "";
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String resultado = "";
        setTipoExame(getDao().buscar(getIdTipoExame()));
        if (getDao().getErrorMessage() == null) {
            resultado = "editTipoExame";
        } else {
            msg = "Erro ao carregar: " + getDao().getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstTipoExame", mensagem);
        }
        return resultado;
    }

    public String pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "Registros carregados com sucesso!";
        setListaTipoExames(getDao().procurar("descricao", getBusca()));
        if (listaTipoExames.isEmpty()) {
            msg = "Nenhum registro foi encontrado!";
        }
        if (getDao().getErrorMessage() != null) {
            msg = "Erro ao procurar: " + getDao().getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstTipoExame", mensagem);
        return "";
    }

    /**
     * @return the tipoExame
     */
    public TipoExame getTipoExame() {
        return tipoExame;
    }

    /**
     * @param tipoExame the tipoExame to set
     */
    public void setTipoExame(TipoExame tipoExame) {
        this.tipoExame = tipoExame;
    }

    /**
     * @return the dao
     */
    public TipoExameDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(TipoExameDao dao) {
        this.dao = dao;
    }

    /**
     * @return the idTipoExame
     */
    public Integer getIdTipoExame() {
        return idTipoExame;
    }

    /**
     * @param idTipoExame the idTipoExame to set
     */
    public void setIdTipoExame(Integer idTipoExame) {
        this.idTipoExame = idTipoExame;
    }

    /**
     * @return the busca
     */
    public String getBusca() {
        return busca;
    }

    /**
     * @param busca the busca to set
     */
    public void setBusca(String busca) {
        this.busca = busca;
    }


    /**
     * @return the listaTipoExames
     */
    public List<TipoExame> getListaTipoExames() {
        return listaTipoExames;
    }

    /**
     * @param aListaTipoExames the listaTipoExames to set
     */
    public void setListaTipoExames(List<TipoExame> aListaTipoExames) {
        listaTipoExames = aListaTipoExames;
    }

}
