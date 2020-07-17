-- phpMyAdmin SQL Dump
-- version 4.9.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 17, 2020 at 06:01 PM
-- Server version: 5.6.47-log
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coxtunes_pagination`
--

-- --------------------------------------------------------

--
-- Table structure for table `pagination`
--

CREATE TABLE `pagination` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pagination`
--

INSERT INTO `pagination` (`id`, `name`, `phone`) VALUES
(1, 'Abir', '01682148802'),
(2, 'Shanta', '01815335389'),
(3, 'Mamun', '016821484712'),
(4, 'Bappy', '01815335389'),
(5, 'Iqbal', '01682148802'),
(6, 'Salsabil', '01815335389'),
(7, 'Amin', '016821484712'),
(8, 'Misbah', '01815335389'),
(9, 'Galib', '016821484712'),
(10, 'Nabil', '01815335389'),
(11, 'A', '01682148802'),
(12, 'B', '01815335389'),
(13, 'C', '016821484712'),
(14, 'D', '01815335389'),
(15, 'E', '01682148802'),
(16, 'F', '01815335389'),
(17, 'G', '016821484712'),
(18, 'H', '01815335389'),
(19, 'I', '016821484712'),
(20, 'J', '01815335389'),
(21, 'K', '016821484712'),
(22, 'K', '01815335389');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pagination`
--
ALTER TABLE `pagination`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pagination`
--
ALTER TABLE `pagination`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
