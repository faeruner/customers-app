-- Table: public.customer_types
DROP SEQUENCE public.customers_seq CASCADE;

DROP TABLE public.customers CASCADE;

DROP TABLE public.customer_types CASCADE;

CREATE TABLE public.customer_types
(
  customer_type_id      INTEGER NOT NULL,
  customer_type_caption CHARACTER VARYING(50) COLLATE pg_catalog."default",
  CONSTRAINT customer_types_pkey PRIMARY KEY (customer_type_id)
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.customer_types
  OWNER TO postgres;

-- Table: public.customers

CREATE TABLE public.customers
(
  customer_id          INTEGER NOT NULL,
  title                CHARACTER VARYING(4) COLLATE pg_catalog."default",
  first_name           CHARACTER VARYING(50) COLLATE pg_catalog."default",
  last_name            CHARACTER VARYING(50) COLLATE pg_catalog."default",
  modified_when        TIMESTAMP WITH TIME ZONE,
  type                 INTEGER,
  first_name_metaphone CHARACTER VARYING(50) COLLATE pg_catalog."default",
  last_name_metaphone  CHARACTER VARYING(50) COLLATE pg_catalog."default",
  CONSTRAINT customers_pkey PRIMARY KEY (customer_id),
  CONSTRAINT customers_type_fk FOREIGN KEY (type)
  REFERENCES public.customer_types (customer_type_id) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
)
WITH (
OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.customers
  OWNER TO postgres;

CREATE SEQUENCE public.customers_seq
INCREMENT 1
START 2
MINVALUE 1
MAXVALUE 9223372036854775807
CACHE 1;

ALTER SEQUENCE public.customers_seq
OWNER TO postgres;