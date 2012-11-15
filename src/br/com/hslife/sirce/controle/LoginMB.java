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

import br.com.hslife.sirce.modelo.Login;
import br.com.hslife.sirce.persistencia.LoginDao;
import br.com.hslife.sirce.util.Util;

public class LoginMB {

    private Login login;
    public static Login usuarioLogado;
    private List<Login> listaLogin;
    private Integer idLogin;
    private String confirmaSenha;
    private String busca;
    private LoginDao dao;
    private String novaSenha;
    private String senhaAtual;

    /** Creates a new instance of LoginMB */
    public LoginMB() {
        login = new Login();        
        dao = new LoginDao(); 
        listaLogin = new ArrayList<Login>();        
    }

    public String efetuarLogin() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String retorno = "";        
        login.setUsuarioSenha(Util.SHA1(login.getUsuarioSenha()));
        usuarioLogado = dao.buscar("usuarioLogin", login.getUsuarioLogin());
        //usuarioLogado.setNomeUsuario(login.getNomeUsuario());
        if (usuarioLogado != null) {
            if (usuarioLogado.isAtivo()) {
            if (usuarioLogado.getUsuarioSenha().equals(login.getUsuarioSenha())) {
                login = new Login();                          
                return "sucessoLogin";
            } else {
                msg = "Senha inválida!";
            }
            } else {
                msg = "Login desativado! Contato o Administrador.";
            }
        } else {
            msg = "Login inválido!";
        }
        login = new Login();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmLogin", mensagem);
        return retorno;
    }

    public String efetuarLogoff() {    	 	
        usuarioLogado = null;
        return "loginPage";
    }

    public void salvar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        login.setUsuarioSenha(Util.SHA1(login.getUsuarioSenha()));
        confirmaSenha = Util.SHA1(confirmaSenha);
        if (confirmaSenha.equals(login.getUsuarioSenha())) {
            login.setDataCriacao(new Date());
            dao.salvar(login);
            if (dao.getErrorMessage() == null) {
                msg = "Cadastro realizado com sucesso!";
            } else {
                msg = "Erro ao cadastrar: " + dao.getErrorMessage();
            }
            login = new Login();
        } else {
            msg = "As senhas digitadas não coincidem!";
        }
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("frmLogin", mensagem);
    }

    public String editar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        String resultado = "";
        login = dao.buscar(idLogin);
        if (dao.getErrorMessage() == null) {
            resultado = "editLogin";
        } else {
            msg = "Erro ao carregar: " + dao.getErrorMessage();
            FacesMessage mensagem = new FacesMessage(msg);
            contexto.addMessage("lstLogin", mensagem);
        }
        return resultado;
    }

    public void alterar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        senhaAtual = Util.SHA1(senhaAtual);
        novaSenha = Util.SHA1(novaSenha);
        confirmaSenha = Util.SHA1(confirmaSenha);
        if (senhaAtual.equals(login.getUsuarioSenha())) {
            if (confirmaSenha.equals(novaSenha)) {
                if (!confirmaSenha.equals(login.getUsuarioSenha())) {
                    login.setUsuarioSenha(novaSenha);
                    dao.alterar(login);
                    if (dao.getErrorMessage() == null) {
                        msg = "Login alterado com sucesso!";
                    } else {
                        msg = "Erro ao alterar: " + dao.getErrorMessage();
                    }
                } else {
                    msg = "Senha nova não pode ser igual a senha antiga!";
                }
            } else {
                msg = "Senhas não coincidem!";
            }
        } else {
            msg = "Senha atual incorreta!";
        }

        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("formLogin", mensagem);
    }

    public void ativarDesativar() {
        FacesContext contexto = FacesContext.getCurrentInstance();
        String msg = "";
        login = dao.buscar(idLogin);
        if (!login.getUsuarioLogin().equals("admin")) {
            login.setAtivo(!login.isAtivo());
        }
        dao.alterar(login);
        if (dao.getErrorMessage() == null) {
            if (login.isAtivo()) {
                msg = "Login ativado com sucesso!";
            } else {
                msg = "Login desativado com sucesso!";
            }
        } else {
            msg = "Erro ao alterar a situação: " + dao.getErrorMessage();
        }
        login = new Login();
        FacesMessage mensagem = new FacesMessage(msg);
        contexto.addMessage("lstLogin", mensagem);
    }

    /**
     * @return the login
     */
    public Login getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(Login login) {
        this.login = login;
    }

    /**
     * @return the listaLogin
     */
    public List<Login> getListaLogin() {
        List<Login> lista = new ArrayList<Login>();
        lista = dao.listarTodos();
        return lista;
    }

    /**
     * @param listaLogin the listaLogin to set
     */
    public void setListaLogin(List<Login> listaLogin) {
        this.listaLogin = listaLogin;
    }

    /**
     * @return the idLogin
     */
    public Integer getIdLogin() {
        return idLogin;
    }

    /**
     * @param idLogin the idLogin to set
     */
    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    /**
     * @return the confirmaSenha
     */
    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    /**
     * @param confirmaSenha the confirmaSenha to set
     */
    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
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
     * @return the usuarioLogado
     */
    public Login getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Login usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    /**
     * @return the novaSenha
     */
    public String getNovaSenha() {
        return novaSenha;
    }

    /**
     * @param novaSenha the novaSenha to set
     */
    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    /**
     * @return the senhaAtual
     */
    public String getSenhaAtual() {
        return senhaAtual;
    }

    /**
     * @param senhaAtual the senhaAtual to set
     */
    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

}