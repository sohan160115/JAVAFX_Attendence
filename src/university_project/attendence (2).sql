-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 02, 2021 at 09:35 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendence`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendances`
--

CREATE TABLE `attendances` (
  `id` bigint(11) NOT NULL,
  `course_id` bigint(11) NOT NULL,
  `student_id` bigint(11) NOT NULL,
  `is_present` int(1) NOT NULL DEFAULT 0,
  `class_date` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `attendances`
--

INSERT INTO `attendances` (`id`, `course_id`, `student_id`, `is_present`, `class_date`) VALUES
(9, 1, 1, 1, '2021-01-31'),
(10, 1, 3, 1, '2021-01-31'),
(20, 2, 3, 1, '2020-12-30'),
(21, 1, 1, 1, '2021-02-01'),
(22, 1, 3, 1, '2021-02-01'),
(36, 1, 1, 1, '2021-01-13'),
(37, 1, 1, 0, '2021-01-13'),
(38, 1, 3, 0, '2021-01-13'),
(39, 1, 1, 1, '2020-12-29'),
(40, 1, 1, 0, '2020-12-29'),
(41, 1, 3, 0, '2020-12-29'),
(42, 1, 1, 1, '2021-01-05'),
(43, 1, 1, 1, '2021-01-08'),
(44, 1, 3, 0, '2021-01-08'),
(45, 1, 1, 1, '2021-02-24'),
(46, 1, 3, 0, '2021-02-24'),
(47, 1, 1, 1, '2021-02-06'),
(48, 1, 3, 0, '2021-02-06'),
(49, 1, 1, 0, '2021-02-14'),
(50, 1, 3, 1, '2021-02-14'),
(51, 1, 1, 1, '2021-02-16'),
(52, 1, 3, 0, '2021-02-16');

-- --------------------------------------------------------

--
-- Table structure for table `class_logs`
--

CREATE TABLE `class_logs` (
  `id` bigint(11) NOT NULL,
  `course_id` bigint(11) NOT NULL,
  `teacher_id` bigint(11) NOT NULL,
  `conducted_at` varchar(64) NOT NULL,
  `attended_students` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class_logs`
--

INSERT INTO `class_logs` (`id`, `course_id`, `teacher_id`, `conducted_at`, `attended_students`) VALUES
(1, 1, 1, '10.30', 2);

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id` bigint(11) NOT NULL,
  `course_no` varchar(64) NOT NULL,
  `course_title` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `course_no`, `course_title`) VALUES
(1, 'CSE-1101', 'Structured Programming Language'),
(2, 'CSE-1102', 'Data Structure & Algorithm'),
(5, 'CSE-4101', 'Artificial Intelligience'),
(6, 'CSE-3202', 'Operating System');

-- --------------------------------------------------------

--
-- Table structure for table `course_student`
--

CREATE TABLE `course_student` (
  `id` bigint(11) NOT NULL,
  `course_id` bigint(11) NOT NULL,
  `student_id` bigint(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_student`
--

INSERT INTO `course_student` (`id`, `course_id`, `student_id`) VALUES
(1, 1, 1),
(2, 1, 3),
(5, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `course_teacher`
--

CREATE TABLE `course_teacher` (
  `id` bigint(11) NOT NULL,
  `course_id` bigint(11) NOT NULL,
  `teacher_id` bigint(11) NOT NULL,
  `scheduled` int(11) NOT NULL DEFAULT 0,
  `taken` int(11) NOT NULL DEFAULT 0,
  `start_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course_teacher`
--

INSERT INTO `course_teacher` (`id`, `course_id`, `teacher_id`, `scheduled`, `taken`, `start_date`) VALUES
(1, 1, 1, 39, 25, '0000-00-00'),
(3, 2, 1, 39, 18, '0000-00-00'),
(5, 2, 2, 20, 5, '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `heads`
--

CREATE TABLE `heads` (
  `id` bigint(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `Islogin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `heads`
--

INSERT INTO `heads` (`id`, `name`, `email`, `password`, `Islogin`) VALUES
(1, 'Nasir', 'nasir.seu@gmail.com', '12345', 0),
(2, 'Sohan', 'sohan.seu@gmail.com', '1234', 0),
(3, 'Joshim', 'joshim.seu@gmial.com', '123', 0),
(4, 'Md Shadman', '2017100000072@gmail.com', '1234', 0);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` bigint(11) NOT NULL,
  `roll` bigint(255) NOT NULL,
  `name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `phone` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `roll`, `name`, `email`, `phone`) VALUES
(1, 160101, 'Md. Rashed', 'rased@gmail.com', '01700000000'),
(3, 160102, 'Shahadat Hosen', 'shahadat@gmail.com', '01789999999'),
(5, 160103, 'Ahsan Ali', 'ashan@gmail.com', '01785643789'),
(6, 160104, 'Shimu Khatun', 'shimu@gmail.com', '0178564789');

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `id` bigint(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `Islogin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`id`, `name`, `email`, `password`, `Islogin`) VALUES
(1, 'Rahman', 'shadmanrahman@gmail.com', '1234', 0),
(2, 'Riduan', 'riduan.seu@gmail.com', '1234', 0),
(5, 'Mahabur', 'mahabur.seu@gmail.com', '9999', 0),
(7, 'Abdur', 'abdur.seu@gmail.com', '8888', 0),
(8, 'Sujon', 'sujon.seu@gmail.com', '2345', 0),
(9, 'Alamin', 'alamin.seu@gmail.com', '9999', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendances`
--
ALTER TABLE `attendances`
  ADD PRIMARY KEY (`id`),
  ADD KEY `attendances_fk0` (`course_id`),
  ADD KEY `attendances_fk1` (`student_id`);

--
-- Indexes for table `class_logs`
--
ALTER TABLE `class_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `class_logs_fk0` (`course_id`),
  ADD KEY `class_logs_fk1` (`teacher_id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `course_no` (`course_no`);

--
-- Indexes for table `course_student`
--
ALTER TABLE `course_student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `course_student_fk0` (`course_id`),
  ADD KEY `course_student_fk1` (`student_id`);

--
-- Indexes for table `course_teacher`
--
ALTER TABLE `course_teacher`
  ADD PRIMARY KEY (`id`),
  ADD KEY `course_teacher_fk0` (`course_id`),
  ADD KEY `course_teacher_fk1` (`teacher_id`);

--
-- Indexes for table `heads`
--
ALTER TABLE `heads`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `roll` (`roll`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendances`
--
ALTER TABLE `attendances`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `class_logs`
--
ALTER TABLE `class_logs`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `course_student`
--
ALTER TABLE `course_student`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `course_teacher`
--
ALTER TABLE `course_teacher`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `heads`
--
ALTER TABLE `heads`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendances`
--
ALTER TABLE `attendances`
  ADD CONSTRAINT `attendances_fk0` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `attendances_fk1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`);

--
-- Constraints for table `class_logs`
--
ALTER TABLE `class_logs`
  ADD CONSTRAINT `class_logs_fk0` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `class_logs_fk1` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`);

--
-- Constraints for table `course_student`
--
ALTER TABLE `course_student`
  ADD CONSTRAINT `course_student_fk0` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `course_student_fk1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`);

--
-- Constraints for table `course_teacher`
--
ALTER TABLE `course_teacher`
  ADD CONSTRAINT `course_teacher_fk0` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `course_teacher_fk1` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
