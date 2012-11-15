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

	<h1 class="post-title"><a href="#" rel="bookmark">Agendamento de Exame</a></h1>


	<center><h:form id="frmExame">
		<h:messages style="font-weight: bold;" />
		<h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">
             
             Paciente:
             <h:selectOneMenu id="txtPaciente" value="#{ExameMB.idPaciente}" required="true" requiredMessage="Informe o paciente!">
             	<f:selectItem itemLabel="Selecione"/>
             	<f:selectItems value="#{ExameMB.listaPacientes}"/>
             </h:selectOneMenu>
             
             Tipo de Exame:
             <h:selectOneMenu id="txtTipoExame" value="#{ExameMB.idTipo}" required="true" requiredMessage="Informe o tipo de exame!">
             	<f:selectItem itemLabel="Selecione"/>
             	<f:selectItems value="#{ExameMB.listaTipos}"/>
             </h:selectOneMenu>
             
             Data do exame:
             <rich:calendar id="txtDataExame" datePattern="dd/MM/yyyy" value="#{ExameMB.exame.data}"  required="true" requiredMessage="Informe a data do exame!"/>
             
             Hora do exame:
             <h:selectOneMenu id="txtHoraExame" value="#{ExameMB.exame.hora}" required="true" requiredMessage="Informe a hora do exame!">
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
			
			<h:inputHidden id="txtId" value="#{ExameMB.exame.id}" />
			<h:commandButton id="btnAgendar" style="padding: 3px 10px;"	value="Agendar" action="#{ExameMB.agendar}" />
		</h:panelGrid>
		<br/>
                <h:dataTable id="tableExame" value="#{ExameMB.listaExames}" var="item" width="100%" style="text-align:center" styleClass="jsflist" >
                    <f:facet name="header">
                        <h:outputText value="Exames marcados"/>
                    </f:facet>                    
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Tipo de exame"/>
                        </f:facet>
                        <h:outputText value="#{item.tipo.descricao}"/>
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
                            <h:outputText value="Hora"/>
                        </f:facet>
                        <h:outputText value="#{item.hora}"/>
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
