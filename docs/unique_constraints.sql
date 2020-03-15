ALTER TABLE public.cfr
    ADD UNIQUE (company_id, year);
    
ALTER TABLE public.employee_2_company
    ADD UNIQUE (employee_id, company_id);
    
ALTER TABLE public.order_2_employee
    ADD UNIQUE (order_id, employee_id);