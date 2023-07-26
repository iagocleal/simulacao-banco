package br.com.iago.simulacaoBanco.config.audit;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@Table(name = "REVISION_INFO", schema = "SIMULACAO")
@RevisionEntity
@AttributeOverrides({
    @AttributeOverride(name = "timestamp", column = @Column(name = "REV_TIMESTAMP")),
    @AttributeOverride(name = "id", column = @Column(name = "REVISION_ID"))
})
public class CustomAuditRevisionEntity extends DefaultRevisionEntity {
	
}
