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

package br.com.hslife.sirce.hibernate;

import br.com.hslife.sirce.modelo.Login;
import br.com.hslife.sirce.util.Util;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/*
 * Classe GerarTabelas
 *
 * Classe que gera o esquema de banco de dados e cria as tabelas no
 * servidor MySQL.
 * O esquema gerado é mostrado no console
 *
 * @author Sergio Mendes - COTI Informática
 * @version 1.0
 *
 */
public class GerarTabelas {

    public static void geraTabelas() {
        Configuration cfg = new AnnotationConfiguration();
        cfg.configure("br/com/sirce/hibernate/hibernate.cfg.xml");

        SchemaExport se = new SchemaExport(cfg);
        se.create(true, true);
    }
    
    public static void main(String[] args) {
        GerarTabelas.geraTabelas();

        // Realiza o cadastro do usuário administrador
        Login l = new Login();
        l.setAtivo(true);
        l.setDataCriacao(new Date());
        l.setNomeUsuario("Administrador do Sistema");
        l.setPerfil("ADMIN");
        l.setUsuarioLogin("admin");
        l.setUsuarioSenha(Util.SHA1("admin123"));
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = sessao.beginTransaction();
        sessao.save(l);
        transacao.commit();
        sessao.close();
    }
}
