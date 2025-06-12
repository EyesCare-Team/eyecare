package com.project_EyeCare.EyeCare.Repository;

import com.project_EyeCare.EyeCare.entity.PD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;




public interface PdRepository extends JpaRepository<PD, Integer>, PagingAndSortingRepository<PD, Integer>
{

}