CREATE TABLE `roles` (
  `role_id` varchar(36) NOT NULL,
  `role_name` varchar(30) UNIQUE NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci