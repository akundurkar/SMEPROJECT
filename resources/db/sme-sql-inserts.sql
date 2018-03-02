-- -------------------------------------------------------------------
-- Data for table `sme_development`.`business_category_masterdata`
-- -------------------------------------------------------------------

INSERT INTO `sme_development`.`business_category_masterdata` (`id`, `business_category_type`) VALUES (1, 'Beauty parlour');
INSERT INTO `sme_development`.`business_category_masterdata` (`id`, `business_category_type`) VALUES (2, 'Clinics Category');
INSERT INTO `sme_development`.`business_category_masterdata` (`id`, `business_category_type`) VALUES (3, 'Fiteness and Health');
INSERT INTO `sme_development`.`business_category_masterdata` (`id`, `business_category_type`) VALUES (4, 'Other');

-- -------------------------------------------------------------------
-- Data for table `sme_development`.`business_type_masterdata`
-- -------------------------------------------------------------------

INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (1, 'Hair Salon',1);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (2, 'Beauty Parlor',1);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (3, 'SPA',1);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (4, 'Skin Clinic',2);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (5, 'Massage Therapy',1);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (6, 'Training Salon',1);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (7, 'Dental Clinic',2);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (8, 'Gym',3);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (9, 'Health Club',3);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (10, 'Yoga Studio',3);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (11, 'Ayurvedic',2);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (12, 'Panchakarma',2);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (13, 'Clinic',2);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (14, 'Other',4);
INSERT INTO `sme_development`.`business_type_masterdata` (`id`, `business_type`,`business_category_id`) VALUES (15, 'Eye Clinic',2);

-- -----------------------------------------------------
-- Data for table `sme_development`.`organisation`
-- -----------------------------------------------------
START TRANSACTION;
USE `sme_development`;
INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (1, 'sme_development', 'KK Market Balajinagar', NULL, 'PUNE', NULL, '9863259845', NULL,1);

INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (2, 'Admark Advertising', 'Satara Road', NULL, 'PUNE', NULL, '9514875698', NULL,2);
INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (3, 'Genesis Advertising', 'M G Road', NULL, 'Mumbai', NULL, '9863259845', NULL,4);

INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (4, 'Arms Ad Vision', 'Mukund Nagar', NULL, 'PUNE', NULL, '7538695147', NULL,3);
INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (5, 'Shree Advertising', 'Hadapsar', NULL, 'PUNE', NULL, '1234567890', NULL,1);
INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (6, 'Sun Shine Advertising', 'ST Stand', NULL, 'Mhasvad', NULL, '9876543210', NULL,2);

INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (7, 'Test M Owner 1', 'Marine Drive', NULL, 'Mumbai', NULL, '9638527410', NULL,4);
INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (8, 'Test M Owner 2', 'Juhu', NULL, 'Mumbai', NULL, '3216549870', NULL,2);
INSERT INTO `sme_development`.`organisation` (`id`, `organisation_name`, `adress1`, `adress2`, `city`, `state`, `phone`, `website`,`business_type_id`) VALUES (9, 'Admark M Owner', 'KK Market Satara Road', NULL, 'Pune', NULL, '3216549870', NULL,1);

-- -----------------------------------------------------
-- Data for table `sme_development`.`role`
-- -----------------------------------------------------

INSERT INTO `sme_development`.`role` (`id`, `role`, `type`) VALUES (1, 'Admin', 'sme_development');
INSERT INTO `sme_development`.`role` (`id`, `role`, `type`) VALUES (2, 'Admin', 'SHOP_OWNER');
INSERT INTO `sme_development`.`role` (`id`, `role`, `type`) VALUES (3, 'Admin', 'ADMIN_SHOP');

INSERT INTO `sme_development`.`role` (`id`, `role`, `type`) VALUES (4, 'Employee', 'EMPLOYEE_SME');
INSERT INTO `sme_development`.`role` (`id`, `role`, `type`) VALUES (5, 'Employee', 'EMPLOYEE_SHOP');

-- -----------------------------------------------------
-- Data for table `sme_development`.`application_user`
-- -----------------------------------------------------

INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (1, 'Aniket', 'Bahadurkar', 1, 'i6tmi3wld/o9j6Iogn1Cog==', 'aniket.bahadurkar@gmail.com', 'aniket@bml.com','98123455', '2017-01-13 00:00:00', 1, NULL, 1,100);
INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (2, 'Rahul', 'Savane', 1, 'i6tmi3wld/o9j6Iogn1Cog==', 'rahul.savane@gmail.com', 'rahul@bml.com','98123455', '2017-01-13 00:00:00', 1, NULL, 1,100);
INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (3, 'Mahendra', 'Navaghane', 1, 'i6tmi3wld/o9j6Iogn1Cog==', 'mahendra.navaghane@gmail.com', 'mahendra@bml.com','98123455', '2017-01-13 00:00:00', 1, NULL, 1,100);
INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (4, 'Sivam', 'Dixit', 1, 'i6tmi3wld/o9j6Iogn1Cog==', 'sivam.dixit@gmail.com', 'sivam@bml.com','98123455', '2017-01-13 00:00:00', 1, NULL, 1,100);


INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (5, 'Admark', 'Ad Agency', 2, 'i6tmi3wld/o9j6Iogn1Cog==', 'bookmyledad@gmail.com','admarkad@bml.com', '9874569856', '2017-06-01 00:00:00', NULL, 1, 1,100);
INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (6, 'Admark', 'Screen', 9, 'i6tmi3wld/o9j6Iogn1Cog==', 'bookmyledad@gmail.com','admarkscreen@bml.com', '9874569856', '2017-06-01 00:00:00', 1, NULL, 1,100);

INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (7, 'Nitin', 'Mane', 6, 'i6tmi3wld/o9j6Iogn1Cog==', 'nitinmane039@gmail.com','nitinmane039@gmail.com', '9874569856', '2017-06-01 00:00:00', 1, NULL, 1,100);

INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (8, 'Aniruddha', 'Kanase', 5, 'i6tmi3wld/o9j6Iogn1Cog==', 'shreeadspune@gmail.com','shreeadspune@gmail.com', '9874569856', '2017-06-01 00:00:00', 0, NULL, 1,100);

INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (9, 'Anoop', 'Mishra', 4, 'i6tmi3wld/o9j6Iogn1Cog==', 'sivam.armsadvision@gmail.com ','sivam.armsadvision@gmail.com', '9874569856', '2017-06-01 00:00:00', 1, NULL, 1,100);

INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (10, 'Genesis', 'Ad Agency', 3, 'i6tmi3wld/o9j6Iogn1Cog==', 'bookmyledad@gmail.com','genesis@bml.com', '9874569856', '2017-06-01 00:00:00', 1, NULL, 1,100);
INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (11, 'Test', 'Owner 1', 7, 'i6tmi3wld/o9j6Iogn1Cog==', 'bookmyledad@gmail.com','testowner1@bml.com', '9874569856', '2017-06-01 00:00:00', NULL, 1, 1,100);
INSERT INTO `sme_development`.`application_user` (`id`, `first_name`, `lastName`, `organisation_id`, `password`, `email_Id`, `login_Id`,`mobile`, `lastLogin`, `individual`, `confirmation_token`, `user_status`,`profile_completion`) VALUES (12, 'Test', 'Owner 2', 8, 'i6tmi3wld/o9j6Iogn1Cog==', 'bookmyledad@gmail.com','testowner2@bml.com', '9874569856', '2017-06-01 00:00:00', NULL, 1, 1,100);
INSERT INTO `sme_development`.`application_user`(`id`,`first_name`,`lastName`,`organisation_id`,`password`,`reset_password`,`reset_pass_validity`,`email_Id`,`login_Id`,`mobile`,`lastLogin`,`individual`,`confirmation_token`,`user_status`,`user_permission`,`profile_completion`,`uniqueId`,`firebaseId`,`start_date`,`finish_date`,`address1`,`address2`,`city`,`state`,`pincode`) VALUES ( '13','Sonal','Patel','5','i6tmi3wld/o9j6Iogn1Cog==',NULL,NULL,'sonal@gmail.com','sonal@gmail.com','1245854375','2017-07-20 12:40:25',NULL,NULL,'1','2','100',NULL,NULL,'2017-07-10',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sme_development`.`application_user`(`id`,`first_name`,`lastName`,`organisation_id`,`password`,`reset_password`,`reset_pass_validity`,`email_Id`,`login_Id`,`mobile`,`lastLogin`,`individual`,`confirmation_token`,`user_status`,`user_permission`,`profile_completion`,`uniqueId`,`firebaseId`,`start_date`,`finish_date`,`address1`,`address2`,`city`,`state`,`pincode`) VALUES ( '14','Pooja','Kale','5','i6tmi3wld/o9j6Iogn1Cog==',NULL,NULL,'pooja@gmail.com','pooja@gmail.com','4356543234','2017-07-20 12:40:25',NULL,NULL,'1','2','100',NULL,NULL,'2017-07-10',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `sme_development`.`application_user`(`id`,`first_name`,`lastName`,`organisation_id`,`password`,`reset_password`,`reset_pass_validity`,`email_Id`,`login_Id`,`mobile`,`lastLogin`,`individual`,`confirmation_token`,`user_status`,`user_permission`,`profile_completion`,`uniqueId`,`firebaseId`,`start_date`,`finish_date`,`address1`,`address2`,`city`,`state`,`pincode`) VALUES ( '15','Geeta','Vishwas','5','i6tmi3wld/o9j6Iogn1Cog==',NULL,NULL,'geeta@gmail.com','geeta@gmail.com','9856346754','2017-07-20 12:40:25',NULL,NULL,'1','2','100',NULL,NULL,'2017-07-10',NULL,NULL,NULL,NULL,NULL,NULL);



-- -----------------------------------------------------
-- Data for table `sme_development`.`user_has_role`
-- -----------------------------------------------------

INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (2, 1);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (3, 1);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (4, 1);


INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (5, 3);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (6, 2);

INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (7, 2);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (8, 2);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (9, 2);

INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (10, 3);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (11, 2);
INSERT INTO `sme_development`.`user_has_role` (`user_id`, `role_id`) VALUES (12, 2);

-- -----------------------------------------------------
-- Data for table `sme_development`.`branch`
-- -----------------------------------------------------

INSERT INTO `sme_development`.`branch`(`id`,`organisation_id`,`location_name`,`contact_no`,`country`,`state`,`city`,`area`,`phone`,`latitude`,`gender`,`ac_available`,`langitude`,`online_booking_status`,`wifi_available`,`pincode`) VALUES ( '1','5','Deccan','1234567890',NULL,'MAHARSHTRA','PUNE','JM Road','1234567890','12.32','1','1','13.12','1','1','411030');

-- -----------------------------------------------------
-- Data for table `sme_development`.`service_masterdata`
-- -----------------------------------------------------

INSERT INTO `sme_development`.`service_masterdata`(`id`,`service_categoty`,`organisation_id`) VALUES ( '1','Hair','5');
INSERT INTO `sme_development`.`service_masterdata`(`id`,`service_categoty`,`organisation_id`) VALUES ( '2','Face','5');

-- -----------------------------------------------------
-- Data for table `sme_development`.`service_offered`
-- -----------------------------------------------------
INSERT INTO `sme_development`.`service_offered`(`id`,`service_name`,`pricing_time`,`treatment_type`,`available_for`,`extra_time_type`,`service_categoty_id`,`duration_in_hrs`,`branch_id`,`price`,`special_price`,`discount`,`tax`) VALUES ( '1','Hair Cut','20',NULL,NULL,NULL,'1','20','1','100',NULL,NULL,NULL);
INSERT INTO `sme_development`.`service_offered`(`id`,`service_name`,`pricing_time`,`treatment_type`,`available_for`,`extra_time_type`,`service_categoty_id`,`duration_in_hrs`,`branch_id`,`price`,`special_price`,`discount`,`tax`) VALUES ( '2','Facial',NULL,NULL,NULL,NULL,'2','45','1','300',NULL,NULL,NULL);
INSERT INTO `sme_development`.`service_offered`(`id`,`service_name`,`pricing_time`,`treatment_type`,`available_for`,`extra_time_type`,`service_categoty_id`,`duration_in_hrs`,`branch_id`,`price`,`special_price`,`discount`,`tax`) VALUES ( '3','Hair color',NULL,NULL,NULL,NULL,'1','60','1','150',NULL,NULL,NULL);
INSERT INTO `sme_development`.`service_offered`(`id`,`service_name`,`pricing_time`,`treatment_type`,`available_for`,`extra_time_type`,`service_categoty_id`,`duration_in_hrs`,`branch_id`,`price`,`special_price`,`discount`,`tax`) VALUES ( '4','BlowDry',NULL,NULL,NULL,NULL,'1','30','1','200',NULL,NULL,NULL);
INSERT INTO `sme_development`.`service_offered`(`id`,`service_name`,`pricing_time`,`treatment_type`,`available_for`,`extra_time_type`,`service_categoty_id`,`duration_in_hrs`,`branch_id`,`price`,`special_price`,`discount`,`tax`) VALUES ( '5','Fruit Facial',NULL,NULL,NULL,NULL,'2','90','1','350',NULL,NULL,NULL);
INSERT INTO `sme_development`.`service_offered`(`id`,`service_name`,`pricing_time`,`treatment_type`,`available_for`,`extra_time_type`,`service_categoty_id`,`duration_in_hrs`,`branch_id`,`price`,`special_price`,`discount`,`tax`) VALUES ( '6','Gold Facial',NULL,NULL,NULL,NULL,'2','90','1','1300',NULL,NULL,NULL);

-- -----------------------------------------------------
-- Data for table `sme_development`.`offer_type`
-- -----------------------------------------------------

INSERT INTO `sme_development`.`offer_type`(`id`,`offer_type`,`event`,`festival`,`organization_id`,`offer_date`) VALUES ( '100000001','Independence Day','0','1','5','2017-08-15');
INSERT INTO `sme_development`.`offer_type`(`id`,`offer_type`,`event`,`festival`,`organization_id`,`offer_date`) VALUES ( '100000002','Pateti','0','1','5','2017-08-17');
INSERT INTO `sme_development`.`offer_type`(`id`,`offer_type`,`event`,`festival`,`organization_id`,`offer_date`) VALUES ( '100000003','Bakari Eid','0','1','5','2017-09-02');
INSERT INTO `sme_development`.`offer_type`(`id`,`offer_type`,`event`,`festival`,`organization_id`,`offer_date`) VALUES ( '100000004','Navratri','0','1','5','2017-09-21');
INSERT INTO `sme_development`.`offer_type`(`id`,`offer_type`,`event`,`festival`,`organization_id`,`offer_date`) VALUES ( '100000005','Dashera','0','1','5','2017-09-30');
INSERT INTO `sme_development`.`offer_type`(`id`,`offer_type`,`event`,`festival`,`organization_id`,`offer_date`) VALUES ( '100000006','Diwali','0','1','5','2017-10-17');
INSERT INTO `sme_development`.`offer_type`(`id`,`offer_type`,`event`,`festival`,`organization_id`,`offer_date`) VALUES ( '100000007','Christmas Day','0','1','5','2017-12-25');

-- ---------------------------------------------------------
-- Data for table `sme_development`.`feedback_masterdata`
-- ---------------------------------------------------------

INSERT INTO `sme_development`.`feedback_masterdata`(`id`,`question`) VALUES ( '100000001','Overall experience with our service');
INSERT INTO `sme_development`.`feedback_masterdata`(`id`,`question`) VALUES ( '100000002','How satisfied are you with the offer ?');
INSERT INTO `sme_development`.`feedback_masterdata`(`id`,`question`) VALUES ( '100000003','Comment');

-- ---------------------------------------------------------
-- Data for table `sme_development`.`client`
-- ---------------------------------------------------------
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000100','5','sachin','devkar','a@gmail.com','1111111111',NULL,'1','M','1987-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000101','5','rajesh','tandel','a@gmail.com','4556888946',NULL,'1','M','1987-07-25','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000102','5','kishor','more','a@gmail.com','8965324145',NULL,'1','M','1977-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000103','5','lalit','pasule','a@gmail.com','4575896523',NULL,'1','M','1987-05-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000104','5','harishchandra','jagtap','a@gmail.com','7894561234',NULL,'1','M','1987-07-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000105','5','krishna','sonavane','a@gmail.com','8529637412',NULL,'1','M','1997-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000106','5','tushar','jadhav','a@gmail.com','8979456512',NULL,'1','M','1987-04-05','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000107','5','sandesh','satam','a@gmail.com','8974563699',NULL,'1','M','1987-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000108','5','mahesh','dholu','a@gmail.com','7410235698',NULL,'1','M','1989-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000109','5','narayan','javdekar','a@gmail.com','8887594260',NULL,'1','M','1977-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000110','5','mandar','devkar','a@gmail.com','1111111111',NULL,'1','M','1987-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000111','5','suresh','chandel','a@gmail.com','4556888946',NULL,'1','M','1987-07-25','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000112','5','jon','marko','a@gmail.com','8965324145',NULL,'1','M','1977-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000113','5','martin','sathe','a@gmail.com','4575896523',NULL,'1','M','1987-05-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000114','5','ricky','gibbs','a@gmail.com','7894561234',NULL,'1','M','1987-07-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000115','5','mathu','samant','a@gmail.com','8529637412',NULL,'1','M','1997-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000116','5','sardar','singh','a@gmail.com','8979456512',NULL,'1','M','1987-04-05','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000117','5','nana','kale','a@gmail.com','8974563699',NULL,'1','M','1987-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000118','5','devdhar','prasad','a@gmail.com','7410235698',NULL,'1','M','1989-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');
INSERT INTO `sme_development`.`client`(`id`,`organisation_id`,`first_name`,`lastName`,`email_Id`,`mobile`,`telephone`,`notification_subscription_type`,`gender`,`dob`,`adress1`,`adress2`,`city`,`state`,`country`,`pincode`,`confirmation_token`,`profile_completion`,`firebaseId`,`marital_status`) VALUES ( '100000119','5','pankage','shindhe','a@gmail.com','8887594260',NULL,'1','M','1977-04-24','varsova',NULL,'MUMBAI','MAHARASHTRA',NULL,'123456',NULL,NULL,NULL,'1');

COMMIT;
