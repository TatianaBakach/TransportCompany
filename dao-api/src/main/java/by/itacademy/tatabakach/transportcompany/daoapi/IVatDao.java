package by.itacademy.tatabakach.transportcompany.daoapi;

import java.util.List;

import by.itacademy.tatabakach.transportcompany.daoapi.entity.table.IVat;
import by.itacademy.tatabakach.transportcompany.daoapi.filter.VatFilter;

public interface IVatDao extends IDao<IVat, Integer> {
	
	List<IVat> find(VatFilter filter);

	long getCount(VatFilter filter);


}
