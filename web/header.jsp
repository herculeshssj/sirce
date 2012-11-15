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


<!-- Código do menu flutuante --> <f:subview id="cabecalho">
	<h:form>
		<div class="menu">

		<ul>
			<li><a href="#">Cadastro</a>
			<ul>
				<li><a href="listAgendaMedica.jsf">Agenda Médica</a></li>
				<li><a href="listEspecialidade.jsf">Especialidades</a></li>
				<li><a href="listMedico.jsf">Médicos</a></li>				
				<li><a href="listPaciente.jsf">Pacientes</a></li>
				<li><a href="listProntRegistro.jsf">Registro de Prontuário</a></li>
				<li><a href="listTipoExame.jsf">Tipos de Exame</a></li>
				<li><a href="listUnidade.jsf">Unidades</a></li>
				<li><a href="listLogin.jsf">Usuários</a></li>				
			</ul>
			</li>
			<li><a href="#">Agendamento</a>
			<ul>
				<li><a href="formConsulta.jsf">Agendamento de consulta</a></li>
				<li><a href="formExame.jsf">Agendamento de exame</a></li>
			</ul>
			</li>
			<li><a href="#">Visualizar</a>
			<ul>				
				<li><a href="listEspecConsultas.jsf">Consultas por especialidade</a></li>
				<li><a href="listMedConsultas.jsf">Consultas por médico</a></li>
				<li><a href="listExame.jsf">Exames agendados</a></li>
				<li><a href="listProntVisualiza.jsf">Prontuário de pacientes</a></li>
			</ul>
			</li>
			<li><h:commandLink action="#{LoginMB.efetuarLogoff}"
				value="Sair" /></li>
		</ul>
		<div align="right"><strong>Usuário: </strong><h:outputText value="#{LoginMB.usuarioLogado.nomeUsuario}"/>
		</div>
		</div>
	</h:form>
</f:subview> <!-- Fim do Código do menu flutuante -->

<div id="wrapper">