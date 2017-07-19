package com.example.leaverequest.service;

import com.example.leaverequest.dto.LeaveRequestDTO;
import com.example.leaverequest.exception.EntityNotFoundException;
import com.example.leaverequest.model.LeaveRequest;
import com.example.leaverequest.model.Status;
import com.example.leaverequest.repository.LeaveRequestRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

/**
 * Created by admin on 19/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LeaveRequestServiceImplTest
{
    @Mock
    LeaveRequestRepository leaveRequestRepository;
    
    @InjectMocks
    LeaveRequestServiceImpl classUnderTest;
    
    @Test
    public void createLeaveRequest() {
        //Given
        LeaveRequest returnedLeaveRequest = Mockito.mock(LeaveRequest.class);
        LeaveRequestDTO dto = new LeaveRequestDTO(1L, "Annual", new Date(), new Date(), 10, new Date(), new Date(), "NEW");
        Mockito.when(leaveRequestRepository.save(any(LeaveRequest.class))).thenReturn(returnedLeaveRequest);
        
        //When
        LeaveRequest leaveRequest = classUnderTest.createLeaveRequest(dto);
        
        //Then
        assertEquals(leaveRequest, returnedLeaveRequest);
    }
    
    @Test
    public void getAllLeaveRequests() {
        //Given
        LeaveRequest leaveRequest = Mockito.mock(LeaveRequest.class);
        Mockito.when(leaveRequestRepository.findAll()).thenReturn(Arrays.asList(leaveRequest));
    
        //When
        List<LeaveRequest> allLeaveRequests = classUnderTest.getAllLeaveRequests();
    
        //Then
        assertEquals(allLeaveRequests.size(), 1);
        assertEquals(allLeaveRequests.get(0), leaveRequest);
    }
    
    @Test
    public void getLeaveRequestById() {
        //Given
        LeaveRequest returnedLeaveRequest = Mockito.mock(LeaveRequest.class);
        Mockito.when(leaveRequestRepository.findOne(1L)).thenReturn(returnedLeaveRequest);
        
        //When
        LeaveRequest leaveRequest = classUnderTest.getLeaveRequestById(1L);
        
        //Then
        assertEquals(leaveRequest, returnedLeaveRequest);
    }
    
    @Test(expected = EntityNotFoundException.class)
    public void approveInexistingLeaveRequest() {
        Mockito.when(leaveRequestRepository.findOne(2L)).thenReturn(null);
        classUnderTest.updateLeaveRequestStatusApproved(2L);
    }
    
    @Test
    public void approveExistingLeaveRequest() {
        //Given
        LeaveRequest mockedLeaveRequest = Mockito.mock(LeaveRequest.class);
        Mockito.when(leaveRequestRepository.findOne(2L)).thenReturn(mockedLeaveRequest);
    
        //When
        classUnderTest.updateLeaveRequestStatusApproved(2L);
        
        //Then
        Mockito.verify(mockedLeaveRequest).setStatus(Status.APPROVED);
        Mockito.verify(leaveRequestRepository).save(mockedLeaveRequest);
        
    }
}