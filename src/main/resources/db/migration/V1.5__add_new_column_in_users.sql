Alter table users add column  account_Non_Locked boolean not null default true;
Alter table users add column  Credentials_Non_Expired boolean not null default true;
Alter table users add column  is_Enabled boolean not null default true;