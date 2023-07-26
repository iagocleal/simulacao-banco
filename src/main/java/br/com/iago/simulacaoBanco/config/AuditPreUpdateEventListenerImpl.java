package br.com.iago.simulacaoBanco.config;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPreUpdateEventListenerImpl;
import org.hibernate.event.spi.PreUpdateEvent;

import br.com.iago.simulacaoBanco.model.Banco;

public class AuditPreUpdateEventListenerImpl extends EnversPreUpdateEventListenerImpl {

	public AuditPreUpdateEventListenerImpl(EnversService enversService) {
        super(enversService);
    }
	
	@Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        if (event.getEntity() instanceof Banco
                && ((Banco) event.getEntity()).getAudit()) {
            return false;
        }
         
        return super.onPreUpdate(event);
    }
}
