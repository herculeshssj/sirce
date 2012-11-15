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
import javax.faces.model.SelectItem;

import br.com.hslife.sirce.logic.AgendaBusinessLogic;
import br.com.hslife.sirce.modelo.Exame;
import br.com.hslife.sirce.modelo.Paciente;
import br.com.hslife.sirce.modelo.TipoExame;
import br.com.hslife.sirce.persistencia.ExameDao;
import br.com.hslife.sirce.persistencia.PacienteDao;
import br.com.hslife.sirce.persistencia.TipoExameDao;

public class ExameMB {

	private Exame exame;
	private Integer idExame;
	private Integer idTipo;
	private ExameDao dao;
	private String busca;
	private List<Exame> listaExames;
	private List<SelectItem> listaTipos;
	private AgendaBusinessLogic agendaBL;
	private Integer idPaciente;
	private List<SelectItem> listaPacientes;

	public ExameMB() {
		exame = new Exame();
		exame.setTipo(new TipoExame());
		exame.setPaciente(new Paciente());
		dao = new ExameDao();
		listaExames = new ArrayList<Exame>();
		listaExames = dao.listarMarcados();
		listaTipos = new ArrayList<SelectItem>();
		listaPacientes = new ArrayList<SelectItem>();
		TipoExameDao daoT = new TipoExameDao();
		for (TipoExame t : daoT.listarTodos()) {
			listaTipos.add(new SelectItem(t.getId(), t.getDescricao()));
		}
		agendaBL = new AgendaBusinessLogic();
		PacienteDao daoP = new PacienteDao();
		for (Paciente p : daoP.listarTodos()) {
			listaPacientes.add(new SelectItem(p.getId(), p.getNome()));
		}
	}

	public void agendar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		exame.getTipo().setId(idTipo);
		exame.getPaciente().setId(idPaciente);
		//dao.salvar(exame);
		Date hoje = new Date();
		if (exame.getData().before(new Date())) {
			msg = "Não é possível agendar para uma data anterior!";
		} else {
			msg = agendaBL.agendarExame(exame);
		}		
		/*
		if (dao.getErrorMessage() == null) {
			msg = "Exame marcado com sucesso";
		} else {
			msg = "Erro ao salvar: " + dao.getErrorMessage();
		}
		*/
		exame = new Exame();
		listaExames = dao.listarMarcados();
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmExame", mensagem);
	}

	public void pesquisar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "Registros carregados com sucesso!";        
        listaExames = dao.buscarPaciente(busca);
        if (listaExames.isEmpty()) {
            msg = "Nenhum registro foi encontrado!";
        }
        if (dao.getErrorMessage() != null) {
            msg = "Erro ao procurar: " + dao.getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstExame", mensagem);
	}

	public void registrar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		exame = dao.buscar(idExame);
		exame.setCompareceu(true);
		dao.alterar(exame);		
		if (dao.getErrorMessage() == null) {
			msg = "Exame registrado com sucesso";
		} else {
			msg = "Erro ao registrar: " + dao.getErrorMessage();
		}
		listaExames = dao.listarMarcados();
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmExame", mensagem);
	}

	public void desmarcar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		if (LoginMB.usuarioLogado.getPerfil().equals("MEDIC")) {
			msg = "Você não tem permissão para alterar!";
		} else {
		exame = dao.buscar(idExame);		
		dao.excluir(exame);		
		if (dao.getErrorMessage() == null) {
			msg = "Exame desmarcado com sucesso";
		} else {
			msg = "Erro ao desmarcar: " + dao.getErrorMessage();
		}}
		listaExames = dao.listarMarcados();
		FacesMessage mensagem = new FacesMessage(msg);
		contexto.addMessage("frmExame", mensagem);
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Exame> getListaExames() {
		return listaExames;
	}

	public void setListaExames(List<Exame> listaExames) {
		this.listaExames = listaExames;
	}

	public String getBusca() {
		return busca;
	}

	public void setBusca(String busca) {
		this.busca = busca;
	}

	public Integer getIdExame() {
		return idExame;
	}

	public void setIdExame(Integer idExame) {
		this.idExame = idExame;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public List<SelectItem> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<SelectItem> listaTipos) {
		this.listaTipos = listaTipos;
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

}
