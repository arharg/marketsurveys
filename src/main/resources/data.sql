--
-- dump some data as example
--
--
-- ISO 3166 country codes 
--
INSERT INTO country (code) VALUES ('DE'), ('ES'), ('FR'), ('GB'), ('US');
--
-- conditions
--
INSERT INTO availability_condition (description, id) VALUES 
	('Publicly available', 'PUBLIC'),
	('Available to owner only', 'OWNER_ONLY'),
	('Not available', 'NOT_AVAILABLE'),
	('May be purchased', 'UNDER_PURCHASE')
	;
--
-- subjects
--
INSERT INTO subject_classification (classification_value, description) VALUES 
	(10101501, 'Cats'),
	(10151525, 'Broccoli seeds or seedlings'),
	(95141901, 'Medical unit')
	;
--
-- roles
--
INSERT INTO role (role_id, name) VALUES (1, 'ADMIN'), (2, 'PROVIDER'), (3, 'REQUESTOR');
--
-- users -> partys
--
INSERT INTO party (party_id, name, address, country, password, dtype) VALUES 
	('prov', 'provider', 'provider address, 1', 'ES', '$2a$04$xHgRxak1grN1sIpre7FfOeSA9gbutbWEsTTC7Mc3FTaAKvVJuDvni', 'user'),
	('ad', 'admin', NULL, NULL, '$2a$04$PwaV2LyJk4DWgjQnODFQhesL2zNcG8B/Gjt9zkDSZY6pNaxTe.KZ.','user'),
	('req', 'requestor', 'requestor address, 1', 'FR', '$2a$04$9JAelvqO4OWr5k0g.P7pEOqXSxcOGb9k1jgggmwePCqeSG8xmGR7W', 'user'),
	('crv', 'caravelo', 'barcelona', 'ES', '$2a$04$sLAiCrVbbjPVKoiN6Jqv9eEbo6.u66/BoObiv.VPpe639Fsw4w71S', 'party'),
	('mul', 'multi', 'multi address, 23', 'ES', '$2a$04$6yQjits3wrOTk4/Bqn97xO/qTzJJvuCwP.IlQ/.5OpfVUAtS/Ee7m', 'user')
	;
	
-- user - roles
INSERT INTO user_roles (party_id, role_id) VALUES 
	('ad', 1), 
	('prov', 2), 
	('req', 3),
	('mul', 2),
	('mul', 3)
	;


