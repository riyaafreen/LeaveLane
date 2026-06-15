package com.example.leavemanagement.controller;
import org.springframework.web.bind.annotation.*;

import com.example.leavemanagement.entity.LeaveRequest;
import com.example.leavemanagement.repository.LeaveRequestRepository;
// This controller handles all leave-related operations such as applying for leave, viewing leave requests, and approving/rejecting leaves.
@RestController
public class LeaveController {
private final LeaveRequestRepository leaveRequestRepository;
// Constructor Injection
public LeaveController(LeaveRequestRepository leaveRequestRepository) {
    this.leaveRequestRepository = leaveRequestRepository;
}
//apply leave 
 //
    @PostMapping("/leave")
public String applyLeave(@RequestBody LeaveRequest leaveRequest) {
// basic validation end date should not be before start date
    if(leaveRequest.getEndDate()
            .isBefore(leaveRequest.getStartDate())) {

        return "End Date cannot be before Start Date";
    }
            // validation for overlapping leaves can be added here
    leaveRequest.setStatus("PENDING");

    leaveRequestRepository.save(leaveRequest);

    return "Leave Request Submitted";
}
// manager can view all leave requests
@GetMapping("/leave")
public java.util.List<LeaveRequest> getAllLeaves() {

    return leaveRequestRepository.findAll();
}
// employee can view their leave history
@GetMapping("/leave/history/{email}")
public java.util.List<LeaveRequest>
getLeaveHistory(@PathVariable String email) {

    return leaveRequestRepository
            .findByEmployeeEmail(email);
}
// manager can approve or reject leave requests
@PutMapping("/leave/approve/{id}")
public String approveLeave(@PathVariable Long id) {

    LeaveRequest leaveRequest =
            leaveRequestRepository.findById(id).orElse(null);
    // if leave request not found return error message
    if (leaveRequest == null) {
        return "Leave Request Not Found";
    }

    leaveRequest.setStatus("APPROVED");

    leaveRequestRepository.save(leaveRequest);

    return "Leave Approved";
}
// manager can reject leave requests
@PutMapping("/leave/reject/{id}")
public String rejectLeave(@PathVariable Long id) {

    LeaveRequest leaveRequest =
            leaveRequestRepository.findById(id).orElse(null);

    if (leaveRequest == null) {
        return "Leave Request Not Found";
    }

    leaveRequest.setStatus("REJECTED");

    leaveRequestRepository.save(leaveRequest);

    return "Leave Rejected";
}
}