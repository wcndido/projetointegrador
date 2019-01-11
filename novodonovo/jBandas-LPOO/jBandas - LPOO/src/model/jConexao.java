/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jConexao {
    
    /* 
        Dados de conexão com o servidor de Banco de Dados,
        que pode ser local, ou, remoto. Caso seja remoto
        basta informar o IP do servidor ao invés de localhost.
    */    
    
    public String servidor = "localhost";
    public String usuario = "root";
    public String senha = "";
    public String banco = "bd_bandas";
    
    /* 
        A seguir temos o método ManipularDados(), este método permite executar operações de 
        INSERT, UPDATE e DELETE.
    
        Para que o método saiba qual comando SQL ele deve executar, foi criado um parâmetro
        
        Um parâmetro é uma variável, note que no método ManipluarDados() existe uma variável
        criada dentro dos paranteses! Achou?
        
        ManipularDados(String sql)
    
        E sabe o que isso significa? Significa que quando acionarmos o método ManipularDados(), 
        OBRIGATORIAMENTE teremos que também passar para ele o valor da variável sql, senão ele
        não irá funcionar
    
        Exemplo;
            ManipularDados("INSERT INTO.... bla bla");
    
        ou, se houver um objeto, teriámos;
            NomeDoObjeto.ManipularDados("INSERT INTO.... bla bla");
    
        Perceba então que a variável sql recebe o comando SQL que deverá ser executado pelo método.
        
        OBS: Este método não trata o comando SELECT.
    */    
    
    
    public void ManipularDados(String sql)
    {
    try
      {   
       Connection con =  (Connection) DriverManager.getConnection("jdbc:mysql://" + servidor +  "/" + banco + "?user=" + usuario + "&password=" + senha);
       
       PreparedStatement grava = (PreparedStatement) con.prepareStatement(sql); 
 
        // executa e encerra o comando
        grava.execute();        
        grava.close(); 
 
        // testa no console para verificar algum erro!
        System.out.println("Gravado!");
 
        //encerra a conexão
        con.close();
      } 
        catch (SQLException ex)
      {
          System.out.println("Erro " + ex);
      }
   }   
    
}
