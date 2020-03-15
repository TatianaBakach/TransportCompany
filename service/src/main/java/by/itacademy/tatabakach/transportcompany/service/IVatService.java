package by.itacademy.tatabakach.transportcompany.service;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IVat;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.VatFilter;

public interface IVatService {
	
	IVat get(Integer id);

	List<IVat> getAll();

	void save(IVat entity);

	void delete(Integer id);

	void deleteAll();

	IVat createEntity();
	
	List<IVat> find(VatFilter filter);

	long getCount(VatFilter filter);

}
