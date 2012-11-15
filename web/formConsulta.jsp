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
<%@taglib prefix="rich" uri="http://richfaces.org/rich" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

 
<f:view>

	<f:subview id="cabecalho">
		<jsp:include page="header.jsp" />
	</f:subview>

	<div id="content">

	<div class="post" id="post-01">

	<h1 class="post-title"><a href="#" rel="bookmark">Agendamento de Consulta</a></h1>


	<center><h:form id="frmConsulta">
		<h:messages style="font-weight: bold;" />
		<h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">
        
             Paciente:
             <h:selectOneMenu id="txtPaciente" value="#{ConsultaMB.idPaciente}" required="true" requiredMessage="Informe o paciente!">
                 <f:selectItem itemLabel="Selecione"/>
             	<f:selectItems value="#{ConsultaMB.listaPacientes}"/>
             </h:selectOneMenu>
             
             Unidade:
             <h:selectOneMenu id="txtUnidade" value="#{ConsultaMB.idUnidade}" required="true" requiredMessage="Informe a unidade!">
             	<f:selectItem itemLabel="Selecione"/>
             	<f:selectItems value="#{ConsultaMB.listaUnidades}"/> 
             	   	         	
             </h:selectOneMenu>
             
             Especialidade:
             <h:selectOneMenu id="txtEspecialidade" value="#{ConsultaMB.idEspecialidade}">
             	<f:selectItem itemLabel="Selecione" itemValue="0"/>
             	<f:selectItems value="#{ConsultaMB.especialidades}"/>               	           	             	
             </h:selectOneMenu>
             
             Médico:
             <h:selectOneMenu id="txtMedico" value="#{ConsultaMB.idMedico}">
             	<f:selectItem itemLabel="Selecione" itemValue="0"/>
             	<f:selectItems value="#{ConsultaMB.medicos}"/>
             </h:selectOneMenu>
                        
             
             Data da consulta:
             <rich:calendar id="txtDataConsulta" datePattern="dd/MM/yyyy" value="#{ConsultaMB.consulta.dataConsulta}" required="true" requiredMessage="Informe a data da consulta!"/>
             
             Hora da consulta:
             <h:selectOneMenu id="txtHoraExame" value="#{ConsultaMB.consulta.turno}" required="true" requiredMessage="Informe a hora da consulta!">
             	<f:selectItem itemLabel="Selecione"/>
             	<f:selectItem itemValue="7" itemLabel="07:00 AM"/>
             	<f:selectItem itemValue="8" itemLabel="08:00 AM"/>
             	<f:selectItem itemValue="9" itemLabel="09:00 AM"/>
             	<f:selectItem itemValue="10" itemLabel="10:00 AM"/>
             	<f:selectItem itemValue="11" itemLabel="11:00 AM"/>
             	<f:selectItem itemValue="12" itemLabel="12:00 PM"/>
             	<f:selectItem itemValue="13" itemLabel="13:00 PM"/>
             	<f:selectItem itemValue="14" itemLabel="14:00 PM"/>
             	<f:selectItem itemValue="15" itemLabel="15:00 PM"/>
             	<f:selectItem itemValue="16" itemLabel="16:00 PM"/>             	
             </h:selectOneMenu>
			
			<h:inputHidden id="txtId" value="#{ConsultaMB.consulta.id}" />
			<h:commandButton id="btnAgendar" style="padding: 3px 10px;"	value="Agendar" action="#{ConsultaMB.agendar}"/>
		</h:panelGrid>
		
		<br/>
                <h:dataTable id="tableDiaria" value="#{ConsultaMB.listaDiarias}" var="item" width="100%" style="text-align:center" styleClass="jsflist" >
                    <f:facet name="header">
                        <h:outputText value="Disponibilidade de vagas"/>
                    </f:facet>                    
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Médico"/>
                        </f:facet>
                        <h:outputText value="#{item.idAgenda.medico.nome}"/>
                    </h:column>                    
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Data"/>
                        </f:facet>
                        <h:outputText value="#{item.data}">
                        	<f:convertDateTime pattern="dd/MM/yyyy"/>
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Turno"/>
                        </f:facet>
                        <h:outputText value="#{item.idAgenda.turno}">
                        	<f:converter converterId="converterturno"/>
                        </h:outputText>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Vagas"/>
                        </f:facet>
                        <h:outputText value="#{item.idAgenda.vagas}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Disponível"/>
                        </f:facet>
                        <h:outputText value="#{item.disponivel}"/>
                    </h:column>                                 
                    
                </h:dataTable>
                </h:form>
            </center>
<br/>
            <div class="post-info">

                <strong><a href="home.jsf"><< Voltar</a></strong>

            </div>

        </div>

    </div>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>



</f:view>
