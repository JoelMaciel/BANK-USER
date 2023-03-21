CREATE TABLE `tb_clients_accounts` (
  `id` varchar(255) NOT NULL,
  `account_id` varchar(255) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_client_accounts` (`client_id`),
  CONSTRAINT `FK_client_accounts` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci