CREATE TABLE "route_item" (
	"id" serial NOT NULL,
	"order_id" integer NOT NULL,
	"address_id" integer NOT NULL,
	"date" TIMESTAMP,
	"cargo_weight" character varying,
	"cargo_volume" character varying,
	"custom_id" integer,
	"contact_person" character varying,
	"contact_phone" character varying,
	"note" TEXT,
	CONSTRAINT "route_item_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_reward" (
	"id" serial NOT NULL,
	"order_id" integer NOT NULL,
	"employee_id" integer NOT NULL,
	"reward_type_id" integer NOT NULL,
	"order_reward_percent_id" integer NOT NULL,
	"additional_expenses" DECIMAL(18,2),
	"amount" DECIMAL(18,2),
	"payment_date" TIMESTAMP,
	"reward_issued" BOOLEAN NOT NULL,
	"note" TEXT,
	CONSTRAINT "order_reward_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "contract" (
	"id" serial NOT NULL,
	"number" character varying(15),
	"our_company_id" integer NOT NULL,
	"company_id" integer NOT NULL,
	"date" TIMESTAMP NOT NULL,
	CONSTRAINT "contract_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "address" (
	"id" serial NOT NULL,
	"postcode" character varying(10),
	"locality_id" integer NOT NULL,
	"exact_address" TEXT,
	"note" TEXT,
	CONSTRAINT "address_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "cfr" (
	"id" serial NOT NULL,
	"company_id" integer NOT NULL,
	"year" integer NOT NULL,
	CONSTRAINT "cfr_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "correspondence" (
	"id" serial NOT NULL,
	"correspondence_type_id" integer NOT NULL,
	"order_id" integer,
	"company_id" integer NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"content" TEXT NOT NULL,
	"note" TEXT,
	CONSTRAINT "correspondence_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_object" (
	"id" serial NOT NULL,
	"number" character varying(8) NOT NULL UNIQUE,
	"date" TIMESTAMP NOT NULL,
	"our_company_id" integer NOT NULL,
	"customer_id" integer NOT NULL,
	"carrier_id" integer NOT NULL,
	"car_id" integer,
	"driver_id" integer,
	"loading_method_id" integer,
	"cargo_type" TEXT,
	"cargo_weight_volume" character varying,
	"customer_cost_id" integer NOT NULL,
	"paid_customer" BOOLEAN NOT NULL,
	"carrier_cost_id" integer NOT NULL,
	"paid_carrier" BOOLEAN NOT NULL,
	"tax_id" integer,
	"additional_conditions" TEXT,
	"creator_id" integer NOT NULL,
	"note" TEXT,
	CONSTRAINT "order_object_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "company" (
	"id" serial NOT NULL,
	"company_type_id" integer NOT NULL,
	"name" character varying NOT NULL,
	"payer_registration_number" character varying(20),
	"legal_address_id" integer,
	"post_address_id" integer,
	"bank_data" TEXT,
	"e_mail" character varying,
	"phone" character varying,
	"creator_id" integer NOT NULL,
	CONSTRAINT "company_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "employee" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"middle_name" character varying,
	"last_name" character varying NOT NULL,
	"department_id" integer,
	"position_id" integer,
	"e_mail" character varying UNIQUE,
	"phone" character varying,
	"login" character varying,
	"password" character varying,
	"salary" DECIMAL(18,2),
	CONSTRAINT "employee_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transaction_cost" (
	"id" serial NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"currency_id" integer NOT NULL,
	"amount" DECIMAL(18,2) NOT NULL,
	"rate" DECIMAL(18,4),
	"intermediate_currency_id" integer,
	"intermediate_currency_rate" DECIMAL(18,4),
	"payment_period" integer NOT NULL,
	"payment_terms_type_id" integer NOT NULL,
	"note" TEXT,
	CONSTRAINT "transaction_cost_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "tax" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"rate" DECIMAL(4,2),
	CONSTRAINT "tax_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "driver" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"middle_name" character varying,
	"last_name" character varying NOT NULL,
	"passport_data" character varying,
	"phone" character varying,
	CONSTRAINT "driver_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car" (
	"id" serial NOT NULL,
	"model" character varying,
	"number" character varying NOT NULL,
	CONSTRAINT "car_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "payment" (
	"id" serial NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"order_id" integer NOT NULL,
	"company_id" integer NOT NULL,
	"currency_id" integer NOT NULL,
	"rate" DECIMAL(18,4),
	"amount" DECIMAL(18,2) NOT NULL,
	"note" TEXT,
	CONSTRAINT "payment_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_reward_percent" (
	"id" serial NOT NULL,
	"percent" DECIMAL(4,2) NOT NULL,
	CONSTRAINT "order_reward_percent_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_2_employee" (
	"order_id" integer NOT NULL,
	"employee_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "employee_2_company" (
	"employee_id" integer NOT NULL,
	"company_id" integer NOT NULL
) WITH (
  OIDS=FALSE
);



CREATE TABLE "locality" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"region_id" integer,
	CONSTRAINT "locality_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "region" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"country_id" integer NOT NULL,
	CONSTRAINT "region_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "country" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT "country_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "route_item" ADD CONSTRAINT "route_item_fk0" FOREIGN KEY ("order_id") REFERENCES "order_object"("id");
ALTER TABLE "route_item" ADD CONSTRAINT "route_item_fk1" FOREIGN KEY ("address_id") REFERENCES "address"("id");
ALTER TABLE "route_item" ADD CONSTRAINT "route_item_fk2" FOREIGN KEY ("custom_id") REFERENCES "address"("id");

ALTER TABLE "order_reward" ADD CONSTRAINT "order_reward_fk0" FOREIGN KEY ("order_id") REFERENCES "order_object"("id");
ALTER TABLE "order_reward" ADD CONSTRAINT "order_reward_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");
ALTER TABLE "order_reward" ADD CONSTRAINT "order_reward_fk2" FOREIGN KEY ("order_reward_percent_id") REFERENCES "order_reward_percent"("id");

ALTER TABLE "contract" ADD CONSTRAINT "contract_fk0" FOREIGN KEY ("our_company_id") REFERENCES "company"("id");
ALTER TABLE "contract" ADD CONSTRAINT "contract_fk1" FOREIGN KEY ("company_id") REFERENCES "company"("id");

ALTER TABLE "address" ADD CONSTRAINT "address_fk0" FOREIGN KEY ("locality_id") REFERENCES "locality"("id");

ALTER TABLE "cfr" ADD CONSTRAINT "cfr_fk0" FOREIGN KEY ("company_id") REFERENCES "company"("id");

ALTER TABLE "correspondence" ADD CONSTRAINT "correspondence_fk0" FOREIGN KEY ("order_id") REFERENCES "order_object"("id");
ALTER TABLE "correspondence" ADD CONSTRAINT "correspondence_fk1" FOREIGN KEY ("company_id") REFERENCES "company"("id");

ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk0" FOREIGN KEY ("our_company_id") REFERENCES "company"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk1" FOREIGN KEY ("customer_id") REFERENCES "company"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk2" FOREIGN KEY ("carrier_id") REFERENCES "company"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk3" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk4" FOREIGN KEY ("driver_id") REFERENCES "driver"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk5" FOREIGN KEY ("customer_cost_id") REFERENCES "transaction_cost"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk6" FOREIGN KEY ("carrier_cost_id") REFERENCES "transaction_cost"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk7" FOREIGN KEY ("tax_id") REFERENCES "tax"("id");
ALTER TABLE "order_object" ADD CONSTRAINT "order_object_fk8" FOREIGN KEY ("creator_id") REFERENCES "employee"("id");

ALTER TABLE "company" ADD CONSTRAINT "company_fk0" FOREIGN KEY ("legal_address_id") REFERENCES "address"("id");
ALTER TABLE "company" ADD CONSTRAINT "company_fk1" FOREIGN KEY ("post_address_id") REFERENCES "address"("id");
ALTER TABLE "company" ADD CONSTRAINT "company_fk2" FOREIGN KEY ("creator_id") REFERENCES "employee"("id");






ALTER TABLE "payment" ADD CONSTRAINT "payment_fk0" FOREIGN KEY ("order_id") REFERENCES "order_object"("id");
ALTER TABLE "payment" ADD CONSTRAINT "payment_fk1" FOREIGN KEY ("company_id") REFERENCES "company"("id");


ALTER TABLE "order_2_employee" ADD CONSTRAINT "order_2_employee_fk0" FOREIGN KEY ("order_id") REFERENCES "order_object"("id");
ALTER TABLE "order_2_employee" ADD CONSTRAINT "order_2_employee_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");

ALTER TABLE "employee_2_company" ADD CONSTRAINT "employee_2_company_fk0" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");
ALTER TABLE "employee_2_company" ADD CONSTRAINT "employee_2_company_fk1" FOREIGN KEY ("company_id") REFERENCES "company"("id");

ALTER TABLE "locality" ADD CONSTRAINT "locality_fk0" FOREIGN KEY ("region_id") REFERENCES "region"("id");

ALTER TABLE "region" ADD CONSTRAINT "region_fk0" FOREIGN KEY ("country_id") REFERENCES "country"("id");


