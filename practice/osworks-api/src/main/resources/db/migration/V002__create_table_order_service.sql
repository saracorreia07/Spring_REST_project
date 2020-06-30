create table order_service(
	id bigint not null auto_increment,
    client_id bigint not null,
    description text not null,
    price decimal (10,2) not null,
	status varchar(20) not null,
    opening_date datetime not null,
    final_date datetime,

    primary key(id)

);

alter table order_service add constraint fk_order_service_client
foreign key (client_id) references client (id);