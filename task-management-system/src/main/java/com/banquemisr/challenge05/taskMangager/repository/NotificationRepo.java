package com.banquemisr.challenge05.taskMangager.repository;

import com.banquemisr.challenge05.taskMangager.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long>
{

}