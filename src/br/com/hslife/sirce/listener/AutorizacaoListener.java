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

package br.com.hslife.sirce.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.hslife.sirce.controle.LoginMB;
import br.com.hslife.sirce.modelo.Login;

public class AutorizacaoListener implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void afterPhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		FacesContext contexto = event.getFacesContext();
		String paginaAtual = contexto.getViewRoot().getViewId();
		boolean isLoginPage =  paginaAtual.lastIndexOf("login.jsp") > -1;
		Login usuario = LoginMB.usuarioLogado;
		if (!isLoginPage && usuario==null) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "loginPage");
		} else if (contexto.getViewRoot().getViewId().equals("/listLogin.jsp") && !usuario.getPerfil().equals("ADMIN")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		} else if (contexto.getViewRoot().getViewId().equals("/formLogin.jsp") && !usuario.getPerfil().equals("ADMIN")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		} else if (contexto.getViewRoot().getViewId().equals("/formExame.jsp") && usuario.getPerfil().equals("MEDIC")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		} else if (contexto.getViewRoot().getViewId().equals("/formConsulta.jsp") && usuario.getPerfil().equals("MEDIC")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		} else if (contexto.getViewRoot().getViewId().equals("/listProntuario.jsp") && usuario.getPerfil().equals("ATEND")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		} else if (contexto.getViewRoot().getViewId().equals("/formProntuario.jsp") && usuario.getPerfil().equals("ATEND")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		} else if (contexto.getViewRoot().getViewId().equals("/listProntRegistro.jsp") && usuario.getPerfil().equals("ATEND")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		} else if (contexto.getViewRoot().getViewId().equals("/listProntVisualiza.jsp") && usuario.getPerfil().equals("ATEND")) {
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, "acessoNegado");
		}
			 
	}
		
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
