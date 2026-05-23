DELETE FROM messages;
DELETE FROM conversations;
DELETE FROM users;

INSERT INTO users (id, email, password, first_name, last_name, role)
VALUES
(1, 'client@test.com', 'password', 'Jean', 'Dupont', 'CUSTOMER'),
(2, 'support@test.com', 'password', 'Alice', 'Martin', 'SUPPORT');

INSERT INTO conversations (id, subject, status, created_at, customer_id)
VALUES
(1, 'Question sur une réservation', 'OPEN', NOW(), 1);

INSERT INTO messages (id, content, sent_at, sender_id, conversation_id)
VALUES
(1, 'Bonjour, j’ai une question sur ma réservation.', NOW(), 1, 1),
(2, 'Bonjour, je suis là pour vous aider.', NOW(), 2, 1);