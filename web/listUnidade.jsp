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

            <h1 class="post-title"><a href="#" rel="bookmark">Unidades Médicas</a></h1>
                        <h:form id="lstUnidade">
            <strong><h:commandLink value="Nova unidade" action="#{UnidadeMB.cadastrar}"></h:commandLink> </strong>
            <br/><br/>

                <h:messages style="font-weight: bold;"/><br/>
                Informe a unidade:
                <h:inputText value="#{UnidadeMB.busca}"/>
                &nbsp;
                <h:commandButton value="Pesquisar" action="#{UnidadeMB.pesquisar}"  style="padding: 3px 10px;"/>
                <br/><br/>
                <h:dataTable id="tableUnidade" value="#{UnidadeMB.listaUnidades}" var="item" width="100%" style="text-align:center" styleClass="jsflist">
                    <f:facet name="header">
                        <h:outputText value="Unidades cadastradas"/>
                    </f:facet>                    
                     <h:column>
                        <f:facet name="header">
                            <h:outputText value="Nome da unidade"/>
                        </f:facet>
                        <h:outputText value="#{item.nomeUnidade}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Bairro"/>
                        </f:facet>
                        <h:outputText value="#{item.endereco.bairro}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Cidade"/>
                        </f:facet>
                        <h:outputText value="#{item.endereco.cidade}"/>
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Telefone"/>
                        </f:facet>
                        (<h:outputText value="#{item.endereco.ddd}"/>)&nbsp;
                        <h:outputText value="#{item.endereco.telefone}"/>
                    </h:column>                   
                    <h:column>
                        <f:facet name="header">
                            <h:outputText value="Ação"/>
                        </f:facet>
                        <h:commandLink value="Ver / Editar" action="#{UnidadeMB.editar}">
                            <f:setPropertyActionListener target="#{UnidadeMB.idUnidade}" value="#{item.id}"/>
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