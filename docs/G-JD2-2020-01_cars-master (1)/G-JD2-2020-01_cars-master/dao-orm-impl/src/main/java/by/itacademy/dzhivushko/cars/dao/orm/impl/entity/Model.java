package by.itacademy.dzhivushko.cars.dao.orm.impl.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import by.itacademy.dzhivushko.cars.dao.api.entity.table.IBrand;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IEngine;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModel;
import by.itacademy.dzhivushko.cars.dao.api.entity.table.IModelInfo;

@Entity
public class Model extends BaseEntity implements IModel {
	@Column
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Brand.class)
	private IBrand brand;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "model", targetEntity = ModelInfo.class)
	private IModelInfo modelInfo;

	@JoinTable(name = "model_2_engine", joinColumns = { @JoinColumn(name = "model_id") }, inverseJoinColumns = {
			@JoinColumn(name = "engine_id") })
	@ManyToMany(targetEntity = Engine.class, fetch = FetchType.LAZY)
	private Set<IEngine> engines = new HashSet<>();

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public IBrand getBrand() {
		return brand;
	}

	@Override
	public void setBrand(final IBrand brand) {
		this.brand = brand;
	}

	@Override
	public IModelInfo getModelInfo() {
		return modelInfo;
	}

	@Override
	public void setModelInfo(final IModelInfo modelInfo) {
		this.modelInfo = modelInfo;
	}

	@Override
	public Set<IEngine> getEngines() {
		return engines;
	}

	@Override
	public void setEngines(final Set<IEngine> engines) {
		this.engines = engines;
	}

	@Override
	public String toString() {
		return "Model [name=" + name + ", getId()=" + getId() + "]";
	}
}
