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
 
<html>

    <head>
        <title>S I R C E - Sistema de Regularização de Consultas e Exames</title>

        <link rel="stylesheet" href="style.css" type="text/css" media="screen" />
        <!--[if IE]><link rel="stylesheet" type="text/css" href="ie.css" media="screen" /><![endif]-->
    </head>

    <body>

        <div id="container">
            <div>
                <img src="images/sirce-logo.jpg" alt="logo" width="950"/>
            </div>
            <div id="wrapper">
                <f:view>
                    <div id="content">
                        <div class="post" id="post-01">
                            <center>                               
                                <br/><br/>
                                <h:form id="frmLogin">

                                    <p>Para entrar no sistema, entre com seu login e senha</p>

                                    <h:messages style="font-weight: bold;"/>
                                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">

                                        Login:
                                        <h:inputText id="txtLogin" required="true" requiredMessage="Informe o login!" value="#{LoginMB.login.usuarioLogin}"/>

                                        Senha:
                                        <h:inputSecret id="txtSenha" required="true" requiredMessage="Informe a senha!" value="#{LoginMB.login.usuarioSenha}"/>

                                        <br/>
                                        <h:commandButton id="btnEntrar" style="padding: 3px 10px;" value="Entrar no Sistema" action="#{LoginMB.efetuarLogin}"/>

                                    </h:panelGrid>
                               </h:form>
                            </center>
                        </div>
                    </div>

                </f:view>
            </div>
            <div id="footer">
                <p align="center">
			SIRCE v1.0 &copy; 2010. Todos os direitos reservados.			
                </p>
            </div>
        </div>

    </body>
</html>
