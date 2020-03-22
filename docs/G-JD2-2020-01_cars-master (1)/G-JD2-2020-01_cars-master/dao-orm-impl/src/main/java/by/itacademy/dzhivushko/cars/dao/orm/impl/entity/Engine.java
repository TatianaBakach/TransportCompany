package by.itacademy.dzhivushko.cars.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import by.itacademy.dzhivushko.cars.dao.api.entity.enums.EngineType;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;

@Entity
public class Engine extends BaseEntity implements IEngine {
	@Column
	@Enumerated(EnumType.STRING)
	private EngineType type;

	@Column
	private String title;

	@Column
	private Integer volume;

	@Override
	public EngineType getType() {
		return type;
	}

	@Override
	public void setType(final EngineType type) {
		this.type = type;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(final String title) {
		this.title = title;
	}

	@Override
	public Integer getVolume() {
		return volume;
	}

	@Override
	public void setVolume(final Integer volume) {
		this.volume = volume;
	}

}
