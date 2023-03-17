CREATE TABLE `tb_clients_accounts` (
  `id` char(36) NOT NULL,
  `account_id` char(36) NOT NULL,
  `client_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3rgtdx4tyht7xaxwp41ov32ch` (`client_id`),
  CONSTRAINT `FK3rgtdx4tyht7xaxwp41ov32ch` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3