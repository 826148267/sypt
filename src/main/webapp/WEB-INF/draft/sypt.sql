create table consignee_tb(
	id int auto_increment not null,		
	user_id varchar(50) not null,
	user_name varchar(50) not null,
	province varchar(20),
	city varchar(20),
	phone char(11),
	mail_box varchar(25),
	QQ varchar(14),
	personalize_signature varchar(150),
	if_have_imgs int(1) default 0 not null,
	primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8

create table user_img_tb(
	id int auto_increment not null,
	user_id varchar(50) not null,
	user_type int(1) not null,
	img_path varchar(50) not null,
	img_type int not null,
	weight int not null,
	primary key(id)	
)engine=InnoDB auto_increment=1 default charset=utf8

create table consignor_tb(
	id int auto_increment not null,
	user_id varchar(50) not null,
	user_name varchar(50) not null,
	province varchar(20),
	city varchar(20),
	phone char(11),
	mail_box varchar(25),
	QQ varchar(14),
	personalize_signature varchar(150),
	primary key(id)	
)engine=InnoDB auto_increment=1 default charset=utf8

create table order_tb(
	id int auto_increment not null,
	order_id varchar(50) not null,
	transit_order_id varchar(50) not null,
	price int not null,
	status int(5) not null,
	consignor_id varchar(50) not null,
	consignee_id varchar(50) not null,
	begin_time datetime,
	end_time datetime,
	weight int not null,
	primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8

create table transit_order_tb(
	id int auto_increment not null,
	transit_order_id varchar(50) not null,
	game_order_id varchar(50) not null,
	game_type int(5) not null,
	time_limit datetime not null,
	price int not null,
	other_requirement varchar(200),
	create_time datetime default now(),
	consignee_id varchar(50) not null,
	if_have_imgs int(1) default 0 not null,
	weight int not null,
	primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8

create table transit_order_img_tb(
	id int auto_increment not null,
	transit_order_id varchar(50) not null,
	img_path varchar(50) not null,
	img_type int(5) not null,
	weight int not null,
	primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8

create table king_honor_tb(
	id int auto_increment not null,
	game_order_id varchar(50) not null,
	current_level varchar(15) not null,
	target_level varchar(15) not null,
	expect_up_star_num int(5) not null,
	game_area varchar(10) not null,
	primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8

create table slide_show_tb(
	id int auto_increment not null,
	slide_show_id int auto_increment not null,
	img_path int(5) not null,
	img_content varchar(200),
	img_title varchar(10) not null,
	overdue_time datetime not null,
	create_time datetime default now(),
	weight int not null,
	primary key(id)
)engine=InnoDB auto_increment=1 default charset=utf8






