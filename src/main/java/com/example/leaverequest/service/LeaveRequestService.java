package com.example.leaverequest.service;

import com.example.leaverequest.dto.LeaveRequestDTO;
import com.example.leaverequest.model.LeaveRequest;

import java.util.List;

public interface LeaveRequestService
{
    LeaveRequest createLeaveRequest(LeaveRequestDTO dto);
    
    List<LeaveRequest> getAllLeaveRequests();
    
    List<LeaveRequest> getAllLeaveRequestsByPersonId(long personId);
    
    LeaveRequest getLeaveRequestById(long id);
    
    LeaveRequest updateLeaveRequestStatusApproved(long id);
    
    LeaveRequest updateLeaveRequestStatusRejected(long id);
}