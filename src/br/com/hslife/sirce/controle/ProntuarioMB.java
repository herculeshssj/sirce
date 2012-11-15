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

import br.com.hslife.sirce.modelo.Medico;
import br.com.hslife.sirce.modelo.Paciente;
import br.com.hslife.sirce.modelo.Prontuario;
import br.com.hslife.sirce.persistencia.MedicoDao;
import br.com.hslife.sirce.persistencia.PacienteDao;
import br.com.hslife.sirce.persistencia.ProntuarioDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class ProntuarioMB {

    /**
     * @return the listaProntuario
     */
    public List<Prontuario> getListaProntuario() {
        return listaProntuario;
    }

    /**
     * @param aListaProntuario the listaProntuario to set
     */
    public void setListaProntuario(List<Prontuario> aListaProntuario) {
        listaProntuario = aListaProntuario;
    }
    private Paciente paciente;
    private Prontuario prontuario;
    private static List<Prontuario> listaProntuario;
    private static List<Paciente> listaPacientes;
    private Integer idPaciente;
    private Integer idProntuario;
    private ProntuarioDao dao;
    private PacienteDao daoP;
    private String busca;
    private static boolean mostrar;
    private Integer idMedico;
	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}
	private List<SelectItem> listaMedicos;

    /** Creates a new instance of ProntuarioMB */
    public ProntuarioMB() {
        paciente = new Paciente();
        prontuario = new Prontuario();
        prontuario.setPaciente(paciente);
        prontuario.setMedico(new Medico());
        dao = new ProntuarioDao();
        daoP = new PacienteDao();
        if (listaProntuario == null) {
            listaProntuario = new ArrayList<Prontuario>();
        }
        listaMedicos = new ArrayList<SelectItem>();
		MedicoDao daoM = new MedicoDao();
		for (Medico m : daoM.listarTodos()) {
			listaMedicos.add(new SelectItem(m.getId(), m.getNome()));
		}
    }

    public void salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        prontuario.getPaciente().setId(idPaciente);
        prontuario.getMedico().setId(idMedico);
        prontuario.setDataInclusao(new Date());
        //prontuario.setNomeMedico(nomeMedico);
        getDao().salvar(getProntuario());
        if (getDao().getErrorMessage() == null) {
            msg = "Cadastro realizado com sucesso!";
        } else {
            msg = "Erro ao cadastrar: " + getDao().getErrorMessage();
        }
        //setListaProntuario(new ArrayList<Prontuario>());
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmProntuario", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String resultado = "";
        setProntuario(getDao().buscar(getIdProntuario()));
        setPaciente(getProntuario().getPaciente());
        setIdProntuario(getProntuario().getId());
        if (getDao().getErrorMessage() == null) {
            resultado = "editProntuario";
        } else {
            msg = "Erro ao carregar: " + getDao().getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstProntuario", mensagem);
        }
        return resultado;
    }

    public String registrar() {
        mostrar = true;
        return "editProntuario";
    }

    public String visualizar() {
        //listaProntuario = dao.listar("idPaciente", idPaciente);
        listaProntuario = dao.listarProntuarioPaciente(idPaciente);
        mostrar = false;
        return "editProntuario";
    }

    public void excluir() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        setProntuario(getDao().buscar(getIdProntuario()));
        getDao().excluir(getProntuario());
        if (getDao().getErrorMessage() == null) {
            msg = "Registro excluído com sucesso!";
        } else {
            msg = "Erro ao excluir: " + getDao().getErrorMessage();
        }
        setListaProntuario(new ArrayList<Prontuario>());
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstProntuario", mensagem);
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "Registros carregados com sucesso!";
        listaPacientes = daoP.procurar("nome", busca);
        if (listaPacientes.isEmpty()) {
            msg = "Nenhum registro foi encontrado!";
        }
        if (dao.getErrorMessage() != null) {
            msg = "Erro ao procurar: " + dao.getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstProntuario", mensagem);
    }

    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the prontuario
     */
    public Prontuario getProntuario() {
        return prontuario;
    }

    /**
     * @param prontuario the prontuario to set
     */
    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    /**
     * @return the idPaciente
     */
    public Integer getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the idProntuario
     */
    public Integer getIdProntuario() {
        return idProntuario;
    }

    /**
     * @param idProntuario the idProntuario to set
     */
    public void setIdProntuario(Integer idProntuario) {
        this.idProntuario = idProntuario;
    }

    /**
     * @return the dao
     */
    public ProntuarioDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ProntuarioDao dao) {
        this.dao = dao;
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
     * @return the listaPacientes
     */
    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    /**
     * @return the mostrar
     */
    public boolean getMostrar() {
        return mostrar;
    }

    /**
     * @param mostrar the mostrar to set
     */
    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

	public List<SelectItem> getListaMedicos() {
		return listaMedicos;
	}

	public void setListaMedicos(List<SelectItem> listaMedicos) {
		this.listaMedicos = listaMedicos;
	}

	public static void setListaPacientes(List<Paciente> listaPacientes) {
		ProntuarioMB.listaPacientes = listaPacientes;
	}
}
