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

package br.com.hslife.sirce.logic;

import java.util.Date;
import java.util.List;

import br.com.hslife.sirce.modelo.AgendaDiaria;
import br.com.hslife.sirce.modelo.AgendaMedica;
import br.com.hslife.sirce.modelo.Consulta;
import br.com.hslife.sirce.modelo.Exame;
import br.com.hslife.sirce.persistencia.GenericDao;

public class AgendaBusinessLogic extends GenericDao {

	/*
	 * Método agendarConsulta
	 * 
	 * Realiza a verificação de disponibilidade de marcar a consulta de acordo
	 * com a agenda do médico e das consultas já marcadas. Este método verifica
	 * se o médico atende no dia e horários indicados e verifica se ainda tem
	 * vaga para marcar de acordo com sua agenda. Retorna uma string informando
	 * o status final da operação. Herda diretamente de GenericDao.java para
	 * obter as informações necessárias direto do banco. Após verificar a
	 * disponibilidade ele salva a consulta marcada e retorna o status da
	 * operação
	 */
	public String agendarConsulta(Consulta consulta) {
		
		// Define a variável que armazenará as consultas SQL
		String sqlQuery = null;
		List<AgendaMedica> lista1 = null;
		List<Consulta> lista2 = null;
		
		// Salva os dados necessários em variáveis
		Integer idMedico = consulta.getMedico().getId();
		Date dataConsulta = consulta.getDataConsulta();
		Integer horario = consulta.getTurno();
		Integer turno=0; // Manhã = 2; Tarde = 3;
		AgendaMedica am = null;
		
		// Resgata o turno do horário agendado		
		if (horario == 7 || horario == 8 || horario == 9|| horario == 10 || horario == 11) {
			turno = 1;		
		} else {
			turno = 2;
		}
		
		// Verifica se o médico atende no dia escolhido
		sqlQuery = "select * from AgendaMedica where idmedico="+idMedico+" and diasemana="+dataConsulta.getDay();
		lista1 = (List)query(AgendaMedica.class, sqlQuery);
		if (lista1.size()>0) {
			// Médico atende no dia informado.
			// Verifica se o médico atende no horário escolhido
			boolean atende = false;
			int quantVagas = 0;
			boolean eIntegral = false;
			for (AgendaMedica a : lista1 ){
				if (a.getTurno() == 1 || a.getTurno() == turno + 1) {
					// Médico atende no horário escolhido
					atende = true;
					quantVagas = a.getVagas();
					eIntegral = (a.getTurno()==1);
					am = a;
				}
			}
			if (atende) {
				// Médico atende no horário escolhido
				// Verifica a disponibilidade de vagas para agendar
				if (eIntegral) {
					sqlQuery = "select * from Consulta where idmedico ="+idMedico+" and dataconsulta='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"' and compareceu = false";
				}else {
					switch(turno) {					
					case 1 : sqlQuery = "select * from Consulta where idmedico ="+idMedico+" and dataconsulta='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"' and compareceu = false and turno in (select distinct turno from consulta where turno < 12)";
							break;
					case 2 : sqlQuery = "select * from Consulta where idmedico ="+idMedico+" and dataconsulta='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"' and compareceu = false and turno in (select distinct turno from consulta where turno >=12)";
							break;
				}	
				}				
				
				lista2 = (List)query(Consulta.class, sqlQuery);
				if (lista2.size()< quantVagas) {
					// Quantidade de consultas marcadas é menor que quantidade de vagas disponível
					// Verifica se não estou marcando a consulta para o mesmo horário
					boolean jaMarcado = false;
					for (Consulta c : lista2){
						if (c.getTurno().equals(consulta.getTurno())) {
							jaMarcado = true;							
						} 
					}
					if (jaMarcado) {
						return "Horário já marcado! Escolha outro horário.";
					} else {
						// Marca a consulta
						salvarConsulta(consulta, am);
						if (errorMessage == null) {
							return "Consulta marcada com sucesso!";
						} else {
							return "Erro ao marcar: " + errorMessage;
						}
					}
				} else {
					return "Agenda do médico lotada! Escolha outro dia/horário";
				}
			} else {
				return "Médico não atende no horário escolhido!"; 
			}
		} else {
			return "Médico não atende no dia escolhido!";
		}		
	}


	/*
	 * Método agendarExame
	 * 
	 * Realiza a verificação de disponibilidade de marcar o exame de acordo com
	 * o horário informado. Impede que o atendente marque um exame para uma data
	 * e horário já marcados. Retorna uma string informando o status final da
	 * operação. Herda diretamente de GenericDao.java para obter as informações
	 * necessárias direto do banco. Após verificar a disponibilidade ele salva o
	 * exame marcado e retorna o status da operação
	 */
	public String agendarExame(Exame exame) {
		// Define a variável que armazenará as consultas SQL
		String sqlQuery = null;		
		List<Exame> lista = null;
		
		// Salva os dados necessários em variáveis
		Integer idTipoExame = exame.getTipo().getId();
		Date data = exame.getData();
		Integer hora = exame.getHora();
		
		// Verifica se o exame já foi marcado para o dia e horário escolhido
		sqlQuery = "select * from Exame where idtipoexame ="+idTipoExame+" and data='"+(data.getYear()+1900)+"-"+(data.getMonth()+1)+"-"+data.getDate()+"' and hora="+hora+" and compareceu = false";
		lista = (List)query(Exame.class, sqlQuery);
		if (lista.size()>0) {
			return "Dia e/ou horário não disponível! Escolha outro dia.";
		} else {
			// Salva o exame
			save(exame);
			if (errorMessage == null) {
				return "Exame marcado com sucesso!";
			} else {
				return "Erro ao marcar: " + errorMessage;
			}
		}
			
	}
	
	private void salvarConsulta(Consulta c, AgendaMedica a) {
		c.setAgenda(a);
		save(c);
		AgendaDiaria ad = new AgendaDiaria();
		Date dataConsulta = c.getDataConsulta();
		//String sqlQuery = "select * from agendadiaria where idagenda = " + a.getId() + " and data='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"'" ;
		String sqlQuery = "select * from AgendaDiaria where idagenda = " + a.getId() + " and data='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"'" ;
		ad = (AgendaDiaria)queryUnique(AgendaDiaria.class, sqlQuery);
		if (ad == null) {
			ad = new AgendaDiaria();
			ad.setData(dataConsulta);
			ad.setDisponivel(a.getVagas()-1);
			ad.setIdAgenda(a);
			save(ad);
		} else {
			// Update ad
			ad.setDisponivel(ad.getDisponivel()-1);
			update(ad);
		}
	}
	
	public String registrarConsulta(Consulta c) {
		// Instancia as variáveis
		AgendaMedica am = new AgendaMedica();
		AgendaDiaria ad = new AgendaDiaria();
		Date dataConsulta = c.getDataConsulta();
		String sqlQuery = "select * from AgendaDiaria where idagenda = " + c.getAgenda().getId() + " and data='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"'" ;
		
		// Resgata a agenda do médico através da consulta informada
		am = (AgendaMedica) find(AgendaMedica.class, c.getAgenda().getId());
		
		// Resgata a agenda diária para atualizar
		sqlQuery = "select * from AgendaDiaria where idagenda = " + c.getAgenda().getId() + " and data='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"'" ;
		ad = (AgendaDiaria)queryUnique(AgendaDiaria.class, sqlQuery);
		
		// Realiza as alterações
		if (ad == null) {
			System.out.println(errorMessage);
			return "Não foi possível registrar a consulta! Por favor, tente novamente!";			
		} else {
			// Update ad
			update(c);
			if (getErrorMessage() == null) {
				ad.setDisponivel(ad.getDisponivel()+1);
				update(ad);
				return "Consulta registrada com sucesso";				
			} else {
				return "Erro ao registrar: " + getErrorMessage();
			}		
			
		}		
		
	}
	
	public String desmarcarConsulta(Consulta c) {
		// Instancia as variáveis
		AgendaMedica am = new AgendaMedica();
		AgendaDiaria ad = new AgendaDiaria();
		Date dataConsulta = c.getDataConsulta();
		String sqlQuery = "select * from AgendaDiaria where idagenda = " + c.getAgenda().getId() + " and data='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"'" ;
		
		// Resgata a agenda do médico através da consulta informada
		am = (AgendaMedica) find(AgendaMedica.class, c.getAgenda().getId());
		
		// Resgata a agenda diária para atualizar
		sqlQuery = "select * from AgendaDiaria where idagenda = " + c.getAgenda().getId() + " and data='"+(dataConsulta.getYear()+1900)+"-"+(dataConsulta.getMonth()+1)+"-"+dataConsulta.getDate()+"'" ;
		ad = (AgendaDiaria)queryUnique(AgendaDiaria.class, sqlQuery);
		
		// Realiza as alterações
		if (ad == null) {
			System.out.println(errorMessage);
			return "Não foi possível desmarcar a consulta! Por favor, tente novamente!";			
		} else {
			// Update ad
			delete(c);
			if (getErrorMessage() == null) {
				ad.setDisponivel(ad.getDisponivel()+1);
				update(ad);
				return "Consulta desmarcada com sucesso";				
			} else {
				return "Erro ao desmarcar: " + getErrorMessage();
			}		
			
		}		
	}

}
