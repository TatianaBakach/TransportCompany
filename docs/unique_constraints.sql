ALTER TABLE public.cfr
    ADD UNIQUE (company_id, year);
    
ALTER TABLE public.company_2_employee
    ADD UNIQUE (company_id, employee_id);
    
ALTER TABLE public.order_2_employee
    ADD UNIQUE (order_id, employee_id);