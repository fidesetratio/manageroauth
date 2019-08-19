USE oauth23;

CREATE TABLE `oauth_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` varchar(100) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


insert into `oauth_permissions`(`id`,`id_user`,`user_role`) values (2,'1','ROLE_USER');
