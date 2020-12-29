CREATE TABLE hibernate_sequence (
  next_val bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO hibernate_sequence (next_val)
VALUES (1);


CREATE TABLE payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    external_id VARCHAR(50),
    created_at  DATETIME,
    updated_at  DATETIME,
    amount      INT(20),
    currency    VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;