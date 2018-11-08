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
  ID       NUMBER                         not null,
  PASSWORD VARCHAR2(255) default 12345678 not null,
  NAME     VARCHAR2(255)                  not null,
  EMAIL    VARCHAR2(255)                  not null,
  PHONE    VARCHAR2(255)                  not null,
  ADDRESS  VARCHAR2(255)                  not null,
  constraint EMPLOYEE_PK
  primary key (ID)
)
/

create table DISTRIBUTOR
(
  ID   NUMBER        not null,
  NAME VARCHAR2(255) not null,
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
  ID              NUMBER            not null,
  NAME            VARCHAR2(255)     not null,
  MAKE            VARCHAR2(255)     not null,
  UNIT_PRICE      FLOAT             not null,
  WARRANTY        NUMBER default 0  not null,
  DELIVERY_WINDOW NUMBER            not null,
  DISTRIBUTOR_ID  NUMBER            not null,
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
  TO_DATE       DATE   not NULL,
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
  ID                     NUMBER           not null,
  PART_ID                NUMBER           not null,
  QUANTITY               NUMBER           not null,
  TOTAL                  FLOAT            not null,
  DISTRIBUTOR_ID         NUMBER           not null,
  CENTER_ID              NUMBER           not null,
  ORDER_DATE             DATE             not null,
  EXPECTED_DELIVERY_DATE DATE             not null,
  ACTUAL_DELIVERY_DATE   DATE,
  STATUS                 NUMBER default 0 not null,
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
  ID                     NUMBER           not null,
  PART_ID                NUMBER           not null,
  QUANTITY               NUMBER           not null,
  TOTAL                  FLOAT            not null,
  FROM_ID                NUMBER           not null,
  TO_ID                  NUMBER           not null,
  ORDER_DATE             DATE             not null,
  EXPECTED_DELIVERY_DATE DATE             not null,
  ACTUAL_DELIVERY_DATE   DATE,
  STATUS                 NUMBER default 0 not null,
  constraint INTERNAL_ORDER_PK
  primary key (ID),
  constraint INTERNAL_ORDER_FROM_C_ID_FK
  foreign key (FROM_ID) references CENTER,
  constraint INTERNAL_ORDER_PART_ID_FK
  foreign key (PART_ID) references PART,
  constraint INTERNAL_ORDER_TO_C_ID_FK
  foreign key (TO_ID) references CENTER
)
/

create table CAR
(
  PLATE         VARCHAR2(255) not null,
  CUSTOMER_ID   NUMBER        not null,
  MAKE          VARCHAR2(255) not null,
  MODEL         VARCHAR2(255) not null,
  YEAR          NUMBER        not null,
  PURCHASE_DATE DATE          not null,
  constraint CAR_PK
  primary key (PLATE),
  constraint CAR_CUSTOMER_ID_FK
  foreign key (CUSTOMER_ID) references CUSTOMER
  on delete cascade
)
/

create table MAINTENANCE
(
  MAKE             VARCHAR2(255) not null,
  MODEL            VARCHAR2(255) not null,
  MAINTENANCE_TYPE NUMBER        not null,
  MILEAGE          NUMBER        not null,
  constraint MAINTENANCE_PK
  primary key (MAKE, MODEL, MAINTENANCE_TYPE)
)
/

create table BASIC_SERVICE
(
  ID          NUMBER        not null,
  NAME        VARCHAR2(255) not null,
  LABOR_HOUR  FLOAT         not null,
  CHARGE_RATE NUMBER        not null,
  constraint BASIC_SERVICE_PK
  primary key (ID)
)
/

create table BASIC_SERVICE_PART
(
  BASIC_SERVICE_ID NUMBER        not null,
  PART_ID          NUMBER        not null,
  MAKE             VARCHAR2(255) not null,
  MODEL            VARCHAR2(255) not null,
  QUANTITY         NUMBER        not null,
  constraint BASIC_SERVICE_PART_PK
  primary key (BASIC_SERVICE_ID, PART_ID, MAKE, MODEL),
  constraint BASIC_SERVICE_ID_FK
  foreign key (BASIC_SERVICE_ID) references BASIC_SERVICE,
  constraint BASIC_SERVICE_PART_ID_FK
  foreign key (PART_ID) references PART
)
/

create table MAINTENANCE_DETAIL
(
  MAKE             VARCHAR2(255) not null,
  MODEL            VARCHAR2(255) not null,
  MAINTENANCE_TYPE NUMBER        not null,
  BASIC_SERVICE_ID NUMBER        not null,
  constraint MAINTENANCE_DETAIL_FK
  foreign key (MAKE, MODEL, MAINTENANCE_TYPE) references MAINTENANCE
)
/

create table DIAGNOSIS
(
  ID      NUMBER        not null,
  PROBLEM VARCHAR2(255) not null,
  ISSUE   VARCHAR2(255) not null,
  FEE     FLOAT         not null,
  constraint DIAGNOSIS_PK
  primary key (ID)
)
/

create table REPAIR_HISTORY
(
  ID           NUMBER        not null,
  CUSTOMER_ID  NUMBER        not null,
  CAR_PLATE    VARCHAR2(255) not null,
  CENTER_ID    NUMBER        not null,
  DIAGNOSIS_ID NUMBER        not null,
  MILEAGE      NUMBER        not null,
  START_TIME   DATE          not null,
  END_TIME     DATE          not null,
  MECHANIC_ID  NUMBER        not null,
  constraint SERVICE_HISTORY_PK
  primary key (ID),
  constraint REPAIR_HISTORY_CAR_PLATE_FK
  foreign key (CAR_PLATE) references CAR,
  constraint REPAIR_HISTORY_CENTER_ID_FK
  foreign key (CENTER_ID) references CENTER,
  constraint REPAIR_HISTORY_CUSTOMER_ID_FK
  foreign key (CUSTOMER_ID) references CUSTOMER,
  constraint REPAIR_HISTORY_DIAGNOSIS_ID_FK
  foreign key (DIAGNOSIS_ID) references DIAGNOSIS,
  constraint REPAIR_HISTORY_EMPLOYEE_ID_FK
  foreign key (MECHANIC_ID) references EMPLOYEE
)
/

create table REPAIR
(
  DIAGNOSIS_ID     NUMBER not null,
  BASIC_SERVICE_ID NUMBER not null,
  constraint REPAIR_BASIC_SERVICE_ID_FK
  foreign key (BASIC_SERVICE_ID) references BASIC_SERVICE,
  constraint REPAIR_DIAGNOSIS_ID_FK
  foreign key (DIAGNOSIS_ID) references DIAGNOSIS
)
/

create table MAINTENANCE_HISTORY
(
  ID               NUMBER        not null,
  CUSTOMER_ID      NUMBER        not null,
  CAR_PLATE        VARCHAR2(255) not null,
  CENTER_ID        NUMBER        not null,
  MAINTENANCE_TYPE NUMBER        not null,
  MILEAGE          NUMBER        not null,
  START_TIME       DATE          not null,
  END_TIME         DATE          not null,
  MECHANIC_ID      NUMBER        not null,
  constraint MAINTENANCE_HISTORY_PK
  primary key (ID),
  constraint M_HISTORY_CAR_PLATE_FK
  foreign key (CAR_PLATE) references CAR,
  constraint M_HISTORY_CENTER_ID_FK
  foreign key (CENTER_ID) references CENTER,
  constraint M_HISTORY_CUSTOMER_ID_FK
  foreign key (CUSTOMER_ID) references CUSTOMER,
  constraint M_HISTORY_EMPLOYEE_ID_FK
  foreign key (MECHANIC_ID) references EMPLOYEE
)
/

create sequence CUSTOMER_ID_SEQ
  start with 1005
  nocache
/

create sequence CENTER_ID_SEQ
  start with 3
  nocache
/

create sequence EMPLOYEE_ID_SEQ
  start with 100000000
  nocache
/

create sequence ORDER_ID_SEQ
  start with 12
  nocache
/

create sequence SERVICE_HISTORY_ID
  start with 17
  nocache
/