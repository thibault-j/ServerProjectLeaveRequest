package com.example.leaverequest.repository;

import com.example.leaverequest.model.LeaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Date;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>
{
    Page<LeaveRequest> findAllByPersonId(long personId, Pageable pageable);
    
    Page<LeaveRequest> findAllByStatusLike(String status, Pageable pageable);
    
    List<LeaveRequest> findAllByStatusLikeAndLeaveFromAfter(String status, Date leaveFrom, Sort sort);
}
