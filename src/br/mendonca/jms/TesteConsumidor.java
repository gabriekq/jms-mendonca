package br.mendonca.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteConsumidor {

	public static void main(String[] args) throws NamingException,Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
        connection.start(); 
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
         
       Destination fila = (Destination) context.lookup("financeiro"); 
       MessageConsumer consumer = session.createConsumer(fila);
         
      
       consumer.setMessageListener(new MessageListener() {
		
		@Override
		public void onMessage(Message message) {
			
		TextMessage textMessage = (TextMessage)message;	
		
		try {
			System.out.println("Recebendo : "+textMessage.getText());
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
