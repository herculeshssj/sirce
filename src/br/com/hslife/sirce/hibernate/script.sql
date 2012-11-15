/*
  
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
  
*/

/* Criação das tabelas */

alter table AgendaDiaria drop foreign key FKBB1365A244DC4FB;
alter table AgendaMedica drop foreign key FK1AD5B087255FECDA;
alter table Consulta drop foreign key FKE202E615244DC4FB;
alter table Consulta drop foreign key FKE202E615255FECDA;
alter table Consulta drop foreign key FKE202E61511C5872A;
alter table Exame drop foreign key FK4045C0611C5872A;
alter table Exame drop foreign key FK4045C06B9436B7C;
alter table Medico drop foreign key FK89237969E3B0679E;
alter table Medico drop foreign key FK89237969F440EC14;
alter table Paciente drop foreign key FK3081779186D22FDA;
alter table Prontuario drop foreign key FKEF8D69D9255FECDA;
alter table Prontuario drop foreign key FKEF8D69D911C5872A;
alter table Unidade drop foreign key FK521D528E86D22FDA;
drop table if exists AgendaDiaria;
drop table if exists AgendaMedica;
drop table if exists Consulta;
drop table if exists Endereco;
drop table if exists Especialidade;
drop table if exists Exame;
drop table if exists Login;
drop table if exists Medico;
drop table if exists Paciente;
drop table if exists Prontuario;
drop table if exists TipoExame;
drop table if exists Unidade;
create table AgendaDiaria (id integer not null auto_increment, data date, disponivel integer, idAgenda integer, primary key (id));
create table AgendaMedica (id integer not null auto_increment, diaSemana integer not null, turno integer not null, vagas integer not null, idMedico integer, primary key (id));
create table Consulta (id integer not null auto_increment, compareceu bit, dataConsulta date, turno integer not null, idAgenda integer, idMedico integer, idPaciente integer, primary key (id));
create table Endereco (id integer not null auto_increment, bairro varchar(50) not null, cep integer not null, cidade varchar(50) not null, complemento varchar(50), ddd integer not null, numero varchar(10), rua varchar(100) not null, telefone integer not null, uf varchar(2) not null, primary key (id));
create table Especialidade (id integer not null auto_increment, descricao varchar(100) not null, primary key (id));
create table Exame (id integer not null auto_increment, compareceu bit, data date not null, hora integer not null, idPaciente integer, idTipoExame integer, primary key (id));
create table Login (id integer not null auto_increment, ativo bit not null, dataCriacao datetime, nomeUsuario varchar(100) not null, perfil varchar(5) not null, usuarioLogin varchar(50) not null unique, usuarioSenha varchar(40) not null, primary key (id));
create table Medico (id integer not null auto_increment, crm varchar(15) not null, nome varchar(100) not null, idEspecialidade integer, idUnidade integer, primary key (id));
create table Paciente (id integer not null auto_increment, anoNasc integer not null, cpf varchar(11), diaNasc integer not null, idade integer not null, mesNasc integer not null, nacionalidade varchar(50), naturalidade varchar(50), nome varchar(100) not null, nomeMae varchar(100), nomePai varchar(100), rg varchar(15), sexo varchar(1) not null, idEndereco integer, primary key (id));
create table Prontuario (id integer not null auto_increment, dataInclusao datetime, diagnostico text not null, idMedico integer not null, idPaciente integer not null, primary key (id));
create table TipoExame (id integer not null auto_increment, descricao varchar(100) not null, primary key (id));
create table Unidade (id integer not null auto_increment, nomeUnidade varchar(100) not null, idEndereco integer, primary key (id));
alter table AgendaDiaria add index FKBB1365A244DC4FB (idAgenda), add constraint FKBB1365A244DC4FB foreign key (idAgenda) references AgendaMedica (id);
alter table AgendaMedica add index FK1AD5B087255FECDA (idMedico), add constraint FK1AD5B087255FECDA foreign key (idMedico) references Medico (id);
alter table Consulta add index FKE202E615244DC4FB (idAgenda), add constraint FKE202E615244DC4FB foreign key (idAgenda) references AgendaMedica (id);
alter table Consulta add index FKE202E615255FECDA (idMedico), add constraint FKE202E615255FECDA foreign key (idMedico) references Medico (id);
alter table Consulta add index FKE202E61511C5872A (idPaciente), add constraint FKE202E61511C5872A foreign key (idPaciente) references Paciente (id);
alter table Exame add index FK4045C0611C5872A (idPaciente), add constraint FK4045C0611C5872A foreign key (idPaciente) references Paciente (id);
alter table Exame add index FK4045C06B9436B7C (idTipoExame), add constraint FK4045C06B9436B7C foreign key (idTipoExame) references TipoExame (id);
alter table Medico add index FK89237969E3B0679E (idEspecialidade), add constraint FK89237969E3B0679E foreign key (idEspecialidade) references Especialidade (id);
alter table Medico add index FK89237969F440EC14 (idUnidade), add constraint FK89237969F440EC14 foreign key (idUnidade) references Unidade (id);
alter table Paciente add index FK3081779186D22FDA (idEndereco), add constraint FK3081779186D22FDA foreign key (idEndereco) references Endereco (id);
alter table Prontuario add index FKEF8D69D9255FECDA (idMedico), add constraint FKEF8D69D9255FECDA foreign key (idMedico) references Medico (id);
alter table Prontuario add index FKEF8D69D911C5872A (idPaciente), add constraint FKEF8D69D911C5872A foreign key (idPaciente) references Paciente (id);
alter table Unidade add index FK521D528E86D22FDA (idEndereco), add constraint FK521D528E86D22FDA foreign key (idEndereco) references Endereco (id);

insert into Login(id, ativo, datacriacao, nomeusuario, perfil, usuariologin, usuariosenha) values (1, true, '2011-01-01', 'Administrador do Sistema', 'ADMIN', 'admin', 'f865b53623b121fd34ee5426c792e5c33af8c227');

/* Fim do script */

--select * from especialidade;
--select * from unidade
--select * from endereco
--select * from medico
--select * from tipoexame
--select * from paciente

--insert into especialidade(descricao) values ('Dermatologia');
--insert into especialidade(descricao) values ('Clínico Geral');
--insert into especialidade(descricao) values ('Cardiologia');


--insert into tipoexame(descricao) values ('Cancer de pele')
--insert into tipoexame(descricao) values ('Eletrocardiograma')
--insert into tipoexame(descricao) values ('Hemograma completo')

--insert into medico(crm, nome, idEspecialidade, idUnidade) values ('12345', 'Carlos',1,1)
--insert into medico(crm, nome, idEspecialidade, idUnidade) values ('98765', 'Daniele',3,1)

-- Para realizar a inserção de unidade e paciente, rode as classes de teste

/*
create table agendaexame(
    id int not null auto_increment,
    idPaciente int,
    idUnidade int,
    data int,
    hora int,
    idTipoExame int,
    primary key(id)
)
*/

--select * from agendaexame

--insert into agendaexame (idPaciente, idUnidade, data, hora, idTipoExame) values(1,1,15,9,1)
--insert into agendaexame (idPaciente, idUnidade, data, hora, idTipoExame) values(1,1,15,10,1)
--insert into agendaexame (idPaciente, idUnidade, data, hora, idTipoExame) values(1,1,15,13,1)
--insert into agendaexame (idPaciente, idUnidade, data, hora, idTipoExame) values(1,1,17,13,1)
--insert into agendaexame (idPaciente, idUnidade, data, hora, idTipoExame) values(1,1,17,15,1)
--insert into agendaexame (idPaciente, idUnidade, data, hora, idTipoExame) values(1,1,16,7,1)

-- Procura os exames marcados para o tipo de exame para determinado dia
--select * from agendaexame where data=15 and idTipoExame=3


-- Listar todos os exames já marcados pelo paciente para determinado dia
--select * from agendaexame where idPaciente=1

/* Sequencia para inclusão de nova marcação de exame */
-- 1º: procurar exames marcados para o tipo de exame no dia solicitado
--select * from agendaexame where data=15 and idTipoExame=1

-- 2º: ao cadastrar verificar se aquele paciente já tem outro exame marcado para o dia e horário escolhido
--select * from agendaexame where idPaciente=1 and data=16 and hora=9
-- Se tiver, apresentar uma mensagem de erro, caso contrário, realizar a inclusão

-- 3º: cadastrar o agendamento para o dia, horário e o tipo de exame
--insert into agendaexame (idPaciente, idUnidade, data, hora, idTipoExame) values(1,1,16,9,1)
/* Fim da sequencia */


--select * from medico
--select * from agendaexame


--alter table agendaexame add column compareceu int
--update agendaexame set compareceu = 0

/*
create table agendamedica(
    id int not null auto_increment,
    diasemana varchar(3),
    turno varchar(8),
    vagas int,
    idMedico int,
    primary key(id)
)
*/
--select * from agendamedica

--insert into agendamedica (diasemana, turno, vagas, idMedico) values ('ter', 'integral', '10', 1)
--insert into agendamedica (diasemana, turno, vagas, idMedico) values ('qua', 'tarde', '3', 1)
--insert into agendamedica (diasemana, turno, vagas, idMedico) values ('seg', 'manha', '10', 2);
--insert into agendamedica (diasemana, turno, vagas, idMedico) values ('ter', 'manha', '10', 2);
--insert into agendamedica (diasemana, turno, vagas, idMedico) values ('qua', 'manha', '10', 2);
--insert into agendamedica (diasemana, turno, vagas, idMedico) values ('qui', 'manha', '10', 2);
--insert into agendamedica (diasemana, turno, vagas, idMedico) values ('sex', 'manha', '10', 2);

-- Para montar a agenda médica é necessário lançar todos os dias da semana que o médico estará disponível,
-- o turno e a quantidade de vagas que ele poderá atender

--select * from medico
-- Jorge Armando: 12
-- Denise Fraga: 13
-- Ulisses Lima: 14

--select * from paciente
-- Hércules: 32
-- Claudia: 34
-- Richard: 37

--select * from agendamedica where idmedico=12
-- Jorge Armando: Seg, integral, 10 vagas; Qua, integral, 10 vagas, Sex, manhã, 5 vagas
-- Denise Fraga: Ter, manhã, 6 vagas; Quinta, tarde, 6 vagas; Sábado, manhã, 3 vagas
-- Ulisses Lima: Segunda a sexta, integral, 10 vagas por dia; Sábado, manhã, 5 vagas

-- Horários: 07:00 às 11:00 - manhã
--	     12:00 às 16:00 - tarde
--	     07:00 às 16:00 - integral

--select * from consulta
-- Consulta: idmedico, idpaciente, dataconsulta, turno, compareceu

-- Consulta: obter todas as consultas marcadas para o dia informado e médico informado e que o paciente ainda não compareceu
--select * from consulta where idmedico =12 and dataconsulta='2010-12-15' and compareceu = false

-- Verifico se o médico atende para o dia da semana informado

--SELECT EXTRACT(DOW FROM DATE '2010-12-4');

-- Consulta: verifico se o médico informado atende no dia da semana escolhido
--select * from agendamedica where idmedico=12 and diasemana = (select extract(dow from date '2010-12-15'));
--select * from agendamedica where idmedico=14 and diasemana=4


--select * from exame

--select * from tipoexame
--select * from exame where idtipoexame=5 and data='2010-12-11' and hora=8 and compareceu=false
/*

select * from consulta

--select * from agendamedica

-- idMedico = 5
-- Segunda, manhã, 3 vagas
-- Dia 1, turno 2, 3 vagas

select count(*) as "vagasocupadas" from consulta where  idmedico=5 and turno in (select distinct turno from consulta where turno >= 12) and dataconsulta='2010-12-06'



select distinct turno from consulta

select c.* from consulta c

select a.id, a.idmedico, a.diasemana, a.turno, a.vagas as "VAGASDISPONIVEIS", 
(select count(*) from consulta where idmedico=5 and turno in (select distinct turno from consulta where turno >= 12) and dataconsulta='2010-12-06') as "VAGASOCUPADAS"
from agendamedica a where idmedico =5



select * from agendadiaria

-- Campos: id, idagenda, data, vagasocupadas


-- Listagem dos dados
-- id, idmedico, turno, vagas, dia, disponivel

select * from agendadiaria

insert into agendadiaria(id, idmedico, turno, vagas, dia, disponivel) values (1, 1, 'manhã', '5', '5')

update agendadiaria set disponivel = 4 where idagendadiaria


-- id, idagenda, data, disponível

insert into agendadiaria(id, idagenda, data, disponivel) values (1, 1, '2010-12-06', 5)

update agendadiaria set disponivel = 4 where idagenda=1

update agendadiaria set disponivel = 4 where id=1

-- List: Id, agenda.idmedico.nome, agenda.turno, agenda.vagas, disponivel


select * from consulta where idmedico=5 and idpaciente=7 and dataconsulta='2010-12-06' and compareceu = false and turno = 9

select * from agendamedica

select * from medico

select d.* from agendadiaria d, agendamedica a, medico m where data='2010-12-11' and d.idagenda = a.id and m.id = 25

select * from agendadiaria;

select * from agendamedica;

select * from consulta;

select * from agendadiaria where idagenda= 9 and data='2010-12-08'

select c.* from consulta c, medico m where m.id = c.idmedico and m.idespecialidade = 6 and c.compareceu = false

select * from especialidade

select * from exame

select * from paciente

select e.* from exame e, paciente p where e.idpaciente=p.id and compareceu=false and p.nome like '%H%'

select e.* from medico m, especialidade e where idunidade = 1 and m.idespecialidade = e.id
select * from medico m where idespecialidade = 1

select * from medico

select * from especialidade

select * from unidade

-- Médicos da Unidade HGNI
select * from medico where idunidade=2
-- Médicos da Unidade HGNI: Jorge, Denise e Carlos

-- Médicos da Unidade 1
select * from medico where idunidade = 51
-- Médico 1 U1 e Médico 2 U1

-- Médicos da Unidade 2
select * from medico where idunidade = 55
-- Médico 1 U2 e Médico 2 U2

-- Médicos da especialidade Cardiologia
select * from medico where idespecialidade = 6
-- Médicos: Jorge Armando

-- Médicos da especiadade Dermatologia
select * from medico where idespecialidade=38
-- Médicos: Denise e Carlos

-- Médicos da especialidade Especialidade 1
select * from medico where idespecialidade=50
-- Médicos: Médico 1 U1 e Médico 1 U2

-- Médicos da especialidade Especialidade 2
select * from medico where idespecialidade=57
-- Médicos: Médico 2 U1 e Médico 2 U2

-- Médicos da especialidade Especialidade 3
select * from medico where idespecialidade=96
-- Médicos: Médico 2 U1 e Médico 2 U2

-- Médicos da especialidade Especialidade 4
select * from medico where idespecialidade=97
-- Médicos: Médico 2 U1 e Médico 2 U2

-- Agora vamos lá!!!!!

-- Especialidades disponíveis na unidade HGNI
select distinct e.* from medico m, especialidade e where idunidade = 2 and m.idespecialidade = e.id
-- Especialidades disponíveis na unidae HGNI: Cardiologia e Dermatologia

-- Especialidades disponíveis na unidade Unidade 1
select distinct e.* from medico m, especialidade e where idunidade = 51 and m.idespecialidade = e.id
-- Especialidades disponíveis na unidade Unidade 1: Especialidae 1 e Especialidade 2

-- Especialidades disponíveis na unidade Unidade 2
select distinct e.* from medico m, especialidade e where idunidade = 55 and m.idespecialidade = e.id
-- Especialidades disponíveis na unidade Unidade 2: Especialidade 3 e Especialidade 4
*/