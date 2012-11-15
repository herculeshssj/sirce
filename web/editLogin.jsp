<!--
  
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
  
  -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<f:view>

    <f:subview id="cabecalho">
        <jsp:include page="header.jsp"/>
    </f:subview>


    <div id="content">

        <div class="post" id="post-01">

            <h1 class="post-title"><a href="#" rel="bookmark">Logins de Usuários</a></h1>

            
            <center>
                <h:form id="frmLogin">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">

                        Nome do Usuário:
                        <h:inputText id="txtNome" value="#{LoginMB.login.nomeUsuario}" required="true" requiredMessage="Informe o nome do usuário!" validatorMessage="Nome deve ter no mínimo 6 dígitos!">
                            <f:validateLength minimum="6"/>
                        </h:inputText>

                        Login do Usuário:
                        <h:outputLabel id="txtLogin" value="#{LoginMB.login.usuarioLogin}"/>
                       
                        Perfil:
                        <h:selectOneMenu value="#{LoginMB.login.perfil}" required="true" requiredMessage="Selecione o nível de acesso!" style="width: 180px;">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItem itemValue="ADMIN" itemLabel="Administrador"/>
                            <f:selectItem itemValue="ATEND" itemLabel="Atendente"/>
                            <f:selectItem itemValue="MEDIC" itemLabel="Médico"/>
                        </h:selectOneMenu>

                        Senha atual:
                        <h:inputSecret id="txtSenha" value="#{LoginMB.senhaAtual}" required="true" requiredMessage="Informe a senha atual do usuário!" validatorMessage="Senha deve ter no mínimo 6 dígitos!">
                            <f:validateLength minimum="6"/>
                        </h:inputSecret>

                        Nova Senha:
                        <h:inputSecret id="txtNovaSenha" value="#{LoginMB.novaSenha}" required="true" requiredMessage="Informe a nova senha do usuário!" validatorMessage="Senha deve ter no mínimo 6 dígitos!">
                            <f:validateLength minimum="6"/>
                        </h:inputSecret>

                        Confirme a senha:
                        <h:inputSecret id="txtConfirmaSenha" value="#{LoginMB.confirmaSenha}" required="true" requiredMessage="Confirme a nova senha do usuário!"/>

                        <h:inputHidden value="#{LoginMB.login.id}"/>
                        <h:commandButton id="btnSalvar" style="padding: 3px 10px;" value="Salvar" action="#{LoginMB.alterar}"/>
                    </h:panelGrid>


                </h:form>
            </center>
            
<br/>

            <div class="post-info">

                <strong><a href="listLogin.jsf"><< Voltar</a></strong>

            </div>

        </div>


    </div>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>



</f:view>
