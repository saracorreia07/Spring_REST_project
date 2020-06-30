create table commentary (
                            id bigint not null auto_increment,
                            order_service_id bigint not null,
                            description text not null,
                            sending_date datetime not null,

                            primary key (id)
);

alter table commentary add constraint fk_commentary_order_service
    foreign key (order_service_id) references order_service (id);