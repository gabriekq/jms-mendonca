package br.mendonca.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteProdutorTopico {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
        connection.start(); 
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
         
        //loja
       Destination topico = (Destination) context.lookup("loja"); 
     
       MessageProducer  producer = session.createProducer(topico);
      
       
      
       for(int index =0 ; index < 1;index++){
    	   Message message = session.createTextMessage("<pedido><id>Teste "+index+"</id><ebook>true</ebook></pedido>");
    	 //  message.setBooleanProperty("ebook", true);
    	   producer.send(message); 
       }
       
      
		
       session.close();
 	   connection.close();
 	  context.close();
		
		

	}

}
