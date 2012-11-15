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

<%--
    This file is an entry point for JavaServer Faces application.
--%>


<f:view>


	<f:subview id="cabecalho">
		<jsp:include page="header.jsp" />
	</f:subview>

	<div id="content">

	<div class="post" id="post-01"><br />
	<h:form id="frmHome">
	<table width="100%" border="0" cellspacing="20">
		<tr>
			<td><h1 class="post-title"><a href="formConsulta.jsf" rel="bookmark">Agendar
		Consulta</a></h1></td>
			<td><h1 class="post-title"><a href="listConsulta.jsf" rel="bookmark">Consultas
		Marcadas</a></h1></td>
		</tr>
		<tr>
			<td><h1 class="post-title"><a href="formExame.jsf" rel="bookmark">Agendar
		Exame</a></h1></td>
			<td><h1 class="post-title"><a href="listExame.jsf" rel="bookmark">Exames
		Marcados</a></h1></td>
		</tr>
		<tr>
			<td><h1 class="post-title"><h:commandLink value="Cadastrar Paciente" action="#{PacienteMB.cadastrarHome}"></h:commandLink></h1></td>
			<td><h1 class="post-title"><a href="listProntVisualiza.jsf" rel="bookmark">Prontuário do Paciente
		</a></h1></td>
		</tr>
	</table>		
	</h:form> <br />
	


	</div>
	<f:subview id="rodape">
		<jsp:include page="footer.jsp" />
	</f:subview>

</f:view>