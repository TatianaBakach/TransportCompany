package by.itacademy.tatabakach.transportcompany.dao.orm.impl.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IBaseEntity;

@MappedSuperclass
public abstract class BaseEntity implements IBaseEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(final Integer id) {
		this.id = id;
	}

}