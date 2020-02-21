CREATE TABLE "route_item" (
	"id" serial NOT NULL,
	"order_id" integer NOT NULL,
	"address_id" integer NOT NULL,
	"date" TIMESTAMP,
	"cargo_weight" character varying,
	"cargo_volume" character varying,
	"custom" integer,
	"contact_person" character varying,
	"contact_phone" character varying,
	CONSTRAINT "route_item_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_reward" (
	"id" serial NOT NULL,
	"order_id" integer NOT NULL,
	"employee_id" integer NOT NULL,
	"order_reward_percent_id" integer NOT NULL,
	"amount" DECIMAL(12,2) NOT NULL,
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
	"country" character varying NOT NULL UNIQUE,
	"region" character varying,
	"district" character varying,
	"locality" character varying NOT NULL,
	"exact_address" character varying,
	"note" character varying,
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
	"order_id" integer NOT NULL,
	"company_id" integer NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"content" TEXT NOT NULL,
	CONSTRAINT "correspondence_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order" (
	"id" serial NOT NULL,
	"number" character varying(8) NOT NULL UNIQUE,
	"our_company_id" integer NOT NULL,
	"employee_id" integer NOT NULL,
	"customer_id" integer NOT NULL,
	"carrier_id" integer NOT NULL,
	"car_id" integer NOT NULL,
	"driver_id" integer NOT NULL,
	"loading_method_id" integer NOT NULL,
	"cargo_type" TEXT,
	"cargo_weight_volume" character varying,
	"customer_cost_id" integer NOT NULL,
	"paid_customer" BOOLEAN NOT NULL,
	"carrier_cost_id" integer NOT NULL,
	"paid_carrier" BOOLEAN NOT NULL,
	"vat_id" integer NOT NULL,
	"additional_conditions" character varying NOT NULL,
	CONSTRAINT "order_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "company" (
	"id" serial NOT NULL,
	"company_type" integer NOT NULL,
	"name" character varying NOT NULL,
	"payer_registration_number" character varying(20),
	"legal_address" TEXT,
	"post_address" TEXT,
	"bank_data" TEXT,
	"e-mail" character varying,
	"phone" character varying,
	CONSTRAINT "company_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "employee" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"middle_name" character varying,
	"last_name" character varying NOT NULL,
	"department_id" integer NOT NULL,
	"position_id" integer NOT NULL,
	"e-mail" character varying NOT NULL UNIQUE,
	"phone" character varying NOT NULL,
	"password" character varying NOT NULL,
	CONSTRAINT "employee_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "department" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT "department_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "currency" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT "currency_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "transaction_cost" (
	"id" serial NOT NULL,
	"date" TIMESTAMP NOT NULL,
	"currency_id" serial NOT NULL,
	"amount" DECIMAL(12,2) NOT NULL,
	"rate" DECIMAL(12,4),
	"note" TEXT,
	CONSTRAINT "transaction_cost_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "vat" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	"rate" DECIMAL(2,2),
	CONSTRAINT "vat_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "driver" (
	"id" serial NOT NULL,
	"first_name" character varying NOT NULL,
	"middle_name" character varying,
	"last_name" character varying NOT NULL,
	"passport_data" character varying NOT NULL,
	"phone" character varying,
	CONSTRAINT "driver_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "car" (
	"id" serial NOT NULL,
	"model" character varying NOT NULL,
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
	"rate" DECIMAL(12,4),
	"amount" DECIMAL(12,2) NOT NULL,
	CONSTRAINT "payment_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "correspondence_type" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT "correspondence_type_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "loading_method" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT "loading_method_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



CREATE TABLE "order_reward_percent" (
	"id" serial NOT NULL,
	"percent" DECIMAL NOT NULL,
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



CREATE TABLE "position" (
	"id" serial NOT NULL,
	"name" character varying NOT NULL,
	CONSTRAINT "position_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);



ALTER TABLE "route_item" ADD CONSTRAINT "route_item_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");
ALTER TABLE "route_item" ADD CONSTRAINT "route_item_fk1" FOREIGN KEY ("address_id") REFERENCES "address"("id");
ALTER TABLE "route_item" ADD CONSTRAINT "route_item_fk2" FOREIGN KEY ("custom") REFERENCES "address"("id");

ALTER TABLE "order_reward" ADD CONSTRAINT "order_reward_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");
ALTER TABLE "order_reward" ADD CONSTRAINT "order_reward_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");
ALTER TABLE "order_reward" ADD CONSTRAINT "order_reward_fk2" FOREIGN KEY ("order_reward_percent_id") REFERENCES "order_reward_percent"("id");

ALTER TABLE "contract" ADD CONSTRAINT "contract_fk0" FOREIGN KEY ("our_company_id") REFERENCES "company"("id");
ALTER TABLE "contract" ADD CONSTRAINT "contract_fk1" FOREIGN KEY ("company_id") REFERENCES "company"("id");


ALTER TABLE "cfr" ADD CONSTRAINT "cfr_fk0" FOREIGN KEY ("company_id") REFERENCES "company"("id");

ALTER TABLE "correspondence" ADD CONSTRAINT "correspondence_fk0" FOREIGN KEY ("correspondence_type_id") REFERENCES "correspondence_type"("id");
ALTER TABLE "correspondence" ADD CONSTRAINT "correspondence_fk1" FOREIGN KEY ("order_id") REFERENCES "order"("id");
ALTER TABLE "correspondence" ADD CONSTRAINT "correspondence_fk2" FOREIGN KEY ("company_id") REFERENCES "company"("id");

ALTER TABLE "order" ADD CONSTRAINT "order_fk0" FOREIGN KEY ("our_company_id") REFERENCES "company"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk2" FOREIGN KEY ("customer_id") REFERENCES "company"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk3" FOREIGN KEY ("carrier_id") REFERENCES "company"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk4" FOREIGN KEY ("car_id") REFERENCES "car"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk5" FOREIGN KEY ("driver_id") REFERENCES "driver"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk6" FOREIGN KEY ("loading_method_id") REFERENCES "loading_method"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk7" FOREIGN KEY ("customer_cost_id") REFERENCES "transaction_cost"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk8" FOREIGN KEY ("carrier_cost_id") REFERENCES "transaction_cost"("id");
ALTER TABLE "order" ADD CONSTRAINT "order_fk9" FOREIGN KEY ("vat_id") REFERENCES "vat"("id");


ALTER TABLE "employee" ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("department_id") REFERENCES "department"("id");
ALTER TABLE "employee" ADD CONSTRAINT "employee_fk1" FOREIGN KEY ("position_id") REFERENCES "position"("id");



ALTER TABLE "transaction_cost" ADD CONSTRAINT "transaction_cost_fk0" FOREIGN KEY ("currency_id") REFERENCES "currency"("id");




ALTER TABLE "payment" ADD CONSTRAINT "payment_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");
ALTER TABLE "payment" ADD CONSTRAINT "payment_fk1" FOREIGN KEY ("company_id") REFERENCES "company"("id");
ALTER TABLE "payment" ADD CONSTRAINT "payment_fk2" FOREIGN KEY ("currency_id") REFERENCES "currency"("id");




ALTER TABLE "order_2_employee" ADD CONSTRAINT "order_2_employee_fk0" FOREIGN KEY ("order_id") REFERENCES "order"("id");
ALTER TABLE "order_2_employee" ADD CONSTRAINT "order_2_employee_fk1" FOREIGN KEY ("employee_id") REFERENCES "employee"("id");

