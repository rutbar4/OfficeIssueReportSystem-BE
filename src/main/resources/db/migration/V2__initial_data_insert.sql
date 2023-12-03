insert into country (ID, country_name)
values ('48a95af7-8b83-4a08-8001-0f865db8ea25', 'Lithuania'),
       ('48a95af7-8b83-4a08-8001-0f865db8ea26', 'United States'),
       ('48a95af7-8b83-4a08-8001-0f865db8ea27', 'Poland');

insert into office (id, office_name, country_id)
values ('48a95af7-8b83-4a08-8001-0f865db8ea28', 'Kaunas', '48a95af7-8b83-4a08-8001-0f865db8ea25'),
       ('48a95af7-8b83-4a08-8001-0f865db8ea29', 'Vilnius', '48a95af7-8b83-4a08-8001-0f865db8ea25'),
       ('48a95af7-8b83-4a08-8001-0f865db8ea30', 'Warsaw', '48a95af7-8b83-4a08-8001-0f865db8ea27'),
       ('48a95af7-8b83-4a08-8001-0f865db8ea31', 'Chicago', '48a95af7-8b83-4a08-8001-0f865db8ea26');

insert into employee (id, full_name, email, password, phone_number, avatar )
values ('d06cb831-9427-41ee-adcc-271f7b02d626', 'Agne Moleikaityte', 'agne@devbridge.com', '$2a$10$cqCBacKxmGCkuSaKfdGEZeXNi8E4vGVOpdjWplv4W2dEo/zD.QZnO', '+370868700100', 'https://upload.wikimedia.org/wikipedia/en/thumb/4/4b/Pok%C3%A9mon_Mew_art.png/220px-Pok%C3%A9mon_Mew_art.png' ),
       ('d06cb831-9427-41ee-adcc-271f7b02d627', 'Sarunas Jurevicius', 'sarunas@devbridge.com', '$2a$10$sPi11IJmih8k7Eh5nGJVEuytg562C1IId..3rIEE6CmosUhQa/Lxq', '+370868700200', 'https://cdn.pixabay.com/photo/2021/12/26/17/31/pokemon-6895600_960_720.png' ),
       ('d06cb831-9427-41ee-adcc-271f7b02d628', 'Lukas Zareckas', 'lukas@devbridge.com', '$2a$10$8RT7hWdqLy/CYakYaIGhW.XllngTry/r2DobYGujIpj0AO69cdmqe', '+370868700300', 'https://upload.wikimedia.org/wikipedia/en/1/1f/Pok%C3%A9mon_Charizard_art.png' );

insert into address (id, street, post_code, state_province, city, country_id, employee_id)
values ('25a95af7-8b83-4a08-8001-0f865db8ea31', 'Savanoriu pr. 1-1', 'LT44203', 'Kauno raj.', 'Kaunas', '48a95af7-8b83-4a08-8001-0f865db8ea25', 'd06cb831-9427-41ee-adcc-271f7b02d626'),
       ('25a95af7-8b83-4a08-8001-0f865db8ea32', 'Vilniaus g. 124-7', 'LT45206', 'Kauno raj.', 'Kaunas', '48a95af7-8b83-4a08-8001-0f865db8ea25', 'd06cb831-9427-41ee-adcc-271f7b02d627'),
       ('25a95af7-8b83-4a08-8001-0f865db8ea33', 'Kreves pr. 89a', 'LT55307', 'Kauno raj.', 'Kaunas', '48a95af7-8b83-4a08-8001-0f865db8ea25', 'd06cb831-9427-41ee-adcc-271f7b02d628');

insert into issue (id, issue_name, issue_status, start_time, rating, description, employee_id, office_id)
values ('abdee4f9-5763-4afc-85ed-98b2fdefb35d', 'Beer is not cold enough','open', '2023-09-20 13:23:02.933','9', 'During the lunch time the beer in the kitchen is not cold enough', 'd06cb831-9427-41ee-adcc-271f7b02d627', '48a95af7-8b83-4a08-8001-0f865db8ea28'),
       ('abdee4f9-5763-4afc-85ed-98b2fdefb39d', 'No special parking places for the motorcycle', 'in progres', '2023-05-20 09:23:02.933', '3', 'There is no special parking place for motorcycles, so we are not saving parking place parking motorcycles in the parking place designet for a vechicle', 'd06cb831-9427-41ee-adcc-271f7b02d626', '48a95af7-8b83-4a08-8001-0f865db8ea28'),
       ('abdee4f9-5763-4afc-85ed-98b2fdefb46d', 'Driving simulator does not have enough tracks', 'in progres', '2023-07-21 16:23:02.933', '5', 'My team feels lack of tracks in the driving simualtor', 'd06cb831-9427-41ee-adcc-271f7b02d628', '48a95af7-8b83-4a08-8001-0f865db8ea28');

insert into "comment" (id, text, time, likes, issue_id, employee_id)
values('d96e1892-3019-4304-bd5a-1ef750be3aca', 'Agnes bike is taking two parking places and itis allways difficult to get out of the car if parked next to her', '2023-05-20 09:35:02.933','2', 'abdee4f9-5763-4afc-85ed-98b2fdefb39d', 'd06cb831-9427-41ee-adcc-271f7b02d628'),
      ('d96e1892-3019-4304-bd5a-1ef750be4aca', 'I dont see any issue at all', '2023-05-20 09:37:02.933', '0', 'abdee4f9-5763-4afc-85ed-98b2fdefb39d', 'd06cb831-9427-41ee-adcc-271f7b02d627'),
      ('d96e1892-3019-4304-bd5a-1ef750be5aca', 'I totaly agree', '2023-07-22 08:23:02.933', '1', 'abdee4f9-5763-4afc-85ed-98b2fdefb46d', 'd06cb831-9427-41ee-adcc-271f7b02d626');

insert into picture (id, link, issue_id, employee_id)
values ('bb2db642-6681-4e96-bff9-d226d6384efb', 'https://qph.cf2.quoracdn.net/main-qimg-c14ac71e4ef826f76526863971ef539e-lq', 'abdee4f9-5763-4afc-85ed-98b2fdefb39d', 'd06cb831-9427-41ee-adcc-271f7b02d626');

