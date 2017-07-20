package com.example.leaverequest.controller;

import com.example.leaverequest.dto.LeaveRequestDTO;
import com.example.leaverequest.model.LeaveRequest;
import com.example.leaverequest.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/leaverequest")
public class LeaveRequestController
{
    @Autowired
    LeaveRequestService leaveRequestService;
    
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createLeaveRequest(@Valid @RequestBody final LeaveRequestDTO dto)
    {
        System.out.println("Creating dto : " + dto.toString());
        return new ResponseEntity(leaveRequestService.createLeaveRequest(dto), HttpStatus.CREATED);
    }
    
    
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequests()
    {
        return new ResponseEntity(leaveRequestService.getAllLeaveRequests(), HttpStatus.OK);
    }
    
    
    @RequestMapping(method = GET, path = "/person/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveRequest>> getAllLeaveRequestsByPersonId(@PathVariable(value = "id") final long id)
    {
        return new ResponseEntity(leaveRequestService.getAllLeaveRequestsByPersonId(id), HttpStatus.OK);
    }
    
    
    @RequestMapping(method = GET, path = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable(value = "id") final long id)
    {
        return new ResponseEntity(leaveRequestService.getLeaveRequestById(id), HttpStatus.OK);
    }
    
    
    @RequestMapping(method = PUT, path = "/{id}/changestatus/approved", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LeaveRequest> updateLeaveRequestStatusApproved(@PathVariable("id") final long id)
    {
        return new ResponseEntity(leaveRequestService.updateLeaveRequestStatusApproved(id), HttpStatus.OK);
    }
    
    
    @RequestMapping(method = PUT, path = "/{id}/changestatus/rejected", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LeaveRequest> updateLeaveRequestStatusRejected(@PathVariable("id") final long id)
    {
        return new ResponseEntity(leaveRequestService.updateLeaveRequestStatusRejected(id), HttpStatus.OK);
    }
}