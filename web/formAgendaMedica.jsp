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
		<jsp:include page="header.jsp" />
	</f:subview>


	<div id="content">

	<div class="post" id="post-01">

	<h1 class="post-title"><a href="#" rel="bookmark">Agenda
	Médica</a></h1>


	<center><h:form id="frmAgendaMedica">
		<h:messages style="font-weight: bold;" />
		<h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">
			Médico:
            <h:selectOneMenu id="txtMedico"
				value="#{AgendaMedicaMB.idMedico}" required="true"
				requiredMessage="É necessário escolher um médico!">
				<f:selectItem itemLabel="Selecione" />
				<f:selectItems value="#{AgendaMedicaMB.listaMedicos}" />
			</h:selectOneMenu>
			Dia da semana:
			<h:selectOneMenu id="txtDiaSemana"
				value="#{AgendaMedicaMB.agenda.diaSemana}" required="true"
				requiredMessage="Selecione um dia da semana!">
				<f:selectItem itemLabel="Selecione" />				
				<f:selectItem itemValue="1" itemLabel="Segunda" />
				<f:selectItem itemValue="2" itemLabel="Terça" />
				<f:selectItem itemValue="3" itemLabel="Quarta" />
				<f:selectItem itemValue="4" itemLabel="Quinta" />
				<f:selectItem itemValue="5" itemLabel="Sexta" />
				<f:selectItem itemValue="6" itemLabel="Sábado" />
			</h:selectOneMenu>
			
			Turno:
			<h:selectOneMenu id="txtTurno" value="#{AgendaMedicaMB.agenda.turno}" required="true" requiredMessage="Selecione o turno!">
				<f:selectItem itemLabel="Selecione" />
				<f:selectItem itemValue="1" itemLabel="Integral" />
				<f:selectItem itemValue="2" itemLabel="Manhã" />
				<f:selectItem itemValue="3" itemLabel="Tarde" />								
			</h:selectOneMenu>
             
            Vagas:
            <h:inputText id="txtVagas" value="#{AgendaMedicaMB.agenda.vagas}" required="true" requiredMessage="Informe a quantidade de vagas!"/>

			<h:inputHidden id="txtId" value="#{AgendaMedicaMB.agenda.id}" />
			<h:commandButton id="btnSalvar" style="padding: 3px 10px;"
				value="Salvar" action="#{AgendaMedicaMB.salvar}" />
		</h:panelGrid>


	</h:form></center><br/>
	<div class="post-info"><strong><a
		href="listAgendaMedica.jsf"><< Voltar</a></strong></div>

	</div>

	</div>
	<f:subview id="rodape">
		<jsp:include page="footer.jsp" />
	</f:subview>



</f:view>
