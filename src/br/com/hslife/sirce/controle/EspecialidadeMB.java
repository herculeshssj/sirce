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

import br.com.hslife.sirce.modelo.Especialidade;
import br.com.hslife.sirce.persistencia.EspecialidadeDao;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class EspecialidadeMB {

	private Especialidade especialidade;
	private EspecialidadeDao dao;
	private Integer idEspecialidade;
	private String busca;
	private static List<Especialidade> listaEspecialidades;

	public EspecialidadeMB() {
		especialidade = new Especialidade();
		dao = new EspecialidadeDao();
		if (listaEspecialidades == null) {
			listaEspecialidades = new ArrayList<Especialidade>();
		}
	}

	public String cadastrar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
			msg = "Você não tem permissão para cadastrar!";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstEspecialidade", mensagem);
		} else {
			resultado = "editEspecialidade";			
		}		
		return resultado;
	}

	public String salvar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";		
			if (especialidade.getId() == null || especialidade.getId() == 0) {
				dao.salvar(especialidade);
				if (dao.getErrorMessage() == null) {
					msg = "Cadastro realizado com sucesso!";
				} else {
					msg = "Erro ao cadastrar: " + dao.getErrorMessage();
				}
				especialidade = new Especialidade();
			} else {
				if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
					msg = "Você não tem permissão para alterar!";
				} else {
				dao.alterar(especialidade);
				if (dao.getErrorMessage() == null) {
					msg = "Alteração realizada com sucesso!";
					listaEspecialidades = dao.listarTodos();
				} else {
					msg = "Erro ao alterar: " + dao.getErrorMessage();
				}
			}		}
		listaEspecialidades = new ArrayList<Especialidade>();
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmEspecialidade", mensagem);
		return "";
	}

	public String editar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		especialidade = dao.buscar(idEspecialidade);
		if (dao.getErrorMessage() == null) {
			resultado = "editEspecialidade";
		} else {
			msg = "Erro ao carregar: " + dao.getErrorMessage();
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstEspecialidade", mensagem);
		}
		return resultado;
	}

	public String pesquisar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "Registros carregados com sucesso!";
		setListaEspecialidades(dao.procurar("descricao", busca));
		if (listaEspecialidades.isEmpty()) {
			msg = "Nenhum registro foi encontrado!";
		}
		if (dao.getErrorMessage() != null) {
			msg = "Erro ao procurar: " + dao.getErrorMessage();
		}
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstEspecialidade", mensagem);
		return "";
	}

	/**
	 * @return the especialidade
	 */
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	/**
	 * @param especialidade
	 *            the especialidade to set
	 */
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	/**
	 * @return the idEspecialidade
	 */
	public Integer getIdEspecialidade() {
		return idEspecialidade;
	}

	/**
	 * @param idEspecialidade
	 *            the idEspecialidade to set
	 */
	public void setIdEspecialidade(Integer idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	/**
	 * @return the busca
	 */
	public String getBusca() {
		return busca;
	}

	/**
	 * @param busca
	 *            the busca to set
	 */
	public void setBusca(String busca) {
		this.busca = busca;
	}

	/**
	 * @return the listaEspecialidades
	 */
	public List<Especialidade> getListaEspecialidades() {
		return EspecialidadeMB.listaEspecialidades;
	}

	/**
	 * @param aListaEspecialidades
	 *            the listaEspecialidades to set
	 */
	public void setListaEspecialidades(List<Especialidade> aListaEspecialidades) {
		listaEspecialidades = aListaEspecialidades;
	}
}
