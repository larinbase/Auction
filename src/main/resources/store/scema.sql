CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create TABLE account
(
    id       uuid primary key NOT NULL default uuid_generate_v4(),
    name     varchar(50)      NOT NULL,
    password varchar(50)      NOT NULL
);
comment on table account is 'Аккаунт';

create TABLE auction
(
    id         serial primary key NOT NULL,
    name       varchar(100)       NOT NULL,
    account_id uuid               NOT NULL,
    status     varchar(10)        NOT NULL check (auction.status in ('open', 'close')) default 'open',
    foreign key (account_id) references account (id)
);
comment on table auction is 'Аукцион';

create TABLE lot
(
    id          serial primary key  NOT NULL,
    name        varchar(100)        NOT NULL,
    description varchar(1000)       NOT NULL,
    artikul     varchar(100) unique NOT NULL                         default uuid_generate_v4(),
    status      varchar(10) check ( lot.status in ('open', 'close')) default 'open',
    auction_id  serial              NOT NULL,
    foreign key (auction_id) references auction (id)
);
comment on table lot is 'Лот';

create TABLE bet
(
    id         serial primary key NOT NULL,
    amount     numeric            NOT NULL,
    date_time  date               NOT NULL default current_date,
    lot_id     serial             NOT NULL,
    account_id uuid               NOT NULL,
    foreign key (lot_id) references lot (id),
    foreign key (account_id) references account (id)
);
comment on table bet is 'Ставка';

create table award
(
    id         serial primary key NOT NULL,
    lot_id     serial             NOT NULL,
    account_id uuid               NOT NULL,
    foreign key (lot_id) references lot (id),
    foreign key (account_id) references account (id)
);
comment on table bet is 'Выигранные лоты';
