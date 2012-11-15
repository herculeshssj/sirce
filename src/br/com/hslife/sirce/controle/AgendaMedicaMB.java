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

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.hslife.sirce.modelo.AgendaMedica;
import br.com.hslife.sirce.modelo.Medico;
import br.com.hslife.sirce.persistencia.AgendaMedicaDao;
import br.com.hslife.sirce.persistencia.MedicoDao;

public class AgendaMedicaMB {

	private AgendaMedica agenda;
	private static List<AgendaMedica> listaAgendas;
	private String busca;
	private Integer idAgenda;
	private AgendaMedicaDao dao;
	private Integer idMedico;
	private List<SelectItem> listaMedicos;
	private Medico medico;

	public AgendaMedicaMB() {
		agenda = new AgendaMedica();
		medico = new Medico();
		agenda.setMedico(medico);
		dao = new AgendaMedicaDao();
		listaMedicos = new ArrayList<SelectItem>();
		busca = "";
		if (listaAgendas == null) {
			listaAgendas = new ArrayList<AgendaMedica>();
		}
		MedicoDao daoM = new MedicoDao();
		for (Medico m : daoM.listarTodos()) {
			listaMedicos.add(new SelectItem(m.getId(), m.getNome()));
		}
	}
	
	public String cadastrar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
			msg = "Você não tem permissão para cadastrar!";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstAgendaMedica", mensagem);
		} else {
			resultado = "editAgenda";			
		}		
		return resultado;
	}

	public void salvar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		if (agenda.getId() == null || agenda.getId() == 0) {
			agenda.getMedico().setId(idMedico);
			dao.salvar(agenda);
			if (dao.getErrorMessage() == null) {
				msg = "Cadastro realizado com sucesso!";
			} else {
				msg = "Erro ao cadastrar: " + dao.getErrorMessage();
			}
			agenda = new AgendaMedica();
		} else {
			if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
				msg = "Você não tem permissão para alterar!";
			} else {
			agenda.getMedico().setId(idMedico);
			dao.alterar(agenda);
			if (dao.getErrorMessage() == null) {
				msg = "Alteração realizada com sucesso!";
				listaAgendas = dao.listarTodos();
			} else {
				msg = "Erro ao alterar: " + dao.getErrorMessage();
			}
		}}
		listaAgendas = new ArrayList<AgendaMedica>();
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmAgendaMedica", mensagem);

	}

	public String editar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		agenda = dao.buscar(idAgenda);
		idMedico = agenda.getMedico().getId();
		if (dao.getErrorMessage() == null) {
			resultado = "editAgenda";
		} else {
			msg = "Erro ao carregar: " + dao.getErrorMessage();
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstAgendaMedica", mensagem);
		}
		return resultado;
	}

	public void excluir() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
			msg = "Você não tem permissão para excluir!";
		} else {
		agenda = dao.buscar(idAgenda);
		dao.excluir(agenda);
		if (dao.getErrorMessage() == null) {
			msg = "Registro excluído com sucesso!";
			listaAgendas = dao.listarTodos();
		} else {
			msg = "Erro ao excluir: " + dao.getErrorMessage();
		} }
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstAgendaMedica", mensagem);

	}

	public void pesquisar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "Registros carregados com sucesso!";
		if (LoginMB.usuarioLogado.getPerfil().equals("MEDIC")) {
			msg = "Você não tem permissão para visualizar outras agendas médicas!";
		} else {		
			listaAgendas = dao.listarAgendaMedico(busca);
			if (listaAgendas.isEmpty()) {
				msg = "Nenhum registro foi encontrado!";
			}	
			if (dao.getErrorMessage() != null) {
				msg = "Erro ao procurar: " + dao.getErrorMessage();
			}
		}
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstAgendaMedica", mensagem);

	}
	
	public void pesquisaMedico() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "Registros carregados com sucesso!";
		if (LoginMB.usuarioLogado.getPerfil().equals("MEDIC")) {
			msg = "Você não tem permissão para visualizar outras agendas médicas!";
		} else {
		try {
			listaAgendas = dao.buscarMedico(idMedico);
			if (listaMedicos.isEmpty()) {
				msg = "Nenhum registro foi encontrado!";
			}
			if (dao.getErrorMessage() != null) {
				msg = "Erro ao procurar: " + dao.getErrorMessage();
			}
		} catch (Exception e) {
			msg = "Valor informado é inválido!";
		}}
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstConsulta", mensagem);
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public AgendaMedica getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaMedica agenda) {
		this.agenda = agenda;
	}

	public List<AgendaMedica> getListaAgendas() {
		return listaAgendas;
	}

	public void setListaAgendas(List<AgendaMedica> listaAgendas) {
		AgendaMedicaMB.listaAgendas = listaAgendas;
	}

	public void setDao(AgendaMedicaDao dao) {
		this.dao = dao;
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public List<SelectItem> getListaMedicos() {
		return listaMedicos;
	}

	public void setListaMedicos(List<SelectItem> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Integer getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Integer idAgenda) {
		this.idAgenda = idAgenda;
	}

}