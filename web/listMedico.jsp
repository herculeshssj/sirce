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

            <h1 class="post-title"><a href="#" rel="bookmark">Médicos da Unidade</a></h1>
            <h:form id="lstMedico">
            <strong><h:commandLink value="Novo médico" action="#{MedicoMB.cadastrar}"></h:commandLink> </strong>
            <br/><br/>
            
               <h:messages style="font-weight: bold;"/><br/>
                Informe o CRM:
                <h:inputText value="#{MedicoMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{MedicoMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>
                <h:dataTable id="tableMedico" value="#{MedicoMB.listaMedicos}" var="item" width="100%" style="text-align:center" styleClass="jsflist" >
                    <f:facet name="header">
                        <h:outputText value="Médicos cadastrados"/>
                    </f:facet>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="CRM"/>
                        </f:facet>
                        <h:outputText value="#{item.crm}"/>
                    </h:column>
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome do médico"/>
                        </f:facet>
                        <h:outputText value="#{item.nome}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Especialidade"/>
                        </f:facet>
                        <h:outputText value="#{item.especialidade.descricao}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Unidade"/>
                        </f:facet>
                        <h:outputText value="#{item.unidade.nomeUnidade}"/>
                    </h:column>                                    
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Ação"/>
                        </f:facet>
                        <h:commandLink value="Ver / Editar" action="#{MedicoMB.editar}">
                            <f:setPropertyActionListener target="#{MedicoMB.idMedico}" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                    
                </h:dataTable>
            </h:form>
            <br/>       
            <p/>
            <div class="post-info">

                <strong><a href="home.jsf"><< Voltar</a></strong>

            </div>
        </div>
    </div>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>