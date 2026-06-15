package com.example.leavemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.leavemanagement.entity.LeaveRequest;
import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeEmail(String employeeEmail);

}