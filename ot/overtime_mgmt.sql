-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 11, 2019 at 12:22 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `overtime_mgmt`
--

-- --------------------------------------------------------

--
-- Table structure for table `available`
--

DROP TABLE IF EXISTS `available`;
CREATE TABLE IF NOT EXISTS `available` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `fromtime` time NOT NULL,
  `totime` time NOT NULL,
  `uniquecode` varchar(5) NOT NULL,
  `employee` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `available`
--

INSERT INTO `available` (`id`, `date`, `fromtime`, `totime`, `uniquecode`, `employee`) VALUES
(1, '2019-03-10', '16:00:00', '20:00:00', 'AAAAA', 'Mike Shroud'),
(5, '2019-04-03', '17:33:00', '18:33:00', 'AAAAA', 'Jordon Gilbert'),
(15, '2019-04-24', '12:30:00', '06:12:00', 'AAAAA', 'Jordon Gilbert'),
(12, '2019-04-09', '17:28:00', '22:28:00', 'AAAAA', 'Jordon Gilbert');

-- --------------------------------------------------------

--
-- Table structure for table `completed shifts`
--

DROP TABLE IF EXISTS `completed shifts`;
CREATE TABLE IF NOT EXISTS `completed shifts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `shifttitle` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `hourlyrate` int(5) NOT NULL,
  `totalhours` int(3) NOT NULL,
  `totalpay` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
CREATE TABLE IF NOT EXISTS `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shifttitle` varchar(50) NOT NULL,
  `description` varchar(535) NOT NULL,
  `hourlyrate` varchar(5) NOT NULL,
  `date` date NOT NULL,
  `ftime` time NOT NULL,
  `ttime` time NOT NULL,
  `uniquecode` varchar(5) NOT NULL,
  `employee` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobs`
--

INSERT INTO `jobs` (`id`, `shifttitle`, `description`, `hourlyrate`, `date`, `ftime`, `ttime`, `uniquecode`, `employee`) VALUES
(1, 'Checkouts 12-6 Thursday', 'Need a person checkouts tomorrow, you will need to be in by 12.', '9.50', '2019-03-14', '12:00:00', '18:00:00', 'AAAAA', ''),
(5, 'All Rounder', 'Need a person to come in and help because it is getting really busy atm \r\n', '10.00', '2019-03-28', '18:00:00', '22:00:00', 'AAAAA', ''),
(6, 'Genius', 'Need a shift for sunday at the genius bar. \r\nTime 9.00am to 15.00pm \r\n', '11.50', '2019-03-10', '09:00:00', '15:00:00', 'BEST1', ''),
(7, 'Fruits', 'Need a person in the fruits section, \r\nwe are currently short staffed so need somebody asap.\r\nTheir is extra pay for anybody who wants to take the shift\r\n', '12.00', '2019-03-28', '18:00:00', '22:00:00', 'AAAAA', ''),
(8, 'Cafe Member', 'Short staffed in the cafe. Need a pair of extra hands. Extra pay will be provided for anybody interested.\r\n', '10.00', '2019-03-28', '18:00:00', '22:00:00', 'AAAAA', ''),
(14, 'checkout', 'need guy at checkouts', '11.50', '2019-04-04', '12:30:00', '18:45:00', 'TIMZZ', ''),
(15, 'cleaner', 'need cleaner\ncontact 0761691626', '50.00', '2019-04-10', '03:15:00', '06:09:00', 'TIMMZ', ''),
(18, 'Checkouts', 'Need a person on checkouts. ', '9.56', '2019-04-01', '12:15:00', '18:30:00', 'AAAAA', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `shiftmanager` tinyint(1) NOT NULL,
  `uniquecode` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `username`, `password`, `email`, `shiftmanager`, `uniquecode`) VALUES
(32, 'Jordon Gilbert', 'jordon', '$2y$10$0ZiWNxmRo970wANqQNHCNuqmJ6a/LMLqkXIP.hTWdbKKifjUNo2AC', 'jordon@hotmail.com', 0, 'AAAAA'),
(31, 'Tommy Oliver', 'Tom', '$2y$10$BL7IKS/AqFlXVyHj8VhiiehYbnW.Y4UBLE7xMXHN0zQIxtpELVqS6', 'olivert@hotmail.com', 0, 'AAAAA'),
(30, 'George Simpleton', 'geogres', '$2y$10$DUCVPeWTSsENfBuMe4ngBe/QCHVppRazvo3tpvU5brcgCLX1xJ4hG', 'simpleg@hotmail.com', 0, 'BEST1'),
(29, 'Greg Hamillton', 'greg', '$2y$10$JoO0AHboF8iLYiv0gJP83eMfYsep/Q9KOs9ec8ELP0mLdh3hgxzeG', 'greg@hotmail.com', 0, 'BEST1'),
(28, 'Jim Peep', 'jim', '$2y$10$Ygvxawj0KZsg2u1aseglv.nJIha6q8hjziVX3cTodnjEwK68plfYS', 'jim@gmail.com', 1, 'BEST1'),
(27, 'James Alderson', 'alderson', '$2y$10$PuFE.fBOQGaF.v0g3f1RReKsFX6VhZWM3uupI3HiPVKqxhPHzjTlG', 'alderson@gmail.com', 1, 'AAAAA'),
(33, 'Mike Shroud', 'shroud', '$2y$10$zjPRWGsZZr35Fnl4nBYoeutYrEhWtbeyQmSOj9dJq6LQazdy0knFi', 'shroud@hotmail.com', 0, 'AAAAA'),
(34, 'thomas van tergen', 'thomas', '$2y$10$cgRQ3/FBHGdsF7hDdiiQTuuovjfO6kKhQc9hGk/CGlrjpSyZxQqu6', 'vanterg@hotmail.com', 1, 'TIMZZ'),
(35, 'craig goodmas', 'craiggoodmass', '$2y$10$B7RYUXLaRrYjRhfHKWWd0ex4BIB1RZs3y2zRw4wFGdDJCoL7BYeLa', 'goodmas@hotmail.com', 0, 'TIMZZ'),
(36, 'Adam Cole', 'acole', '$2y$10$GJD505506Q/3pUmYRZPBA.AxNqliBcJMNq82j1gibLW3lfbbDwKUy', 'coola@hotmail.com', 0, 'TIMZZ');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
