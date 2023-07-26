package br.com.iago.simulacaoBanco.config.audit;

//import org.hibernate.boot.Metadata;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.envers.boot.internal.EnversService;
//import org.hibernate.envers.event.spi.EnversListenerDuplicationStrategy;
//import org.hibernate.envers.event.spi.EnversPostCollectionRecreateEventListenerImpl;
//import org.hibernate.envers.event.spi.EnversPostDeleteEventListenerImpl;
//import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
//import org.hibernate.envers.event.spi.EnversPreCollectionRemoveEventListenerImpl;
//import org.hibernate.envers.event.spi.EnversPreCollectionUpdateEventListenerImpl;
//import org.hibernate.event.service.spi.EventListenerRegistry;
//import org.hibernate.event.spi.EventType;
//import org.hibernate.integrator.spi.Integrator;
//import org.hibernate.service.spi.SessionFactoryServiceRegistry;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostCollectionRecreateEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPostDeleteEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPreCollectionRemoveEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPreCollectionUpdateEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPreUpdateEventListenerImpl;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomEnversConfig {

	private EntityManagerFactory entityManager;
	
	@PostConstruct
	public void registerEnversListeners() {
		
	    SessionFactoryImplementor sessionFactory = entityManager.unwrap(SessionFactoryImplementor.class);
	    ServiceRegistryImplementor serviceRegistry = sessionFactory.getServiceRegistry();
	    
	    final EnversService enversService = serviceRegistry.getService( EnversService.class );
	
	    EventListenerRegistry listenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
	    listenerRegistry.appendListeners(EventType.POST_UPDATE, new CustomPostUpdateEventListenerImpl(enversService));
		listenerRegistry.appendListeners(EventType.POST_INSERT,new EnversPostInsertEventListenerImpl( enversService ));
	}
}