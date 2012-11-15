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

            <center>
                <h:form id="frmMedico">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">

                        Nome do médico:
                        <h:inputText value="#{MedicoMB.medico.nome}" required="true" requiredMessage="Informe o nome do médico!" size="50" maxlength="100"/>

                        CRM:
                        <h:inputText value="#{MedicoMB.medico.crm}" required="true" requiredMessage="Informe o CRM do médico!"/>

                        Especialidade:
                        <h:selectOneMenu id="txtEspecialidade" value="#{MedicoMB.idEspecialidade}" required="true" requiredMessage="É necessário escolher uma especialidade!">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItems value="#{MedicoMB.listaEspecialidades}"/>
                        </h:selectOneMenu>

                       Unidade:
                       <h:selectOneMenu id="txtUnidade" value="#{MedicoMB.idUnidade}" required="true" requiredMessage="É necessário escolher uma unidade!">
                            <f:selectItem itemLabel="Selecione"/>
                            <f:selectItems value="#{MedicoMB.listaUnidades}"/>
                        </h:selectOneMenu>

                       <h:inputHidden value="#{MedicoMB.medico.id}"/>
                       <h:inputHidden/>

                       <h:commandButton value="Salvar dados" style="padding: 3px 10px;" action="#{MedicoMB.salvar}" />
                    </h:panelGrid>

                </h:form>
            </center>
<br/>
            <div class="post-info">

                <strong><a href="listMedico.jsf"><< Voltar</a></strong>

            </div>

        </div>
    </div>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>
