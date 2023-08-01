insert into user_details(id,birth_date,name)
values(1001, current_date(),'Akshitha');


insert into user_details(id,birth_date,name)
values(1002, current_date(),'Janu');

insert into user_details(id,birth_date,name)
values(1003, current_date(),'Nihira');

insert into post(id,description,user_id)
values(2001, 'I am here at clemson',1001);


insert into post(id,description,user_id)
values(2002, 'I am here at starbucks',1001);

insert into post(id,description,user_id)
values(2003, 'I am here at Banglore',1002);

insert into post(id,description,user_id)
values(2004, 'I am here at Home',1003);