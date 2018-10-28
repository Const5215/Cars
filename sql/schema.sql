create sequence CUSTOMER_ID_SEQ
  nocache
/

create table CUSTOMER
(
  ID       NUMBER        not null,
  PASSWORD VARCHAR2(255) not null,
  NAME     VARCHAR2(255) not null,
  EMAIL    VARCHAR2(255) not null,
  PHONE    VARCHAR2(255) not null,
  ADDRESS  VARCHAR2(255) not null,
  constraint CUSTOMER_PK
  primary key (ID)
)
/

create unique index CUSTOMER_EMAIL_UINDEX
  on CUSTOMER (EMAIL)
/

create table EMPLOYEE
(
  ID       NUMBER        not null,
  PASSWORD VARCHAR2(255) not null,
  NAME     VARCHAR2(255) not null,
  EMAIL    VARCHAR2(255) not null,
  PHONE    VARCHAR2(255) not null,
  ADDRESS  VARCHAR2(255) not null,
  constraint EMPLOYEE_PK
  primary key (ID)
)
/

create table DISTRIBUTOR
(
  ID              NUMBER        not null,
  NAME            VARCHAR2(255) not null,
  DELIVERY_WINDOW NUMBER        not null,
  constraint DISTRIBUTOR_PK
  primary key (ID)
)
/

create table CENTER
(
  ID        NUMBER        not null,
  NAME      VARCHAR2(255) not null,
  TELEPHONE VARCHAR2(255) not null,
  ADDRESS   VARCHAR2(255) not null,
  constraint CENTER_PK
  primary key (ID)
)
/

create table PART
(
  ID             NUMBER        not null,
  NAME           VARCHAR2(255) not null,
  UNIT_PRICE     NUMBER        not null,
  DISTRIBUTOR_ID NUMBER        not null,
  constraint PART_PK
  primary key (ID),
  constraint PART_DISTRIBUTOR_ID_FK
  foreign key (DISTRIBUTOR_ID) references DISTRIBUTOR
)
/

create table INVENTORY
(
  CENTER_ID          NUMBER not null,
  PART_ID            NUMBER not null,
  CURRENT_QUANTITY   NUMBER not null,
  AVAILABLE_QUANTITY NUMBER not null,
  MIN_THRESHOLD      NUMBER not null,
  MIN_ORDER_QUANTITY NUMBER not null,
  constraint INVENTORY_PK
  primary key (CENTER_ID, PART_ID),
  constraint INVENTORY_CENTER_ID_FK
  foreign key (CENTER_ID) references CENTER,
  constraint INVENTORY_PART_ID_FK
  foreign key (PART_ID) references PART
)
/

create table EMPLOYMENT
(
  EMPLOYEE_ID  NUMBER not null,
  CENTER_ID    NUMBER not null,
  POSITION     NUMBER not null,
  COMPENSATION FLOAT  not null,
  START_DATE   DATE   not null,
  constraint EMPLOYMENT_PK
  primary key (EMPLOYEE_ID, CENTER_ID),
  constraint EMPLOYMENT_CENTER_ID_FK
  foreign key (CENTER_ID) references CENTER
  on delete cascade,
  constraint EMPLOYMENT_EMPLOYEE_ID_FK
  foreign key (EMPLOYEE_ID) references EMPLOYEE
  on delete cascade
)
/

create table PAYROLL
(
  EMPLOYEE_ID   NUMBER not null,
  FROM_DATE     DATE   not null,
  TO_DATE       DATE,
  PAYCHECK_DATE DATE   not null,
  UNIT          NUMBER not null,
  EARNING       FLOAT  not null,
  constraint PAYROLL_PK
  primary key (EMPLOYEE_ID, PAYCHECK_DATE),
  constraint PAYROLL_EMPLOYEE_ID_FK
  foreign key (EMPLOYEE_ID) references EMPLOYEE
  on delete cascade
)
/

create table EXTERNAL_ORDER
(
  ID             NUMBER not null,
  PART_ID        NUMBER not null,
  QUANTITY       NUMBER not null,
  TOTAL          FLOAT  not null,
  CENTER_ID      NUMBER not null,
  DISTRIBUTOR_ID NUMBER not null,
  ORDER_DATE     DATE   not null,
  DELIVERY_DATE  DATE   not null,
  STATUS         NUMBER not null,
  constraint EXTERNAL_ORDER_PK
  primary key (ID),
  constraint EXTERNAL_ORDER_C_ID_FK
  foreign key (CENTER_ID) references CENTER,
  constraint EXTERNAL_ORDER_D_ID_FK
  foreign key (DISTRIBUTOR_ID) references DISTRIBUTOR,
  constraint EXTERNAL_ORDER_PART_ID_FK
  foreign key (PART_ID) references PART
)
/

create table INTERNAL_ORDER
(
  ID            NUMBER not null,
  PART_ID       NUMBER not null,
  QUANTITY      NUMBER not null,
  TOTAL         FLOAT  not null,
  TO_ID         NUMBER not null,
  FROM_ID       NUMBER not null,
  ORDER_DATE    DATE   not null,
  DELIVERY_DATE DATE   not null,
  STATUS        NUMBER not null,
  constraint INTERNAL_ORDER_PK
  primary key (ID),
  constraint INTERNAL_ORDER_FROM_C_ID_FK
  foreign key (TO_ID) references CENTER,
  constraint INTERNAL_ORDER_PART_ID_FK
  foreign key (PART_ID) references PART,
  constraint INTERNAL_ORDER_TO_C_ID_FK
  foreign key (FROM_ID) references CENTER
)
/

create table CAR_MODEL
(
  ID    NUMBER        not null,
  MAKE  VARCHAR2(255) not null,
  MODEL VARCHAR2(255) not null,
  YEAR  NUMBER        not null,
  constraint CAR_MODEL_PK
  primary key (ID),
  constraint CAR_MAKE_CONSTRAINT
  check (MAKE IN ('Honda', 'Nissan', 'Toyota'))
)
/

create table CAR
(
  PLATE             VARCHAR2(255) not null,
  CUSTOMER_ID       NUMBER        not null,
  CAR_MODEL_ID      NUMBER        not null,
  PURCHASE_DATE     DATE          not null,
  LAST_MILEAGE      NUMBER        not null,
  LAST_SERVICE_TYPE NUMBER,
  LAST_SERVICE_DATE DATE,
  constraint CAR_PK
  primary key (PLATE),
  constraint CAR_CAR_MODEL_ID_FK
  foreign key (CAR_MODEL_ID) references CAR_MODEL,
  constraint CAR_CUSTOMER_ID_FK
  foreign key (CUSTOMER_ID) references CUSTOMER
  on delete cascade
)
/

create table MAINTENANCE
(
  CAR_MODEL_ID NUMBER not null,
  SERVICE_TYPE NUMBER not null,
  MILE         NUMBER not null,
  MONTH        NUMBER not null,
  constraint MAINTENANCE_PK
  primary key (CAR_MODEL_ID, SERVICE_TYPE),
  constraint MAINTENANCE_CAR_MODEL_ID_FK
  foreign key (CAR_MODEL_ID) references CAR_MODEL
  on delete cascade
)
/

create table BASIC_SERVICE
(
  CAR_MODEL_ID   NUMBER        not null,
  NAME           VARCHAR2(255) not null,
  ESTIMATED_HOUR NUMBER        not null,
  CHARGE_RATE    NUMBER        not null,
  SERVICE_TYPE   NUMBER        not null,
  WARRANTY       NUMBER,
  PROBLEM        VARCHAR2(255),
  constraint BASIC_SERVICE_PK
  primary key (CAR_MODEL_ID, NAME),
  constraint BASIC_SERVICE_CAR_MODEL_ID_FK
  foreign key (CAR_MODEL_ID) references CAR_MODEL
  on delete cascade
)
/

create table INTERNAL_NOTIFICATION
(
  ID                NUMBER not null,
  ORDER_ID          NUMBER not null,
  NOTIFICATION_DATE DATE   not null,
  constraint INTERNAL_NOTIFICATION_PK
  primary key (ID),
  constraint INTERNAL_NOTIFICATION_O_ID_FK
  foreign key (ORDER_ID) references INTERNAL_ORDER
  on delete cascade
)
/

create table EXTERNAL_NOTIFICATION
(
  ID                NUMBER not null,
  ORDER_ID          NUMBER not null,
  NOTIFICATION_DATE NUMBER not null,
  constraint EXTERNAL_NOTIFICATION_PK
  primary key (ID),
  constraint EXTERNAL_NOTIFICATION_O_ID_FK
  foreign key (ORDER_ID) references EXTERNAL_ORDER
  on delete cascade
)
/

create table BASIC_SERVICE_PART
(
  CAR_MODEL_ID       NUMBER        not null,
  BASIC_SERVICE_NAME VARCHAR2(255) not null,
  PART_ID            NUMBER        not null,
  QUANTITY           NUMBER        not null,
  constraint BASIC_SERVICE_PART_PK
  primary key (CAR_MODEL_ID, BASIC_SERVICE_NAME, PART_ID),
  constraint BASIC_SERVICE_PART_FK
  foreign key (CAR_MODEL_ID, BASIC_SERVICE_NAME) references BASIC_SERVICE,
  constraint BASIC_SERVICE_PART_PART_ID_FK
  foreign key (PART_ID) references PART
)
/

create table SERVICE_HISTORY
(
  ID               NUMBER        not null,
  CAR_PLATE        VARCHAR2(255) not null,
  SERVICE_TYPE     NUMBER        not null,
  START_TIME       DATE          not null,
  END_TIME         DATE          not null,
  TOTAL_LABOR_HOUR NUMBER        not null,
  STATUS           NUMBER        not null,
  MECHANIC_ID      NUMBER        not null,
  CENTER_ID        NUMBER        not null,
  constraint SERVICE_HISTORY_PK
  primary key (ID),
  constraint SERVICE_HISTORY_CAR_PLATE_FK
  foreign key (CAR_PLATE) references CAR,
  constraint SERVICE_HISTORY_CENTER_ID_FK
  foreign key (CENTER_ID) references CENTER,
  constraint SERVICE_HISTORY_EMPLOYEE_ID_FK
  foreign key (MECHANIC_ID) references EMPLOYEE
)
/

create table SERVICE_HISTORY_DETAIL
(
  SERVICE_HISTORY_ID NUMBER not null,
  PART_ID            NUMBER not null,
  QUANTITY           NUMBER not null,
  constraint SERVICE_HISTORY_DETAIL_PK
  primary key (SERVICE_HISTORY_ID, PART_ID),
  constraint SERVICE_HISTORY_DETAIL_FK
  foreign key (PART_ID) references PART,
  constraint SERVICE_HISTORY_ID_FK
  foreign key (SERVICE_HISTORY_ID) references SERVICE_HISTORY
  on delete cascade
)
/

