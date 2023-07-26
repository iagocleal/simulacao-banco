package br.com.iago.simulacaoBanco.config.audit;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;

import br.com.iago.simulacaoBanco.model.Banco;

public class CustomPostUpdateEventListenerImpl extends EnversPostUpdateEventListenerImpl {

	public CustomPostUpdateEventListenerImpl(EnversService enversService) {
        super(enversService);
    }
	
	@Override
    public void onPostUpdate(PostUpdateEvent event) {
        if (event.getEntity() instanceof Banco
                && ((Banco) event.getEntity()).getAudit()) {
        	super.onPostUpdate(event);
        }
        
        return;
    }
}
