package com.example.leaverequest.service;

import com.example.leaverequest.dto.LeaveRequestDTO;
import com.example.leaverequest.exception.EntityNotFoundException;
import com.example.leaverequest.model.LeaveRequest;
import com.example.leaverequest.model.Status;
import com.example.leaverequest.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService
{
    @Autowired
    LeaveRequestRepository leaveRequestRepository;
    
    @Override
    public LeaveRequest createLeaveRequest(LeaveRequestDTO dto)
    {
        LeaveRequest leaveRequest = new LeaveRequest(dto);
        return leaveRequestRepository.save(leaveRequest);
    }
    
    @Override
    public List<LeaveRequest> getAllLeaveRequests()
    {
        return leaveRequestRepository.findAll();
    }
    
    @Override
    public List<LeaveRequest> getAllLeaveRequestsByPersonId(long personId)
    {
        return leaveRequestRepository.findByPersonIdOrderByLeaveFrom(personId);
    }
    
    @Override
    public LeaveRequest getLeaveRequestById(long id)
    {
        return leaveRequestRepository.findOne(id);
    }
    
    @Override
    public LeaveRequest updateLeaveRequestStatusApproved(long id)
    {
        LeaveRequest leaveRequest = leaveRequestRepository.findOne(id);
        if(leaveRequest == null)
        {
            throw new EntityNotFoundException("Leave request with id " + id + " not found.");
        }
        else
        {
            leaveRequest.setStatus(Status.APPROVED);
            leaveRequest.setApprovalDate(new Date());
            return leaveRequestRepository.save(leaveRequest);
        }
    }
    
    @Override
    public LeaveRequest updateLeaveRequestStatusRejected(long id)
    {
        LeaveRequest leaveRequest = leaveRequestRepository.findOne(id);
        if(leaveRequest == null)
        {
            throw new EntityNotFoundException("Leave request with id " + id + " not found.");
        }
        else
        {
            leaveRequest.setStatus(Status.REJECTED);
            leaveRequest.setApprovalDate(new Date());
            return leaveRequestRepository.save(leaveRequest);
        }
    }
}
