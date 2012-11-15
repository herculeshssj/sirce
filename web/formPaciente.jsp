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

            <h1 class="post-title"><a href="#" rel="bookmark">Pacientes</a></h1>

            <p>
            <center>
                <h:form id="frmPaciente">
                    <h:messages style="font-weight: bold;"/>
                    <h:panelGrid columns="4" cellspacing="10" styleClass="jsfform">

                        Nome do Paciente:
                        <h:inputText value="#{PacienteMB.paciente.nome}" required="true" requiredMessage="Informe o nome do paciente!" size="30" maxlength="100"/>

                        Sexo:
                        <h:selectOneMenu value="#{PacienteMB.paciente.sexo}">
                            <f:selectItem itemValue="M" itemLabel="Masculino"/>
                            <f:selectItem itemValue="F" itemLabel="Feminino"/>
                        </h:selectOneMenu>

                        Nacionalidade:
                        <h:inputText value="#{PacienteMB.paciente.nacionalidade}" maxlength="50"/>

                        Naturalidade:
                        <h:inputText value="#{PacienteMB.paciente.naturalidade}" maxlength="50"/>

                        Idade:
                        <h:inputText value="#{PacienteMB.paciente.idade}" required="true" requiredMessage="Informe a idade do paciente!" size="2" maxlength="2"/>

                        Data de nascimento:
                        <h:panelGroup>
                            <h:selectOneMenu value="#{PacienteMB.paciente.diaNasc}" style="width: 50px">
                                <f:selectItems value="#{PacienteMB.dias}"/>
                            </h:selectOneMenu>
                            &nbsp;/
                            <h:selectOneMenu value="#{PacienteMB.paciente.mesNasc}" style="width: 50px">
                                <f:selectItems value="#{PacienteMB.meses}"/>
                            </h:selectOneMenu>
                            &nbsp;/
                            <h:selectOneMenu value="#{PacienteMB.paciente.anoNasc}" style="width: 60px" required="true" requiredMessage="Informe o ano de nascimento!">
                                <f:selectItem itemLabel=""/>
                                <f:selectItems value="#{PacienteMB.anos}"/>
                            </h:selectOneMenu>
                        </h:panelGroup>

                        CPF (só dígitos):
                        <h:inputText value="#{PacienteMB.paciente.cpf}" size="11" maxlength="11" validatorMessage="CPF informado é inválido!">
                            <f:validator validatorId="validacpf"/>
                        </h:inputText>

                        RG (só dígitos):
                        <h:inputText value="#{PacienteMB.paciente.rg}" size="11" maxlength="15" converterMessage="Campo 'RG' só aceita números!">
                        	<f:converter converterId="converterrg"/>
                        </h:inputText>

                        Nome do pai:
                        <h:inputText value="#{PacienteMB.paciente.nomePai}" size="30"/>

                        Nome da mãe:
                        <h:inputText value="#{PacienteMB.paciente.nomeMae}" size="30"/>

                        Endereco:
                        <h:inputText value="#{PacienteMB.endereco.rua}" required="true" requiredMessage="Informe o endereço da unidade!" size="30" maxlength="100"/>

                        Número:
                        <h:inputText value="#{PacienteMB.endereco.numero}" size="5" maxlength="5" />

                        Complemento:
                        <h:inputText value="#{PacienteMB.endereco.complemento}" maxlength="50"/>

                        Bairro:
                        <h:inputText value="#{PacienteMB.endereco.bairro}" required="true" requiredMessage="Informe o bairro da unidade!" maxlength="50"/>

                        Cidade:
                        <h:inputText value="#{PacienteMB.endereco.cidade}" required="true" requiredMessage="Informe a cidade da unidade!" maxlength="50"/>

                        Estado:
                        <h:selectOneMenu value="#{PacienteMB.endereco.uf}">
                            <f:selectItems value="#{PacienteMB.estados}"/>
                        </h:selectOneMenu>

                        CEP (sem hífen):
                        <h:inputText value="#{PacienteMB.endereco.cep}" required="true" requiredMessage="Informe o CEP da unidade (sem hífen)!" size="8" maxlength="8" converterMessage="Informe somente valores numéricos para o CEP!">
                            <f:validateLength minimum="8"/>
                        </h:inputText>

                        Telefone:
                        <h:panelGroup>
                            <h:inputText value="#{PacienteMB.endereco.ddd}" required="true" requiredMessage="Informe o DDD!" size="2" maxlength="2" converterMessage="Informe somente valores numéricos para o DDD!" validatorMessage="DDD informado é inválido!">
                                <f:validateLength minimum="2"/>
                            </h:inputText>-
                            <h:inputText value="#{PacienteMB.endereco.telefone}" required="true" requiredMessage="Informe o telefone!" size="8" maxlength="8" converterMessage="Informe somente valores numéricos para o telefone!"/>
                        </h:panelGroup>

                        <h:inputHidden value="#{PacienteMB.idEndereco}"/>
                        <h:inputHidden value="#{PacienteMB.paciente.id}"/>

                        <h:commandButton value="Salvar dados" style="padding: 3px 10px;" action="#{PacienteMB.salvar}" />
                    </h:panelGrid>

                </h:form>
                </center>
            </p>
<br/>
            <div class="post-info">

                <strong><a href="listPaciente.jsf"><< Voltar</a></strong>

            </div>

        </div>
    </div>
    <f:subview id="rodape">
        <jsp:include page="footer.jsp"/>
    </f:subview>

</f:view>