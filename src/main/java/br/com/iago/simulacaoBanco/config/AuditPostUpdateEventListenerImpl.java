package br.com.iago.simulacaoBanco.config;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;

import br.com.iago.simulacaoBanco.model.Banco;

public class AuditPostUpdateEventListenerImpl extends EnversPostUpdateEventListenerImpl {

	public AuditPostUpdateEventListenerImpl(EnversService enversService) {
        super(enversService);
    }
	
	@Override
    public void onPostUpdate(PostUpdateEvent event) {
        if (event.getEntity() instanceof Banco
                && ((Banco) event.getEntity()).getAudit()) {
            return;
        }
         
        super.onPostUpdate(event);
    }
}
