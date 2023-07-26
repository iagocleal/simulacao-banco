package br.com.iago.simulacaoBanco.config;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversListenerDuplicationStrategy;
import org.hibernate.envers.event.spi.EnversPostCollectionRecreateEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPostDeleteEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPreCollectionRemoveEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPreCollectionUpdateEventListenerImpl;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManagerFactory;
//
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.envers.boot.internal.EnversService;
//import org.hibernate.event.service.spi.EventListenerRegistry;
//import org.hibernate.event.spi.EventType;
//import org.hibernate.service.spi.ServiceRegistryImplementor;
//import org.springframework.stereotype.Component;
//
//import lombok.AllArgsConstructor;
//
//@Component
//@AllArgsConstructor
//public class EnversConfig {
//
//	private EntityManagerFactory entityManager;
//	
//	@PostConstruct
//	public void registerEnversListeners() {
//		
//	    SessionFactoryImplementor sessionFactory = entityManager.unwrap(SessionFactoryImplementor.class);
//	    ServiceRegistryImplementor serviceRegistry = sessionFactory.getServiceRegistry();
//	    
//	    final EnversService enversService = serviceRegistry.getService( EnversService.class );
//	
//	    EventListenerRegistry listenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
//	    listenerRegistry.appendListeners(EventType.PRE_UPDATE, new AuditPreUpdateEventListenerImpl(enversService));
//	    
//	    listenerRegistry.getEventListenerGroup(EventType.PRE_UPDATE);
//	}
//}

public class MyEnversIntegrator implements Integrator {

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {

		final EnversService enversService = serviceRegistry.getService( EnversService.class );

		final EventListenerRegistry listenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
		listenerRegistry.addDuplicationStrategy( EnversListenerDuplicationStrategy.INSTANCE );

		if ( enversService.getEntitiesConfigurations().hasAuditedEntities() ) {
			listenerRegistry.appendListeners(
					EventType.POST_DELETE,
					new EnversPostDeleteEventListenerImpl( enversService )
			);
			listenerRegistry.appendListeners(
					EventType.POST_INSERT,
					new EnversPostInsertEventListenerImpl( enversService )
			);
			listenerRegistry.appendListeners(
					EventType.PRE_UPDATE,
					new AuditPreUpdateEventListenerImpl( enversService )
			);
			listenerRegistry.appendListeners(
					EventType.POST_UPDATE,
					new AuditPostUpdateEventListenerImpl( enversService )
			);
			listenerRegistry.appendListeners(
					EventType.POST_COLLECTION_RECREATE,
					new EnversPostCollectionRecreateEventListenerImpl( enversService )
			);
			listenerRegistry.appendListeners(
					EventType.PRE_COLLECTION_REMOVE,
					new EnversPreCollectionRemoveEventListenerImpl( enversService )
			);
			listenerRegistry.appendListeners(
					EventType.PRE_COLLECTION_UPDATE,
					new EnversPreCollectionUpdateEventListenerImpl( enversService )
			);
		}
		
	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		// TODO Auto-generated method stub
		
	}
	 
    
 
}