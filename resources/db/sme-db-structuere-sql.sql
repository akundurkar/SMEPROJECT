
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bml_development
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sme_development` ;

-- -----------------------------------------------------
-- Schema sme_development
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sme_development` DEFAULT CHARACTER SET utf8 ;
USE `sme_development` ;

-- -----------------------------------------------------
-- Table `sme_development`.`business_category_masterdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`business_category_masterdata`(     
	`id` INT(11) NOT NULL ,     
	`business_category_type` VARCHAR(256) ,     
PRIMARY KEY (`id`))
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`business_type_masterdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`business_type_masterdata`(     
	`id` INT(11) NOT NULL ,     
	`business_type` VARCHAR(256) ,
	`business_category_id` INT(11) NULL,
PRIMARY KEY (`id`),
INDEX `fk_user_business_category_idx1` (`business_category_id` ASC),
CONSTRAINT `fk_user_business_category_id`
	FOREIGN KEY (`business_category_id`)
	REFERENCES `sme_development`.`business_category_masterdata` (`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`organisation`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`organisation` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `organisation_name` VARCHAR(45),
    `adress1` VARCHAR(45) ,
    `adress2` VARCHAR(45) NULL,
    `city` VARCHAR(45),
    `state` VARCHAR(45) NULL,
    `phone` VARCHAR(45),
    `website` VARCHAR(45) NULL,
    `pincode` VARCHAR(6) NULL,
    `detail_info` VARCHAR(1024) NULL,
    `business_type_id` INT NULL, 
PRIMARY KEY (`id`),
INDEX `fk_user_business_type_idx1` (`business_type_id` ASC),
CONSTRAINT `fk_user_business_type_id`
    FOREIGN KEY (`business_type_id`)
    REFERENCES `sme_development`.`business_type_masterdata` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`role`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NULL,
PRIMARY KEY (`id`))
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`application_user`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`application_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `organisation_id` INT NULL,
  `password` VARCHAR(45),
  `reset_password` VARCHAR(45) NULL,
  `reset_pass_validity` DATETIME NULL,
  `email_Id` VARCHAR(45),
  `login_Id` VARCHAR(45),
  `mobile` VARCHAR(45)  NULL,
  `lastLogin` DATETIME, 
  `individual` INT NULL,
  `confirmation_token` VARCHAR(255) NULL,
  `user_status` INT NULL,
  `user_permission` INT NULL,
  `profile_completion` INT NULL,
  `uniqueId` VARCHAR(36) NULL,
  `firebaseId` VARCHAR(250) NULL,
  `start_date` DATE NULL,
  `finish_date` DATE NULL,
  `address1` VARCHAR(45),
  `address2` VARCHAR(45),
  `city` VARCHAR(45),
  `state` VARCHAR(45) NULL,
  `pincode` VARCHAR(6) NULL,
PRIMARY KEY (`id`),
UNIQUE (login_Id),
INDEX `fk_user_organisation1_idx` (`organisation_id` ASC),
CONSTRAINT `fk_user_organisation1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`client`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisation_id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `email_Id` VARCHAR(45) NULL,
  `mobile` VARCHAR(45)  NULL,
  `telephone` VARCHAR(45)  NULL,
  `notification_subscription_type` INT(2)  NULL,
  `gender` VARCHAR(1)  NULL,
  `dob` DATE DEFAULT NULL,
  `adress1` VARCHAR(45) NULL,
  `adress2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `country` VARCHAR(30) NULL,
  `pincode` VARCHAR(6) NULL,
  `confirmation_token` VARCHAR(255) NULL,
  `profile_completion` INT NULL,
  `firebaseId` VARCHAR(250) NULL,
  `marital_status` BOOLEAN NULL, 
PRIMARY KEY (`id`),
INDEX `fk_client_organisation1_idx` (`organisation_id` ASC),
CONSTRAINT `fk_client_organisation1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`application_session`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`application_session` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `session_id` VARCHAR(45) NULL,
  `lastLogin` DATETIME NOT NULL,
  `user_id` INT NULL,
PRIMARY KEY (`id`),
INDEX `fk_application_session1_idx` (`user_id` ASC),
CONSTRAINT `fk_application_session1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sme_development`.`application_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`user_has_role`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`user_has_role` (
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
PRIMARY KEY (`user_id`, `role_id`),
INDEX `fk_user_has_role_role1_idx` (`role_id` ASC),
INDEX `fk_user_has_role_user1_idx` (`user_id` ASC),
CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `sme_development`.`application_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `sme_development`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`applicationoauthsetting`
-- -----------------------------------------------------

/*Added DDL scripts for OAuth security*/
CREATE TABLE IF NOT EXISTS `sme_development`.`applicationoauthsetting` (                 
   `Id` bigint(20) NOT NULL AUTO_INCREMENT,               
   `Consumer_key` varchar(255) DEFAULT NULL,              
   `Consumer_Secret` varchar(255) DEFAULT NULL,           
   `CreatedDate` date DEFAULT NULL,                       
   `CallBackURL` varchar(255) DEFAULT NULL,               
PRIMARY KEY (`Id`)) 
ENGINE = InnoDB AUTO_INCREMENT=5, 
DEFAULT CHAR SET = latin1;

-- -----------------------------------------------------
-- Table `sme_development`.`oauthsession`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `oauthsession` (                                                                                         
    `Id` bigint(20) NOT NULL AUTO_INCREMENT,                                                                                
    `ApplicationOAuthSettingId` bigint(20) DEFAULT NULL,                                                                    
    `Access_Token` varchar(255) DEFAULT NULL,                                                                               
    `Access_Token_Secret` varchar(255) DEFAULT NULL,                                                                        
    `CreatedDate` date DEFAULT NULL,                                                                                        
    `ConsumerObject` blob,                                                                                                  
    `AccessorObject` blob,                                                                                                  
PRIMARY KEY (`Id`),                                                                                                     
KEY `FK_oauthsession` (`ApplicationOAuthSettingId`),                                                                    
CONSTRAINT `oauthsession_ibfk_1` 
	FOREIGN KEY (`ApplicationOAuthSettingId`) 
	REFERENCES `applicationoauthsetting` (`Id`)) 
ENGINE = InnoDB AUTO_INCREMENT=51183, 
DEFAULT CHAR SET = latin1;

-- -----------------------------------------------------
-- Table `sme_development`.`invoice`
-- -----------------------------------------------------
              
CREATE TABLE IF NOT EXISTS `sme_development`.`invoice`(     
	`invoiceId` INT(20) NOT NULL AUTO_INCREMENT ,     
PRIMARY KEY (`invoiceId`))
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`applicationproperties`
-- -----------------------------------------------------
            
CREATE TABLE  IF NOT EXISTS `sme_development`.`applicationproperties` (                           
	 `ApplicationpropertiesID` bigint(20) NOT NULL AUTO_INCREMENT,  
	 `Name` varchar(255) NOT NULL,                                  
	 `Value` varchar(255) NOT NULL,                                 
	 `Status` char(1) DEFAULT 'A',
PRIMARY KEY (`ApplicationpropertiesID`)) 
ENGINE=InnoDB AUTO_INCREMENT=47 
DEFAULT CHAR SET = latin1;         
		   
-- -----------------------------------------------------
-- Table `sme_development`.`branch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sme_development`.`branch` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisation_id` INT NOT NULL,
  `location_name` VARCHAR(30) NULL,
  `contact_no` VARCHAR(10) NULL,
  `country` VARCHAR(30) NULL,
  `state` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `area` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `latitude` VARCHAR(45) NULL,
  `gender`  INT(3) NOT NULL,
  `ac_available`  TINYINT(1) NULL,
  `langitude` VARCHAR(45) NULL,
  `online_booking_status` TINYINT(1) NULL,
  `wifi_available` TINYINT(1) NULL,
  `pincode` VARCHAR(6) NULL,
PRIMARY KEY (`id`),  
INDEX `fk_branch_organisation1_idx` (`organisation_id` ASC),  
CONSTRAINT `fk_branch_organisation1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`service_masterdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`service_masterdata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `service_categoty` VARCHAR(45) NULL,
  `organisation_id` INT NULL,
PRIMARY KEY (`id`),
INDEX `fk_service_masterdata_organisation1_idx` (`organisation_id` ASC),  
CONSTRAINT `fk_service_masterdata_organisation1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`referral_masterdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`referral_masterdata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisation_id` INT NULL,
  `referral_type` VARCHAR(45) NULL,
  `referral_code` INT NULL,
  `referral_point` INT NULL,
PRIMARY KEY (`id`),
INDEX `fk_referral_masterdata_organisation1_idx` (`organisation_id` ASC),  
CONSTRAINT `fk_referral_masterdata_organisation1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`tax_masterdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`tax_masterdata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `organisation_id` INT NULL,
  `tax_type` VARCHAR(45) NULL,
  `tax_rate` DECIMAL(9,2) NULL,
PRIMARY KEY (`id`),
INDEX `fk_tax_masterdata_organisation1_idx` (`organisation_id` ASC),  
CONSTRAINT `fk_tax_masterdata_organisation1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`branch_Schedule`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`branch_Schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `branch_id` INT NULL,
  `week_day` INT(1) NULL,
  `open_time` VARCHAR(45) NULL,
  `close_time` VARCHAR(45) NULL,
PRIMARY KEY (`id`),
INDEX `fk_branch_Schedule_idx` (`branch_id` ASC),  
CONSTRAINT `fk_branch_Schedule`
    FOREIGN KEY (`branch_id`)
    REFERENCES `sme_development`.`branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`staff_Schedule`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`staff_schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `staff_id` INT NULL,
  `week_day` INT(1) NULL,
  `duty_start_time` VARCHAR(45) NULL,
  `duty_end_time` VARCHAR(45) NULL,
PRIMARY KEY (`id`),
INDEX `fk_staff_schedule_idx` (`staff_id` ASC),  
CONSTRAINT `fk_staff_schedule`
    FOREIGN KEY (`staff_id`)
    REFERENCES `sme_development`.`application_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`service_offered`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`service_offered` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `service_name` VARCHAR(45) NULL,
  `pricing_time` VARCHAR(45) NULL,  
  `treatment_type` VARCHAR(45) NULL,
  `available_for` VARCHAR(45) NULL,
  `extra_time_type` VARCHAR(45) NULL,
  `service_categoty_id` INT NULL,
  `duration_in_hrs` DOUBLE NULL,
  `branch_id` INT NULL,
  `price` DECIMAL(9,2) NULL, 
  `special_price` DECIMAL(9,2) NULL, 
  `discount` DECIMAL(9,2) NULL, 
  `tax` DOUBLE NULL, 
PRIMARY KEY (`id`),
INDEX `fk_order_user1_idx` (`branch_id` ASC),
INDEX `fk_service_masterdata_user1_idx` (`service_categoty_id` ASC),
CONSTRAINT `fk_branch_user1`  
    FOREIGN KEY (`branch_id`)
    REFERENCES `sme_development`.`branch` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION, 
CONSTRAINT `fk_service_categoty_id`
    FOREIGN KEY (`service_categoty_id`)
    REFERENCES `sme_development`.`service_masterdata` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`client_order`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`client_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `invoice_no` VARCHAR(45) NULL,  
  `customer_id` INT NULL,
  `branch_id` INT NULL,
  `last_updated` TIMESTAMP NULL,
  `created_on` TIMESTAMP NULL,
  `ip_adress` VARCHAR(45) NULL,
  `order_payment_done` TINYINT(1) NULL,
  `order_status` INT(2) DEFAULT 0,
  `online_booking` TINYINT(1) NULL,
  `discount_type` INT(2) NULL,
  `offer_code` VARCHAR(10) NULL,
  `lastMinDiscount` DECIMAL(9,2) NULL,
  `lastMinDiscountPer` DOUBLE NULL,
  `tax_type` VARCHAR(45) NULL,
  `tax_rate` DOUBLE NULL,
  `total` DECIMAL(9,2) NULL, 
  `amount_paid` DECIMAL(9,2) NULL,
PRIMARY KEY (`id`),
INDEX `fk_order_client_idx` (`customer_id` ASC),
INDEX `fk_order_branch_idx` (`branch_id` ASC),
CONSTRAINT `fk_order_client`  
	FOREIGN KEY (`customer_id`)
    REFERENCES `sme_development`.`client` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_order_branch`  
    FOREIGN KEY (`branch_id`)
    REFERENCES `sme_development`.`branch` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;
							   						   	
-- -----------------------------------------------------
-- Table `sme_development`.`sub_order`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`sub_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `service_id` INT NOT NULL,  
  `order_id` INT NOT NULL,
  `staff_id` INT NULL,
  `service_cost` DECIMAL(9,2) NULL,
PRIMARY KEY (`id`),
INDEX `fk_sub_order_orderid_idx` (`order_id` ASC),
INDEX `fk_sub_order_service1_idx` (`service_id` ASC),
INDEX `fk_sub_order_staff1_idx` (`staff_id` ASC),
CONSTRAINT `fk_sub_order_staff1`  
    FOREIGN KEY (`staff_id`)
    REFERENCES `sme_development`.`application_user` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION, 
CONSTRAINT `fk_sub_order_service1`  
    FOREIGN KEY (`service_id`)
    REFERENCES `sme_development`.`service_offered` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_sub_order_orderid`  
    FOREIGN KEY (`order_id`)
    REFERENCES `sme_development`.`client_order` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`order_masterdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`order_masterdata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cancel_reason` VARCHAR(45) NULL,
  `organisation_id` INT NULL,
PRIMARY KEY (`id`),
INDEX `fk_order_masterdata_organisation1_idx` (`organisation_id` ASC),
CONSTRAINT `fk_order_masterdata_organisation1`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)      
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`offer_type`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`offer_type` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `offer_type` varchar(20) NOT NULL ,
    `event` TINYINT(1)  NULL,
    `festival` TINYINT(1)  NULL,
    `organization_id` INT,
    `offer_date` DATE DEFAULT NULL, 
PRIMARY KEY (`id`),
INDEX `offer_type_organization_id_idx` (`organization_id` ASC),
CONSTRAINT `fk_offer_type_organization_id`
    FOREIGN KEY (`organization_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`offers`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`offers` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `branch_id` INT NOT NULL,
    `offer_type` INT NOT NULL ,
    `start_date` DATE NOT NULL,
    `end_date` DATE NOT NULL,
    `send_by` VARCHAR(10),
    `reminder_notice`  DATE NOT NULL,
    `offer_discount_per` DOUBLE NULL,
    `offer_sent` BOOLEAN,
    `offer_status` BOOLEAN NOT NULL,
PRIMARY KEY (`id`),
INDEX `fk_offers1_idx` (`branch_id` ASC),
INDEX `fk_offer_type_idx` (`offer_type` ASC),
CONSTRAINT `fk_offers1`
    FOREIGN KEY (`branch_id`)
    REFERENCES `sme_development`.`branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_offers_offer_type`  
    FOREIGN KEY (`offer_type`)
    REFERENCES `sme_development`.`offer_type` (`id`)    
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`offer_template`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`offer_template` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `offer_id` INT NOT NULL ,
    `email_subject` VARCHAR(100),
    `email_template` VARCHAR(500) ,
    `sms_template` VARCHAR(200),
PRIMARY KEY (`id`),
INDEX `fk_offer_template1_idx` (`offer_id` ASC),
CONSTRAINT `fk_offer_template1`
    FOREIGN KEY (`offer_id`)
    REFERENCES `sme_development`.`offers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`filter_customer`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`filter_customer_foroffer` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `offer_id` INT NOT NULL ,
    `filter_customer_type` VARCHAR(20),
PRIMARY KEY (`id`),
INDEX `fk_filter_customer1_idx` (`offer_id` ASC),
CONSTRAINT `fk_filter_customer1`
    FOREIGN KEY (`offer_id`)
    REFERENCES `sme_development`.`offers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`client_review`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`client_review` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`organization_id` INT,
	`name` VARCHAR(45) NULL,
	`email_Id` VARCHAR(45) NULL,
	`phone` VARCHAR(45),
	`rating` DECIMAL(9,2) NULL,
	`comment` VARCHAR(45) NULL, 
PRIMARY KEY (`id`),
INDEX `client_review_organization_id_idx` (`organization_id` ASC),
CONSTRAINT `fk_client_review_organization_id`
    FOREIGN KEY (`organization_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`contact`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`contact` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`organization_id` INT,
	`name` VARCHAR(45) NULL,
	`email_Id` VARCHAR(45) NULL,
	`message` VARCHAR(45) NULL, 
PRIMARY KEY (`id`),
INDEX `contact_organization_id_idx` (`organization_id` ASC),
CONSTRAINT `fk_contact_organization_id`
    FOREIGN KEY (`organization_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`media`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`media` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`organisation_id` INT,
	`file` MEDIUMBLOB NULL,
	`file_type` VARCHAR(45) NULL,
	`file_name` VARCHAR(255) NULL, 
	`original_Media` MEDIUMBLOB NULL,
	`image_type` CHAR(1) NULL,
PRIMARY KEY (`id`),
INDEX `media_organisation_id_idx` (`organisation_id` ASC),
CONSTRAINT `fk_media_organisation_id`
    FOREIGN KEY (`organisation_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`access_rights_master_data`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`access_rights_master_data`(
	`id` INT(2) NOT NULL ,     
	`access_right` VARCHAR(30) ,     
PRIMARY KEY (`id`))
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`access_rights`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`access_rights`(     
	`id` INT(11) NOT NULL ,     
	`org_id` INT(11) ,     
	`access_controll_type` INT(2) ,     
	`role_type` INT(1) ,     
	`allowed` TINYINT(1) ,     
PRIMARY KEY (`id`),
INDEX `access_rights_id_idx` (`access_controll_type` ASC),
INDEX `access_rights_organisation_id_idx` (`org_id` ASC),
INDEX `access_rights_role_type_idx` (`role_type` ASC),
CONSTRAINT `fk_access_rights_master_data_id`
    FOREIGN KEY (`access_controll_type`)
    REFERENCES `sme_development`.`access_rights_master_data` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_access_rights_organisation_id`
    FOREIGN KEY (`org_id`)
    REFERENCES `sme_development`.`organisation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_access_rights_role`
    FOREIGN KEY (`role_type`)
    REFERENCES `sme_development`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`customer_has_offer`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`customer_has_offer`(     
	`id` INT(11) NOT NULL ,     
	`offer_id` INT(11),
	`customer_id` INT(11),
PRIMARY KEY (`id`),
INDEX `customer_has_offer_id_offer` (`offer_id` ASC),
INDEX `customer_has_offer_id_client` (`customer_id` ASC),
CONSTRAINT `fk_customer_has_offer_offer`
    FOREIGN KEY (`offer_id`)
    REFERENCES `sme_development`.`offers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_customer_has_offer_client`
    FOREIGN KEY (`customer_id`)
    REFERENCES `sme_development`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`closed_dates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sme_development`.`closed_dates` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `branch_id` INT NOT NULL,
    `start_date` DATE NOT NULL,
    `end_date` DATE NOT NULL,
	`description` VARCHAR(30) NULL,
PRIMARY KEY (`id`),
INDEX `fk_closed_dates_idx` (`branch_id` ASC),
CONSTRAINT `fk_closed_dates`
    FOREIGN KEY (`branch_id`)
    REFERENCES `sme_development`.`branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB AUTO_INCREMENT=1000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`feedback_masterdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`feedback_masterdata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(45) NULL,
PRIMARY KEY (`id`))    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `sme_development`.`feedback_subdata`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`feedback_subdata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question_id` INT NULL,
  `branch_id` INT NULL,
  `customer_id` INT NULL,
  `order_id` INT NULL,
  `answer` VARCHAR(45) NULL,
PRIMARY KEY (`id`),
INDEX `fk_feedback_subdata_question_idx` (`question_id` ASC),
INDEX `fk_feedback_subdata_branch_idx` (`branch_id` ASC),
INDEX `fk_feedback_subdata_customer_idx` (`customer_id` ASC),
INDEX `fk_feedback_subdata_order_idx` (`order_id` ASC),
CONSTRAINT `fk_feedback_subdata_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `sme_development`.`feedback_masterdata` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_feedback_subdata_branch1`
    FOREIGN KEY (`branch_id`)
    REFERENCES `sme_development`.`branch` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_feedback_subdata_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `sme_development`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_feedback_subdata_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `sme_development`.`client_order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sme_development`.`client_offer`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `sme_development`.`client_offer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `offer_id` INT NULL,
  `customer_id` INT NULL,
PRIMARY KEY (`id`),
INDEX `fk_client_offer_offer_idx` (`offer_id` ASC),
INDEX `fk_client_offer_customer_idx` (`customer_id` ASC),
CONSTRAINT `fk_client_offer_offer1`
    FOREIGN KEY (`offer_id`)
    REFERENCES `sme_development`.`offers` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_client_offer_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `sme_development`.`client` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)    
ENGINE = INNODB AUTO_INCREMENT=100000000,
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


