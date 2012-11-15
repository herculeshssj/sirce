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
import br.com.hslife.sirce.modelo.Paciente;
import br.com.hslife.sirce.persistencia.PacienteDao;
import br.com.hslife.sirce.util.Estados;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class PacienteMB {

    private Paciente paciente;
    private Endereco endereco;
    private static List<Paciente> listaPaciente;
    private Integer idPaciente;
    private Integer idEndereco;
    private PacienteDao dao;
    private String busca;
    private List<SelectItem> dias;
    private List<SelectItem> meses;
    private List<SelectItem> anos;
    private List<SelectItem> estados;

    /** Creates a new instance of PacienteMB */
    public PacienteMB() {
        paciente = new Paciente();
        endereco = new Endereco();
        dao = new PacienteDao();        
        geraComboDataNasc();
         if (listaPaciente == null) {
            listaPaciente = new ArrayList<Paciente>();
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
		if (LoginMB.usuarioLogado.getPerfil().equals("MEDIC")) {
			msg = "Você não tem permissão para cadastrar!";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstPaciente", mensagem);
		} else {
			resultado = "editPaciente";			
		}		
		return resultado;
	}
    
    public String cadastrarHome() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		if (LoginMB.usuarioLogado.getPerfil().equals("MEDIC")) {
			msg = "Você não tem permissão para cadastrar!";
			resultado = "acessoNegado";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstPaciente", mensagem);
		} else {
			resultado = "cadPaciente";			
		}		
		return resultado;
	}

    public void salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        if (paciente.getId() == null || paciente.getId() == 0) {
            paciente.setEndereco(endereco);
            dao.salvar(paciente);
            if (dao.getErrorMessage() == null) {
                msg = "Cadastro realizado com sucesso!";
            } else {
                msg = "Erro ao cadastrar: " + dao.getErrorMessage();
            }
            paciente = new Paciente();
            endereco = new Endereco();
        } else {
        	if (LoginMB.usuarioLogado.getPerfil().equals("MEDIC")) {
        		msg = "Você não tem permissão para alterar!";
        	} else {
            endereco.setId(idEndereco);
            paciente.setEndereco(endereco);
            dao.alterar(paciente);
            if (dao.getErrorMessage() == null) {
                msg = "Alteração realizada com sucesso!";
            } else {
                msg = "Erro ao alterar: " + dao.getErrorMessage();
            }
        }}
        listaPaciente = new ArrayList<Paciente>();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmPaciente", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String resultado = "";
        paciente = dao.buscar(idPaciente);
        endereco = paciente.getEndereco();
        idEndereco = endereco.getId();
        if (dao.getErrorMessage() == null) {
            resultado  ="editPaciente";
        } else {
            msg = "Erro ao carregar: " + dao.getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstPaciente", mensagem);
        }
        return resultado;
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "Registros carregados com sucesso!";        
        listaPaciente = dao.procurar("nome", busca);
        if (listaPaciente.isEmpty()) {
            msg = "Nenhum registro foi encontrado!";
        }
        if (dao.getErrorMessage() != null) {
            msg = "Erro ao procurar: " + dao.getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstPaciente", mensagem);
    }

    private void geraComboDataNasc() {
        // Carrega a combobox com os dias do mês
        dias = new ArrayList<SelectItem>();
        for (int i = 1; i <= 31; i++) {
            dias.add(new SelectItem(i, String.valueOf(i)));
        }
        meses = new ArrayList<SelectItem>();
        for (int i = 1; i <= 12; i++) {
            meses.add(new SelectItem(i, String.valueOf(i)));
        }
        anos = new ArrayList<SelectItem>();
        for (int i = 1930; i <= 2020; i++) {
            anos.add(new SelectItem(i, String.valueOf(i)));
        }
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
     * @return the listaPaciente
     */
    public List<Paciente> getListaPaciente() {
        return listaPaciente;
    }

    /**
     * @param listaPaciente the listaPaciente to set
     */
    public void setListaPaciente(List<Paciente> listaPaciente) {
        PacienteMB.listaPaciente = listaPaciente;
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
     * @return the dias
     */
    public List<SelectItem> getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(List<SelectItem> dias) {
        this.dias = dias;
    }

    /**
     * @return the meses
     */
    public List<SelectItem> getMeses() {
        return meses;
    }

    /**
     * @param meses the meses to set
     */
    public void setMeses(List<SelectItem> meses) {
        this.meses = meses;
    }

    /**
     * @return the anos
     */
    public List<SelectItem> getAnos() {
        return anos;
    }

    /**
     * @param anos the anos to set
     */
    public void setAnos(List<SelectItem> anos) {
        this.anos = anos;
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
