CREATE TABLE `users_roles` (
    `user_id` varchar(36) NOT NULL,
    `role_id`  varchar(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (`user_id`) REFERENCES user(`user_id`),
    FOREIGN KEY (`role_id`) REFERENCES roles(`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


