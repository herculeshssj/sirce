SIRCE
=====

Sistema de Regularização de Consultas e Exames
----------------------------------------------

Sistema para marcação de consultas e exames. Permite redistribuir as consultas marcadas entre as unidades médicas a fim de evitar falta de vagas. Mantém registro das consultas e exames marcados e dos diagnósticos feitos pelos médicos.

Página do projeto: http://hslife.com.br/projects/sirce/

Requisitos:

* Java JDK SE 6 ou superior;
* Eclipse Helios 3.6 ou superior;
* Tomcat 6.0.25 ou superior;
* MySQL 5.0 ou superior;
* astah Community 6 ou superior;
* DBDesigner 4;
* OpenProj 1.4 ou superior;
* Windows ou Linux, qualquer versão capaz de rodar os softwares acima.

Links de download:

* *Java JDK SE 6*: http://www.oracle.com/technetwork/java/javase/downloads/index.html
* *Eclipse Helios*: http://www.eclipse.org/downloads
* *Tomcat 7*: http://tomcat.apache.org/download-70.cgi
* *MySQL 5.5*: http://dev.mysql.com/downloads/mysql/5.6.html
* *DBDesigner*: http://www.fabforce.net/dbdesigner4/downloads.php
* *OpenProj*: http://sourceforge.net/projects/openproj/?source=dlp
* *astah Community*: http://astah.net/editions/community

Configuração do MySQL:

Após instalar o MySQL, acesse via console ou usando uma ferramenta gráfica de administração e execute os seguintes comandos:

```sql
-- Criação da base de dados
create database sirce;

-- Criação do usuário para acessar a base
create user 'sirce'@'localhost' identified by 's1RC3';
grant all privileges on sirce.* to 'sirce'@'localhost' with grant option;
```
