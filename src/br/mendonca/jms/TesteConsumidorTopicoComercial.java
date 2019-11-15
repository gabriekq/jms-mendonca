package br.mendonca.jms;

import java.io.Serializable;
import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.caelum.modelo.Pedido;

public class TesteConsumidorTopicoComercial {

	public static void main(String[] args) throws NamingException,Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection connection = factory.createConnection();
       connection.setClientID("comercial");
		
		connection.start(); 
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
         
       Topic topico = (Topic) context.lookup("loja"); 
       MessageConsumer consumer = session.createDurableSubscriber((Topic) topico,"assinatura");
         
   
      
      consumer.setMessageListener(new MessageListener() {
		
		@Override
		public void onMessage(Message message) {
			
		ObjectMessage   objectMessage = (ObjectMessage )message;	
		
		try {
			Pedido pedido = (Pedido) objectMessage.getObject();
			System.out.println("Recebendo : "+pedido.getCodigo());
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
