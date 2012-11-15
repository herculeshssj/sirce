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
import br.com.hslife.sirce.modelo.Medico;
import br.com.hslife.sirce.modelo.Unidade;
import br.com.hslife.sirce.persistencia.EspecialidadeDao;
import br.com.hslife.sirce.persistencia.MedicoDao;
import br.com.hslife.sirce.persistencia.UnidadeDao;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

public class MedicoMB {

    private Medico medico;
    private static List<Medico> listaMedicos;
    private Integer idMedico;
    private Integer idEspecialidade;
    private Integer idUnidade;
    private MedicoDao dao;
    private String busca;
    private List<SelectItem> listaUnidades;
    private List<SelectItem> listaEspecialidades;

    public MedicoMB() {
        medico = new Medico();
        dao = new MedicoDao();
        medico.setEspecialidade(new Especialidade());
        medico.setUnidade(new Unidade());
        listaEspecialidades = new ArrayList<SelectItem>();
        listaUnidades = new ArrayList<SelectItem>();
        if (listaMedicos == null) {
            listaMedicos = new ArrayList<Medico>();
        }
        EspecialidadeDao daoE = new EspecialidadeDao();
        for (Especialidade e : daoE.listarTodos()) {
            listaEspecialidades.add(new SelectItem(e.getId(), e.getDescricao()));
        }
        UnidadeDao daoU = new UnidadeDao();
        for (Unidade u : daoU.listarTodos()) {
            listaUnidades.add(new SelectItem(u.getId(), u.getNomeUnidade()));
        }
    }

    public String cadastrar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		String msg = "";
		String resultado = "";
		if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
			msg = "Você não tem permissão para cadastrar!";
			FacesMessage mensagem = new FacesMessage(msg);
			contexto.addMessage("lstMedico", mensagem);
		} else {
			resultado = "editMedico";			
		}		
		return resultado;
	}
    
    public void salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        if (getMedico().getId() == null || getMedico().getId() == 0) {
            medico.getEspecialidade().setId(idEspecialidade);
            medico.getUnidade().setId(idUnidade);
            getDao().salvar(getMedico());
            if (getDao().getErrorMessage() == null) {
                msg = "Cadastro realizado com sucesso!";
            } else {
                msg = "Erro ao cadastrar: " + getDao().getErrorMessage();
            }
            setMedico(new Medico());
        } else {
        	if (!LoginMB.usuarioLogado.getPerfil().equals("ADMIN")) {
        		msg = "Você não tem permissão para alterar!";
        	} else {        	
            medico.getEspecialidade().setId(idEspecialidade);
            medico.getUnidade().setId(idUnidade);
            dao.alterar(medico);
            if (getDao().getErrorMessage() == null) {
                msg = "Alteração realizada com sucesso!";
                setListaMedicos(getDao().listarTodos());
            } else {
                msg = "Erro ao alterar: " + getDao().getErrorMessage();
            }
        }}
        setListaMedicos(new ArrayList<Medico>());
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmMedico", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String resultado = "";
        setMedico(getDao().buscar(getIdMedico()));
        idEspecialidade = medico.getEspecialidade().getId();
        idUnidade = medico.getUnidade().getId();
        if (getDao().getErrorMessage() == null) {
            resultado = "editMedico";
        } else {
            msg = "Erro ao carregar: " + getDao().getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstMedico", mensagem);
        }
        return resultado;
    }

    public void pesquisar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "Registros carregados com sucesso!";
        listaMedicos = dao.procurar("crm", busca);
        if (listaMedicos.isEmpty()) {
            msg = "Nenhum registro foi encontrado!";
        }
        if (dao.getErrorMessage() != null) {
            msg = "Erro ao procurar: " + dao.getErrorMessage();
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstMedico", mensagem);
    }

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * @return the idMedico
     */
    public Integer getIdMedico() {
        return idMedico;
    }

    /**
     * @param idMedico the idMedico to set
     */
    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    /**
     * @return the idEspecialidade
     */
    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    /**
     * @param idEspecialidade the idEspecialidade to set
     */
    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
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
     * @return the dao
     */
    public MedicoDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(MedicoDao dao) {
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
     * @return the listaUnidades
     */
    public List<SelectItem> getListaUnidades() {
        return listaUnidades;
    }

    /**
     * @return the listaEspecialidades
     */
    public List<SelectItem> getListaEspecialidades() {
        return listaEspecialidades;
    }

    /**
     * @return the listaMedicos
     */
    public List<Medico> getListaMedicos() {
        return listaMedicos;
    }

    /**
     * @param aListaMedicos the listaMedicos to set
     */
    public void setListaMedicos(List<Medico> aListaMedicos) {
        listaMedicos = aListaMedicos;
    }
}
