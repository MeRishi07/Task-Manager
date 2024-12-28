package com.banquemisr.challenge05.taskMangager.repository;


import com.banquemisr.challenge05.taskMangager.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepo extends JpaRepository<History, Long>
{

}