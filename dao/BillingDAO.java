/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.dao;

import com.coursework.model.Billing;
import com.coursework.model.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;


public class BillingDAO {
    private static List<Billing> billings = new ArrayList<>();
    
    static {
    List<Patient> allPatients = PatientDAO.getAllPatients();
    
    billings.add(new Billing(1,allPatients.get(0) ,5000, 3000));
    billings.add(new Billing(2,allPatients.get(1) ,30000, 25000));
    }
    
    public List<Billing> getAllBillings() {
        return billings;
    }
    
    public Billing getBilling(int Id) {
        for (Billing billing : billings) {
            if (billing.getNo()== Id) {
                return billing;
            }
        }
        return null;
    }

    // Create
    public void addBilling(Billing billing) {
         int newUserId = getNextBillId();
        billing.setNo(newUserId);
        billings.add(billing);
    }
    
     public void updateBilling(Billing updatedBilling) {
        for (int i = 0; i < billings.size(); i++) {
            Billing billing = billings.get(i);
            if (billing.getNo() == updatedBilling.getNo()) {
                billings.set(i, updatedBilling);
                System.out.println("Billing updated: " + updatedBilling);
                return;
            }
        }
    }
    
     public void deleteBills(int No) {
        billings.removeIf(Billing -> Billing.getNo() == No);
    }
     
     public int getNextBillId() {
        // Initialize maxUserId with a value lower than any possible userId
        int maxUserId = Integer.MIN_VALUE;

        // Iterate through the list to find the maximum userId
        for (Billing Bill : billings) {
            int userId = Bill.getNo();
            if (userId > maxUserId) {
                maxUserId = userId;
            }
        }

        // Increment the maximum userId to get the next available userId
        return maxUserId + 1;
    }

}
