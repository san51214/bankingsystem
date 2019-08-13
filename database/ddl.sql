create schema client_transactions;

alter schema client_transactions owner to postgres;

create sequence record_entry_sequence;

alter sequence record_entry_sequence owner to postgres;

create table client
(
	id numeric not null
		constraint client_pkey
			primary key,
	name varchar(50) not null,
	surname varchar(50) not null,
	date_created timestamp default CURRENT_TIMESTAMP not null,
	constraint unique_client_name_surname_
		unique (name, surname)
			deferrable initially deferred
);

alter table client owner to postgres;

create table client_address
(
	id numeric not null
		constraint client_address_pkey
			primary key,
	client_id numeric not null
		constraint "client_id_in_address_FK"
			references client,
	address_type varchar(12) not null,
	line1 text,
	line2 text,
	city text not null,
	country text not null,
	date_created timestamp,
	constraint "Unique_address_address_type_client"
		unique (address_type, client_id)
);

alter table client_address owner to postgres;

create index "fki_client_id_in_address_FK"
	on client_address (client_id);

create table client_account
(
	id numeric not null
		constraint client_account_pkey
			primary key,
	type varchar(15) not null,
	balance double precision not null,
	status varchar(2) not null,
	date_created timestamp not null,
	client_id numeric not null
		constraint client_id_in_account_fk
			references client
);

alter table client_account owner to postgres;

create index fki_client_id_in_account_fk
	on client_account (client_id);

create table account_transaction
(
	id numeric not null
		constraint account_transaction_pkey
			primary key,
	debit_account_id numeric not null
		constraint debit_account_id_in_accounttransaction_fk
			references client_account,
	credit_account_id numeric not null
		constraint credit_account_id_in_accounttransaction_fk
			references client_account,
	amount double precision not null,
	message text,
	date_created timestamp default CURRENT_TIMESTAMP not null
);

alter table account_transaction owner to postgres;

create index fki_debit_account_id_in_accounttransaction_fk
	on account_transaction (debit_account_id);

create index fki_credit_account_id_in_accounttransaction_fk
	on account_transaction (credit_account_id);

