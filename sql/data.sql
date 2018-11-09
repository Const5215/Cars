INSERT INTO CENTER (ID, NAME, TELEPHONE, ADDRESS)
VALUES (1, 'Downtown Auto Care', '800-899-9999', '437 Fayetteville St., Raleigh, NC 27601');
INSERT INTO CENTER (ID, NAME, TELEPHONE, ADDRESS)
VALUES (2, 'Express Auto Shop', '704-333-1555', '201 N Tryon St, Charlotte, NC 28202');

INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (183683346,
        '12345678',
        'Anthony Freeman',
        'anfreeman@acme.com',
        '563-496-7912',
        '1188 Summit Street, Raleigh, NC 27627');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (187658163,
        '12345678',
        'Rickie Henderson',
        'rihenderson@acme.com',
        '931-539-1294',
        '1963 Chenoweth Drive, Charlotte, NC 28228');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (291056276,
        '12345678',
        'Roderick Phillips',
        'rophillips@acme.com',
        '901-777-0492',
        '1133 Burton Avenue, Charlotte, NC 28201');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (310773348,
        '12345678',
        'James Rivera',
        'jarivera@acme.com',
        '903-967-1809',
        '908 Alpha Avenue, Charlotte, NC 28130');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (401671897,
        '12345678',
        'Charles Pudilo',
        'chpudilo@acme.com',
        '641-752-9403',
        '196 Park Boulevard, Charlotte, NC 28222');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (557279280,
        '12345678',
        'Jacob Gloss',
        'jagloss@acme.com',
        '413-335-9523',
        '2014 Leverton Cove Road, Raleigh, NC 27560');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (557279281,
        '12345678',
        'Eric Fowler',
        'erfowler@acme.com',
        '760-457-9846',
        '1114 Fincham Road, Raleigh, NC 27545');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (557279282,
        '12345678',
        'Roland Richmond',
        'rorichmond@acme.com',
        '829-843-3730',
        '1951 Little Acres Lane, Raleigh, NC 27513');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (557279283,
        '12345678',
        'Peter Fitzpatrick',
        'pefitzpatrick@acme.com',
        '315-485-1152',
        '4738 Buckhannan Avenue, Raleigh, NC 27625');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (590424694,
        '12345678',
        'Dustin Esparza',
        'duesparza@acme.com',
        '956-444-0948',
        '3510 Hemlock Lane, Charlotte, NC 28202');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (634622236,
        '12345678',
        'Willis Martin',
        'wimartin@acme.com',
        '213-988-2011',
        '465 Aviation Way, Raleigh, NC 27601');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (911639633,
        '12345678',
        'Dena Holmes',
        'deholmes@acme.com',
        '972-353-3691',
        '1382 Whispering Pines Circle, Charlotte, NC 28205');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (950932130,
        '12345678',
        'Larry Cohen',
        'lacohen@acme.com',
        '316-978-5494',
        '1979 Henry Street, Raleigh, NC 27606');
INSERT INTO EMPLOYEE (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (983204784,
        '12345678',
        'Willis Morton',
        'wimorton@acme.com',
        '757-886-6330',
        '404 Tenmile, Charlotte, NC 28134');

INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (911639633, 2, 2, 9000, TO_DATE('2010-01-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (310773348, 2, 3, 40, TO_DATE('2013-10-29 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (183683346, 1, 3, 40, TO_DATE('2015-02-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (557279281, 1, 3, 35, TO_DATE('2016-05-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (401671897, 2, 3, 40, TO_DATE('2016-07-06 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (950932130, 1, 1, 11000, TO_DATE('2016-08-15 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (590424694, 2, 3, 35, TO_DATE('2017-01-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (187658163, 2, 3, 30, TO_DATE('2017-03-29 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (557279283, 1, 3, 34, TO_DATE('2017-04-12 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (291056276, 2, 1, 12000, TO_DATE('2017-04-15 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (634622236, 1, 2, 8000, TO_DATE('2017-12-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (557279282, 1, 3, 30, TO_DATE('2017-12-28 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (557279280, 1, 3, 30, TO_DATE('2018-07-29 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO EMPLOYMENT (EMPLOYEE_ID, CENTER_ID, POSITION, COMPENSATION, START_DATE)
VALUES (983204784, 2, 3, 30, TO_DATE('2018-10-13 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO DISTRIBUTOR (ID, NAME)
VALUES (1, 'D1');
INSERT INTO DISTRIBUTOR (ID, NAME)
VALUES (2, 'D2');

INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (1, 'Air Filter', 'Toyota', 11, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (2, 'Axel Rod', 'Toyota', 123, 2, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (3, 'Battery', 'Toyota', 63, 6, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (4, 'Brake Fluid', 'Toyota', 24, 0, 4, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (5, 'Brake Shoes', 'Toyota', 62, 2, 5, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (6, 'Camshaft', 'Toyota', 1428, 3, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (7, 'Catalytic Converter', 'Toyota', 801, 1, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (8, 'Coolant', 'Toyota', 63, 0, 4, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (9, 'Drive Belt', 'Toyota', 528, 1, 2, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (10, 'Engine Oil', 'Toyota', 63, 0, 2, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (11, 'Gears', 'Toyota', 1, 1, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (12, 'Light Assembly', 'Toyota', 617, 2, 3, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (13, 'Oil Filter', 'Toyota', 36, 0, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (14, 'Piston', 'Toyota', 1256, 1, 2, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (15, 'Power Steering Fluid', 'Toyota', 17, 0, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (16, 'Spark Plug', 'Toyota', 64, 2, 5, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (17, 'Suspension Fluid', 'Toyota', 70, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (18, 'Valve', 'Toyota', 1338, 1, 3, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (19, 'Wiper Fluid', 'Toyota', 28, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (20, 'Air Filter', 'Honda', 59, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (21, 'Axel Rod', 'Honda', 141, 2, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (22, 'Battery', 'Honda', 79, 3, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (23, 'Brake Fluid', 'Honda', 75, 0, 4, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (24, 'Brake Shoes', 'Honda', 41, 3, 5, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (25, 'Camshaft', 'Honda', 511, 2, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (26, 'Catalytic Converter', 'Honda', 716, 2, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (27, 'Coolant', 'Honda', 10, 0, 4, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (28, 'Drive Belt', 'Honda', 1443, 1, 2, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (29, 'Engine Oil', 'Honda', 27, 0, 2, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (30, 'Gears', 'Honda', 1344, 3, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (31, 'Light Assembly', 'Honda', 1342, 2, 3, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (32, 'Oil Filter', 'Honda', 42, 0, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (33, 'Piston', 'Honda', 1145, 1, 2, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (34, 'Power Steering Fluid', 'Honda', 24, 0, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (35, 'Spark Plug', 'Honda', 50, 2, 5, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (36, 'Suspension Fluid', 'Honda', 77, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (37, 'Valve', 'Honda', 1261, 3, 3, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (38, 'Wiper Fluid', 'Honda', 56, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (39, 'Air Filter', 'Nissan', 61, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (40, 'Axel Rod', 'Nissan', 241, 3, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (41, 'Battery', 'Nissan', 14, 3, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (42, 'Brake Fluid', 'Nissan', 16, 0, 4, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (43, 'Brake Shoes', 'Nissan', 47, 1, 5, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (44, 'Camshaft', 'Nissan', 1295, 2, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (45, 'Catalytic Converter', 'Nissan', 589, 1, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (46, 'Coolant', 'Nissan', 39, 0, 4, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (47, 'Drive Belt', 'Nissan', 1084, 3, 2, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (48, 'Engine Oil', 'Nissan', 14, 0, 2, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (49, 'Gears', 'Nissan', 1176, 3, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (50, 'Light Assembly', 'Nissan', 1367, 2, 3, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (51, 'Oil Filter', 'Nissan', 61, 0, 4, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (52, 'Piston', 'Nissan', 1341, 3, 2, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (53, 'Power Steering Fluid', 'Nissan', 20, 0, 5, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (54, 'Spark Plug', 'Nissan', 11, 1, 5, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (55, 'Suspension Fluid', 'Nissan', 32, 0, 3, 2);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (56, 'Valve', 'Nissan', 1013, 1, 3, 1);
INSERT INTO PART (ID, NAME, MAKE, UNIT_PRICE, WARRANTY, DELIVERY_WINDOW, DISTRIBUTOR_ID)
VALUES (57, 'Wiper Fluid', 'Nissan', 31, 0, 3, 2);

INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 1, 13, 13, 2, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 2, 18, 18, 5, 7);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 3, 10, 10, 2, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 4, 4, 4, 4, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 5, 18, 18, 4, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 6, 12, 12, 2, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 7, 11, 11, 1, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 8, 13, 13, 2, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 9, 15, 15, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 10, 5, 5, 5, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 11, 8, 8, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 12, 2, 2, 1, 8);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 13, 1, 1, 1, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 14, 15, 15, 4, 2);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 15, 2, 2, 1, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 16, 15, 15, 4, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 17, 3, 3, 1, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 18, 12, 12, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 19, 12, 12, 5, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 20, 43, 43, 20, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 21, 8, 8, 5, 7);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 22, 20, 20, 20, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 23, 14, 14, 4, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 24, 8, 8, 4, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 25, 22, 22, 20, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 26, 31, 31, 10, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 27, 23, 23, 23, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 28, 35, 35, 30, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 29, 15, 15, 5, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 30, 18, 18, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 31, 12, 12, 10, 8);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 32, 11, 11, 4, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 33, 55, 55, 48, 2);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 34, 12, 12, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 35, 45, 45, 42, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 36, 13, 13, 11, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 37, 12, 12, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 38, 12, 12, 5, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 39, 33, 33, 20, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 40, 38, 38, 5, 7);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 41, 30, 30, 20, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 42, 34, 34, 4, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 43, 38, 38, 4, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 44, 32, 32, 20, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 45, 31, 31, 10, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 46, 33, 33, 23, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 47, 35, 35, 30, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 48, 35, 35, 5, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 49, 38, 38, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 50, 32, 32, 10, 8);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 51, 31, 31, 4, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 52, 35, 35, 18, 2);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 53, 32, 32, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 54, 35, 35, 12, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 55, 33, 33, 11, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 56, 32, 32, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (1, 57, 12, 12, 5, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 1, 26, 26, 5, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 2, 27, 27, 6, 10);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 3, 24, 24, 2, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 4, 25, 25, 3, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 5, 20, 20, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 6, 22, 22, 2, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 7, 29, 29, 4, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 8, 21, 21, 2, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 9, 20, 20, 3, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 10, 26, 26, 20, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 11, 20, 20, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 12, 28, 28, 6, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 13, 21, 21, 4, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 14, 21, 21, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 15, 27, 27, 6, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 16, 20, 20, 2, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 17, 25, 25, 6, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 18, 11, 11, 3, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 19, 16, 16, 13, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 20, 46, 46, 35, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 21, 7, 7, 6, 10);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 22, 64, 64, 52, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 23, 15, 15, 3, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 24, 20, 20, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 25, 42, 42, 42, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 26, 29, 29, 24, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 27, 21, 21, 12, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 28, 30, 30, 23, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 29, 26, 26, 10, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 30, 70, 70, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 31, 8, 8, 6, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 32, 31, 31, 24, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 33, 81, 81, 73, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 34, 17, 17, 6, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 35, 50, 50, 32, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 36, 15, 15, 6, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 37, 11, 11, 3, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 38, 16, 16, 13, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 39, 16, 16, 5, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 40, 17, 17, 6, 10);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 41, 14, 14, 5, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 42, 15, 15, 3, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 43, 10, 10, 3, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 44, 12, 12, 4, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 45, 19, 19, 4, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 46, 11, 11, 2, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 47, 10, 10, 3, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 48, 16, 16, 10, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 49, 10, 10, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 50, 18, 18, 6, 3);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 51, 11, 11, 4, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 52, 11, 11, 7, 5);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 53, 17, 17, 6, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 54, 10, 10, 2, 4);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 55, 15, 15, 6, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 56, 11, 11, 3, 6);
INSERT INTO INVENTORY (CENTER_ID,
                       PART_ID,
                       CURRENT_QUANTITY,
                       AVAILABLE_QUANTITY,
                       MIN_THRESHOLD,
                       MIN_ORDER_QUANTITY)
VALUES (2, 57, 16, 16, 13, 5);

INSERT INTO CAR_MODEL (ID, MAKE, MODEL)
VALUES (1, 'Toyota', 'Corolla');
INSERT INTO CAR_MODEL (ID, MAKE, MODEL)
VALUES (2, 'Toyota', 'Prius');
INSERT INTO CAR_MODEL (ID, MAKE, MODEL)
VALUES (3, 'Honda', 'Civic');
INSERT INTO CAR_MODEL (ID, MAKE, MODEL)
VALUES (4, 'Honda', 'Accord');
INSERT INTO CAR_MODEL (ID, MAKE, MODEL)
VALUES (5, 'Nissan', 'Altima');
INSERT INTO CAR_MODEL (ID, MAKE, MODEL)
VALUES (6, 'Nissan', 'Rogue');

INSERT INTO CUSTOMER (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (1001,
        '123',
        'Tom Cruise',
        'ethanhunt@gmail.com',
        '123-456-7890',
        '203 Park St, Raleigh, NC 27603');
INSERT INTO CUSTOMER (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (1002,
        '123',
        'Robert Downey Jr.',
        'jarvis@gmail.com',
        '998-987-7791',
        '12A High St, Raleigh, NC 27605');
INSERT INTO CUSTOMER (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (1003,
        '123',
        'Taylor Swift',
        'lovestory@gmail.com',
        '817-982-7199',
        '2A 3rd Ave, Charlotte, NC 28134');
INSERT INTO CUSTOMER (ID, PASSWORD, NAME, EMAIL, PHONE, ADDRESS)
VALUES (1004,
        '123',
        'Serena Williams',
        'venus@gmail.com',
        '871-479-1879',
        '90 Gorman St, Charlotte, NC 28201');

INSERT INTO CAR (PLATE, CUSTOMER_ID, CAR_MODEL_ID, YEAR, PURCHASE_DATE)
VALUES ('AHS-3132', 1001, 2, 2007, TO_DATE('2011-01-02 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO CAR (PLATE, CUSTOMER_ID, CAR_MODEL_ID, YEAR, PURCHASE_DATE)
VALUES ('XYZ-5643', 1001, 3, 2009, TO_DATE('2009-12-24 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO CAR (PLATE, CUSTOMER_ID, CAR_MODEL_ID, YEAR, PURCHASE_DATE)
VALUES ('IRM-1212', 1002, 5, 2001, TO_DATE('2002-09-07 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO CAR (PLATE, CUSTOMER_ID, CAR_MODEL_ID, YEAR, PURCHASE_DATE)
VALUES ('DEL-8888', 1003, 6, 2014, TO_DATE('2016-05-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO CAR (PLATE, CUSTOMER_ID, CAR_MODEL_ID, YEAR, PURCHASE_DATE)
VALUES ('TSW-3462', 1003, 4, 2015, TO_DATE('2015-12-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO CAR (PLATE, CUSTOMER_ID, CAR_MODEL_ID, YEAR, PURCHASE_DATE)
VALUES ('P11-212A', 1004, 4, 2009, TO_DATE('2010-04-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO CAR (PLATE, CUSTOMER_ID, CAR_MODEL_ID, YEAR, PURCHASE_DATE)
VALUES ('WIM-BLE5', 1004, 2, 2013, TO_DATE('2014-03-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'));

INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (1, 'Air filter change', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (2, 'Battery replacement', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (3, 'Brake check', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (4, 'Brake repair', 0.5, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (5, 'Camshaft replacement', 1, 1);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (6, 'Catalytic converter replacement', 1, 1);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (7, 'Coolant recycle', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (8, 'Drive belt replacement', 1, 1);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (9, 'Engine oil change', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (10, 'Gearbox repair', 1, 1);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (11, 'Headlights replacement', 0.5, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (12, 'Oil filter change', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (13, 'Piston replacement', 1, 1);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (14, 'Power steering check', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (15, 'Spark plugs replacement', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (16, 'Suspension check', 0.25, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (17, 'Tail lights replacement', 0.5, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (18, 'Turn lights replacement', 0.5, 0);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (19, 'Valve replacement', 1, 1);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (20, 'Wheel alignment', 1, 1);
INSERT INTO BASIC_SERVICE (ID, NAME, LABOR_HOUR, CHARGE_RATE)
VALUES (21, 'Wiper check', 0.25, 0);

INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (1, 1, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (2, 3, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (3, 4, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (4, 5, 1, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (5, 6, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (6, 7, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (7, 8, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (8, 9, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (9, 10, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (10, 11, 1, 6);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (11, 12, 1, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (12, 13, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (13, 14, 1, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (14, 15, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (15, 16, 1, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (16, 17, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (17, 12, 1, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (18, 12, 1, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (19, 18, 1, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (20, 2, 1, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (21, 19, 1, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (1, 1, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (2, 3, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (3, 4, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (4, 5, 2, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (5, 6, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (6, 7, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (7, 8, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (8, 9, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (9, 10, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (10, 11, 2, 6);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (11, 12, 2, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (12, 13, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (13, 14, 2, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (14, 15, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (15, 16, 2, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (16, 17, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (17, 12, 2, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (18, 12, 2, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (19, 18, 2, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (20, 2, 2, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (21, 19, 2, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (1, 20, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (2, 22, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (3, 23, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (4, 24, 3, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (5, 25, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (6, 26, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (7, 27, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (8, 28, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (9, 29, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (10, 30, 3, 12);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (11, 31, 3, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (12, 32, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (13, 33, 3, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (14, 34, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (15, 35, 3, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (16, 36, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (17, 31, 3, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (18, 31, 3, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (19, 37, 3, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (20, 21, 3, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (21, 38, 3, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (1, 20, 4, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (2, 22, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (3, 23, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (4, 24, 4, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (5, 25, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (6, 26, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (7, 27, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (8, 28, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (9, 29, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (10, 30, 4, 7);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (11, 31, 4, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (12, 32, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (13, 33, 4, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (14, 34, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (15, 35, 4, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (16, 36, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (17, 31, 4, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (18, 31, 4, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (19, 37, 4, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (20, 21, 4, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (21, 38, 4, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (1, 39, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (2, 41, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (3, 42, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (4, 43, 5, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (5, 44, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (6, 45, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (7, 46, 5, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (8, 47, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (9, 48, 5, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (10, 49, 5, 7);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (11, 50, 5, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (12, 51, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (13, 52, 5, 6);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (14, 53, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (15, 54, 5, 6);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (16, 55, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (17, 50, 5, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (18, 50, 5, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (19, 56, 5, 6);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (20, 40, 5, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (21, 57, 5, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (1, 39, 6, 3);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (2, 41, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (3, 42, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (4, 43, 6, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (5, 44, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (6, 45, 6, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (7, 46, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (8, 47, 6, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (9, 48, 6, 3);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (10, 49, 6, 9);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (11, 50, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (12, 51, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (13, 52, 6, 8);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (14, 53, 6, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (15, 54, 6, 8);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (16, 55, 6, 1);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (17, 50, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (18, 50, 6, 4);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (19, 56, 6, 8);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (20, 40, 6, 2);
INSERT INTO BASIC_SERVICE_PART (BASIC_SERVICE_ID, PART_ID, CAR_MODEL_ID, QUANTITY)
VALUES (21, 57, 6, 1);

INSERT INTO DIAGNOSIS (ID, PROBLEM, ISSUE, FEE)
VALUES (1, 'Engine knock', 'Timing issue', 75);
INSERT INTO DIAGNOSIS (ID, PROBLEM, ISSUE, FEE)
VALUES (2, 'Car drifts in a particular direction', 'Wheel alignment issue', 50);
INSERT INTO DIAGNOSIS (ID, PROBLEM, ISSUE, FEE)
VALUES (3, 'Battery does not hold charge', 'Battery needs replacement', 25);
INSERT INTO DIAGNOSIS (ID, PROBLEM, ISSUE, FEE)
VALUES (4, 'Black/unclean exhaust', 'Bad catalytic converter and filters', 75);
INSERT INTO DIAGNOSIS (ID, PROBLEM, ISSUE, FEE)
VALUES (5, 'A/C-Heater not working', 'Drive belt damaged, coolant not enough, weak battery', 50);
INSERT INTO DIAGNOSIS (ID, PROBLEM, ISSUE, FEE)
VALUES (6, 'Headlamps/Tail lamps not working', 'Light assembly damaged', 30);
INSERT INTO DIAGNOSIS (ID, PROBLEM, ISSUE, FEE)
VALUES (7, 'Check engine light', 'Gearbox and torque converter issue', 100);

INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (1, 0, 5000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (1, 1, 25000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (1, 2, 45000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (2, 0, 10000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (2, 1, 28000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (2, 2, 58000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (3, 0, 14000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (3, 1, 29000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (3, 2, 44000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (4, 0, 15000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (4, 1, 37000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (4, 2, 67000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (5, 0, 10000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (5, 1, 25000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (5, 2, 50000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (6, 0, 10000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (6, 1, 37000);
INSERT INTO MAINTENANCE (CAR_MODEL_ID, MAINTENANCE_TYPE, MILEAGE)
VALUES (6, 2, 70000);

INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 0, 7);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 0, 9);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 1, 1);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 1, 3);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 1, 12);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 1, 15);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 1, 21);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 2, 4);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 2, 14);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (1, 2, 16);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 0, 7);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 0, 9);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 0, 12);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 1, 1);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 1, 2);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 1, 3);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 1, 15);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 1, 21);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 2, 4);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 2, 14);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (2, 2, 16);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 0, 3);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 2, 16);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 2, 14);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 1, 21);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 1, 15);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 1, 12);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 1, 4);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 1, 1);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 0, 9);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (3, 0, 7);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 0, 1);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 0, 7);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 0, 9);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 0, 12);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 1, 3);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 1, 15);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 1, 21);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 2, 4);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 2, 14);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (4, 2, 16);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 0, 1);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 0, 7);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 0, 9);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 0, 12);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 1, 3);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 1, 21);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 2, 4);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 2, 14);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 2, 15);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (5, 2, 16);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 0, 1);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 0, 3);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 0, 7);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 0, 9);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 0, 12);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 0, 14);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 1, 15);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 1, 16);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 1, 21);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 2, 2);
INSERT INTO MAINTENANCE_DETAIL (CAR_MODEL_ID, MAINTENANCE_TYPE, BASIC_SERVICE_ID)
VALUES (6, 2, 4);

INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (1, 5);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (1, 8);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (1, 15);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (1, 19);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (2, 20);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (3, 2);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (4, 1);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (4, 6);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (4, 12);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (5, 2);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (5, 7);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (5, 8);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (6, 11);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (6, 17);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (6, 18);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (7, 5);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (7, 10);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (7, 13);
INSERT INTO REPAIR (DIAGNOSIS_ID, BASIC_SERVICE_ID)
VALUES (7, 19);

INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (1,
        3,
        5,
        315,
        1,
        1,
        TO_DATE('2014-06-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2014-06-13 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2014-06-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        1);
INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (2,
        38,
        5,
        280,
        2,
        1,
        TO_DATE('2015-09-16 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2015-09-21 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2015-09-21 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0);
INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (6,
        44,
        5,
        6475,
        1,
        1,
        TO_DATE('2018-10-26 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-11-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-11-05 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        0);
INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (7,
        21,
        12,
        1692,
        1,
        2,
        TO_DATE('2018-11-07 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-11-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        null,
        0);
INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (8,
        25,
        6,
        3066,
        1,
        2,
        TO_DATE('2018-11-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-11-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        null,
        0);
INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (9,
        32,
        5,
        210,
        1,
        2,
        TO_DATE('2018-11-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-11-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        null,
        0);
INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (10,
        23,
        5,
        375,
        2,
        2,
        TO_DATE('2018-11-08 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-11-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        null,
        0);
INSERT INTO EXTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            DISTRIBUTOR_ID,
                            CENTER_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (11,
        38,
        7,
        392,
        2,
        2,
        TO_DATE('2018-11-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-11-14 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        null,
        0);

INSERT INTO INTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            FROM_ID,
                            TO_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (3,
        30,
        5,
        6720,
        1,
        2,
        TO_DATE('2016-02-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2016-02-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2016-02-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        1);
INSERT INTO INTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            FROM_ID,
                            TO_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (4,
        46,
        4,
        195,
        2,
        1,
        TO_DATE('2016-08-09 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2016-08-10 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2016-08-11 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        1);
INSERT INTO INTERNAL_ORDER (ID,
                            PART_ID,
                            QUANTITY,
                            TOTAL,
                            FROM_ID,
                            TO_ID,
                            ORDER_DATE,
                            EXPECTED_DELIVERY_DATE,
                            ACTUAL_DELIVERY_DATE,
                            STATUS)
VALUES (5,
        1,
        6,
        66,
        1,
        2,
        TO_DATE('2018-10-04 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-10-05 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-10-05 00:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        1);

INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (1,
        1004,
        'P11-212A',
        2,
        0,
        38452,
        TO_DATE('2014-06-15 08:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2014-06-15 09:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        310773348);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (5,
        1004,
        'WIM-BLE5',
        2,
        0,
        19876,
        TO_DATE('2016-11-11 08:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2016-11-11 09:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        310773348);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (6,
        1002,
        'IRM-1212',
        1,
        1,
        175452,
        TO_DATE('2017-01-20 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2017-01-20 11:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279281);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (7,
        1004,
        'P11-212A',
        2,
        1,
        60452,
        TO_DATE('2017-09-01 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2017-09-01 10:45:00', 'YYYY-MM-DD HH24:MI:SS'),
        590424694);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (8,
        1001,
        'XYZ-5643',
        1,
        0,
        60452,
        TO_DATE('2017-10-15 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2017-10-15 08:45:00', 'YYYY-MM-DD HH24:MI:SS'),
        183683346);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (9,
        1002,
        'IRM-1212',
        1,
        2,
        200452,
        TO_DATE('2017-12-10 09:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2017-12-10 12:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279281);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (10,
        1001,
        'AHS-3132',
        1,
        0,
        42000,
        TO_DATE('2018-01-28 12:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-01-28 12:45:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279283);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (11,
        1002,
        'IRM-1212',
        1,
        0,
        210452,
        TO_DATE('2018-02-11 08:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-02-11 09:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279281);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (12,
        1003,
        'DEL-8888',
        2,
        0,
        31209,
        TO_DATE('2018-02-11 08:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-02-11 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        187658163);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (13,
        1001,
        'XYZ-5643',
        1,
        1,
        75452,
        TO_DATE('2018-02-25 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-02-25 11:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279281);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (14,
        1001,
        'AHS-3132',
        1,
        1,
        60000,
        TO_DATE('2018-05-15 10:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-05-15 12:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279283);
INSERT INTO MAINTENANCE_HISTORY (ID,
                                 CUSTOMER_ID,
                                 CAR_PLATE,
                                 CENTER_ID,
                                 MAINTENANCE_TYPE,
                                 MILEAGE,
                                 START_TIME,
                                 END_TIME,
                                 MECHANIC_ID)
VALUES (16,
        1001,
        'XYZ-5643',
        1,
        2,
        90452,
        TO_DATE('2018-09-10 10:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-09-10 12:45:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279280);

INSERT INTO REPAIR_HISTORY (ID,
                            CUSTOMER_ID,
                            CAR_PLATE,
                            CENTER_ID,
                            DIAGNOSIS_ID,
                            MILEAGE,
                            START_TIME,
                            END_TIME,
                            MECHANIC_ID)
VALUES (2,
        1004,
        'WIM-BLE5',
        2,
        1,
        15000,
        TO_DATE('2015-09-30 11:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2015-09-30 14:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        401671897);
INSERT INTO REPAIR_HISTORY (ID,
                            CUSTOMER_ID,
                            CAR_PLATE,
                            CENTER_ID,
                            DIAGNOSIS_ID,
                            MILEAGE,
                            START_TIME,
                            END_TIME,
                            MECHANIC_ID)
VALUES (3,
        1004,
        'WIM-BLE5',
        2,
        5,
        10000,
        TO_DATE('2016-01-02 14:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2016-01-02 15:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        401671897);
INSERT INTO REPAIR_HISTORY (ID,
                            CUSTOMER_ID,
                            CAR_PLATE,
                            CENTER_ID,
                            DIAGNOSIS_ID,
                            MILEAGE,
                            START_TIME,
                            END_TIME,
                            MECHANIC_ID)
VALUES (4,
        1003,
        'DEL-8888',
        2,
        6,
        30000,
        TO_DATE('2016-11-05 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2016-11-05 10:30:00', 'YYYY-MM-DD HH24:MI:SS'),
        401671897);
INSERT INTO REPAIR_HISTORY (ID,
                            CUSTOMER_ID,
                            CAR_PLATE,
                            CENTER_ID,
                            DIAGNOSIS_ID,
                            MILEAGE,
                            START_TIME,
                            END_TIME,
                            MECHANIC_ID)
VALUES (15,
        1001,
        'AHS-3132',
        1,
        3,
        65452,
        TO_DATE('2018-08-06 08:00:00', 'YYYY-MM-DD HH24:MI:SS'),
        TO_DATE('2018-08-06 08:15:00', 'YYYY-MM-DD HH24:MI:SS'),
        557279282);
