ALTER TABLE `bank_client`.`client`
ADD COLUMN `client_type` VARCHAR(15) NOT NULL AFTER `phone_number`;