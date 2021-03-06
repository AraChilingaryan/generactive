create table generactive.groups
(
    id        bigint default nextval('generactive.groups_id_seq1'::regclass) not null
        constraint groups_pkey
            primary key,
    name      varchar(255)                                                   not null,
    parent_id bigint
        constraint groups_parent_id_fkey
            references generactive.groups
);

alter table generactive.groups
    owner to postgres;

create table generactive.item
(
    id         bigserial
        constraint item_pkey
            primary key,
    base_price double precision not null,
    name       varchar(255),
    image_url  varchar(255),
    group_id   integer          not null
        constraint group_id
            references generactive.groups,
    currency   generactive.currency
);

alter table generactive.item
    owner to postgres;

create table generactive.stockitem
(
)
    inherits (generactive.item);

alter table generactive.stockitem
    owner to postgres;

create table generactive.generativeitem
(
    complexity generactive.complexity
)
    inherits (generactive.item);

alter table generactive.generativeitem
    owner to postgres;

