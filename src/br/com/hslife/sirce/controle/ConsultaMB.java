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
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.hslife.sirce.logic.AgendaBusinessLogic;
import br.com.hslife.sirce.modelo.AgendaDiaria;
import br.com.hslife.sirce.modelo.AgendaMedica;
import br.com.hslife.sirce.modelo.Consulta;
import br.com.hslife.sirce.modelo.Especialidade;
import br.com.hslife.sirce.modelo.Medico;
import br.com.hslife.sirce.modelo.Paciente;
import br.com.hslife.sirce.modelo.Unidade;
import br.com.hslife.sirce.persistencia.AgendaDiariaDao;
import br.com.hslife.sirce.persistencia.ConsultaDao;
import br.com.hslife.sirce.persistencia.EspecialidadeDao;
import br.com.hslife.sirce.persistencia.MedicoDao;
import br.com.hslife.sirce.persistencia.PacienteDao;
import br.com.hslife.sirce.persistencia.UnidadeDao;

public class ConsultaMB {

	private Consulta consulta;
	private Integer idConsulta;
	private ConsultaDao dao;
	private String busca;
	private List<Consulta> listaConsultas;
	private List<AgendaDiaria> listaDiarias;
	private Integer idMedico;
	private List<SelectItem> listaMedicos;
	private AgendaBusinessLogic agendaBL;
	private AgendaDiaria diaria;
	private Integer idPaciente;
	private List<SelectItem> listaPacientes;
	private Integer idEspecialidade;
	private List<SelectItem> listaEspecialidades;
	private Integer idUnidade;
	private List<SelectItem> listaUnidades;
	private List<SelectItem> especialidades;
	private List<SelectItem> medicos;


	public ConsultaMB() {
		consulta = new Consulta();
		consulta.setMedico(new Medico());
		consulta.setPaciente(new Paciente());
		dao = new ConsultaDao();
		listaConsultas = new ArrayList<Consulta>();
		listaConsultas = dao.listarMarcados();
		listaMedicos = new ArrayList<SelectItem>();
		listaPacientes = new ArrayList<SelectItem>();
		listaEspecialidades = new ArrayList<SelectItem>();
		MedicoDao daoM = new MedicoDao();
		for (Medico m : daoM.listarTodos()) {
			listaMedicos.add(new SelectItem(m.getId(), m.getNome()));
		}
		agendaBL = new AgendaBusinessLogic();	
		diaria = new AgendaDiaria();
		diaria.setIdAgenda(new AgendaMedica());
		listaDiarias = new ArrayList<AgendaDiaria>();
		AgendaDiariaDao daoD = new AgendaDiariaDao();
		listaDiarias = daoD.listarTodos();
		PacienteDao daoP = new PacienteDao();
		for (Paciente p : daoP.listarTodos()) {
			listaPacientes.add(new SelectItem(p.getId(), p.getNome()));
		}
		EspecialidadeDao daoE = new EspecialidadeDao();
        for (Especialidade e : daoE.listarTodos()) {
            listaEspecialidades.add(new SelectItem(e.getId(), e.getDescricao()));
        }
        listaUnidades = new ArrayList<SelectItem>();
        UnidadeDao daoU = new UnidadeDao();
        for (Unidade u : daoU.listarTodos()) {
            listaUnidades.add(new SelectItem(u.getId(), u.getNomeUnidade()));
        }
        especialidades = new ArrayList<SelectItem>();
        medicos = new ArrayList<SelectItem>();
	}

	public String agendar() {		
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
                especialidades = new ArrayList<SelectItem>();
                medicos = new ArrayList<SelectItem>();
                if (idEspecialidade == 0 && idMedico == 0){
                    for (Especialidade e : dao.listarEspecialidades(idUnidade)) {
                        especialidades.add(new SelectItem(e.getId(), e.getDescricao()));
                    }
                    msg = "Informe a especialidade!";
                } else if (idMedico == 0){
                    for (Medico m : dao.listarMedicos(idEspecialidade)) {
                         medicos.add(new SelectItem(m.getId(), m.getNome()));
                    }
                    msg = "Informe o médico!";
                } else {                    
                      AgendaDiariaDao daoD = new AgendaDiariaDao();
			consulta.getMedico().setId(idMedico);
			consulta.getPaciente().setId(idPaciente);
			Date hoje = new Date();
			if (consulta.getDataConsulta().before(new Date())) {
				msg = "Não é possível agendar para uma data anterior!";
			} else {
				msg = agendaBL.agendarConsulta(consulta);
			}
			listaDiarias = daoD.listarTodos();
                }
                /*
		if (idPaciente == 0) {
			msg = "Informe o paciente!";
		} else if (idMedico == 0) {			
			msg = "Informe o médico!";
		} else if (consulta.getDataConsulta() == null) {
			msg = "Informe a data da consulta!";
		} else if (consulta.getTurno() == 0) { 
			msg = "Informe a hora da consulta!";
		} else {
			AgendaDiariaDao daoD = new AgendaDiariaDao();
			consulta.getMedico().setId(idMedico);
			consulta.getPaciente().setId(idPaciente);
			Date hoje = new Date();
			if (consulta.getDataConsulta().before(new Date())) {
				msg = "Não é possível agendar para uma data anterior!";
			} else {
				msg = agendaBL.agendarConsulta(consulta);
			}						
			listaDiarias = daoD.listarTodos();			
		}	*/
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmConsulta", mensagem);
		return "";
	}

	public String pesquisar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "Registros carregados com sucesso!";
		try {
			listaConsultas = dao.buscarConsultas(new Integer(busca));
			if (listaConsultas.isEmpty()) {
				msg = "Nenhum registro foi encontrado!";
			}
			if (dao.getErrorMessage() != null) {
				msg = "Erro ao procurar: " + dao.getErrorMessage();
			}
		} catch (Exception e) {
			msg = "Valor informado é inválido!";
		}
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstConsulta", mensagem);
		return "";
	}
	
	public String pesquisaMedico() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "Registros carregados com sucesso!";
		try {
			listaConsultas = dao.buscarMedico(idMedico);
			if (listaConsultas.isEmpty()) {
				msg = "Nenhum registro foi encontrado!";
			}
			if (dao.getErrorMessage() != null) {
				msg = "Erro ao procurar: " + dao.getErrorMessage();
			}
		} catch (Exception e) {
			msg = "Valor informado é inválido!";
		}
	
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstConsulta", mensagem);
		return "";
	}	
	
	public String pesquisaEspecialidade() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "Registros carregados com sucesso!";
		try {
			listaConsultas = dao.buscarEspecialidade(idEspecialidade);
			if (listaConsultas.isEmpty()) {
				msg = "Nenhum registro foi encontrado!";
			}
			if (dao.getErrorMessage() != null) {
				msg = "Erro ao procurar: " + dao.getErrorMessage();
			}
		} catch (Exception e) {
			msg = "Valor informado é inválido!";
		}
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("lstConsulta", mensagem);
		return "";
	}

	public String registrar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		consulta = dao.buscar(idConsulta);
		consulta.setCompareceu(true);
		msg = agendaBL.registrarConsulta(consulta);
		//dao.alterar(consulta);
		//if (dao.getErrorMessage() == null) {
		//	msg = "Consulta registrada com sucesso";
		//} else {
		//	msg = "Erro ao registrar: " + dao.getErrorMessage();
		//}
		listaConsultas = dao.listarMarcados();
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmConsulta", mensagem);
		return "";
	}

	public String desmarcar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		if (LoginMB.usuarioLogado.getPerfil().equals("MEDIC")) {
			msg = "Você não tem permissão para alterar!";
		} else {
		consulta = dao.buscar(idConsulta);
		//dao.excluir(consulta);
		//if (dao.getErrorMessage() == null) {
		//	msg = "Consulta desmarcada com sucesso";
		//} else {
		//	msg = "Erro ao desmarcar: " + dao.getErrorMessage();
		//}}
		}
		msg = agendaBL.desmarcarConsulta(consulta);
		listaConsultas = dao.listarMarcados();
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmConsulta", mensagem);
		return "";
	}
	
	public String carregaEspecialidades(ValueChangeEvent event) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		especialidades = new ArrayList<SelectItem>();
		for (Especialidade e : dao.listarEspecialidades(idUnidade)) {
            especialidades.add(new SelectItem(e.getId(), e.getDescricao()));
        }
		return "SUCCESS";
	}
	
	public String carregaMedicos(ValueChangeEvent event) {
		FacesContext contexto = FacesContext.getCurrentInstance();
		medicos = new ArrayList<SelectItem>();
		for (Medico m : dao.listarMedicos(idEspecialidade)) {
            medicos.add(new SelectItem(m.getId(), m.getNome()));
        }
		return "SUCCESS";
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public List<Consulta> getListaConsultas() {
		return listaConsultas;
	}

	public void setListaConsultas(List<Consulta> listaConsultas) {
		this.listaConsultas = listaConsultas;
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

	public AgendaDiaria getDiaria() {
		return diaria;
	}

	public void setDiaria(AgendaDiaria diaria) {
		this.diaria = diaria;
	}

	public List<AgendaDiaria> getListaDiarias() {
		return listaDiarias;
	}

	public void setListaDiarias(List<AgendaDiaria> listaDiarias) {
		this.listaDiarias = listaDiarias;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public List<SelectItem> getListaPacientes() {
		return listaPacientes;
	}

	public void setListaPacientes(List<SelectItem> listaPacientes) {
		this.listaPacientes = listaPacientes;
	}

	public Integer getIdEspecialidade() {
		return idEspecialidade;
	}

	public void setIdEspecialidade(Integer idEspecialidade) {
		this.idEspecialidade = idEspecialidade;
	}

	public List<SelectItem> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<SelectItem> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public List<SelectItem> getListaUnidades() {
		return listaUnidades;
	}

	public void setListaUnidades(List<SelectItem> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}

	public List<SelectItem> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<SelectItem> especialidades) {
		this.especialidades = especialidades;
	}

	public List<SelectItem> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<SelectItem> medicos) {
		this.medicos = medicos;
	}

}