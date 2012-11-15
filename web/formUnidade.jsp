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

            <center>

                <h:form id="frmUnidade">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="2" cellspacing="10" styleClass="jsfform">

                        Unidade:
                        <h:inputText value="#{UnidadeMB.unidade.nomeUnidade}" required="true" requiredMessage="Informe o nome da unidade!" maxlength="100" size="50"/>

                        Endereco:
                        <h:inputText value="#{UnidadeMB.endereco.rua}" required="true" requiredMessage="Informe o endereço da unidade!" size="30" maxlength="100"/>

                        Número:
                        <h:inputText value="#{UnidadeMB.endereco.numero}" size="5" maxlength="5" />

                        Complemento:
                        <h:inputText value="#{UnidadeMB.endereco.complemento}" maxlength="50"/>

                        Bairro:
                        <h:inputText value="#{UnidadeMB.endereco.bairro}" required="true" requiredMessage="Informe o bairro da unidade!" maxlength="50"/>

                        Cidade:
                        <h:inputText value="#{UnidadeMB.endereco.cidade}" required="true" requiredMessage="Informe a cidade da unidade!" maxlength="50"/>

                        Estado:
                        <h:selectOneMenu value="#{UnidadeMB.endereco.uf}">
                            <f:selectItems value="#{UnidadeMB.estados}"/>
                        </h:selectOneMenu>

                        CEP (sem hífen):
                        <h:inputText value="#{UnidadeMB.endereco.cep}" required="true" requiredMessage="Informe o CEP da unidade (sem hífen)!" size="8" maxlength="8" converterMessage="Informe somente valores numéricos para o CEP!">
                            <f:validateLength minimum="8"/>
                        </h:inputText>

                        Telefone:
                        <h:panelGroup>
                            <h:inputText value="#{UnidadeMB.endereco.ddd}" required="true" requiredMessage="Informe o DDD!" size="2" maxlength="2" converterMessage="Informe somente valores numéricos para o DDD!" validatorMessage="DDD informado é inválido!">
                                <f:validateLength minimum="2"/>                                
                            </h:inputText>-<h:inputText value="#{UnidadeMB.endereco.telefone}" required="true" requiredMessage="Informe o telefone!" size="8" maxlength="8" converterMessage="Informe somente valores numéricos para o telefone!"/>
                        </h:panelGroup>

                        <h:inputHidden value="#{UnidadeMB.idEndereco}"/>
                        <h:inputHidden value="#{UnidadeMB.unidade.id}"/>


                        <h:commandButton value="Salvar dados" style="padding: 3px 10px;" action="#{UnidadeMB.salvar}" />
                    </h:panelGrid>

                </h:form>

            </center> 
<br/>
            <div class="post-info">

                <strong><a href="listUnidade.jsf"><< Voltar</a></strong>

            </div>

        </div>
    </div>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>
