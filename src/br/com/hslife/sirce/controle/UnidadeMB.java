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

import br.com.hslife.sirce.modelo.Endereco;
import br.com.hslife.sirce.modelo.Unidade;
import br.com.hslife.sirce.persistencia.UnidadeDao;
import br.com.hslife.sirce.util.Estados;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class UnidadeMB {

    private Unidade unidade;
    private Endereco endereco;
    private UnidadeDao dao;
    private Integer idUnidade;
    private Integer idEndereco;
    private String busca;
    private static List<Unidade> listaUnidades;
    private List<SelectItem> estados;
 
    /** Creates a new instance of UnidadeMB */
    public UnidadeMB() {
        unidade = new Unidade();
        endereco = new Endereco();
        dao = new UnidadeDao();
        if (listaUnidades == null) {
            listaUnidades = new ArrayList<Unidade>();
        }
        estados = new ArrayList<SelectItem>();
        // Carrega a combobox com os estados
        for (int i = 0; i < Estados.getTamanho(); i++) {
            estados.add(i, new SelectItem(Estados.getEstados(i), Estados.getEstados(i)));
        }
    }
    
    public String cadastrar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
			msg = "Você não tem permissão para cadastrar!";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstUnidade", mensagem);
		} else {
			resultado = "editUnidade";			
		}		
		return resultado;
	}

    public void salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        if (unidade.getId() == null || unidade.getId() == 0) {
            unidade.setEndereco(endereco);
            dao.salvar(unidade);
            if (dao.getErrorMessage() == null) {
                msg = "Cadastro realizado com sucesso!";
            } else {
                msg = "Erro ao cadastrar: " + dao.getErrorMessage();
            }
            unidade = new Unidade();
            endereco = new Endereco();
        } else {
        	if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
        		msg = "Você não tem permissão para alterar!";
        	} else {
            endereco.setId(idEndereco);
            unidade.setEndereco(endereco);
            dao.alterar(unidade);
            if (dao.getErrorMessage() == null) {
                msg = "Alteração realizada com sucesso!";
            } else {
                msg = "Erro ao alterar: " + dao.getErrorMessage();
            }
        }}
        listaUnidades = new ArrayList<Unidade>();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmUnidade", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String resultado = "";
        unidade = dao.buscar(idUnidade);
        endereco = unidade.getEndereco();
        idEndereco = endereco.getId();
        if (dao.getErrorMessage() == null) {
            resultado = "editUnidade";
        } else {
            msg = "Erro ao carregar: " + dao.getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstUnidade", mensagem);
        }
        return resultado;
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "Registros carregados com sucesso!";
        listaUnidades = dao.procurar("nomeUnidade", busca);
        if (listaUnidades.isEmpty()) {
            msg = "Nenhum registro foi encontrado!";
        }
        if (dao.getErrorMessage() != null) {
            msg = "Erro ao procurar: " + dao.getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstUnidade", mensagem);
    }

    /**
     * @return the unidade
     */
    public Unidade getUnidade() {
        return unidade;
    }

    /**
     * @param unidade the unidade to set
     */
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    /**
     * @return the dao
     */
    public UnidadeDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(UnidadeDao dao) {
        this.dao = dao;
    }

    /**
     * @return the idUnidade
     */
    public Integer getIdUnidade() {
        return idUnidade;
    }

    /**
     * @param idUnidade the idUnidade to set
     */
    public void setIdUnidade(Integer idUnidade) {
        this.idUnidade = idUnidade;
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
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the idEndereco
     */
    public Integer getIdEndereco() {
        return idEndereco;
    }

    /**
     * @param idEndereco the idEndereco to set
     */
    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * @return the listaUnidades
     */
    public List<Unidade> getListaUnidades() {
        return listaUnidades;
    }

    /**
     * @param aListaUnidades the listaUnidades to set
     */
    public void setListaUnidades(List<Unidade> aListaUnidades) {
        listaUnidades = aListaUnidades;
    }

    /**
     * @return the estados
     */
    public List<SelectItem> getEstados() {
        return estados;
    }

    /**
     * @param estados the estados to set
     */
    public void setEstados(List<SelectItem> estados) {
        this.estados = estados;
    }
}
