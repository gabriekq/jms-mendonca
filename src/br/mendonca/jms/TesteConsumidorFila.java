package br.mendonca.jms;

import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteConsumidorFila {

	public static void main(String[] args) throws NamingException,Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
        connection.start(); 
        Session session = connection.createSession(true,Session.SESSION_TRANSACTED);
         
       Destination fila = (Destination) context.lookup("financeiro"); 
       MessageConsumer consumer = session.createConsumer(fila);
         
   
      
      consumer.setMessageListener(new MessageListener() {
		
		@Override
		public void onMessage(Message message) {
			
		TextMessage textMessage = (TextMessage)message;	
		try {
		//	message.acknowledge();
			System.out.println("Recebendo : "+textMessage.getText());
			session.commit();
		} catch (JMSException e) {	
			e.printStackTrace();
			
		}
		
			
		}
	});
       
       
       
       
       
      // session.close();
	  // connection.close();
	  // context.close();
		
		
	}

}
